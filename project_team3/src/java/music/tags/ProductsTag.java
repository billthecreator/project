package music.tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.util.*;
import java.io.IOException;

import music.business.*;

public class ProductsTag extends BodyTagSupport {

    private List<Product> lineItems;
    private Iterator iterator;
    private Product item;

    @Override
    public int doStartTag() {
        ArrayList<Product> products = (ArrayList) pageContext.findAttribute("products");
        lineItems = products;
        if (lineItems.size() <= 0) {
            return SKIP_BODY;
        } else {
            return EVAL_BODY_BUFFERED;
        }
    }

    @Override
    public void doInitBody() throws JspException {
        iterator = lineItems.iterator();
        if (iterator.hasNext()) {
            item = (Product) iterator.next();
            this.setItemAttributes(item);
        }
    }

    private void setItemAttributes(Product item) {
        Product p = item;
        pageContext.setAttribute(
                "productCode", p.getCode());
        pageContext.setAttribute(
                "productDescription", p.getDescription());
        pageContext.setAttribute(
                "productArtist", p.getArtistName());
        pageContext.setAttribute(
                "productAlbum", p.getAlbumName());
        pageContext.setAttribute(
                "productCover", p.getImageURL());
        pageContext.setAttribute(
                "productPrice", p.getPriceCurrencyFormat());
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            if (iterator.hasNext()) {
                item = (Product) iterator.next();
                this.setItemAttributes(item);
                return EVAL_BODY_AGAIN;
            } else {
                JspWriter out = bodyContent.getEnclosingWriter();
                bodyContent.writeOut(out);
                return SKIP_BODY;
            }
        } catch (IOException ioe) {
            System.err.println("error in doAfterBody " + ioe.getMessage());
            return SKIP_BODY;
        }
    }
}