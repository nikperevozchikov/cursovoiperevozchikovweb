package dao;

import entites.Supervision;

import java.sql.SQLException;
import java.util.List;

public interface ISupervisionDAO {
    public void addSupervision(Supervision supervision);
    public void updateSupervision(Supervision supervision);
    public void deleteSupervision(int modeId);
    public List<Supervision> getAllSupervisions();
    public Supervision getSupervisionById(int modeId);

}
