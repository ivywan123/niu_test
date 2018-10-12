package Model;

import Common.Public;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class StepVO extends CaseVO {
    private String Name;
    private String method;
    private String Url;
    private String parameter;
    private String extra_parameter;//维护替换后的参数，用于传递变量
    private String Level;
    private String CheckStr;
    private String transfer;
    private String response;
    private ArrayList<String> checkList=new ArrayList();


    public ArrayList<String> getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkStr) {
        this.checkList.add(checkStr);
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }



    public String getCheckStr() {
        return CheckStr;
    }

    public void setCheckStr(String checkStr) {
        CheckStr = checkStr;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) throws IOException {
        this.response = response;
    }

    public String getExtra_parameter() {
        return extra_parameter;
    }

    public void setExtra_parameter(String extra_parameter) {
        this.extra_parameter = extra_parameter;
    }

}
