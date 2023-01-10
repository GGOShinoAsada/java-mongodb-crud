package com.company.mongodbdemo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProductDao {
    public List<Product> getAllProducts()
    {
        List<Product> list = new ArrayList();
        try (var client = MongoClients.create())
        {
            var db = client.getDatabase("demo");
            var collection = db.getCollection("Products");
            for (var item : collection.find())
            {
                ObjectId id = item.getObjectId("_id");
                String name = item.getString("name");
                double price = item.getDouble("price");
                int countAll = item.getInteger("count");
                int currentCount = item.getInteger("current_count");
                String categoryId = item.getString("category");
                Date date = item.getDate("date");
                String description = item.getString("comment");
                Product product = new Product(id.toString(), name, price, countAll, currentCount, null, date, description);
                CategoryDao dao = new CategoryDao();
                Optional<Category> box = dao.getCategoryById(categoryId);
                if (box.isPresent())
                {
                    product.setCategory(box.get());
                    list.add(product);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
    public Optional<Product> getProductsByName(String name)
    {
      Optional<Product> box = Optional.empty();
        try (var client = MongoClients.create())
        {
            var db = client.getDatabase("demo");
            var collection = db.getCollection("Products");
            var item = collection.find(new Document("name", name)).first();
            ObjectId id = item.getObjectId("_id");
            name = item.getString("name");
            double price = item.getDouble("price");
            int countAll = item.getInteger("count");
            int currentCount = item.getInteger("current_count");
            String categoryId = item.getString("category");
            Date date = item.getDate("date");
            String description = item.getString("description");
            Product product = new Product(id.toString(), name, price, countAll, currentCount, null, date, description);
            CategoryDao dao = new CategoryDao();
            Optional<Category> data = dao.getCategoryById(categoryId);
            if (data.isPresent())
            {
                product.setCategory(data.get());
                box = Optional.of(product);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return box;
    }

    public boolean addNewProduct(Product item)
    {
        boolean flag = true;
        try (var client = MongoClients.create())
        {
            var db = client.getDatabase("demo");
            var collection = db.getCollection("Products");
            Document insert = new Document();
            insert.append("name", item.getName());
            insert.append("price", item.getPrice());
            insert.append("count", item.getTotalCount());
            insert.append("current_count", item.getCurrentCount());
            insert.append("category", item.getCategory().getId());
            insert.append("comment", item.getDescription());
            collection.insertOne(insert);
        }
        catch (Exception ex)
        {
            flag = false;
            ex.printStackTrace();
        }
        return flag;
    }
    public boolean updateProduct(Product item)
    {
        boolean flag = true;
        try (var client = MongoClients.create())
        {
            var db = client.getDatabase("demo");
            var collection = db.getCollection("Products");
            var data = collection.find(new Document("_id", new ObjectId(item.getId()))).first();
            if (data!=null)
            {
                Bson bson = Updates.combine(Updates.set("name", item.getName()), Updates.set("price", item.getPrice()), Updates.set("count", item.getTotalCount()), Updates.set("current_count", item.getCurrentCount()), Updates.set("date", item.getDate()),Updates.set("comment", item.getDescription()));

                UpdateResult result = collection.updateOne(new Document("_id", new ObjectId(item.getId())),bson);
                flag = result.getModifiedCount()>0;
            }
            else {
                flag =false;
            }
        }
        catch (Exception ex)
        {
            flag = false;
            ex.printStackTrace();
        }
        return flag;
    }
    public boolean removeProduct(String name)
    {
        boolean flag = true;
        try(var client = MongoClients.create())
        {
            var db = client.getDatabase("demo");
            var collection = db.getCollection("Products");
            DeleteResult result = collection.deleteOne(new Document("name", name));
            flag = result.getDeletedCount()>0;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return flag;
    }
}
