package dao.implement;

import dao.AdapterDAO;
import dao.IEmployeeDAO;
import entites.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {
private  AdapterDAO dao;
    private String message;

    public EmployeeDAO(AdapterDAO dao) {
        this.dao=dao;
    }

    public void addEmployee(Employee employee) {
        String query = "INSERT INTO employee(fio,seriaNumber,organizationId,positionId) VALUES(?,?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, employee.getFio());
            preparedStatement.setLong(2, employee.getSeriaNumber());
            preparedStatement.setInt(3, employee.getOrganizationId());
            preparedStatement.setInt(4, employee.getPositionId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isEmployeeExists(String fio) {
        String query = "SELECT EXISTS(SELECT employeeid FROM employee WHERE fio=?)";
        boolean res = false;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fio);
            res = preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public Employee getEmployee(String fio, int seriaNumber) {
        Employee employee = new Employee(fio, seriaNumber);
        String query = "select * from employee where fio = ?" +
                " and serianumber = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, fio);
            ps.setLong(2, seriaNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setEmployeeId(rs.getInt(1));
                employee.setFio(rs.getString(2));
                employee.setSeriaNumber(rs.getLong(3));
                employee.setOrganizationId(rs.getInt(4));
                employee.setPositionId(rs.getInt(5));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employee;
    }

    public Employee getEmployeeById(int employeeId) {
        Employee employee = new Employee();
        String query = "select * from employee where employeeid=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setEmployeeId(rs.getInt(1));
                employee.setFio(rs.getString(2));
                employee.setSeriaNumber(rs.getLong(3));
                employee.setOrganizationId(rs.getInt(4));
                employee.setPositionId(rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }


    public Integer getIdByFio(String fio) {
        String query = "select employeeid from employee where fio=?";
        int res = -1;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, fio);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                res = rs.getInt("employeeid");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    public String getFioById(int employeeId) {
        String fio = "";
        String query = "select fio from employee where employeeid=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("fio");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fio;
    }


    public void updateEmployee(Employee employee) {
        String query = "UPDATE employee SET fio = ?, serianumber = ?, organizationid = ?," +
                " positionid = ? WHERE employeeid = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, employee.getFio());
            ps.setLong(2, employee.getSeriaNumber());
            ps.setInt(3, employee.getOrganizationId());
            ps.setInt(4, employee.getPositionId());
            ps.setInt(5, employee.getEmployeeId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteEmployee(int employeeId) {
        String query = "delete from employee where employeeid = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, employeeId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

  /*  public List<Employee> getSorted(String col) throws SQLException {
        switch (col) {
            case ("employeeId"): {
                col = "employeeid";
                break;
            }
            case ("organizationId"): {
                col = "organizationid";
                break;
            }
            case ("positionId"): {
                col = "positionid";
                break;
            }
        }
        List<Employee> employees = null;
        try (Connection connection = dao.getConnection()) {
            ResultSet set = connection.createStatement().executeQuery(String.format("SELECT * FROM \"Employee\" ORDER BY %s", col));
            employees = new ArrayList<>();
            while (set.next()) {
                try {
                    employees.add(new Employee(
                            set.getInt("employeeid"),
                            new String((set.getString("fio")).getBytes("ISO-8859-1"), "UTF-8"),
                            set.getLong("serianumber"),
                            set.getInt("organizationid"),
                            set.getInt("positionid")
                    ));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return employees;
    }*/

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee ORDER BY employeeid ASC";
       Employee employee;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee(rs.getInt("employeeId"), rs.getString("fio"), rs.getLong("seriaNumber"), rs.getInt("organizationId"), rs.getInt("positionId"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
   /* public List<Employee> getAllEmployes() {
        List<Employee> lista = null;

        String sql = "SELECT id, nombres, celular, correo FROM contactos";

        Connection cn = db.getConnection();

        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                lista = new LinkedList<Contactos>();
                while (rs.next()) {
                    Contactos contactos = new Contactos();
                    contactos.setId(rs.getInt(1));
                    contactos.setNombres(rs.getString(2));
                    contactos.setCelular(rs.getInt(3));
                    contactos.setCorreo(rs.getString(4));

                    lista.add(contactos);
                }
                ps.close();

            } catch (SQLException e) {
                setMensaje("Problemas para listar: " + e.getMessage());
            } finally {
                try {
                    cn.close();
                } catch (SQLException ex) {
                    setMensaje(ex.getMessage());
                }
            }
        } else {
            setMensaje("Error en conexion: " + db.getMessage());
        }

        return lista;
    }*/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
