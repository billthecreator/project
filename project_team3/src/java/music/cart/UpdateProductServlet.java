package music.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import music.business.Product;
import music.business.ProductError;
import music.color.ColorPalette;
import music.data.ProductCover;
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
        List<Product> products = ProductDB.selectProducts();
        
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
            long productId;
            try { 
                productId = Long.parseLong(request.getParameter("productId"));                
            } catch (Exception e) {
                
            }
           
            
            String productCode      = request.getParameter("productCode");
            String productArtist    = request.getParameter("productArtist");
            String productAlbum     = request.getParameter("productAlbum");
            String productPrice     = request.getParameter("productPrice");
            String productCoverURL  = request.getParameter("productCoverURL");
            
            ProductError prodError = new ProductError();
            
            
            String message = "";
            int errorCode = 0;
            
            //if the product code has any value 
            if (productCode != null){
                if(productCode == null || productCode.length() == 0) {
//                    message += "<i class=\"fa fa-warning\"></i>Please enter a code<br/>";
                    prodError.setCodeError(true);
                    errorCode = 1;
                    
                }
                if(productArtist == null || productArtist.length() == 0) {
//                    message += "<i class=\"fa fa-warning\"></i>Please enter an artist name<br/>";
                    prodError.setArtistError(true);
                    errorCode = 1;
                }
                if(productAlbum == null || productAlbum.length() == 0) {
//                    message += "<i class=\"fa fa-warning\"></i>Please enter an album name<br/>";
                    prodError.setAlbumError(true);
                    errorCode = 1;
                }
                if(productPrice == null || productPrice.length() == 0) {
//                    message += "<i class=\"fa fa-warning\"></i>Please enter a price<br/>";
                    prodError.setPriceError(true);
                    errorCode = 1;
                }else{
                    try{
                        Double.parseDouble(productPrice);
                    }catch(NumberFormatException e){
//                        message += "<i class=\"fa fa-warning\"></i>The price must be numeric<br/>";
                        prodError.setPriceError2(true);
                    errorCode = 1;
                        
                    }
                }
                
                
                
                
                if(errorCode >= 1){
                    // all fields blank                    
                    Product newProduct = new Product();
//                    newProduct.setId(productId);
                    newProduct.setCode(productCode);
                    newProduct.setDescription(productArtist + " - " + productAlbum);
                    newProduct.setCoverURL(productCoverURL);
                    
                    try{
                        double newPrice = Double.parseDouble(productPrice);
                        newProduct.setPrice(newPrice);
                    }catch(NumberFormatException e){
                    }
                    
                    session.setAttribute("product", newProduct);
                    session.setAttribute("prodError", prodError);
                    
                }
                    
                if(errorCode == 0){
                                       
                    Product newProduct = new Product();
//                    newProduct.setId(productId);
                    newProduct.setCode(productCode);
                    newProduct.setDescription(productArtist + " - " + productAlbum);
                    newProduct.setCoverURL(productCoverURL);
                    
                    ColorPalette palette = new ColorPalette();
                    session.setAttribute("pageColor", palette.updatePrimary500);
                    session.setAttribute("pageAccentColor", palette.updateSecondary500);
                    url = "/loadProducts";   
                    try {
                        //test to see if price is double
                         newProduct.setPrice(Double.parseDouble(productPrice));
                        //cleared - update, or insert
                        if (ProductDB.exists(newProduct.getCode())){
                            // if this product exists in the list, then update it
                            ProductDB.update(newProduct);
                        } else {
                            // if this product doesn't exist, create a new one
                            ProductDB.insert(newProduct);
                        }
                        try {
                            String dest = getServletContext().getRealPath("musicStore/images/");
                            ProductCover.getProductCover(newProduct, dest);
                        } catch (Exception e) {
                            prodError.setCoverURLError(true);
                        }
                        session.setAttribute("pageColor", palette.defaultPrimary500);
                        session.setAttribute("pageAccentColor", palette.defaultSecondary500);
                        
                    } catch(NumberFormatException e){
                        //the price is not a double
                        
                      
                        session.setAttribute("product", newProduct);
                        prodError.setPriceError2(true);
                        session.setAttribute("prodError", prodError);

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
