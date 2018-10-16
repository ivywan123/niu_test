package Inspection;

import Common.Public;
import Model.CaseVO;
import Model.StepVO;
import Parameter.ParametersFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.io.IOException;

/**
 *
 */
public class Inspectoscope {
//    private static final Logger log = org.apache.log4j.spi.LoggerFactory
//            LoggerFactory.getLogger(Inspectoscope.class);
    static Logger log= LogManager.getLogger(Inspectoscope.class);
    public String Inspectoscope(CaseVO cs,StepVO step) throws IOException {
        ParametersFactory Parameters =new  ParametersFactory();
        String CheckStr[]=step.getCheckStr().split(",");
        String response =step.getResponse();
        Calculate calculate=new  Calculate();
        for(int i=0;i<CheckStr.length;i++){
          String CheckArrStr=Parameters.Extraction(CheckStr[i]);
          if(calculate.calculate(CheckArrStr,response)==false){
//              Public.log("【Fail 校验失败】 步骤名："+step.getName()+"检查点 : "+CheckArrStr );
              log.error("【Fail 校验失败】 步骤名："+step.getName()+"检查点 : "+CheckArrStr);  //error时会显示为红色
              step.setCheckList("【Fail 校验失败】 步骤名："+step.getName()+"检查点 : "+CheckArrStr );
              cs.setResult(false);
              step.setResult(false);
          }else{
//              Public.log("【Pass 校验成功】 步骤名：" + step.getName() + "检查点 : " + CheckArrStr);
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
