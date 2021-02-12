package lk.royalInstitute.business.custom.impl;

import lk.royalInstitute.business.custom.CourseBO;
import lk.royalInstitute.dao.DAOFactory;
import lk.royalInstitute.dao.DAOType;
import lk.royalInstitute.dao.SuperDAO;
import lk.royalInstitute.dao.custom.CourseDAO;
import lk.royalInstitute.dto.CourseDTO;
import lk.royalInstitute.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
    @Override
    public boolean addCourse(CourseDTO courseDTO) throws Exception {
        return courseDAO.add(new Course(courseDTO.getCode(),
                courseDTO.getCourseName(),
                courseDTO.getCourseFee(),
                courseDTO.getDuration()));
    }

    @Override
    public boolean deleteCourse(String id) throws Exception {
        return courseDAO.delete(id);
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws Exception {
        return courseDAO.update(new Course(courseDTO.getCode(),
                courseDTO.getCourseName(),
                courseDTO.getCourseFee(),
                courseDTO.getDuration()));
    }

    @Override
    public CourseDTO getCourse(String id) throws Exception {
        Course course = courseDAO.get(id);
        return new CourseDTO(course.getCode(),
                course.getCourseName(),
                course.getCourseFee(),
                course.getDuration());
    }

    @Override
    public List<CourseDTO> getAllCourses() throws Exception {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        List<Course> courseList = courseDAO.getAll();
        for (Course course : courseList) {
            courseDTOList.add(new CourseDTO(course.getCode(),
                    course.getCourseName(),
                    course.getCourseFee(),
                    course.getDuration()));
        }
        return courseDTOList;
    }

    @Override
    public String getNextId() throws Exception {
        return courseDAO.getNextID();
    }

    @Override
    public String getCourseID(String  courseName) throws Exception {
        return courseDAO.getCourseID(courseName);
    }
}
