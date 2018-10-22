package Common;

import Inspection.Calculate;
import Model.ConfigVO;
import Model.StepVO;
import Parameter.ParametersFactory;
import Trigger.httpclient;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class GetResponse {
    private static final Logger log = LoggerFactory.getLogger(GetResponse.class);
    /**
     * 分解步骤请求方式，获取步骤返回值
      * @param step
     * @return
     * @throws Exception
     */
    public  String GetResponse(ConfigVO config,StepVO step) throws Exception {
        String response="";
        String Url = "";
        String dbName="";
        String sql_url="";
        String Parameter="";
        Calculate ca=new Calculate();
        //url的组装，将路径前半部分从配置文件中取
        String Method = step.getMethod().toUpperCase();
        if(( "POST".equals(Method) ) || "GET".equals(Method) || "DELETE".equals(Method)) {
            Url = config.getEnvironment() + Public.replaceStr(ParametersFactory.Extraction(step.getUrl()));
            Parameter = ParametersFactory.get_para(step.getParameter());
        } else if(( "SQL-SELECT".equals(Method) ) || "SQL-UPDATE".equals(Method) || "SLEEP".equals(Method)){
            //如果需要查询数据库，Url要做切割，以冒号分割
            Url = Public.replaceStr(ParametersFactory.Extraction(step.getUrl()));
            if(Url.contains(":")){
                dbName = Public.replaceStr(Url.substring(0,Url.indexOf(":")));
                sql_url = Public.replaceStr(Url.substring(Url.indexOf(":")+1,Url.length()));
            }
        }
//        String Parameter=Public.replaceStr(ca.para_GetLS(ParametersFactory.Extraction(step.getParameter())));

        //保存替换后的参数
        step.setExtra_parameter(Parameter);
        httpclient send=new  httpclient();
        try{
        switch (Method) {
            case "GET":
                response=send.sendGet(Url,Parameter);
                log.info("【response:】"+response);
                break;
            case "POST":
                System.out.println(Url);
                System.out.println(Parameter);
                response=send.sendPost(Url,Parameter);
                log.info("【response:】"+response);
                break;
            case "DELETE":
                response=send.sendDelete(Url,Parameter);
                log.info("【response:】"+response);
                break;
            case "SQL-SELECT":
                System.out.println(sql_url);
                SqlConnection st=new SqlConnection();
                response = st.Select(dbName,sql_url);
                log.info("【response:】"+response);
                break;
            case "SQL-UPDATE":
                //返回更新的条数，固定写成参数cnt，写法为：$.cnt>0
                response = new  SqlConnection().insert(dbName,sql_url);
                System.out.println(response);
                break;
            case "SLEEP":
                response = Action.sleep(Url);
                break;
        }}catch (Exception e){
            Public.saveELog(e);
        }
        return response;
    }
}
