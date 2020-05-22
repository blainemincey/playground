package org.example;

import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;

/**
 *
 */
public class MyMongo {

    public MyMongo() {
        this.getMovies();
    }

    /**
     *
     */
    private void getMovies() {
        System.out.println("=== Get Movies ===");

        // Assuming MongoDB instance is on localhost
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("sample_mflix");
        MongoCollection<Document> collection = database.getCollection("movies");

        // Filter for a single document for quick test
        Document doc = collection.find(eq("title", "The Matrix")).first();
        System.out.println("Results for single doc: " + doc);

        // Print block to print out multiple docs
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };

        System.out.println("Retrieve multiple docs using the OR filter...");
        collection.find(
                or(
                        eq("title","The Matrix"),
                        eq("title", "The Birth of a Nation")
                ))
                .forEach(printBlock);

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("=Starting=");

        new MyMongo();

        System.out.println("=Ending=");
    }
}
