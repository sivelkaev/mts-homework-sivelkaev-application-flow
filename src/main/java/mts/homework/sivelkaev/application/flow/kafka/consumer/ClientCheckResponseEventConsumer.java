package mts.homework.sivelkaev.application.flow.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.flow.helper.JsonMarshallingHelper;
import mts.homework.sivelkaev.application.flow.kafka.dto.response.ClientCheckResponse;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientCheckResponseEventConsumer {
    private final JsonMarshallingHelper jsonMarshallingHelper;
    private final RuntimeService runtimeService;

    @KafkaListener(topics = "application.client.check.response",
                   groupId = "application")
    public void consume(String message) {
        log.info("Принято сообщение из Kafka {}.", message);

        ClientCheckResponse res = jsonMarshallingHelper.unmarshall(ClientCheckResponse.class, message);

        Map<String, Object> variables = Map.of("id", res.getApplicationId(),
                "checkStatus", res.getStatus(),
                "clientId", res.getClientId());
        runtimeService.correlateMessage("ClientCheck", res.getApplicationId().toString(), variables);
    }
}
