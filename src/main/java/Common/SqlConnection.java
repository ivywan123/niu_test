package Common;
import Configuration.ReadConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class SqlConnection  {
    public static  String Ip= ReadConfig.readconfig("DataBase");//定义你的工作存储地址
    public static  String tableName= ReadConfig.readconfig("DataBaseName");//定义你的工作存储地址
    public static  String user= ReadConfig.readconfig("UserName");//定义你的工作存储地址
    public static  String pwd= ReadConfig.readconfig("Passwd");//定义你的工作存储地址
    public static  String url= "jdbc:mysql://"+Ip+":3306/"+tableName;

/**
     * 查询
     * @param sql
     */
 public  void Select(String sql) {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url,user,pwd);
        st = conn.createStatement();
        rs = st.executeQuery(sql);
        while(rs.next()) {
            System.out.println(rs.getObject(1) + "  " +
                    rs.getObject(2) + "  " + rs.getInt("birth"));
        }
        //分别捕获异常
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            //判断资源是否存在
            if(rs != null) {
                rs.close();
                //显示的设置为空，提示gc回收
                rs = null;
            }
            if(st != null) {
                st.close();
                st = null;
            }
            if(conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    /**
     * 插入修改 删除
     * @param sql
     */
    public  void insert (String sql) {
        System.out.println( sql);
        Connection conn = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pwd);
            st = conn.createStatement();
            //注意，此处是excuteUpdate()方法执行
            st.executeUpdate(sql);
            //分别捕获异常
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(st != null) {
                    st.close();
                    st = null;
                }
                if(conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
