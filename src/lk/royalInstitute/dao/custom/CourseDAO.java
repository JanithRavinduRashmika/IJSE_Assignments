package lk.royalInstitute.dao.custom;

import lk.royalInstitute.dao.CrudDAO;
import lk.royalInstitute.entity.Course;

public interface CourseDAO extends CrudDAO<Course,String> {
    public String getNextID() throws Exception;

}
