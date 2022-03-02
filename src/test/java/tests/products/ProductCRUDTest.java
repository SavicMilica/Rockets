package tests.products;
import apimethods.EmptyClass;
import apimethods.ProductAPI;
import asserts.ProductAssert;
import common.TestBase;
import data.models.products.Product;
import data.providers.ProductData;
import org.testng.Assert;
import org.testng.annotations.Test;
import data.models.products.ProductRequest;

import java.util.List;

public class ProductCRUDTest extends TestBase {

    private Product createdProduct;

    @Test
    public void createProductTest() {
        ProductRequest productRequest = ProductData.prepareProductRequest();
        Product actualProduct = ProductAPI.createProduct(productRequest);
        Product expectedProduct = Product.parseFullCreateProductResponse(productRequest);
        ProductAssert.createProductAssert(actualProduct, expectedProduct);
    }

    @Test
    public static void createNewProductIfListIsEmpty() {
        ProductRequest productRequest = ProductData.prepareProductRequest();
        Product actualProduct = ProductAPI.createProductIfListIsEmpty(productRequest);
        Product expectedProduct = Product.parseFullCreateProductResponse(productRequest);
        ProductAssert.createProductAssert(actualProduct, expectedProduct);
    }

    @Test
    public void getProduct() {
        createdProduct = ProductAPI.createProduct(ProductData.prepareProductRequest());
        Product actualProduct = ProductAPI.getProductById(createdProduct.getId());
        Product expectedProduct = Product.parseFullProductResponse(createdProduct);
        ProductAssert.getProductAssert(actualProduct, expectedProduct);
    }

    @Test
    public void getProductFromAList() {
        createdProduct = ProductAPI.createProduct(ProductData.prepareProductRequest());
        Product actualProduct = ProductAPI.getProductFromListOfProducts(createdProduct.getId());
        Product expectedProduct = Product.parseFullProductResponse(createdProduct);
        ProductAssert.getProductAssert(actualProduct, expectedProduct);
    }

    @Test
    public void checkIfTheProductExist() {
        createdProduct = ProductAPI.createProduct(ProductData.prepareProductRequest());
        ProductAPI.checkIfTheProductExist(createdProduct.getId(), false);
    }

    @Test
    public void updateProduct() {
        createdProduct = ProductAPI.createProduct(ProductData.prepareProductRequest());
        ProductRequest productRequest = ProductData.prepareProductRequestForUpdate(createdProduct.getId());
        Product actualProduct = ProductAPI.updateProduct(productRequest, createdProduct.getId());
        Product expectedProduct = Product.parseFullCreateProductResponse(productRequest);
        ProductAssert.updateProductAssert(actualProduct, expectedProduct);
    }

    @Test
    public void updateExistingProduct() {
        ProductRequest productRequest = ProductData.prepareProductRequest();
        ProductRequest productRequestForUpdate = ProductData.prepareProductRequestForUpdate(null);
        Product actualProduct = ProductAPI.updateExistingProduct(productRequest, productRequestForUpdate);
        Product expectedProduct = Product.parseFullCreateProductResponse(productRequestForUpdate);
        ProductAssert.updateProductAssert(actualProduct, expectedProduct);
    }

    @Test
    public void deleteProduct() {
        createdProduct = ProductAPI.createProduct(ProductData.prepareProductRequest());
        ProductAPI.deleteProduct(createdProduct.getId());
        EmptyClass emptyClass = ProductAPI.getProductWithError(createdProduct.getId());
        Assert.assertNotNull(emptyClass);
    }

    @Test
    public void deleteAllProducts() {
        ProductAPI.deleteAllProducts();
        List<Product> productList = ProductAPI.getAllProducts();
    }

    @Test
    public void deleteExistingProduct() {
        ProductRequest productRequest = ProductData.prepareProductRequest();
        ProductAPI.deleteExistingProduct(productRequest);
        List<Product> productList = ProductAPI.getAllProducts();
    }

    @Test
    public void getPriceList() {
        ProductAPI.returnProductsThatHaveThePriceHigherThan50();
    }

    @Test
    public void getDifferentCurrencies() {
        ProductAPI.returnProductsWithOtherCurrencies();
    }

    @Test
    public void returnTheSum() {
        ProductAPI.returnTheSumOfAllProducts();
    }












//    @Test(dataProvider = "prepareProduct", dataProviderClass = ProductData.class)
//    public void createProductTest2(ProductRequest productRequest) {
//        Response response = ProductAPI.createProduct(productRequest);
//        System.out.println(response.path(PRICE).toString());
//    }

    }


























