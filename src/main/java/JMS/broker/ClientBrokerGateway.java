package JMS.broker;

import JMS.connection.MessageReceiverGateway;
import JMS.connection.MessageSenderGateway;
import com.google.gson.Gson;
import domain.Ticket;

import javax.jms.*;


public class ClientBrokerGateway implements MessageListener {

    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;
    private String channelName;
    private Broker broker;

    public ClientBrokerGateway(String channelName) {
        this.channelName = channelName;
        sender = new MessageSenderGateway(channelName);
        receiver = new MessageReceiverGateway("RequestsToBroker");
        receiver.setListerner(this);
    }

    public void sendReply(Ticket reply) {
        Gson g = new Gson();
        String ticketJSON = g.toJson(reply);
        sender.sendTextMessage(sender.createTextMessage(ticketJSON));
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(message.toString());

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage)message;
                String json = textMessage.getText();
                Gson g = new Gson();
                Ticket ticket = g.fromJson(json, Ticket.class);

                //Ticket ticket = (Ticket) ((ObjectMessage) message).getObject();
                broker.sendTicketToDeveloper(ticket);
            }
            else{
                System.out.println("Received a message which was not a textmessage");
            }

        } catch (JMSException e) {
            System.out.print("\n Something went wrong when trying to (de)serialize : " + e.getMessage());
        }
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }
}
