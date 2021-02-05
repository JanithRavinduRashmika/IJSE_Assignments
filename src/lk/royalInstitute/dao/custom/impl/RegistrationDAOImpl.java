package lk.royalInstitute.dao.custom.impl;

import lk.royalInstitute.dao.custom.RegistrationDAO;
import lk.royalInstitute.entity.Registration;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean add(Registration entity) throws Exception {
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
            session.delete(session.load(Registration.class,s));
            transaction.commit();
            return true;

        }catch (Exception e){
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Registration entity) throws Exception {
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
    public Registration get(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Transaction transaction = session.beginTransaction();
            Registration registration = session.get(Registration.class, s);
            transaction.commit();
            return registration;

        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Registration> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from " + Registration.class);
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
