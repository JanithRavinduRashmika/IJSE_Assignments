package lk.royalInstitute.dao.custom;

import lk.royalInstitute.dao.SuperDAO;
import lk.royalInstitute.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO{
    public List<CustomEntity> getStudentDetails(String courseID) throws Exception;

    public List<CustomEntity> getStudentWhoRegisterForAllCourses() throws Exception;
}
