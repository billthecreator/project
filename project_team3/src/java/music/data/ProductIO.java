/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.data;
import java.io.*;
import java.util.*;
import music.business.*;

/**
 *
 * @author William
 */
public class ProductIO {
    private static ArrayList<Product> products = null;
    private static String filePath = null;
    String path = "/WEB-INF/products.txt";

    // Called once from the controller based on servlet context
    public static void init(String filePath) {
        ProductIO.filePath = filePath;
    }

    public static ArrayList<Product> selectProducts() {
        products = new ArrayList<Product>();
        File file = new File(filePath);
        try {
            BufferedReader in
                    = new BufferedReader(
                            new FileReader(file));

            String line = in.readLine();
            while (line != null) {
                StringTokenizer t = new StringTokenizer(line, "|");
                if (t.countTokens() >= 3) {
                    String code = t.nextToken();
                    String description = t.nextToken();
                    String priceAsString = t.nextToken();
                    double price = Double.parseDouble(priceAsString);

                    Product p = new Product();
                    p.setCode(code);
                    p.setDescription(description);
                    p.setPrice(price);

                    products.add(p);
                }
                line = in.readLine();
            }
            in.close();
            return products;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static Product selectProduct(String productCode) {
        products = selectProducts();
        for (Product p : products) {
            if (productCode != null
                    && productCode.equalsIgnoreCase(p.getCode())) {
                return p;
            }
        }
        return null;
    }

    public static boolean exists(String productCode) {
        Product p = selectProduct(productCode);
        if (p != null) return true;
        else return false;
    }    
    
    private static void saveProducts(ArrayList<Product> products) {
        BufferedWriter output = null;
        try {
            File file = new File(filePath);
//            PrintWriter out = new PrintWriter(filePath, "UTF-8");
            output = new BufferedWriter(new FileWriter(file));

            for (Product p : products) {
                output.write(p.getCode() + "|"
                        + p.getDescription() + "|"
                        + p.getPrice()+"\n");
            }
            
        } catch (IOException e) {
            System.out.println(e);
        } finally {
             try {output.close();} catch (Exception ex) {/*ignore*/}
        }
    }

    public static void insertProduct(Product product) {
        products = selectProducts();
        products.add(product);
        saveProducts(products);
    }

    public static void updateProduct(Product product) {
        products = selectProducts();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (product.getCode() != null
                  && product.getCode().equalsIgnoreCase(p.getCode())) {
                products.set(i, product);
            }
        }
        saveProducts(products);
    }
    public static void deleteProduct(Product product) {
        products = selectProducts();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (product != null
                  && product.getCode().equalsIgnoreCase(p.getCode())) {
                products.remove(i);
            }
        }
        saveProducts(products);
    }
}
