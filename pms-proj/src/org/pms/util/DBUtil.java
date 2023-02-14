package org.pms.util;

/**
 * 连接数据库工具类
 *
 * @Date 2023-02-14
 * @Author zqx
 */
import java.sql.*;

public class DBUtil {
    // 第一：定义连接数据库的相关信息
    private static final String USERNAME = "sa"; 	        // 帐号

    private static final String PASSWORD = "123"; 		// 密码

    // 语法：jdbc:数据库类型://服务器地址:端口号;DatabaseName=数据库名
    // 其中，服务器地址：还可以是IP地址 或 域名 ; localhost表示本地服务器
    private static final String URL = "jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=productdb"; // 连接地址（服务器+数据库）

    // 驱动程序 : JDBC接口的实现 ，由数据库厂商提供
    private static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";

    // 静态代码块 : 加载驱动程序
    static {
        // 第二：加载驱动程序
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序失败");
            e.printStackTrace();
        }
    }

    /**
     * 返回连接对象 - 建立连接数据库的桥梁
     */
    public static Connection getConnection() {
        // 第三：连接数据库，并返回连接对象
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("获取连接对象失败");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库对象，释放资源
     *
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("关闭结果集对象失败");
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("关闭语句对象失败");
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败");
                e.printStackTrace();
            }
        }
    }

    //定义主方法，测试是否连接成功
    public static void main(String[] args) {
        System.out.println(DBUtil.getConnection());
    }
}
