package com.krunal.MongoDb.Database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.krunal.MongoDb.Utility.*;
import static com.mongodb.client.model.Filters.and;

public class DataBaseOperation {

    public static void InsertMany() {

        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");

        List<Document> insertMany = new ArrayList<>();
        insertMany.add(new Document("Name", "Jay").append("age", 30)
                .append("Birthday",getDateFromDD_MM_YYYY("02/03/1996")));
        insertMany.add(new Document("Name", "neel").append("age", 33)
                .append("Birthday",getDateFromDD_MM_YYYY("10/06/1998")));
        insertMany.add(new Document("Name", "neel").append("age", 44)
                .append("Birthday",getDateFromDD_MM_YYYY("27/05/1996")));
        insertMany.add(new Document("Name", "neel").append("age", 45)
                .append("Birthday",getDateFromDD_MM_YYYY("04/05/1992")));
        insertMany.add(new Document("Name", "Karan").append("age", 100)
                .append("Birthday",getDateFromDD_MM_YYYY("01/01/1991")));


        TestCollection1.insertMany(insertMany);
    }


    public static void InsertOne() {
        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");

        // To insert current Datetime.
        TestCollection1.insertOne(new Document("Name", "meet").append("age", 34)
                .append("Birthday",new Date()));
    }

    public static void findOne() {
        // find one By name Document Object.

        // Select * from Test where Name = 'neel' LIMIT 1

        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");

        Document insertQuery = Document.parse("{'Name': \"neel\"}");

        FindIterable<Document> list = TestCollection1.find(insertQuery).limit(1);

        MongoCursor<Document> cursor = list.iterator();
        try {

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }


    }

    public static void findOneByCondition() {

        // Select * from Test where Name = 'neel' and age > 36

        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");

//      Document query = Document.parse("{$and: [{ age: { $gt: 36 }},{'Name': \"neel\"}]}");

        Document query = Document.parse("{\"Name\": \"neel\",\"age\": {\"$gt\": 38}}");

        // Both above query work same.
        // http://www.querymongo.com/
        // for converting MySQL queries to MongoDB syntax.

        FindIterable<Document> list = TestCollection1.find(query);


        MongoCursor<Document> cursor = list.iterator();

        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }


    }

    public static void findByDate() {

        // Select * from Test where Birthday BETWEEN '1992-05-04' AND '1996-05-27'

        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");

        Document query = Document.parse("{Birthday: {$gte: ISODate('"
                + queryDateConversion("04/05/1992") + "')"
                +", $lte: ISODate('" + queryDateConversion("27/05/1996") + "')}}");

        FindIterable<Document> list = TestCollection1.find(query);

        try {
            if (list != null){
                for (Document item : list){
                    System.out.println("_id:- " + item.get("_id"));
                    System.out.println("Birthday:- " + getDD_MM_YYYYFromDate((Date)
                            item.get("Birthday")));
                    System.out.println("Name:- " + item.get("Name"));
                    System.out.println("age:- " + item.get("age"));
                    System.out.println("---------------------------------------");
                }
            }
        }catch (Exception e){
            System.out.println("Exception:- " + e.getMessage());

        }

    }

    public static void findAll() {

        // Select * from Test

        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");

        FindIterable<Document> list = TestCollection1.find();

        try {
            if (list != null){
                for (Document item : list){
                    System.out.println("_id:- " + item.get("_id"));
                    System.out.println("Birthday:- " + getDD_MM_YYYYFromDate((Date)
                            item.get("Birthday")));
                    System.out.println("Name:- " + item.get("Name"));
                    System.out.println("age:- " + item.get("age"));
                    System.out.println("---------------------------------------");
                }
            }
        }catch (Exception e){
            System.out.println("Exception:- " + e.getMessage());

        }
    }


    public static void UpdateOneByName() {

        // UPDATE Test SET age = 98, Name = 'Karan patel' where Name IN (SELECT Name
        //                FROM Test
        //                WHERE Name = 'Karan'
        //                ORDER BY Name asc
        //                LIMIT 1)

        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");

        // filter query.
        Document queryByName = Document.parse("{Name: 'Karan'}");

        // query to update new values.
        Document querySet = Document.parse("{ $set: { age: '98',Name: 'Karan patel'}}");

        UpdateResult updateResult = TestCollection1.updateOne(queryByName, querySet);

        System.out.println(updateResult);

    }

    public static void UpdateOneByMultipleCondition() {

        // UPDATE  Test SET age = 98, Name = 'Karan patel' where Name = 'neel' and age = 33

        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");

        // filter query.
        Document queryByName = Document.parse("{Name: 'neel',age: 33}");

        // query to update new values.
        Document querySet = Document.parse("{ $set: { age: '200',Name: 'sarth'}}");

        UpdateResult updateResult = TestCollection1.updateMany(queryByName, querySet);

        System.out.println(updateResult);

    }


    public static void UpdateManyByName() {

        // UPDATE  Test SET age = 98, Name = 'Karan patel' where Name = 'Karan'

        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");

        // filter query.
        Document queryByName = Document.parse("{Name: 'neel'}");

        // query to update new values.
        Document querySet = Document.parse("{ $set: { age: '98',Name: 'parth'}}");

        UpdateResult updateResult = TestCollection1.updateMany(queryByName, querySet);

        System.out.println(updateResult);

    }

    public static void DeleteOne() {
        // Delete By Hole Document Object.
        // DELETE FROM Test where Name= 'Jay' and age = 30
        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");


        TestCollection1.deleteOne(new Document("Name", "Jay").append("age", 30));
    }

    public static void DeleteOneByName() {
        // Delete One By name Document Object.
        // Please Note than this method will only delete Single Record with name "neel"
        // even if there are many "neel" records in the collection.

        // DELETE FROM Test where Name in (SELECT Name FROM Test WHERE Name = 'neel'
        // ORDER BY Name asc LIMIT 1 )

        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");


        TestCollection1.deleteOne(new Document("Name", "neel"));
    }

    public static void DeleteManyByName() {
        // Delete Many By name Document Object.

        // DELETE FROM Test where Name= 'neel'
        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");


        TestCollection1.deleteMany(new Document("Name", "neel"));
    }


    public static void Drop_Test_Collection(){

        MongoCollection<Document> TestCollection1 = MongoDbClient.getInstance()
                .getCollection("Test");
        TestCollection1.drop();
    }


}
