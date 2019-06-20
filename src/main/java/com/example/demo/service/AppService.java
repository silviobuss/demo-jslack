package com.example.demo.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

@Service
public class AppService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppService.class);
    private static final String NEW_LINE = "\n";

    @Value("${slack.webhook}")
    private String urlSlackWebHook;

    public void sendMessageToSlack(String message) {
        StringBuilder messageBuider = new StringBuilder();
        messageBuider.append("*My message*: " + message + NEW_LINE);
        messageBuider.append("*Item example:* " + exampleMessage() + NEW_LINE);

        process(messageBuider.toString());
    }

    private void process(String message) {
        Payload payload = Payload.builder()
                .channel("#app-alerts")
                .username("Bob Bot")
                .iconEmoji(":rocket:")
                .text(message)
                .build();
        try {
            WebhookResponse webhookResponse = Slack.getInstance().send(
                    urlSlackWebHook, payload);
            LOGGER.info("code -> " + webhookResponse.getCode());
            LOGGER.info("body -> " + webhookResponse.getBody());
        } catch (IOException e) {
            LOGGER.error("Unexpected Error! WebHook:" + urlSlackWebHook);
        }
    }

    private String exampleMessage(){
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Aliquam eu odio est. Donec viverra hendrerit lacus et tempor.";
    }
}
