package listeners;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.*;

public class AutomationListener implements ITestListener, ISuiteListener {

    public Response postMessage(String status, String log){
        JSONObject body = new JSONObject();
        body.put("test_status", status)
                .put("test_log",log);


        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(body.toString())
                .post("https://2wdg4glmpb.execute-api.us-west-1.amazonaws.com/Test1/tests")
                .then()
                .extract()
                .response();
        return response;
    }

    @Override
    public void onStart(ISuite suite) {
        postMessage("[START, SUITE]", suite.toString());
    }

    @Override
    public void onFinish(ISuite suite) {
        postMessage("[FINISH, SUITE]", suite.toString());
    }

    @Override
    public void onTestStart(ITestResult result) {
        postMessage("[START]", result.toString());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        postMessage("[SUCCESS]", result.toString());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        postMessage("[FAILURE]", result.toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        postMessage("[SKIPPED]", result.toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        postMessage("[FAILURE, SUCCESS PERCENTAGE]", result.toString());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        postMessage("[FAILURE, TIMEOUT]", result.toString());
    }

    @Override
    public void onStart(ITestContext context) {
        postMessage("[START, CONTEXT]", context.toString());
    }

    @Override
    public void onFinish(ITestContext context) {
        postMessage("[FINISH, CONTEXT]", context.toString());
    }
}
