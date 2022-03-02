package asserts;

import data.models.products.Product;
import org.testng.asserts.SoftAssert;


public class ProductAssert {

//    private final SoftAssert softAssert;
//    public ProductAssert() {
//        this.softAssert = new SoftAssert();
//    }

    public static void createProductAssert(Product actualProduct, Product expectedProduct) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProduct.getTitle(), expectedProduct.getTitle(), "title didn't match");
        softAssert.assertEquals(actualProduct.getPrice(), expectedProduct.getPrice(), "price didn't match");
        softAssert.assertEquals(actualProduct.getCurrency(), expectedProduct.getCurrency(), "currency didn't match");
        softAssert.assertNotNull(actualProduct.getId(), "id is null");
        softAssert.assertAll();
    }

    public static void getProductAssert(Product actualProduct, Product expectedProduct) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProduct.getId(), expectedProduct.getId(), "id didn't match");
        softAssert.assertEquals(actualProduct.getTitle(), expectedProduct.getTitle(), "title didn't match");
        softAssert.assertEquals(actualProduct.getPrice(), expectedProduct.getPrice(), "price didn't match");
        softAssert.assertEquals(actualProduct.getCurrency(), expectedProduct.getCurrency(), "currency didn't match");
        softAssert.assertAll();
    }

    public static void updateProductAssert(Product actualProduct, Product expectedProduct) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProduct.getId(), expectedProduct.getId(), "id didn't match");
        softAssert.assertEquals(actualProduct.getTitle(), expectedProduct.getTitle(), "title didn't match");
        softAssert.assertEquals(actualProduct.getPrice(), expectedProduct.getPrice(), "price didn't match");
        softAssert.assertEquals(actualProduct.getCurrency(), expectedProduct.getCurrency(), "currency didn't match");
        softAssert.assertAll();
    }
}
