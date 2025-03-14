package lk.royalInstitute.dao.custom;

import lk.royalInstitute.dao.CrudDAO;
import lk.royalInstitute.entity.Registration;

public interface RegistrationDAO extends CrudDAO<Registration,String> {
    public String getNextID() throws Exception;
}
