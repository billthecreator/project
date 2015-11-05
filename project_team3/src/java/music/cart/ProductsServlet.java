package music.cart;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.lang.NumberFormatException;
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
        ArrayList<Product> products = prodIO.selectProducts();
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
                session.setAttribute("product",  prodIO.selectProduct(productCode));
//                session.setAttribute("productCode", productCode);
//                session.setAttribute("productDesc", prodIO.selectProduct(productCode).getDescription());
//                session.setAttribute("productPrice", prodIO.selectProduct(productCode).getPrice());
            }
            else {
                //if the product code is null, set everything blank
                //so the the product is new
                session.setAttribute("productCode", null);
                session.setAttribute("productDesc", "");
                session.setAttribute("productPrice", "");
            }
            NumberFormatException e = null;
            session.setAttribute("e", e);
            
        }
        else if (action.equals("removeProduct")) {
            //clicking delete will grab the infomation and send it to the delete jsp
            String productCode = request.getParameter("productCode");
                session.setAttribute("product",  prodIO.selectProduct(productCode));
//                session.setAttribute("productCode", productCode);
//                session.setAttribute("productDesc", prodIO.selectProduct(productCode).getDescription());
//                session.setAttribute("productPrice", prodIO.selectProduct(productCode).getPrice());
            url = "/deleteProduct.jsp";
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}