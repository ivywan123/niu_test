package Model;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 */
public class ModuleVO {
    private int  Pass;
    private int  Fail;
    private int  Sum;
    private String PassRate;
    private String Name;
    private String fileName;
    private String doTime;
    private String stepCount;
    private String result;
    private ArrayList<CaseVO> CaseList=new ArrayList();

    public ArrayList<CaseVO> getCaseList() {
        return CaseList;
    }

    public void setCaseList(ArrayList<CaseVO> caseList) {
        CaseList = caseList;
    }

    public int getPass() {
        return Pass;
    }

    public void setPass(int pass) {
        Pass += pass;
    }

    public int getFail() {
        return Fail;
    }

    public void setFail(int fail) {
        Fail += fail;
    }

    public int getSum() {
        setSum();
        return Sum;
    }

    public void setSum() {
        Sum = Fail+Pass;
    }

    public String getPassRate() {
        setPassRate();
        return PassRate;
    }

    public void setPassRate() {
        double str = (double) Pass / (Pass + Fail);
        DecimalFormat df = new DecimalFormat("0.00%");
        String r = df.format(str);
        PassRate = r;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDoTime() {
        return doTime;
    }

    public void setDoTime(long doTime) {
        this.doTime = doTime+"s";
    }

    public String getStepCount() {
        return stepCount;
    }

    public void setStepCount(String stepCount) {
        this.stepCount = stepCount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
