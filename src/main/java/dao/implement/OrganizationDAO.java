package dao.implement;

import dao.AdapterDAO;
import dao.IOrganizationDAO;
import entites.Organization;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrganizationDAO implements IOrganizationDAO {
    private AdapterDAO dao;
    public  OrganizationDAO (AdapterDAO dao){this.dao= dao;}
    private String message;
    public void addOrganization(Organization organization) {
        String query = "INSERT INTO organization(nameOrganization,ogrn,dateFoundation,eventid,modeid) VALUES(?,?,?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, organization.getNameOrganization());
            preparedStatement.setLong(2, organization.getOgrn());
            preparedStatement.setDate(3, organization.getDateFoundation());
            preparedStatement.setInt(4, organization.getEventId());
            preparedStatement.setInt(5, organization.getModeId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateOrganization(Organization organization) {
        String query = "UPDATE organization SET nameorganization = ?, ogrn = ?,datefoundation=?, eventid = ?," +
                " modeid = ? WHERE organizationid = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, organization.getNameOrganization());
            ps.setLong(2, organization.getOgrn());
            ps.setDate(3, organization.getDateFoundation());
            ps.setInt(4, organization.getEventId());
            ps.setInt(5, organization.getModeId());
            ps.setInt(6, organization.getOrganizationId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteOrganization(int organizationId) {
        String query = "delete from organization where organizationid = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, organizationId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getOrgById(int organizationId) {
        String nameorganization = "";
        String query = "select nameorganization from organization where organizationid=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, organizationId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("nameorganization");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameorganization;
    }
    public Organization getOrganizationById(int organizationId) {
        Organization organization = new Organization();
        String query = "select * from organization where organizationid=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, organizationId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                organization.setOrganizationId(rs.getInt(1));
                organization.setNameOrganization(rs.getString(2));
                organization.setOgrn(rs.getLong(3));
                organization.setDateFoundation(rs.getDate(4));
                organization.setEventId(rs.getInt(5));
                organization.setModeId(rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organization;
    }

    public int getByName(String nameorganization) {
        List<Organization> listPositions = this.getAllOrganizations();

        int organizationId = 0;

        try {
            nameorganization = new String(nameorganization.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (Organization item : listPositions) {
            if (item.getNameOrganization().equals(nameorganization)) {
                organizationId = item.getOrganizationId();
            }
        }

        return organizationId;
    }

    public List<Organization> getAllOrganizations() {
        List<Organization> organizations = new ArrayList<>();
        String query = "SELECT * FROM organization ORDER BY organizationid ASC";
        Organization organization;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                organization = new Organization(rs.getInt("organizationid"), rs.getString("nameorganization"), rs.getLong("ogrn"), rs.getDate("datefoundation"),
                        rs.getInt("eventid"), rs.getInt("modeid"));
                organizations.add(organization);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organizations;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
