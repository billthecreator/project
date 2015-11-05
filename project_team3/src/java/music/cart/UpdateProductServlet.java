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
        
        //get the path of the file containing the products
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");
        //create a product list holder
        ProductIO prodIO = new ProductIO();
        prodIO.init(path);
        //put the list of products in a List
        ArrayList<Product> products = prodIO.selectProducts();
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
            
            //if the product code has any value 
            if (productCode != null){
                Product newProduct = new Product();
                newProduct.setCode(productCode);
                newProduct.setDescription(productArtist + " - " + productAlbum);
                
                url = "/loadProducts";   
                try {
                     newProduct.setPrice(Double.parseDouble(productPrice));
                     
                    if (prodIO.exists(newProduct.getCode())){
                        // if this product exists in the list, then update it
                        prodIO.updateProduct(newProduct);
                    } else {
                        // if this product doesn't exist, create a new one
                        prodIO.insertProduct(newProduct);
                    }
                 
                     
                } catch(NumberFormatException e){
                    session.setAttribute("product", newProduct);
                    session.setAttribute("e", e);
                    
                    
                    url = "/addProduct.jsp";   
                }
                
          
            } else {
                url = "/addProduct.jsp";
            }
            
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
