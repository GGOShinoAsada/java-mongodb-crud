package com.company.mongodbdemo;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        exec();
    }

    static void exec()
    {
        boolean flag = true;
        while (flag)
        {
            System.out.println("please select: ");
            System.out.println("1 - products");
            System.out.println("2 - categories");
            System.out.println("0 - exit");
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            String name = "";
            double price = 0;
            int countAll = 0; int currentCount = 0;
            Category category = new Category();
            Product product = new Product();
            Date date = new Date();
            String description = "";
            boolean f1 = true;
            Optional<Product> productBox = Optional.empty();
            Optional<Category> categoryBox = Optional.empty();
            List<Product> productList = new ArrayList();
            List<Category> categoryList = new ArrayList();
            String catName = "";
            boolean res = true;
            ProductDao prDao = new ProductDao();
            CategoryDao catDao = new CategoryDao();
            switch (n)
            {
                case 1:
                    while (f1) {
                        System.out.println("please select: ");
                        System.out.println("1 - get all products");
                        System.out.println("2 - get product by name");
                        System.out.println("3 - add new product");
                        System.out.println("4 - update information about product");
                        System.out.println("5 - remove product");
                        System.out.println("0 - exit");
                        int n1 = scanner.nextInt();


                        switch (n1)
                        {
                            case 1:
                                productList = prDao.getAllProducts();
                                for (var item :productList)
                                {
                                    System.out.println("name "+item.getName()+", price "+item.getPrice()+", total count "+item.getTotalCount()+", current count "+item.getCurrentCount()+", date "+item.getDate()+", category "+item.getCategory().getName()+", description "+item.getDescription());
                                }
                                break;
                            case 2:
                                System.out.println("enter name");
                                scanner = new Scanner(System.in);
                                name = scanner.nextLine();
                                productBox = prDao.getProductsByName(name);
                                if (productBox.isPresent())
                                {
                                    Product item = productBox.get();
                                    System.out.println("name "+item.getName()+", price "+item.getPrice()+", total count "+item.getTotalCount()+", current count "+item.getCurrentCount()+", date "+item.getDate()+", category "+item.getCategory().getName()+", description "+item.getDescription());
                                }

                                break;
                            case 3:
                                scanner = new Scanner(System.in);
                                product = new Product();
                                System.out.println("enter name");
                                name = scanner.nextLine();
                                System.out.println("enter count");
                                countAll = scanner.nextInt();
                                currentCount = countAll;
                                System.out.println("enter price");
                                price = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.println("enter name of category");
                                catName = scanner.nextLine();
                                System.out.println("enter description");
                                description = scanner.nextLine();
                                categoryBox = catDao.getCategoryByName(catName);
                                if (categoryBox.isPresent())
                                {
                                    category = categoryBox.get();
                                    product.setName(name);
                                    product.setTotalCount(countAll);
                                    product.setCurrentCount(currentCount);
                                    product.setPrice(price);
                                    product.setCategory(category);
                                    product.setDescription(description);
                                    product.setDate(new Date());
                                    res = prDao.addNewProduct(product);
                                }
                                else {
                                    res = false;
                                }
                                if (res)
                                {
                                    System.out.println("insert success");
                                }
                                else {
                                    System.out.println("insert failed");
                                }
                                break;
                            case 4:
                                scanner = new Scanner(System.in);
                                System.out.println("enter name");
                                name = scanner.nextLine();
                                productBox = prDao.getProductsByName(name);
                                if (productBox.isPresent())
                                {
                                    Product item = productBox.get();
                                    System.out.println("enter new name");
                                    name= scanner.nextLine();
                                    System.out.println("enter count");
                                    countAll = scanner.nextInt();
                                    System.out.println("enter current count");
                                    currentCount = scanner.nextInt();
                                    System.out.println("enter price");
                                    price = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("enter name of category");
                                    catName = scanner.nextLine();
                                    System.out.println("enter description");
                                    description = scanner.nextLine();
                                    categoryBox = catDao.getCategoryByName(catName);
                                    if (categoryBox.isPresent())
                                    {
                                        category = categoryBox.get();
                                        item.setName(name);
                                        item.setTotalCount(countAll);
                                        item.setCurrentCount(currentCount);
                                        item.setPrice(price);
                                        item.setCategory(category);
                                        item.setDescription(description);
                                        res = prDao.updateProduct(item);
                                    }
                                    else{
                                        res = false;
                                    }
                                }
                                else {
                                    res = false;
                                }
                                if (res)
                                {
                                    System.out.println("update success");
                                }
                                else {
                                    System.out.println("update failed");
                                }
                                break;
                            case 5:
                                scanner = new Scanner(System.in);
                                System.out.println("enter name");
                                name = scanner.nextLine();
                                res = prDao.removeProduct(name);
                                if (res)
                                {
                                    System.out.println("removing success");
                                }
                                else {
                                    System.out.println("removing failed");
                                }
                                break;
                            case 0:
                                f1 = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    while (f1)
                    {
                        System.out.println("please select: ");
                        System.out.println("1 - get all categories");
                        System.out.println("2 - get category by name");
                        System.out.println("3 - add new category");
                        System.out.println("4 - update information about category");
                        System.out.println("5 - remove category");
                        System.out.println("0 - exit");
                        int n1 = scanner.nextInt();
                        categoryList = new ArrayList();
                        category = new Category();
                        catDao = new CategoryDao();
                        switch (n1)
                        {
                            case 1:
                                categoryList = catDao.getAllCategories();
                                for (Category item: categoryList)
                                {
                                    System.out.println("name "+item.getName()+" description "+ item.getDescription());
                                }
                                break;
                            case 2:
                                System.out.println("enter name:");
                                scanner = new Scanner(System.in);
                                name = scanner.nextLine();
                                 categoryBox = catDao.getCategoryByName(name);
                                 if (categoryBox.isPresent())
                                 {
                                     System.out.println("search result:");
                                     Category item = categoryBox.get();
                                     System.out.println("name "+item.getName()+" description "+ item.getDescription());
                                 }
                                break;
                            case 3:
                                System.out.println("enter name");
                                scanner = new Scanner(System.in);
                                name = scanner.nextLine();
                                System.out.println("enter description");
                                description = scanner.nextLine();
                                res = catDao.addNewCategory(new Category(name, description));
                                if (res)
                                {
                                    System.out.println("insert success");
                                }
                                else {
                                    System.out.println("insert failed");
                                }
                                break;
                            case 4:
                                System.out.println("enter name");
                                scanner = new Scanner(System.in);
                                name = scanner.nextLine();
                                categoryBox = catDao.getCategoryByName(name);
                                if (categoryBox.isPresent())
                                {
                                    category = categoryBox.get();
                                    System.out.println("enter new name");
                                    scanner = new Scanner(System.in);
                                    name = scanner.nextLine();
                                    System.out.println("enter new description");
                                    description = scanner.nextLine();
                                    category.setName(name);
                                    category.setDescription(description);
                                    res = catDao.updateCategory(category);
                                }
                                else{
                                    res = false;
                                }
                                if (res)
                                {
                                    System.out.println("update success");
                                }
                                else {
                                    System.out.println("update failed");
                                }

                                break;
                            case 5:
                                System.out.println("enter name");
                                scanner = new Scanner(System.in);
                                name = scanner.nextLine();
                                res = catDao.removeCategory(name);
                                if (res)
                                {
                                    System.out.println("removing success");
                                }
                                else {
                                    System.out.println("removing failed");
                                }
                                break;
                            case 0:
                                f1 = false;
                                break;
                        }
                    }
                    break;
                case 0:
                    flag = false;
                break;
            }
        }

    }
}
