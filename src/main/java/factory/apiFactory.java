package factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utils.configReader;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class apiFactory {
    private configReader FileReader;
    Properties props;
    public static String baseURI;
    public static String jsonString;

    public apiFactory(){
        FileReader=new configReader();
        props=FileReader.init_prop();
        baseURI=props.getProperty("baseURI");
    }

    public static RequestSpecification getBasicRequest(Map<String,String> requestHeaders){
        RestAssured.baseURI=baseURI;
        RequestSpecification httpRequest=RestAssured.given().headers(requestHeaders);
        return httpRequest;
    }

    public static Response hitGetRequest1(String url){
        Map<String,String> RequestHeaders=new HashMap<>();
        RequestHeaders.put("Content-Type","application/json");
        Response response=getBasicRequest(RequestHeaders).get(url);
        return response;
    }

    public static Response hitPostRequest(String url,String JsonBody){
        Map<String,String> RequestHeaders=new HashMap<>();
        RequestHeaders.put("Content-Type","application/json");
        Response response=getBasicRequest(RequestHeaders).body(JsonBody).post(url);
        return response;
    }
    public static String getJsonString(Object myObj){
        ObjectMapper mapper=new ObjectMapper();
        try {
             jsonString=mapper.writeValueAsString(myObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;

    }

    public static void validateJSONSchema(String schema,Response response){
        String schemaName=schema+".json";
        File file=new File("./src/test/resources/schema/"+schemaName);
        ValidatableResponse vresponse=response.then();
        vresponse.body(matchesJsonSchema(file));

    }
}
