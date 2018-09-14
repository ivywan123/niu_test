package Model;

import java.util.ArrayList;

/**
 *
 */
public class CaseVO {
    private String Name;//用例结果
    private String Level;//用例等级
    private int stepCount;
    private Boolean result=true; //用例结果
    private ArrayList<StepVO> stepList=new ArrayList(); //步骤对象数组

    public ArrayList<StepVO> getStepList() {
        return stepList;
    }

    public void setStepList(StepVO step) {
        this.stepList.add(step);
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
