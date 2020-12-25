package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {

    String dbUrl = "jdbc:oracle:thin:@3.80.189.73:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        //create connection
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset objetc
        ResultSet resultSet=statement.executeQuery("select * from departments");

        //how to find many rows we have for the query
        // go to last row
        resultSet.last();
        //get the row count
        System.out.println("resultSet.getRow() = " + resultSet.getRow());
        resultSet.beforeFirst();

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();



    }

    @Test
    public void MetaDataExample() throws SQLException {

        //create connection
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset objetc
        ResultSet resultSet=statement.executeQuery("select * from employees");


        //get the databsae realted data inside the meta data object

        DatabaseMetaData dbMetadata=connection.getMetaData();

        System.out.println("User =" + dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());


       //get the resultset meta data
       ResultSetMetaData rsMetadata= resultSet.getMetaData();

       //how many columns we have?

        int colCount=rsMetadata.getColumnCount();
        System.out.println("colCount = " + colCount);
        //result depends on  "departments"------ResultSet resultSet=statement.executeQuery("select * from departments");

        //column names
        System.out.println("rsMetadata.getColumnName(1) = " + rsMetadata.getColumnName(1));
        System.out.println("rsMetadata.getColumnName(2) = " + rsMetadata.getColumnName(2));


        //print all column names dynamically
        int i=1;
        while(i<rsMetadata.getColumnCount()){
            System.out.println("rsMetadata.getColumnName(i) = " + rsMetadata.getColumnName(i));
            i++;

        }





        //close all connections
        resultSet.close();
        statement.close();
        connection.close();



    }







}
