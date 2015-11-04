package music.cart;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;

import music.data.*;
import music.business.*;

public class ProductsServlet extends HttpServlet {

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
        List<Product> products = prodIO.selectProducts();
        //set the List to the "products" attribute
        session.setAttribute("products", products);
        
        //default landing page
        String url = "/productMaint.jsp";
        //create the action 
        String action = request.getParameter("action");
        if (action == null) {
            //default action
            action = "displayProducts"; 
        }
        
        //actions:
        if (action.equals("displayProducts")) {
            //default action, just show the list of products
            url = "/productMaint.jsp";    
        } 
        else if (action.equals("addProduct")) {
            //adding or editing a product
            String productCode = request.getParameter("productCode");
            
            url = "/addProduct.jsp";
            if (productCode != null) {
                //if the product code not not a null value,
                //grab the details from the product code
                session.setAttribute("productCode", productCode);
                session.setAttribute("productDesc", prodIO.selectProduct(productCode).getDescription());
                session.setAttribute("productPrice", prodIO.selectProduct(productCode).getPrice());
            }
            else {
                //if the product code is null, set everything blank
                //so the the product is new
                session.setAttribute("productCode", "");
                session.setAttribute("productDesc", "");
                session.setAttribute("productPrice", "");
            }
            
        }
        else if (action.equals("updateProduct")) {
            //create or update the product based on the product code.
            String productCode = request.getParameter("productCode");
            String productDesc = request.getParameter("productDesc");
            String productPrice= request.getParameter("productPrice");
            
            //if the product code has any value 
            if (productCode != null || !productCode.isEmpty()){
                Product newProduct = new Product();
                newProduct.setCode(productCode);
                newProduct.setDescription(productDesc);
                newProduct.setPrice(Double.parseDouble(productPrice));
                
                if (prodIO.exists(newProduct.getCode())){
                    // if this product exists in the list, then update it
                    prodIO.updateProduct(newProduct);
                } else {
                    // if this product doesn't exist, create a new one
                    prodIO.insertProduct(newProduct);
                }
                url = "/productMaint.jsp?action=displayProducts";
                //#####
                //change this to make it work. manual refresh at the moment
            } else {
                url = "/addProduct.jsp";
            }
            
        }
        else if (action.equals("removeProduct")) {
            //remove the product from the list
            String productCode = request.getParameter("productCode");
            
            if (productCode != null || !productCode.isEmpty()){
                Product productToDelete = prodIO.selectProduct(productCode);
                                
                if (prodIO.exists(productToDelete.getCode())){
                    // if this product exists in the list, then update it
                    prodIO.deleteProduct(productToDelete);
                } else {
                }
                url = "/productMaint.jsp";    
            } else {
                url = "/addProduct.jsp";
            }
            
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}