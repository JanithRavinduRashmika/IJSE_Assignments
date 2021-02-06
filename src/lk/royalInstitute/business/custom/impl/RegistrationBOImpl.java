package lk.royalInstitute.business.custom.impl;

import lk.royalInstitute.business.custom.RegistrationBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.DAOType;
import lk.royalInstitute.dao.SuperDAO;
import lk.royalInstitute.dao.custom.RegistrationDAO;
import lk.royalInstitute.dto.RegistrationDTO;
import lk.royalInstitute.entity.Registration;

import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = DAOFactory.getInstance().getDAO(DAOType.REGISTRATION);
    @Override
    public boolean addRegistration(RegistrationDTO registrationDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteRegistration(String id) throws Exception {
        return false;
    }

    @Override
    public boolean updateRegistration(RegistrationDTO registrationDTO) throws Exception {
        return false;
    }

    @Override
    public RegistrationDTO getRegistration(String id) throws Exception {
        return null;
    }

    @Override
    public List<RegistrationDTO> getAllRegistration() throws Exception {
        return null;
    }
}
