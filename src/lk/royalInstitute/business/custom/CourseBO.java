package lk.royalInstitute.business.custom;

import lk.royalInstitute.business.SuperBO;
import lk.royalInstitute.dto.CourseDTO;
import lk.royalInstitute.entity.Course;

import java.util.List;

public interface CourseBO extends SuperBO {

    public boolean addCourse(CourseDTO courseDTO) throws Exception;

    public boolean deleteCourse(String id) throws Exception;

    public boolean updateCourse(CourseDTO courseDTO) throws Exception;

    public CourseDTO getCourse(String id) throws Exception;

    public List<CourseDTO> getAllCourses() throws Exception;

    public String getNextId() throws Exception;
    
    public String getCourseID(String courseName) throws Exception;
}
