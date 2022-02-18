package data.providers;

import data.models.products.ProductRequest;
import org.testng.annotations.DataProvider;

public class ProductData {

    public static ProductRequest prepareProductRequest() {
        return new ProductRequest("Rocket", 10, "EUR");
    }

    public static ProductRequest prepareProductRequestForUpdate(Integer productId) {
        return new ProductRequest(productId, "Falcon", 50, "DIN");
    }



    //TODO Procitaj kako se pravi TESTNG data provider

    @DataProvider(name = "prepareProduct")
    public static Object[][] prepareProduct() {
        return new Object[][]{
                {new ProductRequest("Rocket", 10, "EUR")},
                {new ProductRequest(null, 10, "EUR")},
                {new ProductRequest("Rocket", null, "EUR")},
                {new ProductRequest("Rocket", 10, null)}
        };
    }
}




