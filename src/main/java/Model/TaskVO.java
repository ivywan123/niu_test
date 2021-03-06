package Model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 */
public class TaskVO {
    private int  Pass;
    private int  Fail;
    private int  Skip;
    private int  Sum;
    private String PassRate;
    private  String runTime;

    private ArrayList<ModuleVO> report =new ArrayList<>() ;


    public void TaskVO(int pass,int fail,ModuleVO module) {
        setFail(fail);setPass(pass);setReport(module);setSum();setPassRate();
    }

    public void ReportMap(ConfigVO config) {
        config.setMap("casetal",String.valueOf(Sum));
        config.setMap("casepass",String.valueOf(Pass));
        config.setMap("caseerror",String.valueOf(Fail));
        config.setMap("passinfo",String.valueOf(PassRate));
        config.setMap("caseskip",String.valueOf(Skip));
        config.setMap("Environment", config.getEnvironment());
        config.setMap("Urlinfo","www.baidu.com" );
        config.setMap("Taltime", runTime);
        config.setMap("<planName>", "自动化测试"+config.getEnvironment());
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime+"s";
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

    public int getSkip() {
        return Skip;
    }

    public void setSkip(int skip) {
        Skip = skip;
    }

    public int getSum() {
        setSum();
        return Sum;
    }

    public void setSum() {
        Sum = Pass+Fail;
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

    public ArrayList<ModuleVO> getReport() {
        return report;
    }

    public void setReport(ModuleVO module) {
        this.report.add(module);
    }
}
