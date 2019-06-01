package dao;

import entites.Event;

import java.sql.SQLException;
import java.util.List;

public interface IEventDAO {
    public void addEvent(Event event);
    public void updateEvent(Event event);
    public void deleteEvent(int eventId);
    public List<Event> getAllEvents();

}
