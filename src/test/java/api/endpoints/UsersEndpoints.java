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
                        .cookie("accessToken",token)
                        .when()
                        .get(getUrl().getString("currentAuthUser_url"));
        return response;
    }

    public static Response getSingleUser(int id)
    {
        Response response =
                given()
                        .when()
                        .get(getUrl().getString("getSingleUser_url")+id);
        return response;
    }

    public static Response searchUsers(String searchKey)
    {
        Response response =
                given()
                        .queryParam("q",searchKey)
                        .when()
                        .get(getUrl().getString("searchUsers_url"));
        return response;
    }

    public static Response filterUsers(String key, String value)
    {
        Response response =
                given()
                        .queryParam("key",key)
                        .queryParam("value",value)
                        .when()
                        .get(getUrl().getString("filterUsers_url"));
        return response;
    }

    public static Response limitAndSkipUsers(int limit, int skip, String select)
    {
        Response response =
                given()
                        .queryParam("limit",limit)
                        .queryParam("skip",skip)
                        .queryParam("select",select)
                        .when()
                        .get(getUrl().getString("limitAndSkipUsers_url"));
        return response;
    }

    public static Response sortUsers(String sortBy, String orderBy)
    {
        Response response = given()
                .queryParam("sortBy",sortBy)
                .queryParam("orderBy",orderBy)
                .when()
                .get(getUrl().getString("sortUsers_url"));
        return response;
    }

    public static Response getAllTags()
    {
        Response response =
                given()
                        .when()
                        .get(getUrl().getString("getAllTags_url"));
        return response;
    }
}
