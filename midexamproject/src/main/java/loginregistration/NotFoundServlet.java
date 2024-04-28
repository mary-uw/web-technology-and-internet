package loginregistration;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;



public class NotFoundServlet extends HttpServlet {

	 public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		    
		 try {
	            // Pass request along the filter chain
	            filterChain.doFilter(servletRequest, servletResponse);
	        } catch (Throwable throwable) {
	            // Handle exception
	            servletRequest.getRequestDispatcher("/error-404.jsp").forward(servletRequest, servletResponse);
	            
	        }
	    }

	    @Override
	    public void destroy() {
	        // Cleanup code, if needed
	    }
}