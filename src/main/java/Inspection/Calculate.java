package Inspection;

import Common.Public;
import Parameter.ParametersFactory;

import java.math.BigDecimal;

/**
*
*/
public class Calculate {

public static void main(String[] args) {
    Calculate  ca=new Calculate();
    System.out.println(ca.calculate("$.cnt>=1","{\"cnt\":\"1\"}"));

}

public Boolean calculate(String ArrStrA, String jsonStr) {
    Boolean ispassed = true;
    try {
        if (ArrStrA.contains(">")||ArrStrA.contains("<")||ArrStrA.contains("=")||ArrStrA.contains(":")) {

        //>
        if (ArrStrA.contains(">") && ArrStrA.contains(">=") == false) {
            String Arr = ArrStrA.substring(0, ArrStrA.indexOf(">"));
            String Arr1 = ArrStrA.substring(ArrStrA.indexOf(">") + 1, ArrStrA.length());

            double sortOne = Double.parseDouble(StrGetLS(Arr, jsonStr).trim());
            double sortTne = Double.parseDouble(StrGetLS(Arr1, jsonStr).trim());
            System.out.println("sortOne:>" + sortOne + "  sortTne:" + sortTne);

            if (sortOne > sortTne) {
                ispassed = true;
            } else {
                ispassed = false;
            }
        }

        //>=
        if (ArrStrA.contains(">") && ArrStrA.contains(">=") == true) {
                String Arr = ArrStrA.substring(0, ArrStrA.indexOf(">="));
                String Arr1 = ArrStrA.substring(ArrStrA.indexOf(">=") + 2, ArrStrA.length());

                double sortOne = Double.parseDouble(StrGetLS(Arr, jsonStr).trim());
                double sortTne = Double.parseDouble(StrGetLS(Arr1, jsonStr).trim());
                System.out.println(sortOne + " >= "+ sortTne);
                if (sortOne > sortTne || sortOne == sortTne) {
                    ispassed = true;
                } else {
                    ispassed = false;
                }
            }

        //<
        if (ArrStrA.contains("<")  && ArrStrA.contains("<=") == false) {
            String Arr = ArrStrA.substring(0, ArrStrA.indexOf("<"));
            String Arr1 = ArrStrA.substring(ArrStrA.indexOf("<") + 1, ArrStrA.length());
            double sortOne = Double.parseDouble(StrGetLS(Arr, jsonStr).trim());
            double sortTne = Double.parseDouble(StrGetLS(Arr1, jsonStr).trim());
            System.out.println("sortOne:<"+sortOne+"  sortTne:"+sortTne);
            if (sortOne < sortTne) {
                ispassed = true;
            } else {
                ispassed = false;
            }
        }

        //<=
        if (ArrStrA.contains("<") && ArrStrA.contains("<=") == true) {
            String Arr = ArrStrA.substring(0, ArrStrA.indexOf("<="));
            String Arr1 = ArrStrA.substring(ArrStrA.indexOf("<=") + 2, ArrStrA.length());
            double sortOne = Double.parseDouble(StrGetLS(Arr, jsonStr).trim());
            double sortTne = Double.parseDouble(StrGetLS(Arr1, jsonStr).trim());
            System.out.println("sortOne:<="+sortOne+"  sortTne:"+sortTne);
            if (sortOne < sortTne || sortOne == sortTne) {
                ispassed = true;
            } else {
                ispassed = false;
            }
        }

        if (ArrStrA.contains("=") && ArrStrA.contains("==") == false) {
            String Arr = ArrStrA.substring(0, ArrStrA.indexOf("="));
            String Arr1 = ArrStrA.substring(ArrStrA.indexOf("=") + 1, ArrStrA.length());

            //StrGetLS改进，包含$，用jsonpath取值
            //否则，调用四则运算的方法
            double sortOne  = Double.parseDouble(StrGetLS(Arr, jsonStr).trim());
            double sortTne = Double.parseDouble(StrGetLS(Arr1, jsonStr).trim());
            if (sortOne == sortTne) {
                ispassed = true;
            } else {
                ispassed = false;
            }
        }

        if (ArrStrA.contains(":")) {
            if (Public.getSubString(ArrStrA, ":") < 2 && ArrStrA.contains("\"") == false) {
                String Arr = ArrStrA.substring(0, ArrStrA.indexOf(":"));
                String Arr1 = ArrStrA.substring(ArrStrA.indexOf(":") + 1, ArrStrA.length());
                String sortOne = StrGetLS(Arr, jsonStr).trim();
                String sortTne = StrGetLS(Arr1, jsonStr);
                if (sortOne.equals(sortTne)) {
                    ispassed = true;
                } else {
                    ispassed = false;
                }
            }
        }}else{
            if(jsonStr.contains(ArrStrA.trim())){
                ispassed = true;
            } else {
                ispassed = false;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        ispassed = false;

    }

    return  ispassed;
}
public String VARTOOLS(String ArrStrA, ParametersFactory polly) throws Exception {
    String CheckStr = new String();
    if (ArrStrA.contains("${") || ArrStrA.contains("Var")) {
        ArrStrA = polly.Extraction(ArrStrA);
    }
    CheckStr = ArrStrA;
    return CheckStr;
}


public String StrGetLS(String ArrStrA, String JsonStr) throws Exception {
    String boolString = ArrStrA;
    if (ArrStrA.contains("$")) {
        boolString=getvar( ArrStrA,  JsonStr);
    }else{
        //调用四则运算方法
        try{
            BigDecimal result = cal(ArrStrA).setScale(2);
            boolString=ArrStrA=String.valueOf(result);
        }catch(Exception e){
        }
    }
    return boolString;
}


    /**
     *节点值提取
     * @param ArrStrA
     * @param JsonStr
     * @return
     * @throws Exception
     */
    public String getvar(String ArrStrA, String JsonStr) throws Exception {
    String boolString = ArrStrA;
    JsonAnalysis json =new  JsonAnalysis();
    while(ArrStrA.contains("$")) {
        if (ArrStrA.contains("$.")) {
            boolString=ArrStrA=json.JsonPath(JsonStr, ArrStrA);
            if(boolString.contains("\"")){
                boolString=boolString.replace("\"","");
            }
        }
        if(ArrStrA.contains("$")==false){
            break;
        }
    }
    return boolString;
}

    /**
     * 四则运算方法
     */
    public BigDecimal cal(String str) {
        if (str == null) {
            return null;
        }
        String fuhao = "";
        int index = 0;
        str = str.replaceAll("--", "+");// 等价替换;
        str = str.replaceAll(" ", "");// 去掉空格

        fuhao = "(";
        index = str.lastIndexOf(fuhao);
        if (index >= 0) {
            int rightIndex = str.indexOf(")", index);
            String left = str.substring(0, index);
            String right = "";
            if (rightIndex + 1 < str.length()) {
                right = str.substring(rightIndex + 1);
            }
            BigDecimal middle = cal(str.substring(index + 1, rightIndex));
            return cal(left + middle + right);
        }

        fuhao = "+";
        index = str.lastIndexOf(fuhao);
        if (index > 0) {
            BigDecimal left = cal(str.substring(0, index));
            BigDecimal right = cal(str.substring(index + 1));
            return left.add(right);
        }

        fuhao = "-";
        index = str.lastIndexOf(fuhao);
        if (index == 0) { // 负数处理
            BigDecimal result = cal(str.substring(index + 1));
            if (result.compareTo(new BigDecimal("0")) == -1) { // 小于0
                return result.abs(); // 绝对值
            } else {
                return result.negate(); // 相反数
            }
        } else if (index > 0) {
            BigDecimal left = cal(str.substring(0, index));
            BigDecimal right = cal(str.substring(index + 1));
            return left.subtract(right);
        }

        fuhao = "*";
        index = str.lastIndexOf(fuhao);
        if (index > 0) {
            BigDecimal left = cal(str.substring(0, index));
            BigDecimal right = cal(str.substring(index + 1));
            return left.multiply(right);
        }

        fuhao = "%";
        index = str.lastIndexOf(fuhao);
        if (index > 0) {
            BigDecimal left = cal(str.substring(0, index));
            BigDecimal right = cal(str.substring(index + 1));
            return left.divide(right);
        }
        return new BigDecimal(str);
    }

}
