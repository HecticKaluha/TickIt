package JMS.connection;


import exceptions.CouldNotCreateConnectionException;

import javax.jms.*;
import java.io.Serializable;

public class MessageSenderGateway {

    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;

    public MessageSenderGateway(String channelName) {
        try
        {
            this.connection = ConnectionManager.getNewConnection();
            connection.start();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.destination = session.createQueue(channelName);
            this.producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        }
        catch(JMSException | CouldNotCreateConnectionException e)
        {
            System.out.print("Something went wrong while creating the MessageSenderGateway because of " + e.getMessage());
        }

    }
    public ObjectMessage createObjectMessage(Serializable object) {
        try {
            return session.createObjectMessage(object);
        }
        catch (JMSException e) {
            e.printStackTrace();

        }
        return null;
    }

    public void send(ObjectMessage msg)
    {
        try {
            producer.send(destination, msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
