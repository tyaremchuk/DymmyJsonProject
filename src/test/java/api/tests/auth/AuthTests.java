package api.tests.auth;

import api.endpoints.AuthEndpoints;
import api.payloads.RefreshToken;
import api.payloads.UserLogin;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AuthTests
{
    @Test(priority = 1)
    public void loginUserTest(ITestContext context)
    {
        UserLogin payload = new UserLogin("emilys","emilyspass",30);

        Response response = AuthEndpoints.loginUser(payload);
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));

        String authToken = response.jsonPath().getString("accessToken");

        context.setAttribute("authToken",authToken);
    }

    @Test(priority = 2)
    public void getAuthMeTest(ITestContext context)
    {
        String authToken = context.getAttribute("authToken").toString();

        Response response = AuthEndpoints.getAuthMe(authToken);
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
    }

    @Test(priority = 3)
    public void getAuthMeTestFail(ITestContext context)
    {
        String authToken = context.getAttribute("authToken").toString()+1;

        Response response = AuthEndpoints.getAuthMe(authToken);
        response.then().log().all();
        assertThat(response.getStatusCode(),equalTo(500));
    }

    @Test(priority = 4)
    public void refreshTokeTest(ITestContext context)
    {
        String authToken = context.getAttribute("authToken").toString();
        RefreshToken refreshToken = new RefreshToken(authToken);

        Response response = AuthEndpoints.refreshToken(refreshToken);
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
    }
}
