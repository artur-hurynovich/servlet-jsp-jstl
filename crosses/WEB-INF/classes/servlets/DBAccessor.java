package servlets;
import java.sql.*;
import java.util.ArrayList;
public class DBAccessor {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ArrayList<String[]> result;
    private ResultSet resultSet;
    public DBAccessor() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/my_db", "root", "PASSWORD");
            preparedStatement = connection.prepareStatement
                    ("SELECT * FROM crosses WHERE number = (?);");
            result = new ArrayList<>();
        }
        catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String[]> getCrosses(String search) {
        try {
            preparedStatement.setString(1, search);
            resultSet = preparedStatement.executeQuery();
            if (result.size() != 0) {
                result.clear();
            }
            while (resultSet.next()) {
                result.add(new String[]{resultSet.getString("c_number"),
                        resultSet.getString("c_brand"),
                        resultSet.getString("description")});
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void close() {
        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
