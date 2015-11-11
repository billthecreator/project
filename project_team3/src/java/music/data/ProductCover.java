/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import music.business.Product;

/**
 *
 * @author William
 */
public class ProductCover {
       
    
    public static void ProductCover() {}
    
    public static void getProductCover(Product product, String dest) throws IOException {
        try{
            
            String destName = dest + "\\" + product.getId().toString() + "_cover.jpg";
            
            if (!new File(destName).exists()) {           
                if(product.getCoverURL().length() > 0){
                    URL url = new URL(product.getCoverURL());
                    BufferedImage image = ImageIO.read(url);
                    ImageIO.write(image, "jpg",new File(destName));
                }
            }
 
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
