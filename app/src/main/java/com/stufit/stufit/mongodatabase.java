package com.stufit.stufit;

import com.mongodb.BasicDBList;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.util.JSON;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;

public class mongodatabase {
    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    MongoDatabase db = mongoClient.getDatabase("stufit");


    static JSONObject reader = null;

    public  void main(String[] args) {
        //searchRecent();
        //String coa = searchBySubject("COA");
        //System.out.println(coa);
        //searchByDay("18/6/16");
        searchBySubject("COA");
    }

            //searchRecent();
//            String x = searchBySubject("COA");
            //searchByDay("18/6/16");

    public  void searchRecent() {
        FindIterable<Document> iterable = db.getCollection("summaries").find(new Document("mostRecent", true)).projection(fields(excludeId()));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }
    public  void searchByDay(String date)
    {
        FindIterable<Document> iterable = db.getCollection("summaries").find(new Document("day" , date)).projection(fields(excludeId()));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });

    }
    public  void searchBySubject(String subject) {
//        JSONObject reader = null;
//        String summary = "";
//        try {
//            reader = new JSONObject("summaries");
//            JSONObject lectures = reader.getJSONObject("lectures");
//            String sbj = lectures.getString("subject");
//            if(sbj==subject){
//                summary = lectures.getString("summary");
//                System.out.println(summary);
//            }
//        }
//
//        catch (JSONException e) {
//            e.printStackTrace();
//        }

//        FindIterable<Document> iterable = db.getCollection("summaries").find(new Document( "lectures.subject",  subject)).projection(fields(excludeId()));
//
//        iterable.forEach(new Block<Document>() {
//            @Override
//            public void apply(Document document) {
////                try {
////                    mongodatabase.reader = new JSONObject(document.toString());
////                } catch(Exception e)
////                {
////                    e.printStackTrace();
////                }
////
//////                x[0] = document.toString();
////                System.out.println(document);
//                mongodatabase.doSomething(document);
//            }
        MongoCursor<Document> iterator = db.getCollection("summaries").find(new Document( "lectures.subject",  subject)).iterator();

        BasicDBList list = new BasicDBList();
        while (iterator.hasNext()) {
            Document doc = iterator.next();
            list.add(doc);
        }
        String[] stra = JSON.serialize(list).split("");
        System.out.println(JSON.serialize(list));
        int i;
        for(i=0; i < stra.length; i++)
        {
            System.out.println(stra[i]);
        }
//        });
//        System.out.println(reader.toString());
//        return x[0];
//        return reader;
    }
}
