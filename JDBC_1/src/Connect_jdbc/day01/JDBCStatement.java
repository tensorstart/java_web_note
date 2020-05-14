package Connect_jdbc.day01;

import java.sql.*;
import java.util.Scanner;

public class JDBCStatement {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.println("输入asc或desc：");
        String s = sc.nextLine();
        //六步
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_login", "root", "root");
            statement = connection.createStatement();
            String ss="select * from t_user order by loginName "+s;
            resultSet = statement.executeQuery(ss);
            while (resultSet.next()){
                System.out.println(resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (resultSet!=null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement!=null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection!=null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}
