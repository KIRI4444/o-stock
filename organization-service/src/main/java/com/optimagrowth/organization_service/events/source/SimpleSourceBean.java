package com.optimagrowth.organization_service.events.source;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.optimagrowth.organization_service.model.OrganizationChangeModel;
import com.optimagrowth.organization_service.util.ActionEnum;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleSourceBean {

    private final StreamBridge streamBridge;
    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    public SimpleSourceBean(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publishOrganizationChange(ActionEnum action, String organizationId) {
        logger.info("Sending Kafka message {} for Organization Id: {}", action, organizationId);

        OrganizationChangeModel change = new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action.toString(),
                organizationId
        );

        // Отправка сообщения через StreamBridge
        boolean sent = streamBridge.send("output-out-0",
                MessageBuilder.withPayload(change).build()
        );

        if (sent) {
            logger.info("Message sent successfully");
        } else {
            logger.info("Failed to send message");
        }
    }
}
