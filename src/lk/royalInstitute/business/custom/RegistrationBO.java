package lk.royalInstitute.business.custom;

import lk.royalInstitute.business.SuperBO;
import lk.royalInstitute.dto.RegistrationDTO;

import java.util.List;

public interface RegistrationBO extends SuperBO {

    public boolean addRegistration(RegistrationDTO registrationDTO) throws Exception;

    public boolean deleteRegistration(String id) throws Exception;

    public boolean updateRegistration(RegistrationDTO registrationDTO) throws Exception;

    public RegistrationDTO getRegistration(String id) throws Exception;

    public List<RegistrationDTO> getAllRegistration() throws Exception;

    public String getNextID() throws Exception;
}
