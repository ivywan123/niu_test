package Parameter;


import Common.Public;
import Inspection.JsonAnalysis;
import Model.StepVO;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 */
public class ParametersFactory {
    private static final Logger log = LoggerFactory.getLogger(ParametersFactory.class);
private static HashMap<String, String> daoMap=new  HashMap<String, String>();
private String VarList[];


    /**
     * 参数提取
     * @param step
     * @throws IOException
     */
    public  static void   GetParameter(StepVO step) throws IOException {
        String response=step.getResponse();
        String Parameter=step.getTransfer();
        String extra_parameter = step.getExtra_parameter(); //替换后的参数
        if(Parameter!=null){
            String ParameterList[]=Parameter.split(",");
            for(int ium=0;ium<ParameterList.length;ium++){
                String Pstr=ParameterList[ium];
                if(Pstr.contains("=")){
                    JsonAnalysis jpath=new JsonAnalysis();
                    String key =Pstr.substring(0,Pstr.indexOf("="));
                    String value=Pstr.substring(Pstr.indexOf("=")+1,Pstr.length());
//                    JSONObject obj = JSONObject.fromObject(response);
                    //判断替换后的参数中是否有value值，如果没有，就从response中取值
                    String la=value.substring(2,value.length());
                    if(extra_parameter.contains(la)){
                        //从替换后的参数中取值
                        String[] strs=extra_parameter.split("&");
                        for (String str : strs) {
                            if(str.contains(la)){
                                String done=str.substring(str.indexOf(la)+la.length()+1,str.length());
                                setDaoMap(key,done);
                            }
                        }
                    }else{
                        setDaoMap(key,jpath.JsonPath(response,value));
                    }

                }
            }
        }
        log.info("参数池: " + daoMap.toString());
    }



    public HashMap<String, String> getDaoMap() {
        return daoMap;
    }

    public static void setDaoMap(String Key,String value) {
        daoMap.put(Key,value);
    }

    public String[] getVarList() {
        return VarList;
    }

    public void setVarList(String varList) {
        VarList = varList.split(",");
    }




    /**
     * 参数替换
     * @param
     * @throws IOException
     */
    public static  String   Extraction(String ArrStr) throws IOException {
        Global Global=new  Global();
        //替换传递参数
       if(ArrStr.contains("${")){
           while (ArrStr.contains("${")){
               String key =ArrStr.substring(ArrStr.indexOf("${")+2,ArrStr.indexOf("}"));
               if(daoMap.get(key.trim())!=null){
                   ArrStr=ArrStr.replace("${"+key+"}",daoMap.get(key.trim()));
               }else{
                   ArrStr=ArrStr.replace("${"+key+"}","null");
               }
           }

       }
        //替换当前时间VarTime()
        if(ArrStr.contains("VarTime")){
            if(ArrStr.contains("VarTime(")){
               String done=ArrStr.substring(ArrStr.indexOf("VarTime(")+8,ArrStr.indexOf(")"));
               ArrStr=ArrStr.replaceAll("VarTime("+done+")",Global.Time(done));

            }else{
               ArrStr=ArrStr.replaceAll("VarTime",Global.Time(null));
            }
        }

        //替换随机字符串
        if(ArrStr.contains("VarRandomStr(")){
            if(ArrStr.contains("VarRandomStr(")){
                String done=ArrStr.substring(ArrStr.indexOf("VarRandomStr(")+13,ArrStr.indexOf(")"));
                ArrStr=ArrStr.replaceAll("VarRondomStr("+done+")",Global.RandomStr(done));

            }else{
                ArrStr=ArrStr.replaceAll("VarTime",Global.Time(null));
            }
        }

        //替换当前JAVA时间
        if(ArrStr.contains("JTime")){
            if(ArrStr.contains("JTime(")){
                String done=ArrStr.substring(ArrStr.indexOf("JTime(")+8,ArrStr.indexOf(")"));
                long sumTime=System.currentTimeMillis()+Integer.parseInt(done);
                ArrStr=ArrStr.replaceAll("VarTime("+done+")",String.valueOf(sumTime));
            }else{
                ArrStr=ArrStr.replaceAll("VarTime",String.valueOf(System.currentTimeMillis()));
            }
        }


        //替换随机数
        if(ArrStr.contains("VarRandom")){
            if(ArrStr.contains("VarRandom(")){
                String	VarRondom=new String();
                VarRondom	= ArrStr.substring( ArrStr.indexOf( "VarRandom(" ), ArrStr.lastIndexOf( ")" ) ) + ")";
                String	inter= VarRondom.substring( VarRondom.indexOf( "(" ) + 1, VarRondom.lastIndexOf( ")" ) );
                ArrStr=ArrStr.replaceAll("VarTime("+VarRondom+")",Global.Random(inter));
            }
        }

        //替换随机手机号vartel() 1380000
        if(ArrStr.contains("vartel()")){
            System.out.println(Global.getTelephone());
            ArrStr=ArrStr.replace("vartel()",Global.getTelephone());
        }

        //替换随机姓名
        if(ArrStr.contains("realname()")){
            ArrStr=ArrStr.replace("realname()",Global.getChineseName());
        }

        //替换随机身份证号
        if(ArrStr.contains("idcard()")){
            ArrStr=ArrStr.replace("idcard()",Global.getRandomidentitycard());
        }

        //替换随机银行卡号
        if(ArrStr.contains("cardno()")){
            ArrStr=ArrStr.replace("cardno()",Global.getCardNO(14));
        }

        //获取当前日期
        if(ArrStr.contains("curdate()")){
            ArrStr=ArrStr.replace("vardate()",Global.getCurDate());
        }
        /*
        //替换环境变量
        if(ArrStr.contains("Envr")){

        }
        */

        return ArrStr;
    }
    public static void main(String[] args){
        String str="VarTime(2017-8-10 09:30:28)";
        if(str.contains("VarTime")){
            if(str.contains("VarTime(")){
                String done=str.substring(str.indexOf("VarTime(")+8,str.indexOf(")"));
                System.out.println(done);
                str=str.replaceAll("VarTime("+done+")",Global.Time(done));

            }else{
                str=str.replaceAll("VarTime",Global.Time(null));
            }
            System.out.println(str);
        }
        }

    }






