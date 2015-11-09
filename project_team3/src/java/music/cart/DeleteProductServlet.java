package music.cart;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import music.business.Product;
import music.color.ColorPalette;
import music.data.ProductDB;

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
                
        //put the list of products in a List
        ArrayList<Product> products = ProductDB.selectProducts();
        
        //set the List to the "products" attribute
        session.setAttribute("products", products);
        
        //default landing page
        String url = "/productMaint.jsp";
        
        ColorPalette palette = new ColorPalette();
        session.setAttribute("pageColor", palette.defaultPrimary500);
        session.setAttribute("pageAccentColor", palette.defaultSecondary500);

        String action = request.getParameter("action");
        if (action == null) {
            //default action
            action = "confirmDeletion"; 
        }
        if (action.equals("confirmDeletion")) {         
            
            //remove the product from the list
            String productCode = request.getParameter("productCode");
            
            if (productCode != null || !productCode.isEmpty()){
                Product productToDelete = ProductDB.selectProduct(productCode);
                                
                if (ProductDB.codeExists(productToDelete.getCode())){
                    // if this product exists in the list, then delete it
                    ProductDB.delete(productToDelete);
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
