package dao;

import entites.Organization;

import java.sql.SQLException;
import java.util.List;

public interface IOrganizationDAO  {
    public void addOrganization(Organization organization);
    public void updateOrganization(Organization organization);
    public void deleteOrganization(int organizationId);
    public String getOrgById(int organizationId) ;
    public Organization getOrganizationById(int organizationId) ;
    public int getByName(String nameorganization) ;
    public List<Organization> getAllOrganizations() ;

}
