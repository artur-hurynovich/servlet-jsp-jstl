package servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
@WebServlet(name = "crosses", urlPatterns = "/crosses")
public class CrossesServlet extends HttpServlet {
    private ArrayList<String[]> result;
    private DBAccessor accessor;
    @Override
    public void init() throws ServletException {
        super.init();
        accessor = new DBAccessor();
        result = new ArrayList<>();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("search");
        result = accessor.getCrosses(search);
        request.setAttribute("search", search.toUpperCase());
        request.setAttribute("result", result);
        try {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        catch (ServletException |IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void destroy() {
        accessor.close();
        super.destroy();
    }
}
