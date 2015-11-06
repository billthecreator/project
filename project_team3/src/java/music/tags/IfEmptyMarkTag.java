/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.tags;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
//import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

/**
 *
 * @author William
 */
public class IfEmptyMarkTag extends TagSupport {

    private String field;
    private String color = "#00529B";
    
    public void setField(String field) {
        this.field = field;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public int doStartTag() throws JspException {
        
        try {
            JspWriter out = pageContext.getOut();
            if (field == null || field.length() == 0){
                out.print("<font color=" + color + "> *</font>");
            }
        } catch (java.io.IOException ex) {
            System.out.println(ex);
        }
        return SKIP_BODY;
    }
    
}
