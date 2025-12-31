package api.endpoints;

import api.payloads.UserLogin;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
public class AuthEndpoints
{
    static ResourceBundle getUrl()
    {
        ResourceBundle routes = ResourceBundle.getBundle("routes"); // load the properties file; "routes" is file name
        return routes;
    }

    public static Response loginUser(UserLogin payload)
    {
        String login_url = getUrl().getString("login_url");

        Response response =
        given()
                .contentType(JSON)
                .when()
                .body(payload)
                .post(login_url);

        return response;
    }
}
