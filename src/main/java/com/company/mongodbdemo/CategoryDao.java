package com.company.mongodbdemo;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CategoryDao {
    public List<Category> getAllCategories()
    {
        List<Category> list = new ArrayList();
        try(var client = MongoClients.create()) {
            var db = client.getDatabase("demo");
            MongoCollection<Document> collection = db.getCollection("Categories");
            for (Document item: collection.find())
            {
                ObjectId id = item.getObjectId("_id");
                String name = item.getString("name");
                String description = item.getString("description");
                list.add(new Category(id.toString(), name, description));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
    public Optional<Category> getCategoryByName(String name)
    {
        Optional<Category> box = Optional.empty();
        try (var client = MongoClients.create())
        {
            var db = client.getDatabase("demo");
            MongoCollection<Document> collection = db.getCollection("Categories");
            for (Document item : collection.find(new Document("name", name)))
            {
                ObjectId id = item.getObjectId("_id");
                String description = item.getString("description");
                box = Optional.of(new Category(id.toString(), name, description));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  box;
    }

    public Optional<Category> getCategoryById(String id)
    {
        Category result = new Category();
        try (var client = MongoClients.create())
        {
            var db = client.getDatabase("demo");
            var collection = db.getCollection("Categories");
            var data = collection.find(new Document("_id", new ObjectId(id))).first();
            if (data!=null)
                result = new Category(data.getObjectId("_id").toString(), data.getString("name"), data.getString("description"));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return Optional.of(result);
    }

    public boolean addNewCategory(Category category)
    {
        boolean flag = true;
        try (var client = MongoClients.create()) {
            var db = client.getDatabase("demo");
            var collection = db.getCollection("Categories");
            Document document = new Document();
            document.append("name", category.getName());
            document.append("description", category.getDescription());
            collection.insertOne(document);
        }
        catch (Exception ex)
        {
            flag = false;
            ex.printStackTrace();
        }
        return flag;
    }
    public boolean updateCategory(Category item)
    {
        boolean flag = true;
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = mongoClient.getDatabase("demo");
            MongoCollection<Document> collection = database.getCollection("Categories");
            Document query = new Document().append("_id", new ObjectId(item.getId()));
            Bson update = Updates.combine(Updates.set("name", item.getName()), Updates.set("description", item.getDescription()));
            UpdateResult result = collection.updateOne(query, update);
            flag = result.getModifiedCount()>0;
        }
        catch (Exception ex) {
            flag = false;
            ex.printStackTrace();
        }

        return flag;
    }
    public boolean removeCategory(String name)
    {
        boolean flag = true;
        try (var client = MongoClients.create())
        {
            var db = client.getDatabase("demo");
            var collection = db.getCollection("Categories");
            DeleteResult result = collection.deleteOne(new Document("name", (name)));
            flag = result.getDeletedCount()>0;
        }
        catch (Exception ex)
        {
            flag = false;
            ex.printStackTrace();
        }
        return flag;
    }
}
