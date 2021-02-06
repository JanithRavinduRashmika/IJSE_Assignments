package lk.royalInstitute.business.custom.impl;

import lk.royalInstitute.business.custom.StudentBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.DAOType;
import lk.royalInstitute.dao.SuperDAO;
import lk.royalInstitute.dao.custom.StudentDAO;
import lk.royalInstitute.dto.StudentDTO;
import lk.royalInstitute.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);

    @Override
    public boolean addStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(studentDTO.getId(),
                studentDTO.getStudentName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDate(),
                studentDTO.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(studentDTO.getId(),
                studentDTO.getStudentName(),
                studentDTO.getAddress(),
                studentDTO.getContact(),
                studentDTO.getDate(),
                studentDTO.getGender()));
    }

    @Override
    public StudentDTO getStudent(String id) throws Exception {
        Student student = studentDAO.get(id);
        return new StudentDTO(student.getId(),
                student.getStudentName(),
                student.getAddress(),
                student.getContact(),
                student.getDate(),
                student.getGender());
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        List<Student> studentList = studentDAO.getAll();
        for (Student student : studentList) {
            studentDTOList.add(new StudentDTO(student.getId(),
                    student.getStudentName(),
                    student.getAddress(),
                    student.getContact(),
                    student.getDate(),
                    student.getGender()));
        }
        return studentDTOList;
    }
}
