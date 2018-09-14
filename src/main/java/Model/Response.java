package Model;

/**
 *
 */
public class Response {
    private  String  ResponseTime;
    private  String  ResponseContent ;
    private  String  ResponseCode ;

    public String getResponseTime() {
        return ResponseTime;
    }

    public void setResponseTime(String responseTime) {
        ResponseTime = responseTime;
    }

    public String getResponseContent() {
        return ResponseContent;
    }

    public void setResponseContent(String responseContent) {
        ResponseContent = responseContent;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }
}
