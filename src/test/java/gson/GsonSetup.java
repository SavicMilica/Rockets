package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.restassured.response.Response;
import org.testng.Assert;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class GsonSetup {

    //This method will be used to convert any JSON response into a Java Class Object
    public static <T> T convertJsonToClass(Response jsonResponse, Class<T> classOfT) {
        //We will use the Try/Catch block to handle the exception
        try {
            String prettyJsonString = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(jsonResponse.body().asString()));
            //We want to make sure the response is as expected in the model — one way to go is to assert the status code
            if (jsonResponse.getStatusCode() >= 400) {
                //We will use the TestNG Assert to stop test execution and print out the error that was received
                Assert.fail(classOfT + "\n returned status code: " + jsonResponse.getStatusCode() + " and error: " + prettyJsonString);
            } else {
                //Return JSON converted into an Object
                return new Gson().fromJson(prettyJsonString, (Type) classOfT);
            }
        } catch (JsonSyntaxException |IllegalStateException e) {
            //Return the exception that occurred during the JSON serialization
            Assert.fail(classOfT + "\n returned IllegalStateException: " + e.getMessage());
        }
        return null;
    }

    public static <T> T convertErrorResponse(Response jsonResponse, Class<T> classOfT) {
        //We will use the Try/Catch block to handle the exception
        try {
            String prettyJsonString = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(jsonResponse.body().asString()));
            //We want to make sure the response is as expected in the model — one way to go is to assert the status code
            if (jsonResponse.getStatusCode() < 400) {
                //We will use the TestNG Assert to stop test execution and print out the error that was received
                Assert.fail(classOfT + "\n returned status code: " + jsonResponse.getStatusCode() + " and json: " + prettyJsonString);
            } else {
                //Return JSON converted into an Object
                return new Gson().fromJson(prettyJsonString, (Type) classOfT);
            }
        } catch (JsonSyntaxException |IllegalStateException e) {
            //Return the exception that occurred during the JSON serialization
            Assert.fail(classOfT + "\n returned IllegalStateException: " + e.getMessage());
        }
        return null;
    }

    public static <T> List<T> parseSuccessResponseAsListToModel(Response jsonResponse, Class<T[]> classOfT) {
        String json = jsonResponse.body().asString();
        String prettyJsonString = "";
        try {
            prettyJsonString = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(json));
            if (jsonResponse.getStatusCode() >= 400 && jsonResponse.getStatusCode() < 600) {
                Assert.fail("Endpoint for processing " + classOfT + "\n return error: " + prettyJsonString);
            } else {
                return Arrays.asList(new Gson().fromJson(prettyJsonString, (Type) classOfT));
            }
        } catch (JsonSyntaxException|IllegalStateException e) {
            Assert.fail("Endpoint for processing " + classOfT + "\n return error: " + prettyJsonString +
                    "\n serialization exception error: " + e.getMessage());
        }
        return null;
    }
}
