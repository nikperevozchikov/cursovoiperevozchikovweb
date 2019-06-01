package dao.implement;

import dao.AdapterDAO;
import dao.IPositionDAO;
import entites.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO implements IPositionDAO {
    private AdapterDAO dao;
    public  PositionDAO (AdapterDAO dao){this.dao= dao;}
    private String message;
    public void addPosition(Position position) {
        String query = "INSERT INTO position(nameposition,salary) VALUES(?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, position.getNamePosition());
            preparedStatement.setDouble(2, position.getSalary());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePosition(Position position) {
        String query = "UPDATE position SET nameposition = ?, salary = ? WHERE positionid = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, position.getNamePosition());
            ps.setDouble(2, position.getSalary());
            ps.setInt(3, position.getPositionId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deletePosition(int positionId) {
        String query = "delete from position where positionid = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, positionId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Position getPositionById(int positionId) {
        Position position = new Position();
        String query = "select * from position where positionid=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, positionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                position.setNamePosition(rs.getString(1));
                position.setSalary(rs.getDouble(2));
                position.setPositionId(rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return position;
    }

    public String getPosById(int positionId){
        String nameposition = "";
        String query = "select nameposition from position where positionid=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, positionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("nameposition");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameposition;
    }



    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        String query = "SELECT * FROM position ORDER BY positionid ASC";
        Position position;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                position = new Position(rs.getInt("positionid"), rs.getString("nameposition"), rs.getDouble("salary"));
                positions.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
