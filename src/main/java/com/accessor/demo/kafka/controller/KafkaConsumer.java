package com.accessor.demo.kafka.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.accessor.demo.model.User;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "javatechie", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "javatechie", groupId = "group_id", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user) {
        System.out.println("Consumed JSON Message: " + user);
    }
}