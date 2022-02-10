package products.apimethods;

import common.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import products.models.ProductRequest;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static products.constants.Header.CONTENT;
import static products.constants.Header.JSON_FORMAT;
import static products.constants.KeyParameters.*;
import static products.constants.ProductsPath.PRODUCTS;

public class UpdateProduct {

    public static Response updateProduct(Integer id, String title, Integer price, String currency) {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(id);
        productRequest.setTitle(title);
        productRequest.setPrice(price);
        productRequest.setCurrency(currency);


        Response response = given().header(CONTENT, JSON_FORMAT).body(productRequest).put(PRODUCTS + id);
        return response;
    }
}
