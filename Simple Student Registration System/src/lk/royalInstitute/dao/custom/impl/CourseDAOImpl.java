package lk.royalInstitute.dao.custom.impl;

import lk.royalInstitute.dao.custom.CourseDAO;
import lk.royalInstitute.entity.Course;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean add(Course entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(session.load(Course.class,s));
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public Course get(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Course course = session.get(Course.class, s);
        session.close();
        return course;
    }

    @Override
    public List<Course> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("from Course" );
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public String getNextID() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT code FROM course ORDER BY code DESC LIMIT 1");
        String s = (String) nativeQuery.uniqueResult();
        String nextID = "";
        if (s==null){
            nextID = "CT1000";
        }else{
            int i = Integer.parseInt(s.substring(2, 6));
            nextID = "CT"+(i+1);
        }
        return nextID;
    }

    @Override
    public String getCourseID(String courseName) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT code FROM course WHERE courseName = ?1").setParameter(1,courseName);
        String courseID = (String) nativeQuery.uniqueResult();
        return courseID;
    }
}
