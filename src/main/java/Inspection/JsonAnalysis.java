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
		String jsonStr="{\"nick\":\"MTkwMDAwMDExNzY\\u003d\",\"uid\":\"53967477912725\",\"userInfo\":\"AbQA3kOM+0thtRi5OecOp/2GrxS6LMTSZiRCrq1jIw2XNJrkwBFHNJ+bHhBt9HzFuZGpyiKkRQBB2mSvCyr7Y1hqs0C/Ua1HySjMiK/jrhXirtPv7FH3VGC7UuAdbmbWlVkNhhB606OkSLw6EBdFpvCL4ylKoWbPwoq8+00BEyCTBXwZqGhnxJ+Ths1pA7TcFvWRZx3CFvq6lkVTiuhSw7sM2JYzanBCYWUzn3MhE3NYWeAZ41p3Jel7YA\\u003d\\u003d\",\"times\":\"0\",\"bind\":\"false\",\"userLever\":\"1\",\"success\":true,\"verify\":\"6d6d384cf38546b917bb5bccc2abde59\",\"token\":\"eyJhbGciOiJIUzI1NiJ9.eyJuaWNrIjoiTVRrd01EQXdNREV4TnpZPSIsInVpZCI6IjUzOTY3NDc3OTEyNzI1IiwidXNlckluZm8iOiJBYlFBM2tPTSswdGh0Umk1T2VjT3AvMkdyeFM2TE1UU1ppUkNycTFqSXcyWE5Kcmt3QkZITkorYkhoQnQ5SHpGdVpHcHlpS2tSUUJCMm1TdkN5cjdZMWhxczBDL1VhMUh5U2pNaUsvanJoWGlydFB2N0ZIM1ZHQzdVdUFkYm1iV2xWa05oaEI2MDZPa1NMdzZFQmRGcHZDTDR5bEtvV2JQd29xOCswMEJFeUNUQlh3WnFHaG54SitUaHMxcEE3VGNGdldSWngzQ0Z2cTZsa1ZUaXVoU3c3c00ySll6YW5CQ1lXVXpuM01oRTNOWVdlQVo0MXAzSmVsN1lBPT0iLCJ1c2VyTGV2ZXIiOiIxIn0.Zw04FtGvRvMzOLCz37GDjIIu76r8g1--DTkcioK1FGg\"}";
		JsonAnalysis json=new   JsonAnalysis();
		String expression1="$.success";//获取所有节点下的price
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
