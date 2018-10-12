package Common;





import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class Public {
    private static final Logger log = LoggerFactory.getLogger(Public.class);


    /**
     * 读取html内容
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String html(String fileName) throws IOException {
        String a = null;
        String text = null;
        String filePath =fileName;
        CharBuffer cbuf = null;
        File file = new File(filePath);
        try{
            InputStreamReader fReader=new InputStreamReader(new FileInputStream(file),"utf-8");
            cbuf = CharBuffer.allocate((int) file.length());
            fReader.read(cbuf);
            text = new String(cbuf.array());
            return text;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;


    }

    /**
     * 计算字符串中某字符出现次数
     * @param str
     * @param key
     * @return
     */
    public static int  getSubString(String str,String key) {
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(key, index)) != -1) {
            index = index + key.length();
            count++;
        }
        return count;
    }



    /**
     *错误日志保存
     * @param e
     */
    public static void saveELog(Exception e) {
        try {
            File file = new File( Action.path+"Log" + File.separator + new Action().time("yyyyMMddHHmss")  + ".txt" );
            PrintWriter writer = null;
            FileWriter fileWrite = new FileWriter(file, true);
            writer = new PrintWriter(fileWrite);
            writer.append(System.getProperty("line.separator")
                    + new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss")
                    .format(new Date()) + "__" );
            writer.append(System.getProperty("line.separator"));
            writer.append("      *************************" + e.toString()
                    + "*************************");
            writer.append(System.getProperty("line.separator"));
            e.printStackTrace(writer);
            writer.flush();
            writer.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }


    /**
     * 处理字符串格式
     * @param str
     * @return
     */
    public static String replaceStr(String str) {
        String dest = "";
        if (str!=null) {
//            str=str.trim();  //去掉左右两边的空格
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str.trim());
//            dest = m.replaceAll("");  //sql中有很多空格，不能去掉所有的空格

            dest = str;
        }
        return dest;
    }

    /**
     *
     * @param log
     * @throws IOException
     */
    public static  void logLayout( String log) throws IOException{
        System.out.println("==========================================================");
        System.out.println(new Action().time("[yyyy-MM-dd HH:mm:ss]-| ")+log);
        System.out.println("==========================================================");
    }

    /**
     * 普标题打印
     * 输出log日志
     * **/
    public static  void log( String log) throws IOException{
        System.out.println(log);
        try {
            File file = new File( Action.path+"Log" + File.separator + new Action().time("yyyyMMddHHmss")  + ".txt" );
            PrintWriter writer = null;
            FileWriter fileWrite = new FileWriter(file, true);
            writer = new PrintWriter(fileWrite);
            writer.append("\n");
            writer.append(System.getProperty("line.separator")
                    + new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss")
                    .format(new Date()) + "__" );
            writer.append(log);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static  void main( String []args) throws IOException{
        System.out.println(html(  Action.path+"mail/mail.txt" ));
    }


}
