package pl.sda;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Optional;

public class JDBCMain {
    public static void main(String[] args) {

//        createStatement("1500");
        createStatement("2000 or 1=1");

    }

    private static void createStatement(String sallary) {
        try {
            Connection connection = createConnection()
                    .orElseThrow(() -> new RuntimeException("Nie udalo sie utworzyc połaczenia"));

            String query = "Select ename, job, sal from sdajdbc.employee where sal >=" + sallary;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                BigDecimal sal = resultSet.getBigDecimal("sal");
                System.out.println(ename + " " + job + " " + sal);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    private static Optional<Connection> createConnection() {
        Connection connection = null;
        try {
              connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306?serverTimezone=UTC",
                    "root",
                    "Rafal2101");
        } catch (Throwable throwables) {
            System.out.println(throwables.getMessage());
        }
        return Optional.ofNullable(connection);
    }
}
