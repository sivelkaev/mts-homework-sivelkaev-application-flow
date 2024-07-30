package mts.homework.sivelkaev.application.flow.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.flow.helper.JsonMarshallingHelper;
import mts.homework.sivelkaev.application.flow.kafka.dto.request.CreateAccountRequest;
import mts.homework.sivelkaev.application.flow.kafka.producer.KafkaProducerMethod;
import mts.homework.sivelkaev.application.flow.service.UpdateStatusService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateAccountDelegate implements JavaDelegate {
    private final JsonMarshallingHelper jsonMarshallingHelper;
    private final KafkaProducerMethod kafkaProducerMethod;
    private final UpdateStatusService updateStatusService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Start create account");

        var applicationId = execution.getVariable("id").toString();
        var clientId = execution.getVariable("clientId").toString();

        var createAccountRequest = CreateAccountRequest.builder()
               .applicationId(Long.valueOf(applicationId))
               .clientId(Long.valueOf(clientId))
               .build();
        var createAccountRequestMessage = jsonMarshallingHelper.marshall(createAccountRequest);
        kafkaProducerMethod.send("create.account", createAccountRequestMessage);

        updateStatusService.updateStatus(applicationId, "DONE");
    }
}
