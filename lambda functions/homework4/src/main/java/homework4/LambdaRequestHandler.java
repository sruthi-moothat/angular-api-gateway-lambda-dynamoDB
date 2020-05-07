package homework4;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LambdaRequestHandler implements RequestStreamHandler {
    private static final String DYNAMODB_TABLE_NAME = System.getenv("TABLE_NAME");

    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        //parse the data from the input stream and convert into json object
        JSONParser parser = new JSONParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONObject responseJson = new JSONObject();

        //interact with db
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDB dynamoDb = new DynamoDB(client);

        try {
            JSONObject event = (JSONObject) parser.parse(reader);

            Student student = new Student(event.toString());
            System.out.println(student);
            //put value in the db
            dynamoDb.getTable(DYNAMODB_TABLE_NAME)
                    .putItem(new PutItemSpec().withItem(new Item().withNumber("id", student.getId())
                            .withString("firstName", student.getFirstName())
                            .withString("lastName", student.getLastName())
                            .withString("emailId", student.getEmailId())
                            .withString("address", student.getAddress())
                            .withString("city", student.getCity())
                            .withString("state", student.getState())
                            .withString("zip", student.getZip())
                            .withString("tel", student.getTel())
                            .withString("sdate", student.getSdate())
                            .withString("thingsLiked", student.getThingsLiked())
                            .withString("interest", student.getInterest())
                            .withString("recommend", student.getRecommend())));

            //create different responses for the http requests
            responseJson.put("message", "New item created");

        } catch (Exception pex) {
            responseJson.put("message", "Error creating item");
        }

        //write to output stream
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJson.toString());
        writer.close();
    }


    public void handleGetAll(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDB dynamoDb = new DynamoDB(client);

        ItemCollection<ScanOutcome> result = dynamoDb.getTable(DYNAMODB_TABLE_NAME).scan(new ScanSpec());
        Iterator<Item> iter = result.iterator();
        List<Student> list = new ArrayList<Student>();
        //Iterate over the item collection and add each item into the list
        while (iter.hasNext()) {
            Item item = iter.next();
            System.out.println(item.toJSON());
            list.add(new Student(item.toJSON()));
        }
        //convert the list to a json object
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(new Gson().toJson(list));
        writer.close();
    }


}




