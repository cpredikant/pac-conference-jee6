package de.predikant.conference.service.jms;

import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "TalkChangeMessageConsumer", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "/jms/queue/conference"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
@Timed
public class TalkChangeMessageConsumer implements MessageListener {

    @Inject
    private Logger logger;

    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;

        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                logger.info("MESSAGE BEAN: Message received: " +
                        msg.getText());
            } else {
                logger.warn("Message of wrong type: " +
                        message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.error("Error", e);
            mdc.setRollbackOnly();
        }

    }
}
