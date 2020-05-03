package com.krunal.MongoDb;

import com.google.gson.Gson;
import com.krunal.MongoDb.Database.DataBaseOperation;
import com.krunal.MongoDb.Database.MongoDbClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here

        DataBaseOperation.InsertMany();
        DataBaseOperation.InsertOne();

        DataBaseOperation.findOne();
        DataBaseOperation.findOneByCondition();
        DataBaseOperation.findAll();
        DataBaseOperation.findByDate();

        DataBaseOperation.UpdateOneByName();
        DataBaseOperation.UpdateOneByMultipleCondition();
        DataBaseOperation.UpdateManyByName();

        DataBaseOperation.DeleteOne();
        DataBaseOperation.DeleteOneByName();
        DataBaseOperation.DeleteManyByName();

        // Drop_Test_Collection
//        DataBaseOperation.Drop_Test_Collection();


//        Gson gson2 = new Gson();
//        String jsonInString2 = gson2.toJson(insertMany);

//        System.out.println("employee: " + jsonInString2);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                MongoCollection<Document> TestCollection = MongoDbClient.getInstance()
//                        .getCollection("Test");
//                Document document = new Document("username", "Thread 1")
//                        .append("email", "testemail@example.com");
//                System.out.println("employee Thread 1 hase: " + TestCollection.hashCode());
//                TestCollection.insertOne(document);
//
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                MongoCollection<Document> TestCollection = MongoDbClient.getInstance()
//                        .getCollection("Test");
//                Document document = new Document("username", "Thread 2")
//                        .append("email", "testemail@example.com");
//                System.out.println("employee Thread 2 hase : " + TestCollection.hashCode());
//                TestCollection.insertOne(document);
//
//
//            }
//        }).start();


    }
}
