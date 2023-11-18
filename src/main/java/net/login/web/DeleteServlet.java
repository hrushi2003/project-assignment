package net.login.web;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
     private String id;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customer_id = request.getParameter("customer_id");
		 id = customer_id;
		 
		 String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid="+id;
		 
		 try {
	            // Create an instance of HttpClient
	            HttpClient httpClient = HttpClient.newHttpClient();
	            HttpSession session = request.getSession();
	            System.out.println("token session id is:"+session.getAttribute("token"));

	            // Build the request
	            HttpRequest requestApi = HttpRequest.newBuilder()
	                    .uri(URI.create(apiUrl))
	                    .header("Content-Type", "application/json")
	                    .header("Authorization", "Bearer " + session.getAttribute("token"))
	                    .POST(HttpRequest.BodyPublishers.ofString(
	                            "{\"}"
	                    ))
	                    .build();

	            // Send the request and get the response
	            HttpResponse<String> responseApi = httpClient.send(requestApi, BodyHandlers.ofString());

	            // Print the status code
	            System.out.println("Status Code: " + responseApi.statusCode());

	            // Print the response body
	            System.out.println("Response Body: " + responseApi.body());

	            // Print the headers
	           // HttpHeaders headers = responseApi.headers();
	           // headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
	           response.sendRedirect("http://localhost:8080/customers_list/register");
	       
	
}catch(Exception e) {
e.printStackTrace();

}
		
		
		
	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
