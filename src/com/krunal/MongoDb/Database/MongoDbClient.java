package com.krunal.MongoDb.Database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.Serializable;

public class MongoDbClient implements Serializable {

    private static MongoClient mClient;
    private final static String TestDb = "JavaDemoDb";
    private volatile static MongoDbClient mINSTANCE = null;


    private MongoDbClient(){}

    public static MongoDbClient getInstance() {
        if (mINSTANCE == null) {
            synchronized (MongoDbClient.class){
                if (mINSTANCE == null){
                    mINSTANCE = new MongoDbClient();
                }
            }
        }
        return mINSTANCE;
    }


    // Singleton for MongoClient
    // Creates a single connection pool internally
    private MongoClient getMongoClientConnection() {
        if (mClient == null) {
            mClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        }
        return mClient;
    }


    // Utility method to get database instance
    public MongoDatabase getDB() {
        return getMongoClientConnection().getDatabase(TestDb);
    }


    // Utility method to get user collection
    public MongoCollection<Document> getCollection(String collectionName) {
        return getDB().getCollection(collectionName);
    }



    // implement readResolve method

    protected Object readResolve()
    {
        return mINSTANCE;
    }

//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
}
