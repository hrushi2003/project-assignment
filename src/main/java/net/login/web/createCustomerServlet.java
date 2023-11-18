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

import net.customer_list.loginbean.*;


/**
 * Servlet implementation class createCustomerServlet
 */
@WebServlet("/create")
public class createCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() {
      
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	 getServletContext().getRequestDispatcher("/createNew.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String street  = request.getParameter("street_name");
		String address= request.getParameter("address_name");
		String city = request.getParameter("city_name");
		String state = request.getParameter("state_name");
		String email = request.getParameter("email_name");
		String phone = request.getParameter("phone_name");
		customer customer = new customer();
		customer.setFirst_name(first_name);
		customer.setLast_name(last_name);
		customer.setStreet(street);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setState(state);
		customer.setEmail(email);
		customer.setPhone(phone);
		
		
				//HttpSession session = request.getSession();
				// session.setAttribute("username",username);
				 String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create";
			       // String token = "<token_received_in_authentication_API_call>";

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
			                            "{\"first_name\":\""+customer.getFirst_name()+"\",\"last_name\":\""+customer.getLast_name()+"\",\"street\":\""+customer.getStreet()+"\",\"address\":\""+customer.getAddress()+"\",\"city\":\""+customer.getCity()+"\",\"state\":\""+customer.getState()+"\",\"email\":\""+customer.getEmail()+"\",\"phone\":\""+customer.getPhone()+"\"}"
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

			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        response.sendRedirect("http://localhost:8080/customers_list/register");
				 
		
	}

}
