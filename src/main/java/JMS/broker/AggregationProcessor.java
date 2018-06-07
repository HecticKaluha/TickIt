package JMS.broker;

import domain.Ticket;

import java.util.HashMap;
import java.util.Map;

public class AggregationProcessor {
    private Broker broker;
    private Map<Ticket, String> received;

    public AggregationProcessor(Broker broker) {
        this.broker = broker;
        this.received = new HashMap<>();
    }

    public void setMessage(Ticket ticket) {
        if (ticket.isFinished()) {
            updateRequester(ticket);
        }
    }

    public void setRecipients(Ticket ticket, String sentTo) {
        received.put(ticket, sentTo);
    }

    public void updateRequester(Ticket ticket) {
        broker.sendTicketToClient(ticket);
    }


}
