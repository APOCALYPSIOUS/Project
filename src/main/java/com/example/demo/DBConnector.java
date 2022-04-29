package com.example.demo;



import java.sql.*;


public class DBConnector {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/project";// MySQL URL and followed by the
    // database name
    private static final String username = "root"; // MySQL username
    private static final String password = ""; // MySQL password

    public static Connection createConnection() throws SQLException {
        Connection connection = null;

        try {
            try {
                Class.forName(JDBC_DRIVER); // loading mysql driver
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(DATABASE_URL, "root", ""); // attempting to connect to MySQL
            // database
            System.out.println("Printing connection object " + connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;


    }
    public static  ResultSet executeQuery(String Query) throws SQLException {
        Connection connection = DBConnector.createConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement
                .executeQuery(Query);
        resultSet.next();

        return resultSet;
    }
    public static void updateQuery(String Query) throws SQLException {
        Connection connection = DBConnector.createConnection();
        Statement statement = connection.createStatement();
        statement
                .executeUpdate(Query);


    }

}

    /*   public static void main(String[] args) throws SQLException {
        Connection connection = DBConnector.createConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select * from comments;");
            resultSet.next();
        System.out.println(resultSet.getObject(1));
        connection.close();


    }


    }

*/
