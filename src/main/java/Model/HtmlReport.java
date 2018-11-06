package Model;

import java.util.ArrayList;

/**
 *
 */
public class HtmlReport {

    public  StringBuffer Tab=new StringBuffer();
    public  StringBuffer Module=new StringBuffer();
    public  StringBuffer Case=new StringBuffer();
    public  StringBuffer Step=new StringBuffer();
    public  StringBuffer Data=new StringBuffer();



    public StringBuffer getModule(ModuleVO module) {
        setModule( module);
        return Module;
    }

    public void setModule(ModuleVO module) {
        String ModuleStr="<div class=\"tab-content J_tabContent\" style=\"width:96%;overflow:hidden;margin:0 2%\"><div class=\"alert alert-warning\"><strong>【运行Case总数】:<span>sum</span> 【通过】: <span>TalPass</span> 【失败】: <span>TalFail</span> 【通过率】: <span>PassRate</span></strong></div>";
        StringBuffer Buffer=new StringBuffer();
        ModuleStr = ModuleStr.replace("sum",String.valueOf(module.getSum()));
        ModuleStr = ModuleStr.replace("TalPass",String.valueOf(module.getPass()));
        ModuleStr = ModuleStr.replace("TalFail",String.valueOf(module.getFail()));
        ModuleStr = ModuleStr.replace("PassRate",String.valueOf(module.getPassRate()));
        Buffer.append(ModuleStr);
        for (int i=0;i<module.getCaseList().size();i++) {
            Buffer.append(getCase( module.getCaseList().get(i))+ "</div>");
        }
        Buffer.append("</div>");
        Module.append(Buffer);
    }

    public StringBuffer getCase(CaseVO cs) {
        setCase(cs);
        return Case;
    }

    public void setCase(CaseVO cs) {
        String CaseStr="<div class=\"case\"><div class=\"J_caseBox\"><div class=\"panel panel-info\"><div class=\"panel-heading\">ID: CaseId<span style=\"margin-left:10px\">用例名称: CaseName</span></div><div class=\"panel-body\">[--]&lt;&lt;【执行结果】:CaseResult</div></div>";
        StringBuffer Buffer=new StringBuffer();
        CaseStr = CaseStr.replace("CaseName",cs.getName());
        if(cs.getResult()==false) {
            CaseStr = CaseStr.replace("CaseResult", "FAIL");
        }else{
            CaseStr = CaseStr.replace("CaseResult", "PASS");
        }
        Buffer.append(CaseStr);
        for (int i=0;i<cs.getStepList().size();i++) {
            Buffer.append(getStep( cs.getStepList().get(i),i,cs.getStepList().get(i).getCheckList()));
        }
        Buffer.append("</div>");
        Case.setLength(0);
        Case.append(Buffer);


    }

    public StringBuffer getStep(StepVO step,int  i,ArrayList<String > checkList) {
        setStep(step,i,checkList);
        return Step;
    }

    public void setStep(StepVO step, int i, ArrayList<String > checkList) {
        String StepStr="<div class=\"J_stepBox\" style=\"padding:0 1%\"><div class=\"panel panel-success\"><div class=\"panel-heading\">1/stepName</div><div class=\"panel-body\"><div class=\"table-responsive\"><table class=\"table\"><thead><tr><th></th><th></th></tr></thead><tbody><tr><td nowrap>执行结果</td><td>[--]&lt;&lt;【执行结果】:StepResult</td></tr><tr><td nowrap>执行时间</td><td>StepTimems</td></tr><tr><td nowrap>请求地址</td><td>URL</td></tr><tr><td nowrap>请求方法</td><td>method</td></tr>checkList</tbody></table><div class=\"panel-group\"><div class=\"panel panel-default J_panel\"><div class=\"panel-heading\"><h4 class=\"panel-title\"><a class=\"J_openCol\" href=\"javascript:;\">请求数据</a></h4></div><div class=\"panel-collapse collapse J_panelCol\"><div class=\"panel-body\"><div class=\"custom-pre\">requestStr</div></div></div></div><div class=\"panel panel-default J_panel\"><div class=\"panel-heading\"><h4 class=\"panel-title\"><a class=\"J_openCol\" href=\"javascript:;\">返回数据</a></h4></div><div class=\"panel-collapse collapse J_panelCol\"><div class=\"panel-body\"><div class=\"J_stepResponseJSON hide\">JsonStr</div><pre>                                        <div class=\"json-view-custom J_jsonView\"></div> </pre></div></div></div></div></div></div></div></div>";
        StringBuffer checkBuffer=new StringBuffer();
        StepStr = StepStr.replace("stepName",i+"-"+step.getName());
        if(step.getResult()==false) {
            StepStr = StepStr.replace("StepResult", "FAIL");
        }else{
            StepStr = StepStr.replace("StepResult", "PASS");
        }
        StepStr = StepStr.replace("StepTimems",step.getName());
        StepStr = StepStr.replace("URL",step.getUrl());
        StepStr = StepStr.replace("method",step.getMethod());
        StepStr = StepStr.replace("requestStr",step.getUrl()+step.getParameter());
        System.out.println(step.getName());
        System.out.println(step.getResponse());
        if(step.getResponse() != null) {
            StepStr = StepStr.replace("JsonStr", step.getResponse());
        }
        for(int k=0;k<checkList.size();k++) {
            checkBuffer.append("<tr> <td nowrap>检查步骤-"+k+"</td><td>"+checkList.get(k)+"</td></tr>");
        }
        String StepAtr= StepStr.replace("checkList", checkBuffer.toString());
        Step.setLength(0);
        Step.append(StepAtr);
    }


    public StringBuffer getTab() {
        return Tab;
    }

    public void setTab(String tab,int index) {
        String TabStr =new  String();
        if(index==0) {
            TabStr = "<li class=\"active\"><a href=\"javascript:;\">" + tab + "</a></li>";
        }else{
            TabStr = "<li><a href=\"javascript:;\">" + tab + "</a></li>";

        }
        Tab.append(TabStr);
    }

    public StringBuffer getData(String environment,String PassRate,int Fail,String runtime,int Pass,String CreateUers,String taskName) {
        setData(environment,PassRate,Fail,runtime,Pass,CreateUers,taskName);
        return Data;
    }

    public void setData(String environment,String PassRate,int Fail,String runtime,int Pass,String CreateUers,String taskName) {
        Data.append("{\"tittle\":{\"id\":4406,\"environment\":\""+environment+"\",\"PassRate\":\""+PassRate+"\",\"state\":1,\"Fail\":"+Fail+",\"runtime\":\""+runtime+"\",\"Pass\":"+Pass+",\"CreateUers\":\""+CreateUers+"\",\"taskName\":\""+taskName+"\"}}"
        );
    }
}
