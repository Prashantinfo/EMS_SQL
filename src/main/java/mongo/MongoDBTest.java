package mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.sql.SQLOutput;

public class MongoDBTest {
    public static void main(String[] args) {
        String connectionString = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            System.out.println("this is output" +mongoClient);
            // Get database (will create if doesn't exist)
            MongoDatabase database = mongoClient.getDatabase("testdb");

            // Get collection (will create if doesn't exist)
            MongoCollection<Document> collection = database.getCollection("test_collection");

            // Create a test document
            Document doc = new Document("name", "test")
                    .append("value", "Hello MongoDB!");

            // Insert the document
            collection.insertOne(doc);

            // Find and print the document
            Document foundDoc = collection.find().first();
            System.out.println("Found document: " + foundDoc.toJson());

            // Clean up - delete the test document
            collection.deleteOne(doc);

            System.out.println("Successfully completed MongoDB operations!");
        }
    }
}