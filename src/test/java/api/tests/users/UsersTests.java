package api.tests.users;
import api.endpoints.UsersEndpoints;
import api.payloads.UsersPayloads;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UsersTests
{
    @Test(priority = 1)
    public void getAllUsersTest()
    {
        Response response = UsersEndpoints.getAllUsers();
        response.then().log().all();

        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test(priority = 2)
    public void getAllUsersSchema()
    {
        String schemaPath = "schemas/getAllUsersSchema.json";

        Response response = UsersEndpoints.getAllUsers();

        assertThat(response.getStatusCode(), equalTo(200));
        response.then().body(matchesJsonSchemaInClasspath(schemaPath));
        //assertThat(response.getBody(),matchesJsonSchemaInClasspath(schemaPath));
    }

    @Test(priority = 3)
    public void loginUserTest(ITestContext context)
    {
        UsersPayloads userData = new UsersPayloads("emilys","emilyspass");
        Response response = UsersEndpoints.loginUser(userData);
        response.then().log().all();

        String token = response.jsonPath().getString("refreshToken");
        context.setAttribute("token",token);

        String schemaPath = "schemas/usersLoginSchema.json";
        response.then().body(matchesJsonSchemaInClasspath(schemaPath));

        assertThat(response.getStatusCode(),equalTo(200));
        assertThat(response.header("Content-Type"),equalTo("application/json; charset=utf-8"));
    }

    @Test(priority = 4)
    public void loginUserTest400()
    {
        UsersPayloads userData = new UsersPayloads("asd","asd");
        Response response = UsersEndpoints.loginUser(userData);
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(400));
    }

    @Test(priority = 5)
    public void userMeTest(ITestContext context)
    {
        String token = context.getAttribute("token").toString();

        Response response = UsersEndpoints.getCurrentAuthenticatedUser(token);
        response.then().log().all();

        assertThat(response.getStatusCode(),equalTo(200));
    }
}
