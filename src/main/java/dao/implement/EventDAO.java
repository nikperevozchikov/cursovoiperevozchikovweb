package dao.implement;

import dao.AdapterDAO;
import dao.IEventDAO;
import entites.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAO implements IEventDAO {
    private AdapterDAO dao;
    private String message;
    public  EventDAO (AdapterDAO dao){this.dao= dao;}
    public void addEvent(Event event) {
        String query = "INSERT INTO event(nameevent,dateofevent,resultofevent) VALUES(?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, event.getNameEvent());
            preparedStatement.setDate(2, event.getDateOfEvent());
            preparedStatement.setString(3, event.getResultOfEvent());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void updateEvent(Event event) {
        String query = "UPDATE event SET nameevent = ?, dateofevent = ?, resultofevent = ? WHERE eventid = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, event.getNameEvent());
            ps.setDate(2, event.getDateOfEvent());
            ps.setString(3, event.getResultOfEvent());
            ps.setInt(4, event.getEventId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteEvent(int eventId) {
        String query = "delete from event where eventid = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, eventId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Event getEventById(int eventId) {
        Event event=new Event();
        String query = "select * from event where eventID=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                event.setEventId(rs.getInt(1));
                event.setNameEvent(rs.getString(2));
                event.setDateOfEvent(rs.getDate(3));
                event.setResultOfEvent(rs.getString(4));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM event ORDER BY eventid ASC";
        Event event;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                event = new Event(rs.getInt("eventid"), rs.getString("nameevent"), rs.getDate("dateofevent"),
                        rs.getString("resultofevent"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
