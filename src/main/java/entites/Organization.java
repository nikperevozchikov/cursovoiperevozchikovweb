package entites;

import java.sql.Date;

public class Organization {
    private int organizationId;
    private String nameOrganization;
    private long ogrn;
    private Date dateFoundation;
    private int eventId;
    private int modeId;

    public Organization() {
    }

    public long getOgrn() {
        return ogrn;
    }

    public void setOgrn(long ogrn) {
        this.ogrn = ogrn;
    }

    public Organization(int organizationId, String nameOrganization, long ogrn, Date dateFoundation, int eventId, int modeId) {
        this.organizationId = organizationId;
        this.nameOrganization = nameOrganization;
        this.ogrn = ogrn;
        this.dateFoundation = dateFoundation;
        this.eventId = eventId;
        this.modeId = modeId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public Date getDateFoundation() {
        return dateFoundation;
    }

    public void setDateFoundation(Date dateFoundation) {
        this.dateFoundation = dateFoundation;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getModeId() {
        return modeId;
    }

    public void setModeId(int modeId) {
        this.modeId = modeId;
    }
}
