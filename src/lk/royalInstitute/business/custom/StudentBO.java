package lk.royalInstitute.business.custom;

import lk.royalInstitute.business.SuperBO;
import lk.royalInstitute.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    public boolean addStudent(StudentDTO studentDTO) throws Exception;

    public boolean deleteStudent(String id) throws Exception;

    public boolean updateStudent(StudentDTO studentDTO) throws Exception;

    public StudentDTO getStudent(String id) throws Exception;

    public List<StudentDTO> getAllStudents() throws Exception;

    public String getNextID() throws Exception;
}
