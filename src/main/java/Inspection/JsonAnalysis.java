package Inspection;

import com.jayway.jsonpath.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;


public class JsonAnalysis {

	private static final Logger logger = LoggerFactory.getLogger(JsonAnalysis.class);
	public static  void  main(String[]args) throws IOException {
//	String jsonStr="{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"Evelyn Waugh\",\"title\":\"Sword of Honour\",\"price\":12.99},{\"category\":\"fiction1\",\"author\":\"Herman Melville\",\"title\":\"Moby Dick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction23\",\"author\":\"32J. R. R. Tolkien\",\"title\":\"The Lord of the Rings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}}}";
//		JsonAnalysis json=new   JsonAnalysis();
//		String expression1="$..price";//获取所有节点下的price
//		String expression2="$..book[1].price";//获取所有书籍里的第二个价格
//		String expression3="$.store.book[?(@.category == 'reference')]";//获取Book category 为reference 的书籍
//		String expression4="$.store.book[?(@.isbn)]";//获取有 isbn 的书籍
//		String expression5="$.store.book[?(@.price > 10)]";//获取Book 价格大于10 的书籍
//		String expression6="$..book[-1:]";//获取最后一本书
//		String expression10="$..book[-2:]";//获取最后两本书
//		String expression7="$.store.book[0].title";//获取第一本书的书名
//		String expression8="$.store.bicycle.color";//获取自动车的颜色
//		String expression9="$.store.book[*].author";//获取所有的书本作者
//
//		logger.info("info1:"+json.JsonPath(jsonStr,expression1));
//		logger.info("info2:"+json.JsonPath(jsonStr,expression2));
//		logger.info("info3:"+json.JsonPath(jsonStr,expression3));
//		logger.info("info4:"+json.JsonPath(jsonStr,expression4));
//		logger.info("info5:"+json.JsonPath(jsonStr,expression5));
//		logger.info("info6:"+json.JsonPath(jsonStr,expression6));
//		logger.info("info7:"+json.JsonPath(jsonStr,expression7));
//		logger.info("info8:"+json.JsonPath(jsonStr,expression8));
//		logger.info("info9:"+json.JsonPath(jsonStr,expression9));
//		logger.info("info9:"+json.JsonPath(jsonStr,expression10));
		String jsonStr="{\"trade\":{\"id\":50180921102016,\"tradeId\":40921009,\"brandId\":52989279149851,\"accountId\":53620168304725,\"fundPoolId\":1,\"phoneNumber\":19000008888,\"status\":1,\"leverCapitalAmount\":1000,\"unLeverCapitalAmount\":0,\"wfPercent\":7000,\"borrowAmount\":6000,\"wfAlertPercent\":6500,\"wfOpenLine\":6300,\"wfDuration\":2,\"accountMgAmt\":11.88,\"wfInterest\":0.00198,\"firstTradeDate\":\"2018-09-25 00:00:00\",\"endTradeDate\":\"2018-09-26 00:00:00\",\"createTime\":\"2018-09-21 15:44:26\",\"productId\":52825118558251,\"profitRate\":100,\"pzMultiple\":6,\"usedDays\":0,\"commissionRatio\":0,\"minCommission\":5.00,\"profitAmount\":0,\"bcsrAmount\":0,\"lstUpdTm\":\"2018-09-21 15:44:26\",\"lstUpdPsn\":53620168304725,\"crtTm\":\"2018-09-21 15:44:26\",\"crtPsn\":53620168304725,\"recStatId\":10101,\"datVer\":1537515866299,\"statusNm\":\"操盘中\",\"whetherOverTime\":false,\"product\":{\"id\":52825118558251,\"brandId\":52989279149851,\"productName\":\"财云-按天操盘\",\"pzType\":303,\"inputType\":true,\"bjAmount\":0.00,\"pzAmount\":0.00,\"pzAmountMin\":2000.00,\"pzAmountMax\":5000000.00,\"multipleOptions\":\"10,9,8,7,6,5\",\"cycleType\":0,\"cycleOptions\":\"2,3,4,5,10,15,20\",\"profitRate\":\"100\",\"alertLineRate\":\"{1:0.5,2:0.5,3:0.5,4:0.5,5:0.5,6:0.5,7:0.5,8:0.5,9:0.5,10:0.5}\",\"openLineRate\":\"{1:0.3,2:0.3,3:0.3,4:0.3,5:0.3,6:0.3,7:0.3,8:0.3,9:0.3,10:0.3}\",\"discount\":0.0,\"showCapital\":\"\",\"limitNumber\":20,\"can\":true,\"enabled\":true,\"trade\":\"0\",\"showInTradeList\":true,\"commissionRatio\":0,\"minCommission\":5.00,\"autoExtension\":false,\"accountMgAmt\":\"{3:0.00135,4:0.00162,5:0.0018,6:0.00198,7:0.00216,8:0.00234,9:0.00252,10:0.0027}\",\"bcsr\":false,\"touchOpenLineAutoCancel\":true,\"quota\":false,\"quotaAmount\":0.00,\"tradeService\":\"\",\"tradeUrl\":\"\",\"maxDuration\":365,\"paidRenewCost\":\"0\",\"functionMenu\":\"11603,11604,11607,11601,11608,11602\",\"firstUseDefault\":true,\"lstUpdTm\":\"2018-09-21 11:51:11\",\"lstUpdPsn\":1,\"crtTm\":\"2018-06-06 10:13:06\",\"crtPsn\":1,\"recStatId\":10101,\"datVer\":1537501870731,\"brand\":{\"brandId\":52989279149851,\"brandNumber\":102,\"brandName\":\"财云\",\"brandCode\":\"caiyun\",\"brandAddress\":\"http://192.168.1.74:7070\",\"lstUpdTm\":\"2018-09-21 14:59:35\",\"lstUpdPsn\":1,\"crtTm\":\"2018-09-03 10:46:06\",\"crtPsn\":1,\"recStatId\":10101,\"datVer\":1537513175145}},\"wftransactionChangeRecordList\":[{\"id\":53751586660150,\"type\":11501,\"bizId\":53751556944525,\"tradeId\":50180921102016,\"changedAmount\":6000.00,\"preLeverCapitalAmount\":1000.00,\"preUnLeverCapitalAmount\":0.00,\"preWfPercent\":7000.00,\"preBorrowAmount\":6000.00,\"preWfAlertPercent\":6500.00,\"preWfOpenLine\":6300.00,\"preAccountMgAmt\":11.88,\"nowLeverCapitalAmount\":1000.00,\"nowUnLeverCapitalAmount\":0.00,\"nowWfPercent\":7000.00,\"nowBorrowAmount\":6000.00,\"nowWfAlertPercent\":6500.00,\"nowWfOpenLine\":6300.00,\"nowAccountMgAmt\":11.88,\"createTime\":\"2018-09-21 15:44:27\",\"updateTime\":\"2018-09-21 15:44:27\"}],\"deductionAmount\":0,\"bizId\":53751556944525,\"isTradeClient\":true,\"buyStockLimit\":\"主板单票100%，创业板总仓位100%\"},\"success\":true}";
		JsonAnalysis json=new   JsonAnalysis();
		String expression1="$.trade.id";//获取所有节点下的price
		System.out.println(json.JsonPath(jsonStr,expression1));
}

public   String  JsonPath(String json,String expression ) throws IOException{
	String GetValue=new String();

	try{
		if(json!=null&&expression!=null) {
			GetValue=JsonPath.read(json, expression).toString();
			if(GetValue.contains("[")){
				GetValue=GetValue.substring(GetValue.indexOf("[")+1,GetValue.length()-1);
			}
		}
		}catch(Exception e){
		e.printStackTrace();
		System.out.println("JsonPath取值失败：");
	}

	return GetValue.replaceAll("\"","");
}

}
