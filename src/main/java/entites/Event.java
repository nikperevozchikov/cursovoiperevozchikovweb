package entites;

import java.sql.Date;
public class Event {
    private int eventId;
    private String nameEvent;
    private Date dateOfEvent;
    private String resultOfEvent;

    public Event() {
    }

    public Event(int eventId, String nameEvent, Date dateOfEvent, String resultOfEvent) {
        this.eventId = eventId;
        this.nameEvent = nameEvent;
        this.dateOfEvent = dateOfEvent;
        this.resultOfEvent = resultOfEvent;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public String getResultOfEvent() {
        return resultOfEvent;
    }

    public void setResultOfEvent(String resultOfEvent) {
        this.resultOfEvent = resultOfEvent;
    }
}
