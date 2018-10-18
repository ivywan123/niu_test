package Inspection;

import Common.Public;
import Model.CaseVO;
import Model.StepVO;
import Parameter.ParametersFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

/**
 *
 */
public class Inspectoscope {
    private static final Logger log = LoggerFactory.getLogger(Inspectoscope.class);

    public String Inspectoscope(CaseVO cs,StepVO step) throws IOException {
        ParametersFactory Parameters =new  ParametersFactory();
        String CheckStr[]=step.getCheckStr().split(",");
        String response =step.getResponse();
        Calculate calculate=new  Calculate();
        for(int i=0;i<CheckStr.length;i++){
          String CheckArrStr=Parameters.Extraction(CheckStr[i]);
          if(calculate.calculate(CheckArrStr,response)==false){
              log.error("【Fail 校验失败】 步骤名："+step.getName()+"检查点 : "+CheckArrStr);  //error时会显示为红色
              step.setCheckList("【Fail 校验失败】 步骤名："+step.getName()+"检查点 : "+CheckArrStr );
              cs.setResult(false);
              step.setResult(false);
          }else{
              log.info("【Pass 校验成功】 步骤名：" + step.getName() + "检查点 : " + CheckArrStr);
              step.setCheckList("【Pass 校验成功】 步骤名："+step.getName()+"检查点 : "+CheckArrStr );
          }

        }
        return  null;
    }



    public String Inspectoscope(String  CheckStr) {
        if(CheckStr.contains("=")){

        }
        return  null;
    }


}
