package api.tests.auth;

import api.endpoints.AuthEndpoints;
import api.payloads.UserLogin;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AuthTests
{
    @Test(priority = 1)
    public void loginUserTest()
    {
        UserLogin payload = new UserLogin("emilys","emilyspass",30);

        Response response = AuthEndpoints.loginUser(payload);
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
    }
}
