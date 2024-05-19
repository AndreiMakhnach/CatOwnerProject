package com.project2.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "kafkaProject", groupId = "my_consumer")
    public void listen(String message){
        System.out.println("Received message = " + message);
    }
}
