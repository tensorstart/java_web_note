package Connect_jdbc.day01.utils;

import java.sql.*;

/**
 * @author xxx
 * JDBC工具类，获取连接
  */
public class DButil {
    /**
     * @author xxx
     * 工具类中的构造方法都是私有的
     * 因为工具类中的方法都是静态的，不需要new对象，直接采用类名调用
     * */
    private DButil(){}
    //静态代码块在类加载时执行，并且只执行一次
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * @author xxx
     * 获取数据库连接对象
     * @return 连接对象*/
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_login", "root",
                   "root");
    }
    /**
     * @param connection 连接对象
     * @param statement 数据库操作对象
     * @param resultset 结果集
     */
    public static void close(Connection connection, Statement statement, ResultSet resultset){
        if (resultset!=null) {
            try {
                resultset.close();
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
