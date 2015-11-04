/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
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
public class DeleteProductServlet extends HttpServlet {


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
        String url = "/productMaint.jsp";
        
        String action = request.getParameter("action");
        if (action == null) {
            //default action
            action = "confirmDeletion"; 
        }
        if (action.equals("confirmDeletion")) {         
            
            //remove the product from the list
            String productCode = request.getParameter("productCode");
            
            if (productCode != null || !productCode.isEmpty()){
                Product productToDelete = prodIO.selectProduct(productCode);
                                
                if (prodIO.exists(productToDelete.getCode())){
                    // if this product exists in the list, then delete it
                    prodIO.deleteProduct(productToDelete);
                }
                //return to the list of products after deletion or cancelation 
                url = "/loadProducts";    
            }
            
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }


}
