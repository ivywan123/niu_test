package Parameter;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
public class Global {
    public static final Map<String, Integer> idRegion = new HashMap<String, Integer>();
    /**
     * 获取指定长度随机简体中文
     * @param length String
     * @return String
     */
    public  String RandomStr(String length) {
        String ret=new String ();
        int len=Integer.parseInt(length);
        for(int i=0;i<len;i++){
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try{
                str = new String(b, "GBk"); //转成中文
            }
            catch (UnsupportedEncodingException ex)
            {
                ex.printStackTrace();
            }
            ret+=str;
        }
        return ret;
    }


    /**
     * 获取随机数
     * @param ArrStr String
     * @return String
     */
    public  String Random(String ArrStr) {
        int	sum	 = Integer.parseInt( ArrStr );
        int	like = (int) (Math.random() * sum);
        return String.valueOf(like);
    }


    /**
     * 获取当前时间
     * 传入 yyyy:MM:dd:HH:mm:ss
     * @param TimeFormat
     * @return
     */
    public static String Time(String TimeFormat) {
        SimpleDateFormat	df	=null;
        if(TimeFormat==null){
            df	= new SimpleDateFormat( "yyyyMMddHHmmss" );
        }else{
            df	= new SimpleDateFormat(TimeFormat );
        }
        String	time	= df.format( new Date() );
        return(time);
    }

    /**
     *
      * @param start
     * @param end
     * @return
     */
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }


    /**
    * 获取随机手机号码，默认使用138号段
     */
    public String getTelephone(){
        String[] telFirst ="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
        int index =  getNum(0,telFirst.length-1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1,888)+10000).substring(1);
        String third = String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    /**
     * 随机生成银行卡（62开头，16位）
     * @param length 14，银行卡开头为62
     */
    public String getCardNO(int length){
        char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        buffer.append("62");
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(10)]);
        }
        return buffer.toString();
    }

    /**
     * 随机生成姓名，身份证
     * 随机生成银行卡（62开头，16位）
     */
    public String getChineseName() {
        String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
        String girl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
        String boy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";

        String name_sex = "";
        int index=getNum(0, firstName.length()-1);
        String first=firstName.substring(index, index+1);
        int sex=getNum(0,1);
        String str=boy;
        int length=boy.length();
        if(sex==0){
            str=girl;
            length=girl.length();
            name_sex = "女";
        }else {
            name_sex="男";
        }
        index=getNum(0,length-1);
        String second=str.substring(index, index+1);
        int hasThird=getNum(0,1);
        String third="";
        if(hasThird==1){
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return first+second+third;
    }


    /**
     * 生成身份证号码
     * @return
     */

    public String getRandomidentitycard() {
        {
            Global.idRegion.put("北京市", 110000);
            Global.idRegion.put("市辖区", 110100);
            Global.idRegion.put("东城区", 110101);
            Global.idRegion.put("西城区", 110102);
            Global.idRegion.put("崇文区", 110103);
            Global.idRegion.put("宣武区", 110104);
            Global.idRegion.put("朝阳区", 110105);
            Global.idRegion.put("丰台区", 110106);
            Global.idRegion.put("石景山区", 110107);
            Global.idRegion.put("海淀区", 110108);
            Global.idRegion.put("门头沟区", 110109);
            Global.idRegion.put("房山区", 110111);
            Global.idRegion.put("通州区", 110112);
            Global.idRegion.put("顺义区", 110113);
            Global.idRegion.put("昌平区", 110114);
            Global.idRegion.put("大兴区", 110115);
            Global.idRegion.put("怀柔区", 110116);
            Global.idRegion.put("平谷区", 110117);
            Global.idRegion.put("辽中县", 110200);
            Global.idRegion.put("密云县", 110228);
            Global.idRegion.put("延庆县", 110229);

        }
        StringBuilder generater = new StringBuilder();
        generater.append(this.getRandomArea());
        generater.append(this.getRandomBirthday());
        generater.append(this.getRandomNumber());
        generater.append(this.getTestCode(generater.toString().toCharArray()));
        return generater.toString();
    }

    /**
     * 随机获取地区
     *
     * @return
     */
    public int getRandomArea() {
        int index = (int) (Math.random() * Global.idRegion.size());
        Collection<Integer> values = Global.idRegion.values();
        Iterator<Integer> it = values.iterator();
        int i = 0;
        int code = 0;
        while (i < index && it.hasNext()) {
            i++;
            code = it.next();
        }
        return code;
    }

    /**
     * 随机获取出生日期
     *
     * @return
     */
    public String getRandomBirthday() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, (int) (Math.random() * 60) + 1950);
        birthday.set(Calendar.MONTH, (int) (Math.random() * 12));
        birthday.set(Calendar.DATE, (int) (Math.random() * 31));

        StringBuilder builder = new StringBuilder();
        builder.append(birthday.get(Calendar.YEAR));
        long month = birthday.get(Calendar.MONTH) + 1;
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month);
        long date = birthday.get(Calendar.DATE);
        if (date < 10) {
            builder.append("0");
        }
        builder.append(date);
        return builder.toString();
    }



    /**
     * 获取效验码
     * @param chars
     * @return
     */
    public char getTestCode(char[] chars) {
        if (chars.length < 17) {
            return ' ';
        }
        int[] c = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        char[] r = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(chars[i] + "");
        }
        for (int i = 0; i < n.length; i++) {
            result += c[i] * n[i];
        }
        return r[result % 11];
    }

    /**
     *获取3位随机数
     * @return
     */
    public String getRandomNumber() {
        int code = (int) (Math.random() * 1000);
        if (code < 10) {
            return "00" + code;
        } else if (code < 100) {
            return "0" + code;
        } else {
            return "" + code;
        }
    }
}
