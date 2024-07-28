package mts.homework.sivelkaev.application.flow.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerMethod {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String message) {
        log.info("topic: {} - Подготовка сообщения к отправке в Kafka.", topic);

        try {
            kafkaTemplate.send(topic, message);
        } catch (Exception e) {
            log.error("topic: {} - Произошла ошибка при отправке сообщения в Kafka.", topic, e);
            throw new IllegalArgumentException();
        }

        log.info("topic: {} - Сообщение успешно отправлено в Kafka {}.", topic, message);
    }
}
