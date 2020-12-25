package jdbctests;

import javax.management.relation.RelationSupport;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@3.80.189.73:1521:xe";
        //------------------------------@ip..........:port:   from oracle connection
        String dbUsername = "hr";
        String dbPassword = "hr";

        //create connection
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement=connection.createStatement();
        //run query and get the result in resultset objetc
        ResultSet resultSet=statement.executeQuery("select * from departments");


     /*   //move ponter to first row
        resultSet.next();

        //getting information with colunm name
        System.out.println("resultSet.getString(\"region_name\") = " + resultSet.getString("region_name"));
        //getting information with colunm index
        System.out.println("resultSet.getString(2) = " + resultSet.getString(2));
        //"1-Europe"
        System.out.println(resultSet.getString(1) + "-" + resultSet.getString("region_name"));


        //move ponter to first row
        resultSet.next();

        //getting information with colunm name
        System.out.println("resultSet.getString(\"region_name\") = " + resultSet.getString("region_name"));
        //getting information with colunm index
        System.out.println("resultSet.getString(2) = " + resultSet.getString(2));
        //"2-Americas"
        System.out.println(resultSet.getString(1) + "-" + resultSet.getString("region_name"));

*/

        while(resultSet.next()){
            System.out.println(resultSet.getString(1) + "-" + resultSet.getString(2)+ "-" + resultSet.getString(3)+ "-" + resultSet.getString(4));
        }



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();





    }
}
