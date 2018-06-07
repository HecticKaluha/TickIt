package JMS.broker;

import JMS.connection.MessageSenderGateway;
import domain.Ticket;

public class RecipientProcessor {
    private BrokerDeveloperGateway brokerDeveloperGateway;
    private AggregationProcessor aggregationProcessor;


    public RecipientProcessor(Broker broker) {
        aggregationProcessor = new AggregationProcessor(broker);
        brokerDeveloperGateway = new BrokerDeveloperGateway(aggregationProcessor);
    }


    public void sendTicketToDeveloper(Ticket ticket) {
        //BankInterestRequest bir = new BankInterestRequest(request.getAmount(), request.getTime());
        if (ticket.getType().equals("Angular") || ticket.getType().equals("Java") || ticket.getType().equals("C#")) {
            brokerDeveloperGateway.setSender(new MessageSenderGateway(ticket.getType()));
            brokerDeveloperGateway.sendTicketToDeveloper(ticket);
            aggregationProcessor.setRecipients(ticket, ticket.getType());
        } else {
            throw new IllegalArgumentException(ticket.getType() + " is not a valid Requesttype. Use a valid Requesttype!");
        }
    }
}
