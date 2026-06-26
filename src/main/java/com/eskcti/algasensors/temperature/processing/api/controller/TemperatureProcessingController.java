package com.eskcti.algasensors.temperature.processing.api.controller;

import org.springframework.http.MediaType;

import java.time.OffsetDateTime;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.eskcti.algasensors.temperature.processing.api.model.TemperatureLogOutput;
import com.eskcti.algasensors.temperature.processing.common.IdGenerator;
import com.eskcti.algasensors.temperature.processing.infrastructure.rabbitmq.RabbitMQCconfig;

import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures/data")
@Slf4j
@RequiredArgsConstructor
public class TemperatureProcessingController {
    private final RabbitTemplate rabbitTemplate;

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public void data(@PathVariable("sensorId") TSID sensorId, @RequestBody String input) {
        if (input == null || input.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Double temperature;

        try {
            temperature = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        TemperatureLogOutput logOutput = TemperatureLogOutput.builder()
                .id(IdGenerator.generateTimeBasedUUID())
                .sensorId(sensorId)
                .value(temperature)
                .registeredAt(OffsetDateTime.now())
                .build();

        log.info(logOutput.toString());

        String exchange = RabbitMQCconfig.FANOUT_EXCHANGE_NAME;

        String routingKey = "";
        Object payload = logOutput;

        MessagePostProcessor messagePostProcessor = (MessagePostProcessor) message -> {
            message.getMessageProperties().setHeader("sensorId", logOutput.getSensorId().toString());
            return message;
        };
        rabbitTemplate.convertAndSend(exchange, routingKey, payload, messagePostProcessor);
    }
}
