import java.io.*;
import java.util.*;
import java.util.Map.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
    	// Used as Main.java
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		// frontend.jsp
		if(request.getParameter("keyword")== null) { 
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("frontend.jsp").forward(request, response);
			return;
		}
		// googleitem.jsp
		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		HashMap<String, String> query = google.query();
		
		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for(Entry<String, String> entry : query.entrySet()) { 
			String key = entry.getKey();
			String value = entry.getValue();
			s[num][0] = key;//title
			s[num][1] = value;//url
			num++;
		}
		
		@SuppressWarnings("static-access")
		KeywordList kLst = google.keywordList;
		
		for(int i = 0 ; i < kLst.lst.size() ; i++) {
			 s[i][0] = kLst.lst.get(i).name;
			 s[i][1] = kLst.lst.get(i).url;
		}
		
		//relative keyword
		String[] r = new String[google.relative.size()];
		request.setAttribute("relative", r);
		int i = 0;
		for(String entry : google.relative) {
			r[i] = entry;
			i++;
		}
				
		request.getRequestDispatcher("googleitem.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}