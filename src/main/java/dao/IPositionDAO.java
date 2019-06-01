package dao;

import entites.Position;

import java.sql.SQLException;
import java.util.List;

public interface IPositionDAO {
    public void addPosition(Position position);
    public void updatePosition(Position position) ;
    public void deletePosition(int positionId) ;
    public Position getPositionById(int positionId) ;
    public List<Position> getAllPositions() ;
}
