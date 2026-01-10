package api.endpoints;
import api.payloads.UsersPayloads;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;
import static io.restassured.RestAssured.*;

public class UsersEndpoints
{
    static ResourceBundle getUrl()
    {
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response getAllUsers()
    {
        String getAllUsersUrl = getUrl().getString("getAllUsers_url");

        Response response =
                given()
                        .when()
                        .get(getAllUsersUrl);

        return response;
    }

    public static Response loginUser(UsersPayloads userData)
    {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .body(userData)
                        .post(getUrl().getString("userLogin_url"));
        return response;
    }

    public static Response getCurrentAuthenticatedUser(String token)
    {
        Response response =
                given()
                        .header("Authorization","Bearer "+token)
                        .when()
                        .get(getUrl().getString("currentAuthUser_url"));
        return response;
    }
}
