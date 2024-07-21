package mts.homework.sivelkaev.application.flow.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.flow.helper.JsonMarshallingHelper;
import mts.homework.sivelkaev.application.flow.kafka.dto.ApplicationStartFlowRequest;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartApplicationFlowEventConsumer {
    private static final String PROCESS_NAME = "ApplicationFlowStart";

    private final JsonMarshallingHelper jsonMarshallingHelper;
    private final RuntimeService runtimeService;

    @KafkaListener(topics = "application.flow.start",
                   groupId = "application")
    public void consume(String message) {
        log.info("Принято сообщение из Kafka {}.", message);

        ApplicationStartFlowRequest req = jsonMarshallingHelper.unmarshall(ApplicationStartFlowRequest.class, message);
        Map<String, Object> variables = Map.of("id", req.getId(),
                "type", req.getType(),
                "status", req.getStatus(),
                "firstName", req.getFirstName(),
                "middleName", req.getMiddleName(),
                "lastName", req.getLastName(),
                "birthDate", req.getBirthDate(),
                "passportNumber", req.getPassportNumber());

        runtimeService.startProcessInstanceByKey(PROCESS_NAME, req.getId().toString(), variables);
    }
}
