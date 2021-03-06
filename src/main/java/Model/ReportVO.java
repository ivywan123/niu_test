package Model;

import java.util.HashMap;

/**
 *
 */
public class ReportVO {
    private  int Pass;
    private  int Fail;
    private  int Sum;
    private  String Passrate;
    private  String mail;
    private StringBuffer MailStr;
    private HashMap<String,String> ResultData=new HashMap<String,String>();;

    public int getPass() {
        return Pass;
    }

    public void setPass(int pass) {
        Pass = pass;
    }

    public int getFail() {
        return Fail;
    }

    public void setFail(int fail) {
        Fail = fail;
    }

    public int getSum() {
        return Sum;
    }

    public void setSum(int sum) {
        Sum = sum;
    }

    public String getPassrate() {
        return Passrate;
    }

    public void setPassrate(String passrate) {
        Passrate = passrate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public StringBuffer getMailStr() {
        return MailStr;
    }

    public void setMailStr(StringBuffer mailStr) {
        MailStr = mailStr;
    }

    public HashMap<String, String> getResultData() {
        return ResultData;
    }

    public void setResultData(HashMap<String, String> resultData) {
        ResultData = resultData;
    }
}
