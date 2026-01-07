package api.tests.users;

import api.endpoints.UsersEndpoitns;
import api.payloads.UsersPayloads;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class UsersTests
{
    @Test(priority = 1)
    public void getAllUsersTest()
    {
        Response response = UsersEndpoitns.getAllUsers();
        response.then().log().all();

        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test(priority = 2)
    public void loginUserTest()
    {
        UsersPayloads userData = new UsersPayloads("emilys","emilyspass");
        Response response = UsersEndpoitns.loginUser(userData);
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
    }
}
