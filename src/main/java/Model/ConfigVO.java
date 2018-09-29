package Model;

import Configuration.ReadConfig;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 */
public class ConfigVO {
    private String FilePath;
    private String FileList[];
    private String RunLevel ;//配置需要运行的case等级
    private String tomail;//邮件接收人
    private String Environment;//执行环境
    private LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();
    private HashMap<String,  ModuleVO >  Table=new  HashMap<String,  ModuleVO>();


    public LinkedHashMap<String, String> getMap() {
        return map;
    }

    public void setMap(String key,String value) {
        this.map.put(key,value);
    }

    public HashMap<String, ModuleVO> getTable() {
        return Table;
    }

    public void setTable(String key,ModuleVO module) {
        Table.put(key,module);
    }

    public String getFilePath() {
        setFilePath();
        return FilePath;
    }

    public void setFilePath() {
        FilePath = ReadConfig.readconfig("path");
    }

    public String[] getFileList() {
        setFileList();
        return FileList;
    }

    public void setFileList() {
        FileList = ReadConfig.readconfig("Template").split(",");
    }

    public String getRunLevel() {
        setRunLevel();
        return RunLevel;
    }

    public void setRunLevel() {
        RunLevel = ReadConfig.readconfig("Level");
    }

    public String getTomail() {
        setTomail();
        return tomail;
    }

    public void setTomail() {
        this.tomail = ReadConfig.readconfig("mail");
    }

    public String getEnvironment() {
        setEnvironment();
        return Environment;
    }

    public void setEnvironment() {
        Environment = ReadConfig.readconfig("environment");
    }
}
