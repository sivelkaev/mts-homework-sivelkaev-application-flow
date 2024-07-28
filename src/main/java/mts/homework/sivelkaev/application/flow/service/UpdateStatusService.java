package mts.homework.sivelkaev.application.flow.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.flow.helper.JsonMarshallingHelper;
import mts.homework.sivelkaev.application.flow.kafka.dto.request.UpdateStatusRequest;
import mts.homework.sivelkaev.application.flow.kafka.producer.KafkaProducerMethod;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateStatusService {
    private final JsonMarshallingHelper jsonMarshallingHelper;
    private final KafkaProducerMethod kafkaProducerMethod;

    public void updateStatus(String applicationId,
                             String status) {
        var updateStatusRequest = UpdateStatusRequest.builder()
                .id(Long.valueOf(applicationId))
                .status(status)
                .build();

        var updateStatusRequestMessage = jsonMarshallingHelper.marshall(updateStatusRequest);

        kafkaProducerMethod.send("application.update.status", updateStatusRequestMessage);
    }
}
