package com.stufit.stufit;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;

public class mongodatabase {
    static MongoClient mongoClient = new MongoClient();
    static MongoDatabase db = mongoClient.getDatabase("summary");
    public static void main(String arg[])
    {

//            searchRecent();
        searchBySubject("COA");
//        searchByDay("18/6/16");
    }
    public static void searchRecent() {
        FindIterable<Document> iterable = db.getCollection("summaries").find(new Document("mostRecent", true)).projection(fields(excludeId()));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }
    public static void searchByDay(String date)
    {
        FindIterable<Document> iterable = db.getCollection("summaries").find(new Document("day" , date)).projection(fields(excludeId()));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }
    public static void searchBySubject(String subject)
    {
        FindIterable<Document> iterable = db.getCollection("summaries").find(new Document( "lectures.subject",  "COA")).projection(fields(excludeId()));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }
}
