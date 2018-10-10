package ControlCenter;

import Common.GetResponse;
import Common.Public;
import Inspection.Inspectoscope;
import Model.CaseVO;
import Model.ConfigVO;
import Model.ModuleVO;
import Model.StepVO;
import Parameter.ParametersFactory;

import java.util.ArrayList;

/**
 *
 */
public class Implement {


    /**
     * 分解模块
     * @param module
     */
    public void ResolveModule(ConfigVO config, ModuleVO module) throws Exception {
        ArrayList<CaseVO> CaseList=module.getCaseList();
        for(int i=0;i<CaseList.size();i++){
            String caseName=CaseList.get(i).getName();
            String caseLevel=CaseList.get(i).getLevel();
            Public.log("【用例名称】:"+caseName+"\n【用例等级】:"+caseLevel);
            Public.logs("【用例名称】:"+caseName+"\n【用例等级】:"+caseLevel);
            ResolveCase(config,CaseList.get(i));
            if(CaseList.get(i).getResult()){
                module.setPass(1);
            }else{
                module.setFail(1);
            }
        }
    }
    /**
     * 分解用例
     * @param config
     */
    public void ResolveCase(ConfigVO config, CaseVO cs) throws Exception {
        String caseLevel=cs.getLevel().toUpperCase();
        ArrayList<StepVO> stepList= cs.getStepList();
        if(config.getRunLevel().contains(caseLevel)){//判断
            ResolveStep(config,cs,stepList);
            cs.setStepCount(stepList.size());
        }

    }


    /**
     * 执行步骤
     * @param cs
     * @param stepList
     * @throws Exception
     */
    public void ResolveStep(ConfigVO config,CaseVO cs, ArrayList<StepVO> stepList) throws Exception {

        for(int i=0;i<stepList.size();i++){
            GetResponse response=new GetResponse();
            Inspectoscope check=new Inspectoscope();
            String StepName=stepList.get(i).getName();
            System.out.println(stepList.get(i).getUrl());
            Public.log("【步骤名称】:"+StepName);
            Public.logs("【步骤名称】:"+StepName);
            stepList.get(i).setResponse(response.GetResponse(config,stepList.get(i)));
            //取参数和做断言换执行顺序，有些接口的断言要根据从data中取到的参数做计算
            if(stepList.get(i).getTransfer()!=null){  //判断是否需要提取参数
                ParametersFactory.GetParameter(stepList.get(i));
            }
            check.Inspectoscope(cs, stepList.get(i));
        }
    }


}
