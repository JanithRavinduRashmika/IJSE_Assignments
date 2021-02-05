package lk.royalInstitute.dao.custom.impl;

import lk.royalInstitute.dao.custom.StudentDAO;
import lk.royalInstitute.entity.Student;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    Session session = FactoryConfiguration.getInstance().getSession();

    @Override
    public boolean add(Student entity) throws Exception {

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
        return false;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        return false;
    }

    @Override
    public Student get(String s) throws Exception {
        return null;
    }

    @Override
    public List<Student> getAll() throws Exception {
        return null;
    }
}
