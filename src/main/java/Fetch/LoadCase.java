package Fetch;


import Common.Public;
import Model.ModuleVO;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

/**
 *
 */
public class LoadCase {
    private static final Logger logger = LoggerFactory.getLogger(LoadCase.class);
    public static  void  main(String[] args) throws Exception {
        JSONObject JSON=new JSONObject();
        String [] Template ={"test.xls"};
        LoadCase data=new  LoadCase();
        JSON.put("test",data.LoadCase(Template));
        logger.info(JSON.toString());
    }


    /**
     * 把所有的case装起来
     * @param Template
     * @return
     * @throws Exception
     */
    public  ArrayList<ModuleVO>  LoadCase(String [] Template) throws Exception {
        ArrayList<ModuleVO> ModuleList =new    ArrayList<ModuleVO>();
        for(int i=0;i<Template.length;i++){
            try{
                ModuleVO module=new  ModuleVO();
                ReadExecl LoadData=new  ReadExecl();
                module.setName(Template[i]);
                module.setFileName(Template[i]);
                module.setCaseList(LoadData.read(Template[i]));
                ModuleList.add(module);

            }catch(Exception e){
                  e.printStackTrace();
          }
        }
        return ModuleList;
    }


}
