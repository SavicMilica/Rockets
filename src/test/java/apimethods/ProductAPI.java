package apimethods;

import common.RestAssuredMethods;
import constants.ApiEndpoints;
import data.models.products.Product;
import gson.GsonSetup;
import data.models.products.ProductRequest;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ProductAPI {
    public static Product createProduct(ProductRequest productRequest) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.post(productRequest, ApiEndpoints.PRODUCTS), Product.class);
    }

    public static Product updateProduct(ProductRequest productRequest, Integer productId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.put(productRequest, ApiEndpoints.product(productId)), Product.class);
    }

    public static Product getProductById(Integer productId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.get(ApiEndpoints.product(productId)), Product.class);
    }

    public static List<Product> getAllProducts() {
        return GsonSetup.parseSuccessResponseAsListToModel
                (RestAssuredMethods.get(ApiEndpoints.PRODUCTS),
                        Product[].class);
    }

    public static EmptyClass deleteProduct(Integer productId) {
        return GsonSetup.convertJsonToClass
                (RestAssuredMethods.delete(ApiEndpoints.product(productId)), EmptyClass.class);
    }

    public static EmptyClass getProductWithError(Integer productId) {
        return GsonSetup.convertErrorResponse(RestAssuredMethods.get(ApiEndpoints.product(productId)), EmptyClass.class);
    }

    public static Product getProductFromListOfProducts(Integer productId) {
        List<Product> productList = getAllProducts();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(productId)) {
                return productList.get(i);
            }
        }
        return null;
    }

    public static void deleteAllProducts() {
        List<Product> productList = getAllProducts();
        for (int i = 0; i < productList.size(); i++) {
               deleteProduct(productList.get(i).getId());
            }
    }

   public static Product createProductIfListIsEmpty(ProductRequest productRequest) {
        List<Product> productList = getAllProducts();
        if(productList.isEmpty()) {
            return createProduct(productRequest);
        }
        return productList.get(0);
   }

   public static Product updateExistingProduct(ProductRequest productRequest, ProductRequest productRequestForUpdate) {
        List<Product> productList = getAllProducts();
            if(!productList.isEmpty()) {
                Integer productId = productList.get(productList.size() - 1).getId();
                productRequestForUpdate.setId(productId);
                return updateProduct(productRequestForUpdate, productId);
            } else {
                Integer productId = createProduct(productRequest).getId();
                productRequestForUpdate.setId(productId);
                return updateProduct(productRequestForUpdate, productId);
            }
   }

   public static void deleteExistingProduct(ProductRequest productRequest) {
        List<Product> productList = getAllProducts();
        if(!productList.isEmpty()) {
            Integer productId = productList.get(productList.size() - 1).getId();
            deleteProduct(productId);
        } else {
            Integer productId = createProduct(productRequest).getId();
            deleteProduct(productId);
        }
   }


   public static Product checkIfTheProductExist(Integer productId, Boolean expectToExist) {
        List<Product> productList = getAllProducts();
        for(int i = 0; i < productList.size(); i++) {
            if(expectToExist) {
                if(productList.get(i).getId().equals(productId)) {
                    return productList.get(i);
                }
            }
            if(!expectToExist) {
                if(productList.get(i).getId().equals(productId)) {
                    Assert.fail("Error");
                }
            }
        }
       return null;
   }

   public static List<Product> returnProductsThatHaveThePriceHigherThan50() {
       List<Product> productList = getAllProducts();
       List<Product> priceList = new ArrayList<>();
        for(int i = 0; i < productList.size(); i++) {
             Integer price = productList.get(i).getPrice();
            if(price > 50) {
                priceList.add(productList.get(i));
            }
        }
       return priceList;
   }

   public static List<Product> returnProductsWithOtherCurrencies() {
        List<Product> productList = getAllProducts();
        List<Product> currencyList = new ArrayList<>();
        for(int i = 0; i < productList.size(); i++) {
            String currency = productList.get(i).getCurrency();
            if(!currency.equals("EUR")) {
                currencyList.add(productList.get(i));
            }
        }
       return productList;
   }

   public static Integer returnTheSumOfAllProducts() {
        List<Product> productList = getAllProducts();
        Integer sum = 0;
        for(int i = 0; i <productList.size(); i++) {
            sum += productList.get(i).getPrice();
        }
       return sum;
   }






    public static void createProductEachTimeInNewList(ProductRequest productRequest) {
        List<Product> productList = getAllProducts();
        if(!productList.isEmpty()) {
            deleteAllProducts();
            createProductIfListIsEmpty(productRequest);
        } else {
            createProductIfListIsEmpty(productRequest);
        }
    }

}

