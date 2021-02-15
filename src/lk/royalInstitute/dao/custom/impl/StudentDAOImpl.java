package lk.royalInstitute.dao.custom.impl;

import lk.royalInstitute.dao.custom.StudentDAO;
import lk.royalInstitute.entity.Student;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
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
        try{
            Transaction transaction = session.beginTransaction();
            session.delete(session.load(Student.class,s));
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
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
    public Student get(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Student student = session.get(Student.class, s);
        session.close();
        return student;

    }

    @Override
    public List<Student> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("from Student");
        List list = query.list();
        session.close();
        return list;

    }

    @Override
    public String getNextID() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createNativeQuery("SELECT id FROM Student ORDER BY id DESC LIMIT 1");
        String s  = (String) query.uniqueResult();
        String nextID ="";
        if (s==null){
            nextID = "S10000";
        }else{
            int i = Integer.parseInt(s.substring(1,6));
            nextID = "S"+(i+1);
        }
        session.close();
        return nextID;

    }
}
