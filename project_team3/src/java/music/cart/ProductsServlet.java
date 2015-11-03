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
        ProductIO prodIO = new ProductIO();
        prodIO.init(path);
        
        List<Product> products = prodIO.selectProducts();
        session.setAttribute("products", products);
        
        // create the action 
        String action = request.getParameter("action");
        if (action == null) {
            action = "displayProducts";  // default action
        }

        String url = "/productMaint.jsp";
        if (action.equals("displayProducts")) {
            url = "/productMaint.jsp";    
        } 
        else if (action.equals("addProduct")) {
            String productCode = request.getParameter("productCode");
            
            url = "/addProduct.jsp";
            if (productCode != null) {
                
                session.setAttribute("productCode", productCode);
                session.setAttribute("productDesc", prodIO.selectProduct(productCode).getDescription());
                session.setAttribute("productPrice", prodIO.selectProduct(productCode).getPrice());
            }
            else {
                session.setAttribute("productCode", "");
                session.setAttribute("productDesc", "");
                session.setAttribute("productPrice", "");
            }
            
        }
        else if (action.equals("updateProduct")) {
            String productCode = request.getParameter("productCode");
            String productDesc = request.getParameter("productDesc");
            String productPrice= request.getParameter("productPrice");
            
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