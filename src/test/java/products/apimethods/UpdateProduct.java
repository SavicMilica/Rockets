package products.apimethods;

import common.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.ProductsPath.PRODUCTS;

public class UpdateProduct {

    public static Response updateProduct(Integer id, String title, Integer price, String currency) {
        HashMap<String, Object> body = new HashMap();
        body.put("id", id);
        body.put("title", title);
        body.put("price", price);
        body.put("currency", currency);
        Response response = given().header(CONTENT, JSON_FORMAT).body(body).put(PRODUCTS + id);
        return response;
    }
}
