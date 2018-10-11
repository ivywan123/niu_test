package Common;

import Model.ConfigVO;
import Model.StepVO;
import Parameter.ParametersFactory;
import Trigger.httpclient;

/**
 *
 */
public class GetResponse {

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
        //url的组装，将路径前半部分从配置文件中取
        String Method = step.getMethod().toUpperCase();
        if(( "POST".equals(Method) ) || "GET".equals(Method) || "DELETE".equals(Method)) {
            Url = config.getEnvironment() + Public.replaceStr(ParametersFactory.Extraction(step.getUrl()));
        } else if(( "SQL-SELECT".equals(Method) ) || "SQL-UPDATE".equals(Method)){
            //如果需要查询数据库，Url要做切割，以冒号分割
            Url = Public.replaceStr(ParametersFactory.Extraction(step.getUrl()));
            if(Url.contains(":")){
                dbName = Url.substring(0,Url.indexOf(":"));
                sql_url = Url.substring(Url.indexOf(":")+1,Url.length());
            }
        }
        String Parameter=Public.replaceStr(ParametersFactory.Extraction(step.getParameter()));
        //保存替换后的参数
        step.setExtra_parameter(Parameter);
        httpclient send=new  httpclient();
        try{
        switch (Method) {
            case "GET":
                response=send.sendGet(Url,Parameter);
                break;
            case "POST":
                response=send.sendPost(Url,Parameter);
                break;
            case "DELETE":
                response=send.sendDelete(Url,Parameter);
                break;
            case "SQL-SELECT":
                SqlConnection st=new SqlConnection();
                response = st.Select(dbName,sql_url);
                break;
            case "SQL-UPDATE":
                new  SqlConnection().insert(dbName,sql_url);
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
