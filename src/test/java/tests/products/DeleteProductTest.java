package tests.products;

import apimethods.EmptyClass;
import apimethods.PostAPI;
import common.TestBase;
import data.providers.ProductData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import apimethods.ProductAPI;

public class DeleteProductTest extends TestBase {
    Integer productId;

    @BeforeMethod
    public void prepareData() {
        productId = ProductAPI.createProduct(ProductData.prepareProductRequest()).getId();
    }

    @Test
    public void deleteProduct() {
        ProductAPI.deleteProduct(productId);
        EmptyClass emptyClass = ProductAPI.getProductWithError(productId);
        Assert.assertNotNull(emptyClass);
    }
}

//TODO Make two tests
