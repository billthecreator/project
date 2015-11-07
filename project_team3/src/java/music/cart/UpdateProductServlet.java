/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import music.business.Product;
import music.data.ProductIO;
import music.data.ProductDB;

/**
 *
 * @author William
 */
@WebServlet(name = "updateProductServlet", urlPatterns = {"/update"})
public class UpdateProductServlet extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
                
        //put the list of products in a List
        ArrayList<Product> products = ProductDB.selectProducts();
        
        //set the List to the "products" attribute
        session.setAttribute("products", products);
        
        //default landing page
        String url = "/loadProducts";
        
        String action = request.getParameter("action");
        if (action == null) {
            //default action
            action = "updateProduct"; 
        }
        
        if (action.equals("updateProduct")) {
            //create or update the product based on the product code.
            String productCode = request.getParameter("productCode");
            String productArtist = request.getParameter("productArtist");
            String productAlbum = request.getParameter("productAlbum");
            String productPrice= request.getParameter("productPrice");
            String message = "";
            int errorCode = 0;
            
            //if the product code has any value 
            if (productCode != null){
                if(productCode == null || productCode.length() == 0) {
                    message += "<i class=\"fa fa-warning\"></i>Please enter a code<br/>";
                    errorCode += 2;
                }
                if(productArtist == null || productArtist.length() == 0) {
                    message += "<i class=\"fa fa-warning\"></i>Please enter an artist name<br/>";
                    errorCode += 4;
                }
                if(productAlbum == null || productAlbum.length() == 0) {
                    message += "<i class=\"fa fa-warning\"></i>Please enter an album name<br/>";
                    errorCode += 8;
                }
                if(productPrice == null || productPrice.length() == 0) {
                    message += "<i class=\"fa fa-warning\"></i>Please enter a price<br/>";
                    errorCode += 1;
                }else{
                    try{
                        Double.parseDouble(productPrice);
                    }catch(NumberFormatException e){
                        message += "<i class=\"fa fa-warning\"></i>The price must be numeric<br/>";
                        errorCode += 16;
                    }
                }
                
                
                
                
                if(errorCode >= 1){
                    // all fields blank
                    session.setAttribute("e", errorCode);
                    session.setAttribute("message", message);
                    
                    Product newProduct = new Product();
                    newProduct.setCode(productCode);
                    newProduct.setDescription(productArtist + " - " + productAlbum);
                    
                    try{
                        double newPrice = Double.parseDouble(productPrice);
                        newProduct.setPrice(newPrice);
                    }catch(NumberFormatException e){
                    }
                    
                    session.setAttribute("product", newProduct);
                    
                }
                    
                if(errorCode == 0){
                                       
                    Product newProduct = new Product();
                    newProduct.setCode(productCode);
                    newProduct.setDescription(productArtist + " - " + productAlbum);
                    
                    url = "/loadProducts";   
                    try {
                        //test to see if price is double
                         newProduct.setPrice(Double.parseDouble(productPrice));
                        //cleared - update, or insert
                        if (ProductDB.codeExists(newProduct.getCode())){
                            // if this product exists in the list, then update it
                            ProductDB.update(newProduct);
                        } else {
                            // if this product doesn't exist, create a new one
                            ProductDB.insert(newProduct);
                        }
                    } catch(NumberFormatException e){
                        //the price is not a double
                        
                      
                        session.setAttribute("product", newProduct);
                        message += "<i class=\"fa fa-warning\"></i>Price must be numeric<br/>";
                                
                        session.setAttribute("e", e);
                        session.setAttribute("message", message);

                        url = "/addProduct.jsp";   
                    }          
                } else {
                    url = "/addProduct.jsp";
                }
            }
            
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
