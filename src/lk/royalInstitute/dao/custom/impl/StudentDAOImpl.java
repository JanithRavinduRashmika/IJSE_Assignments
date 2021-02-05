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
        try{
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, s);
            transaction.commit();
            return student;

        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Student> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Transaction transaction = session.beginTransaction();
            String hql = "from Student";
            Query query = session.createQuery(hql);
            List list = query.list();
            transaction.commit();
            return list;

        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }
}
