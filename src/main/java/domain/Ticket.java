package domain;

import java.io.Serializable;

public class Ticket implements Serializable {

    private String type;
    private String request;
    private String summary;
    private boolean finished;

    public Ticket() {
    }

    public Ticket(String type, String request, String summary, boolean finished) {
        this.type = type;
        this.request = request;
        this.summary = summary;
        this.finished = finished;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return type + " Ticket " +
                ", request: " + request +
                ", summary: " + summary +
                ", finished: " + finished;
    }
}
