package Inspection;

import java.math.BigDecimal;

/**
 * Created by admin on 2018/10/16.
 */
public class CalculateNum {
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
//        System.out.println(str);
//        System.out.println("1111111111111111111111111111");
//        BigDecimal decimal = new BigDecimal(str);
//        System.out.println(decimal);
//        BigDecimal setScale = decimal.setScale(2);
//        return setScale;
        return new BigDecimal(str);
    }
    public static void main(String[] args) throws Exception {
        CalculateNum cn=new CalculateNum();
        String str = "98100.36-6000%6-(6000*0.00198)";
        BigDecimal s = cn.cal(str);
        System.out.println(s);
        System.out.println(s.setScale(2));
        System.out.println(cn.cal(str).setScale(2));
    }

}

