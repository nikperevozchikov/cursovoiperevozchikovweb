package entites;

public class Employee {
    private int employeeId;
    private String fio;
    private long seriaNumber;
    private int organizationId;
    private int positionId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee() {
    }

    public Employee(int employeeId, String fio, long seriaNumber, int organizationId, int positionId) {
        this.employeeId = employeeId;
        this.fio = fio;
        this.seriaNumber = seriaNumber;
        this.organizationId = organizationId;
        this.positionId = positionId;
    }

    public Employee(String fio, long seriaNumber) {
        this.fio = fio;
        this.seriaNumber = seriaNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fio='" + fio + '\'' +
                ", seriaNumber=" + seriaNumber +
                ", organizationId=" + organizationId +
                ", positionId=" + positionId +
                '}';
    }



    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public long getSeriaNumber() {
        return seriaNumber;
    }

    public void setSeriaNumber(long seriaNumber) {
        this.seriaNumber = seriaNumber;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}
