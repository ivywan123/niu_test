package Fetch;

import Common.Public;
import Configuration.ReadConfig;
import Model.CaseVO;
import Model.StepVO;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;


/**
 *
 */
public class ReadExecl {
public  static String smple=null;
    private static final Logger logger = LoggerFactory.getLogger(ReadExecl.class);
    public  ArrayList<CaseVO> read(String Template) throws Exception {
        Workbook wb = null;
        Public.log("【数据装箱】： FileName:"+ReadConfig.readconfig("Templatepath")+Template);
        Public.logs("【数据装箱】： FileName:"+ReadConfig.readconfig("Templatepath")+Template);
        File FileSytem =new File(ReadConfig.readconfig("Templatepath")+Template);
        wb = Workbook.getWorkbook(FileSytem);
        ArrayList<CaseVO> list = new ArrayList<CaseVO>();
        if (wb != null) {
            Sheet[] sheets = wb.getSheets();
            if (sheets != null && sheets.length != 0) {
                for (int i=0;i<sheets.length;i++) {
                    int rows = sheets[i].getRows();
                    for (int j=1;j<rows;j++) {
                        try{
                        Cell[] cells = sheets[i].getRow(j);
                        if(cells[3].toString().isEmpty()==false&&cells[2].toString().isEmpty()==false){
                            String CaseName=cells[0].getContents();
                            if(CaseName!=null&&CaseName.isEmpty()==false&&CaseName.length()>1){
                                CaseVO cs=new  CaseVO();
                                cs.setName(CaseName);
                                cs.setLevel(cells[1].getContents());
                                cs.setStepList(read(cells));
                                for(int k=j+1;k<rows;k++) {
                                    Cell[] cell = sheets[i].getRow(k);
                                    if(cell[0].getContents()==null||cell[0].getContents().length()<1) {
                                        cs.setStepList(read(cell));
                                    }else{
                                        break;
                                    }
                                }
                                list.add(cs);
                               }
                        }
                    }catch ( Exception eo) {
                            eo.printStackTrace();
                        }
                 }
                }
            }

        }

        wb.close();
        return list;
    }



    public  StepVO read(Cell[] cells) throws Exception {
        StepVO step = new StepVO();
        try{
            if (cells != null && cells.length != 0) {
               step.setName(cells[3].getContents());
               step.setMethod(cells[2].getContents());
               step.setUrl(cells[4].getContents());
               step.setParameter(cells[5].getContents());
               step.setTransfer(cells[6].getContents());
               step.setCheckStr(cells[7].getContents());
           }}catch(Exception e){
            e.printStackTrace();
        }
        return step;
    }

}
