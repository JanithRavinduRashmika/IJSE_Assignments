import javafx.application.Application;
import javafx.stage.Stage;
import lk.royalInstitute.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppInitializer {

    public static void main(String[] args) {
        Session sessions = FactoryConfiguration.getInstance().getSessions();
        Transaction transaction = sessions.beginTransaction();



        transaction.commit();
    }

}
