package com.stufit.stufit;

import com.mongodb.BasicDBList;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.util.JSON;
import java.util.Arrays;

public class mongodb_timetable {
    static MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    static MongoDatabase db = mongoClient.getDatabase("stufit");

    public static void main(String[] args) {
        String[] res = getTimeTable("Mon");
        System.out.println(res[4]);
    }

    public  static String[] getTimeTable(String day) {

        MongoCursor<Document> iterator = db.getCollection("timetable").find(new Document( "day",  day)).iterator();


        BasicDBList list = new BasicDBList();
        while (iterator.hasNext()) {
            Document doc = iterator.next();
            list.add(doc);
        }
        String spl = "[,:{}\\[\\]\"]";
        String[] stra = JSON.serialize(list).split(spl);
        String[] res = new String[5];
        Arrays.fill(res,"");
        int i;
        for(i=0; i < stra.length; i++)
        {
            if(i==32)
                res[0] = stra[i];
            else if(i==44)
                res[1] = stra[i];
            else if(i==56)
                res[2] = stra[i];
            else if(i==68)
                res[3] = stra[i];
            else if(i==80)
                res[4] = stra[i];

        }
        for(i=0;i<5;i++) {
            if(res[i].equals(""))
                res[i]="No Class!";
        }
        return res;
    }
}
