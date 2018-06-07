package JMS.broker;

import domain.Ticket;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Broker {

    private RecipientProcessor recipientProcessor;
    private ClientBrokerGateway clientBrokerGateway;

    public Broker() {

    }

    @PostConstruct
    public void start()
    {
        System.out.println("Starting broker");
        clientBrokerGateway = new ClientBrokerGateway("BrokerToClient");
        clientBrokerGateway.setBroker(this);
        recipientProcessor = new RecipientProcessor(this);
    }

    public void sendTicketToDeveloper(Ticket ticket) {
        recipientProcessor.sendTicketToDeveloper(ticket);
    }

    public void sendTicketToClient(Ticket ticket) {
        clientBrokerGateway.sendReply(ticket);
    }
}
