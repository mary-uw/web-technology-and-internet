package loginregistration;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "SessionTimeoutServlet", urlPatterns = {"/checkSessionTimeout"})
public class SessionTimeoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the session is still valid
        if (request.getSession(false) == null || request.getSession().getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect to login page
        }
    }
}
