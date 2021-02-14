package lk.royalInstitute.dao.custom.impl;

import lk.royalInstitute.dao.custom.QueryDAO;
import lk.royalInstitute.entity.CustomEntity;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomEntity> getStudentDetails(String courseID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        ArrayList<CustomEntity> customEntityArrayList = new ArrayList<>();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT s.id,s.studentName,r.regNo FROM registration r INNER JOIN student s on r.student_id = s.id WHERE r.course_code = ?1").setParameter(1, courseID);
        List<Object[]> objects =nativeQuery.list();
        for (Object[] object : objects) {
            customEntityArrayList.add(new CustomEntity(object[0].toString(),
                    object[1].toString(),
                    Integer.parseInt(object[2].toString())));

        }

        session.close();

        return customEntityArrayList;


    }

    @Override
    public List<CustomEntity> getStudentWhoRegisterForAllCourses() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        ArrayList<CustomEntity> customEntityArrayList = new ArrayList<>();

        NativeQuery nativeQuery = session.createNativeQuery("select count(code) from course");
        Object o = nativeQuery.uniqueResult();
        NativeQuery nativeQuery1 = session.createNativeQuery("select student_id,count(course_code)\n" +
                "from registration\n" +
                "group by student_id\n" +
                "having count(course_code) = ?1").setParameter(1, o.toString());
        List<Object[]> list = nativeQuery1.list();
        for (Object[] o1 : list) {
            customEntityArrayList.add(new CustomEntity(o1[0].toString()));
        }
        return customEntityArrayList;
    }
}
