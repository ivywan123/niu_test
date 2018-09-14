package Common;

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
    public  String GetResponse(StepVO step) throws Exception {
        String response=new String();
        String Url=Public.replaceStr(ParametersFactory.Extraction(step.getUrl()));
        String Parameter=Public.replaceStr(ParametersFactory.Extraction(step.getParameter()));
        String Method = step.getMethod();
        httpclient send=new  httpclient();
        try{
        switch (Method) {
            case "GET":
                response=send.sendGet(Url,Parameter);
                break;
            case "POST":
                response=send.sendPost(Url,Parameter);
                break;
            case "Sql-select":
                new  SqlConnection().Select(Url);
                break;
            case "Sql-update":
                new  SqlConnection().insert(Url);
                break;
            case "sleep":
                response = Action.sleep(Url);
                break;
        }}catch (Exception e){
            Public.saveELog(e);
        }
        return response;
    }
}
