package entites;

public class Position {
    private int positionId;
    private String namePosition;
    private double salary;

    public Position() {
    }

    public Position(int positionId, String namePosition, double salary) {
        this.positionId = positionId;
        this.namePosition = namePosition;
        this.salary = salary;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
