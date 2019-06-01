package dao.implement;

import dao.AdapterDAO;
import dao.ISupervisionDAO;
import entites.Position;
import entites.Supervision;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupervisionDAO implements ISupervisionDAO {
    private AdapterDAO dao;
    public  SupervisionDAO (AdapterDAO dao){this.dao= dao;}
    private String message;
    public void addSupervision(Supervision supervision) {
        String query = "INSERT INTO supervision(namemode,resultsupervision) VALUES(?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, supervision.getNameMode());
            preparedStatement.setString(2, supervision.getResultSupervision());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void updateSupervision(Supervision supervision) {
        String query = "UPDATE supervision SET namemode = ?, resultsupervision = ? WHERE modeid = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supervision.getNameMode());
            ps.setString(2, supervision.getResultSupervision());
            ps.setInt(3, supervision.getModeId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteSupervision(int modeId) {
        String query = "delete from supervision where modeid = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, modeId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public List<Supervision> getAllSupervisions() {
        List<Supervision> supervisions = new ArrayList<>();
        String query = "SELECT * FROM supervision ORDER BY modeId ASC";
        Supervision supervision;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                supervision = new Supervision(rs.getInt("modeid"), rs.getString("namemode"), rs.getString("resultsupervision"));
                supervisions.add(supervision);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supervisions;
    }
    public Supervision getSupervisionById(int modeId) {
       Supervision supervision = new Supervision();
        String query = "select * from supervision where modeid=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, modeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                supervision.setModeId(rs.getInt(1));
                supervision.setNameMode(rs.getString(2));
                supervision.setResultSupervision(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supervision;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
