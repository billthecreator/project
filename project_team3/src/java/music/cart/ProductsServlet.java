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
        
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");
        ProductIO.init(path);
        
        List<Product> products = ProductIO.selectProducts();
        session.setAttribute("products", products);
        
        // create the action 
        String action = request.getParameter("action");
        if (action == null) {
            action = "displayProducts";  // default action
        }

        String url = "/productMaint.jsp";
        if (action.equals("displayProducts")) {
            url = "/productMaint.jsp";    // the "index" page
        } 
        else if (action.equals("addProduct")) {
            String productCode = request.getParameter("productCode");
            
            url = "/addProduct.jsp";
            if (productCode != null) {
                
                session.setAttribute("productCode", productCode);
                session.setAttribute("productDesc", ProductIO.selectProduct(productCode).getDescription());
                session.setAttribute("productPrice", ProductIO.selectProduct(productCode).getPrice());
            }
            
        }
        else if (action.equals("checkout")) {
            url = "/checkout.jsp";
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