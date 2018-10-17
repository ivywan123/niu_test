package Common;
import Configuration.ReadConfig;
import Model.DataBaseVO;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SqlConnection  {
    private static final Logger log = LoggerFactory.getLogger(SqlConnection.class);
    private static ArrayList<DataBaseVO> databaselist = new ArrayList<>() ;
    //    public static  String Ip= ReadConfig.readconfig("DataBase");//DataBase
//    public static  String tableName= ReadConfig.readconfig("DataBaseName");//DataBaseName
//    public static  String user= ReadConfig.readconfig("UserName");//UserName
//    public static  String pwd= ReadConfig.readconfig("Passwd");//Passwd
//    public static  String url= "jdbc:mysql://"+Ip+":3306/"+tableName+"?autoReconnect=true";
//    private ArrayList<DataBaseVO> databaselist = new ArrayList<>() ;
    /**
     * 装在数据库信息
     */
    public static void loaddatabase(){
//        ArrayList<DataBaseVO> dblist = new ArrayList<>() ;
        //固定最多只能有3个数据库
        for(int i=1;i<4;i++){
            try {
                DataBaseVO dbo = new DataBaseVO();
                String database = ReadConfig.readconfig("DataBase"+i);
                if (database!=""|| database!=null) {
                    dbo.setDataBase(database);
                    dbo.setDataBaseName(ReadConfig.readconfig("DataBaseName"+i));
                    dbo.setUserName(ReadConfig.readconfig("UserName"+i));
                    dbo.setPasswd(ReadConfig.readconfig("Passwd"+i));
                    dbo.setUrl();
                    databaselist.add(dbo);
                }else{

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取数据库连接实例
     *
     */
    public static Connection getconnect(String databaseName) throws Exception{
        loaddatabase();
        Connection conn = null;
        //循环遍历list，查找到对应的数据库，做连接
        for(int j=0;j<databaselist.size();j++){
            if(databaseName.equals(databaselist.get(j).getDataBaseName())){
                String url = databaselist.get(j).getUrl();
                String user = databaselist.get(j).getUserName();
                String pwd = databaselist.get(j).getPasswd();
                try {
//                    Class.forName("com.mysql.jdbc.Driver");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(url, user, pwd);
                }catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return conn;
    }

    /**
     * 查询
     * @param sql
     */
 public  String Select(String databaseName,String sql) {
     List<String> result = new ArrayList<String>();
     Connection conn2 = null;
    Statement st = null;
    ResultSet rs = null;
    try {
//        Class.forName("com.mysql.jdbc.Driver");
//        conn = DriverManager.getConnection(url,user,pwd);
        conn2 = getconnect(databaseName);
        st = conn2.createStatement();
        rs = st.executeQuery(sql);
        ResultSetMetaData rm = rs.getMetaData();
        while(rs.next()) {
            JSONObject json_obj = new JSONObject();
            for (int i=1; i<=rm.getColumnCount(); i++){
                json_obj.put(rm.getColumnLabel(i), rs.getString(i));
            }
            result.add(json_obj.toString());
        }
        //分别捕获异常
    } catch (Exception e) {
        e.printStackTrace();
    }  finally {
        close(conn2,st,rs);
//        try {
//            //判断资源是否存在
//            if(rs != null) {
//                rs.close();
//                //显示的设置为空，提示gc回收
//                rs = null;
//            }
//            if(st != null) {
//                st.close();
//                st = null;
//            }
//            if(conn2 != null) {
//                conn2.close();
//                conn2 = null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
//     System.out.println(String.join(",",result));
    return String.join(",",result);
}

    /**
     * 插入修改 删除
     * @param sql
     */
    public  String insert (String databaseName,String sql) {
        System.out.println( sql);
        List<String> result = new ArrayList<String>();
        Connection conn3 = null;
        Statement st = null;
        int res=0;  //返回更新的记录条数，如果返回为0，表示未更新
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url,user,pwd);
            conn3 = getconnect(databaseName);
            st = conn3.createStatement();
            //注意，此处是excuteUpdate()方法执行
            res=st.executeUpdate(sql);
            //分别捕获异常
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(st != null) {
                    st.close();
                    st = null;
                }
                if(conn3 != null) {
                    conn3.close();
                    conn3 = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JSONObject json_obj = new JSONObject();
        json_obj.put("cnt", String.valueOf(res));
        result.add(json_obj.toString());
        return String.join(",",result);
    }

    /**
     * 关闭数据库连接
     */
    private void close(Connection con,Statement sta,ResultSet rs){

        try {
            if(rs !=null)rs.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        try {
            if(sta !=null)sta.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        try {
            if(con !=null)con.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}
