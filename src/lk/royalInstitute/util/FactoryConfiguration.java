package lk.royalInstitute.util;

import lk.royalInstitute.entity.Course;
import lk.royalInstitute.entity.Registration;
import lk.royalInstitute.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private  SessionFactory sessionFactory;
    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure().
                addAnnotatedClass(Student.class).
                addAnnotatedClass(Registration.class).
                addAnnotatedClass(Course.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration==null) ? factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSessions(){
        return sessionFactory.openSession();
    }


}
