package music.cart;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.lang.NumberFormatException;
import music.data.*;
import music.business.*;
import music.color.ColorPalette;

public class ProductsServlet extends HttpServlet {
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();  
        
        ColorPalette palette = new ColorPalette();
                
        //put the list of products in a List
        ArrayList<Product> products = ProductDB.selectProducts();
        
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
            session.setAttribute("pageColor", palette.defaultPrimary500);
            session.setAttribute("pageAccentColor", palette.defaultSecondary500);
            url = "/productMaint.jsp";    
        } 
        else if (action.equals("addProduct")) {
            //adding or editing a product
            String productCode = request.getParameter("productCode");
            
            url = "/addProduct.jsp";
            if (productCode != null) {
                //if the product code not not a null value
                session.setAttribute("product",  ProductDB.selectProduct(productCode));
            }
            else {
                //if the product code is null, set everything blank
                //so the the product is new
                session.setAttribute("product",  new Product());
            }
            ProductError prodError = new ProductError();
//            NumberFormatException e = null;
            session.setAttribute("prodError", prodError);
            session.setAttribute("pageColor", palette.updatePrimary500);
            session.setAttribute("pageAccentColor", palette.updateSecondary500);
            
        }
        else if (action.equals("removeProduct")) {
            //clicking delete will grab the infomation and send it to the delete jsp
            String productCode = request.getParameter("productCode");
            session.setAttribute("product",  ProductDB.selectProduct(productCode));
            session.setAttribute("pageColor", palette.deletePrimary500);
            session.setAttribute("pageAccentColor", palette.deleteSecondary500);
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