package Model;

/**
 * Created by admin on 2018/10/10.
 */
public class DataBaseVO {
    private String DataBase;
    private String DataBaseName;
    private String UserName;
    private String Passwd;
    private String url;

    public String getDataBase() {
        return DataBase;
    }

    public void setDataBase(String dataBase) {
        DataBase = dataBase;
    }

    public String getDataBaseName() {
        return DataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        DataBaseName = dataBaseName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPasswd() {
        return Passwd;
    }

    public void setPasswd(String passwd) {
        Passwd = passwd;
    }

    public String getUrl() {
        setUrl();
        return url;
    }

    public void setUrl() {
        this.url = "jdbc:mysql://"+this.DataBase+":3306/"+this.DataBaseName+"?autoReconnect=true";
    }
}
