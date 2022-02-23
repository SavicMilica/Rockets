package tests.products;
import asserts.ProductAssert;
import common.TestBase;
import data.models.products.Product;
import data.providers.ProductData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import apimethods.ProductAPI;

public class GetProductTest extends TestBase {

    private Product createdProduct;

    @BeforeClass
    public void prepareData() {
        createdProduct = ProductAPI.createProduct(ProductData.prepareProductRequest());
    }

    @Test
    public void getProduct() {
        Product actualProduct = ProductAPI.getProductById(createdProduct.getId());
        Product expectedProduct = Product.parseFullProductResponse(createdProduct);
        ProductAssert.getProductAssert(actualProduct, expectedProduct);
    }
}
