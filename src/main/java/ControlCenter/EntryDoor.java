package ControlCenter;
import Common.Action;
import Common.Public;
import Fetch.LoadCase;
import Model.*;
import java.util.ArrayList;
import org.testng.annotations.Test;
import net.sf.json.JSONObject;

/**
 *
 */
public class EntryDoor {
public ConfigVO config=new ConfigVO();
public LoadCase judy=new LoadCase();
public HtmlReport hreport=new HtmlReport();
public StringBuffer reportHtml=new StringBuffer();
public TaskVO Assignment =new TaskVO();
public ArrayList<ModuleVO> Module=new ArrayList<ModuleVO> ();
public Implement doIt=new Implement();


/**
 * 主方法入口
 * @throws Exception
 */
@Test
public  void Main() throws Exception {
    long startTime = System.currentTimeMillis();
    Module=judy.LoadCase(config.getFileList());    //数据装箱
    //遍历moduleList
    for(int i=0;i<Module.size();i++){
        long startModuleTime = System.currentTimeMillis();
        Public.logs("【模块名称】: "+Module.get(i).getName());
        Public.log("【模块名称】: "+Module.get(i).getName());
        doIt.ResolveModule(config,Module.get(i));
        Assignment.TaskVO(Module.get(i).getPass(),Module.get(i).getFail(),Module.get(i));
        long overModuleTime = System.currentTimeMillis();
        Module.get(i).setDoTime((overModuleTime-startModuleTime)/1000);
        config.setTable(Module.get(i).getName(),Module.get(i));
        hreport.setTab(Module.get(i).getName(),i);
        reportHtml.append(hreport.getModule(Module.get(i)));
    }
    long overTime = System.currentTimeMillis();
    Assignment.setRunTime((overTime-startTime)/1000);
    Assignment.ReportMap(config);
    JSONObject JSON= new JSONObject();
    JSON.put("result",Assignment);
    Public.logs(JSON.toString());
    Public.log(JSON.toString());
    Action.HtmlStr( config, Assignment, reportHtml, hreport);//结果数据处理

}

    public static void main(String[] args) throws Exception {
        EntryDoor EntryDoor=new EntryDoor();
        EntryDoor.Main();
    }

}
