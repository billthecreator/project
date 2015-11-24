package music.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import music.color.ColorPalette;

import music.business.*;
import music.data.*;

public class AdminController extends HttpServlet {

    ColorPalette palette = new ColorPalette();
    List<Product> products = ProductDB.selectProducts();
    HttpSession session;  
    
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        
        session = request.getSession();
        products = ProductDB.selectProducts();
        session.setAttribute("products", products);
        
        String requestURI = request.getRequestURI();
        String url = "/admin";
        if (requestURI.endsWith("/displayProducts")) {
            url = displayProducts(request, response);
        } else if (requestURI.endsWith("/deleteProduct")) {
            url = deleteProduct(request, response);
        } else if (requestURI.endsWith("/updateProduct")) {
            url = updateProduct(request, response);
        } else if (requestURI.endsWith("/logout")){
            url = "/admin/logout.jsp";
        } else if (requestURI.endsWith("/dashboard")){
            url = "/admin/index.jsp";
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        session = request.getSession();
        products = ProductDB.selectProducts();
        session.setAttribute("products", products);
        
        String requestURI = request.getRequestURI();
        String url = "/admin";
        if (requestURI.endsWith("/updateProduct")) {
            url = updateProductNOW(request, response);
        } else if (requestURI.endsWith("/deleteProduct")) {
            url = deleteProductNOW(request, response);
        }
        
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String deleteProductNOW(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //remove the product from the list
        String productCode = request.getParameter("productCode");
        String url = "/admin/listProducts.jsp";   
        if (productCode != null || !productCode.isEmpty()){
            Product productToDelete = ProductDB.selectProduct(productCode);

            if (ProductDB.exists(productToDelete.getId())){
                // if this product exists in the list, then delete it
                ProductDB.delete(productToDelete);
            }
            //return to the list of products after deletion or cancelation  
        }
        session.setAttribute("pageColor", palette.defaultPrimary500);
        session.setAttribute("pageAccentColor", palette.defaultSecondary500);
        return url;
    }
    private String updateProductNOW(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        
            //create or update the product based on the product code.
            long productId = 0;
            try { 
                productId = Long.parseLong(request.getParameter("productId"));                
            } catch (Exception e) {}
           
            String productCode      = request.getParameter("productCode");
            String productArtist    = request.getParameter("productArtist");
            String productAlbum     = request.getParameter("productAlbum");
            String productPrice     = request.getParameter("productPrice");
            String productCoverURL  = request.getParameter("productCoverURL");
            
            ProductError prodError = new ProductError();
            String url = "/admin/addProduct.jsp";
            
            String message = "";
            int errorCode = 0;
            
            //if the product code has any value 
            if (productCode != null){
                if(productCode.length() == 0) {
                    prodError.setCodeError(true);
                    errorCode = 1;
                    
                }
                if(productArtist == null || productArtist.length() == 0) {
                    prodError.setArtistError(true);
                    errorCode = 1;
                }
                if(productAlbum == null || productAlbum.length() == 0) {
                    prodError.setAlbumError(true);
                    errorCode = 1;
                }
                if(productPrice == null || productPrice.length() == 0) {
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
                
                if (ProductDB.exists(productCode)) {
                    if (productId != ProductDB.selectProduct(productCode).getId()) {
                        prodError.setCodeError2(true);
                        errorCode = 1;
                    }
                }
                
                if(errorCode >= 1){
                    // all fields blank                    
                    Product newProduct = new Product();
                    newProduct.setId(productId);
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
                    if(productId > 0) {
                        newProduct.setId(productId);
                    }
                    newProduct.setCode(productCode);
                    newProduct.setDescription(productArtist + " - " + productAlbum);
                    newProduct.setCoverURL(productCoverURL);
                    
                    ColorPalette palette = new ColorPalette();
                    session.setAttribute("pageColor", palette.updatePrimary500);
                    session.setAttribute("pageAccentColor", palette.updateSecondary500);
                    url = "/admin/listProducts.jsp";   
                    try {
                        //test to see if price is double
                         newProduct.setPrice(Double.parseDouble(productPrice));
                        //cleared - update, or insert
                        
                        if (ProductDB.exists(productId)){
                            if (ProductDB.selectProduct(productId).getId() == productId) {
                                // if a product already exists using the given code, AND
                                // that product's ID matches the same as the pages, THEN
                                // UPDATE
                                ProductDB.update(newProduct);
                            }
                            
                            // if this product exists in the list, then update it
                        } else {
                            // if this product doesn't exist, create a new one
                            ProductDB.insert(newProduct);
                        }
                        try {
                            String dest = getServletContext().getRealPath("../musicStore/images/");
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
 
                    }          
                } else {
                    
                }
            }
            
        return url;
    }
    
    private String displayProducts(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        
        session.setAttribute("pageColor", palette.defaultPrimary500);
        session.setAttribute("pageAccentColor", palette.defaultSecondary500);
        
        String url = "/admin/listProducts.jsp";
        return url;
    }
    
    private String deleteProduct(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        
        
        String productCode = request.getParameter("productCode");
        session.setAttribute("product",  ProductDB.selectProduct(productCode));
        session.setAttribute("pageColor", palette.deletePrimary500);
        session.setAttribute("pageAccentColor", palette.deleteSecondary500);
        String url = "/admin/deleteProduct.jsp";
        return url;
    }
    
    private String updateProduct(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
       
            String productCode = null;
            String url = "/admin/addProduct.jsp";
            //adding or editing a product
            try {
                productCode = request.getParameter("productCode");
                session.setAttribute("product",  ProductDB.selectProduct(productCode));
            } catch (Exception e) {
                productCode = null;
                session.setAttribute("product",  new Product());
            }
            
            ProductError prodError = new ProductError();
//            NumberFormatException e = null;
            session.setAttribute("prodError", prodError);
            session.setAttribute("pageColor", palette.updatePrimary500);
            session.setAttribute("pageAccentColor", palette.updateSecondary500);
            
            return url;
            
    }
    
}