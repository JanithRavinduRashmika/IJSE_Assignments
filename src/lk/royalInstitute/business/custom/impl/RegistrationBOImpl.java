package lk.royalInstitute.business.custom.impl;

import lk.royalInstitute.business.custom.RegistrationBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.DAOType;
import lk.royalInstitute.dao.SuperDAO;
import lk.royalInstitute.dao.custom.RegistrationDAO;
import lk.royalInstitute.dto.RegistrationDTO;
import lk.royalInstitute.entity.Course;
import lk.royalInstitute.entity.Registration;
import lk.royalInstitute.entity.Student;

import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO = DAOFactory.getInstance().getDAO(DAOType.REGISTRATION);
    @Override
    public boolean addRegistration(RegistrationDTO registrationDTO) throws Exception {
        return registrationDAO.add(new Registration(registrationDTO.getRegNo(),
                registrationDTO.getRegDate(),
                registrationDTO.getRegFee(),
                new Student(registrationDTO.getStudentId()),
                new Course(registrationDTO.getCourseId())));
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

    @Override
    public String getNextID() throws Exception {
        return registrationDAO.getNextID();
    }
}
