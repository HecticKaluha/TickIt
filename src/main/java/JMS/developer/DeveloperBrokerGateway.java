package JMS.developer;

import JMS.connection.MessageReceiverGateway;
import JMS.connection.MessageSenderGateway;
import domain.Ticket;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class DeveloperBrokerGateway implements MessageListener {

    private MessageSenderGateway sender;
    private MessageReceiverGateway receiver;
    private IDeveloperFrame developerFrame;

    public DeveloperBrokerGateway(String BrokerToDeveloper) {
        sender = new MessageSenderGateway("DeveloperToBroker");
        receiver = new MessageReceiverGateway(BrokerToDeveloper);
        receiver.setListerner(this);
    }
    public void sendReply(Ticket ticket)
    {
        sender.send(sender.createObjectMessage(ticket));
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                System.out.print("\n I got your Ticketrequest! The Ticketrequest was: " + message.toString());

                Ticket ticket = (Ticket)((ObjectMessage) message).getObject();
                developerFrame.add(ticket);
            }
            else{
                System.out.print("\n Something went wrong while de-enqueueing the message");
            }
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void setDeveloperFrame(IDeveloperFrame df) {
        this.developerFrame = df;
    }
}
