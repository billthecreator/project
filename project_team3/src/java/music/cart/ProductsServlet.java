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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String path = getServletContext().getRealPath("/WEB-INF/products.txt");
        ProductIO.init(path);
        List<Product> products = ProductIO.selectProducts();
        session.setAttribute("products", products);

        String url = "/productMaint.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}