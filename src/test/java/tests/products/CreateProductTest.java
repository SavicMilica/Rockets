package tests.products;
import apimethods.ProductAPI;
import asserts.ProductAssert;
import common.TestBase;
import data.models.products.Product;
import data.providers.ProductData;
import org.testng.annotations.Test;
import data.models.products.ProductRequest;

public class CreateProductTest extends TestBase {

    @Test
    public void createProductTest() {
        ProductRequest productRequest = ProductData.prepareProductRequest();
        Product actualProduct = ProductAPI.createProduct(productRequest);
        Product expectedProduct = Product.parseFullCreateProductResponse(productRequest);
        ProductAssert.createProductAssert(actualProduct, expectedProduct);
    }


//    @Test(dataProvider = "prepareProduct", dataProviderClass = ProductData.class)
//    public void createProductTest2(ProductRequest productRequest) {
//        Response response = ProductAPI.createProduct(productRequest);
//        System.out.println(response.path(PRICE).toString());
//    }

    }


























