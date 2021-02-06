package lk.royalInstitute.business.custom.impl;

import lk.royalInstitute.business.custom.StudentBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.DAOType;
import lk.royalInstitute.dao.SuperDAO;
import lk.royalInstitute.dao.custom.StudentDAO;
import lk.royalInstitute.dto.StudentDTO;
import lk.royalInstitute.entity.Student;

import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(studentDTO.getId(),
                studentDTO.getStudentName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDate(),
                studentDTO.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return false;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        return false;
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        return null;
    }
}
