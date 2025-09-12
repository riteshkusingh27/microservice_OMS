package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @KafkaListener(topics ="notificationTopic")
    public void handleNotification(OrderplacedEvent orderPlacedevent) {
        // handle the notification logic here
        log.info("Received notification for order number: {}", orderPlacedevent.getOrderNumber());

    }

}