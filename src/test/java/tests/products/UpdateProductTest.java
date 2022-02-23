package tests.products;
import common.TestBase;
import asserts.ProductAssert;
import data.models.products.Product;
import data.providers.ProductData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import apimethods.ProductAPI;
import data.models.products.ProductRequest;

public class UpdateProductTest extends TestBase {
    private Product createdProduct;

    @BeforeClass
    public void prepareData() {
        createdProduct = ProductAPI.createProduct(ProductData.prepareProductRequest());
    }

    @Test
    public void updateProduct() {
        ProductRequest productRequest = ProductData.prepareProductRequestForUpdate(createdProduct.getId());

        Product actualProduct = ProductAPI.updateProduct(productRequest, createdProduct.getId());
        Product expectedProduct = Product.parseFullCreateProductResponse(productRequest);

        ProductAssert.updateProductAssert(actualProduct, expectedProduct);
    }
}
