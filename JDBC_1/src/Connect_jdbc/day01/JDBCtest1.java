package Connect_jdbc.day01;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



public class JDBCtest1 {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String, String> loginuser = InitUI();
        //验证用户名和密码
        boolean isLogin = login(loginuser);
        //最后结果
        System.out.println(isLogin ? "登录成功" : "登录失败");


    }

    /**
     * @param loginuser 用户登录信息
     * @return true表示成功 false表示失败
     * @author sk
     */
    private static boolean login(Map<String, String> loginuser) {
        //打标记
        boolean issuccess=false;
        //JDBC代码
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;

        try {
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_login","root","root");
            //3、获取数据库操作对象
            state=con.createStatement();
            //4、执行SQL
            //字符串拼接的口诀：双引号双引号，中间加两个加号
            //使用这种方法会出现SQL注入的问题
            String sql="select * from t_user where loginName='"+loginuser.get("loginName")+"' and loginPwd='"+
                    loginuser.get("loginPwd")+"'";
            rs=state.executeQuery(sql);
            //5、处理数据结果集
            if (rs.next()){
                issuccess=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }

        return issuccess;
    }

    /**
     * @return 初始化用户名和密码
     * @author xxx
     * 用户的用户名和登录信息
     */
    private static Map<String, String> InitUI() {
        Scanner sc = new Scanner(System.in);
        System.out.println("用户名：");
        String name = sc.nextLine();

        System.out.println("密码：");
        String passwd = sc.nextLine();

        Map<String, String> userinf = new HashMap<>();
        userinf.put("loginName", name);
        userinf.put("loginPwd", passwd);
        return userinf;
    }
}
