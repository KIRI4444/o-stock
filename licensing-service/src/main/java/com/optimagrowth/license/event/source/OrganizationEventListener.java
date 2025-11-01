package com.optimagrowth.license.event.source;

import com.optimagrowth.license.model.OrganizationChangeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public class OrganizationEventListener {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationEventListener.class);

    @Bean
    public Consumer<Message<OrganizationChangeModel>> organizationChangeHandler() {
        return message -> {
            OrganizationChangeModel orgChange = message.getPayload();

            logger.info("Received an {} event for organization id {}",
                    orgChange.getAction(), orgChange.getOrganizationId());

            handleOrganizationChange(orgChange);
        };
    }

    private void handleOrganizationChange(OrganizationChangeModel orgChange) {
        switch (orgChange.getAction()) {
            case "CREATED":
                logger.info("Handling organization CREATE event for id: {}", orgChange.getOrganizationId());
                break;
            case "UPDATE":
                logger.info("Handling organization UPDATE event for id: {}", orgChange.getOrganizationId());
                break;
            case "DELETE":
                logger.info("Handling organization DELETE event for id: {}", orgChange.getOrganizationId());
                break;
            default:
                logger.info("Unknown action type: {}", orgChange.getAction());
        }
    }
}