package mts.homework.sivelkaev.application.flow.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.flow.helper.JsonMarshallingHelper;
import mts.homework.sivelkaev.application.flow.kafka.producer.KafkaProducerMethod;
import mts.homework.sivelkaev.application.flow.kafka.dto.request.ClientCheckRequest;
import mts.homework.sivelkaev.application.flow.service.UpdateStatusService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientCheckRequestDelegate implements JavaDelegate {
    private final UpdateStatusService updateStatusService;
    private final JsonMarshallingHelper jsonMarshallingHelper;
    private final KafkaProducerMethod kafkaProducerMethod;
    private final RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Start client check");

        var applicationId = Long.valueOf(execution.getVariable("id").toString());
        var passportNumber = execution.getVariable("passportNumber").toString();

        updateStatusService.updateStatus(applicationId.toString(), "CLIENT_CHECK");

        var clientCheckRequest = ClientCheckRequest.builder()
                        .applicationId(applicationId)
                        .passportNumber(passportNumber)
                        .build();

        var clientCheckRequestMessage = jsonMarshallingHelper.marshall(clientCheckRequest);

        Map<String, Object> variables = Map.of("id", applicationId.toString(),
                "checkStatus", 0);
        runtimeService
                .createMessageCorrelation("ClientCheck")
                .processInstanceBusinessKey(applicationId.toString())
                .setVariables(variables);

        kafkaProducerMethod.send("application.client.check", clientCheckRequestMessage);
    }
}
