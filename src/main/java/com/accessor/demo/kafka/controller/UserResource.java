package com.accessor.demo.kafka.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accessor.demo.model.User;

@RestController
@RequestMapping("/api")
public class UserResource {

	//KafkaTemplate is for producer 
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate; 

    private static final String TOPIC = "javatechie";

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name) {

        kafkaTemplate.send(TOPIC, new User(name, "Technology", 12000L));

        return "Published successfully";
    }
}
