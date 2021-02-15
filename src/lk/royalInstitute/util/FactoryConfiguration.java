package lk.royalInstitute.util;

import lk.royalInstitute.entity.Course;
import lk.royalInstitute.entity.LogIn;
import lk.royalInstitute.entity.Registration;
import lk.royalInstitute.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private  SessionFactory sessionFactory;
    private FactoryConfiguration(){

        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
            sessionFactory = new Configuration().mergeProperties(properties)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Registration.class)
                    .addAnnotatedClass(LogIn.class)
                    .buildSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration==null) ? factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }


}
