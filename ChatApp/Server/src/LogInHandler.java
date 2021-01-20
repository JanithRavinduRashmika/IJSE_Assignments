import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInHandler extends Thread {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public LogInHandler(Socket socket){
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String msg;
            if ((msg = reader.readLine()) != null){
                String[] tokens = msg.split("\\s+",3);
                String cmd = tokens[0];
                if (cmd.equals("logIn")){
                    String userName = tokens[1].trim();
                    String password = tokens[2].trim();
                    if(isThisUserExits(userName,password)){
                        writer.println("True");

                    }else{
                        writer.println("False");
                    }

                }
            }
            this.socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isThisUserExits(String userName, String password) throws SQLException, ClassNotFoundException {
        ResultSet rst = execute("SELECT * FROM User WHERE userName = ? and password = ?",userName,password);
        if (rst.next()){
            return true;
        }else {
            return false;
        }

    }

    public static <T> T execute(String sql,Object...args) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            stm.setObject((i+1),args[i]);
        }

        if (sql.startsWith("SELECT")){
            return (T) stm.executeQuery();
        }
        return (T)(Boolean)(stm.executeUpdate()>0);
    }
}
