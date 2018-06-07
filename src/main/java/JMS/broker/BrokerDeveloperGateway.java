package JMS.broker;

import JMS.connection.MessageReceiverGateway;
import JMS.connection.MessageSenderGateway;
import domain.Ticket;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BrokerDeveloperGateway implements MessageListener {

    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;
    private String channelName;
    private Broker broker;
    private AggregationProcessor aggregationProcessor;

    public BrokerDeveloperGateway() {
    }

    public BrokerDeveloperGateway(String channelName) {
        this.channelName = channelName;
        sender = new MessageSenderGateway(channelName);
        receiver = new MessageReceiverGateway("DeveloperToBroker");
        receiver.setListerner(this);
    }

    public BrokerDeveloperGateway(AggregationProcessor aggregationProcessor) {
        receiver = new MessageReceiverGateway("DeveloperToBroker");
        receiver.setListerner(this);
        this.aggregationProcessor = aggregationProcessor;
    }

    public void sendTicketToDeveloper(Ticket ticket) {
        ObjectMessage om = sender.createObjectMessage(ticket);
        sender.send(om);
    }


    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                System.out.println("Received Ticket from Developer: " + message.toString());
                Ticket ticket = (Ticket) ((ObjectMessage) message).getObject();
                aggregationProcessor.setMessage(ticket);
            } else {
                System.out.println("The message wasnt of the correct type. It was not an instance of ObjectMessage");
            }
        } catch (JMSException e) {
            System.out.println("Something went wrong when receiving a message in the BrokerDeveloperGateway: onMessage" + e.getMessage());
        }
    }

    public MessageSenderGateway getSender() {
        return sender;
    }

    public void setSender(MessageSenderGateway sender) {
        this.sender = sender;
    }

    public MessageReceiverGateway getReceiver() {
        return receiver;
    }

    public void setReceiver(MessageReceiverGateway receiver) {
        this.receiver = receiver;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }
}
