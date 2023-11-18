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

import org.json.JSONArray;
import org.json.JSONObject;

import net.customer_list.loginbean.*;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private String id;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String customer_id = request.getParameter("customer_id");
		 id = customer_id;
		 
		 
			 customer Customer = new customer();
				String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
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
			                    .GET()
			                    .build();

			            // Send the request and get the response
			            HttpResponse<String> responseApi = httpClient.send(requestApi, BodyHandlers.ofString());

			            // Print the status code
			            System.out.println("Status Code: " + responseApi.statusCode());

			            // Print the response body
			            System.out.println("Response Body: " + responseApi.body());
			            
			            String bearerToken = "{data:"+responseApi.body() +"}";
		                String jsonString = bearerToken;
		                
		                JSONObject jsnobject = new JSONObject(jsonString);
		                JSONArray jsonArray = jsnobject.getJSONArray("data");
		                for (int i = 0; i < jsonArray.length(); i++){
		                	System.out.println("hello");
		                    JSONObject explrObject = jsonArray.getJSONObject(i);
		                    if(id.equals(explrObject.getString("uuid"))) {
		                   
		                    String id = explrObject.getString("uuid");
		                    String first_name = explrObject.getString("first_name");
		                    String last_name = explrObject.getString("last_name");
		                    String street = explrObject.getString("street");
		                    String address = explrObject.getString("address");
		                    String city = explrObject.getString("city");
		                    String state = explrObject.getString("state");
		                    String email = explrObject.getString("email");
		                    String phone = explrObject.getString("phone");
		           
		        				Customer.setId(id);
		        				Customer.setFirst_name(first_name);
		        				Customer.setLast_name(last_name);
		        				Customer.setStreet(street);
		        				Customer.setAddress(address);
		        				Customer.setCity(city);
		        				Customer.setState(state);
		        				Customer.setEmail(email);
		        				Customer.setPhone(phone);
		        				break;
		                    }
		                   
		                }
			        }catch(Exception e) {
			        	e.printStackTrace();
			        }
				
				request.setAttribute("data", Customer);
				
				 
		 
		 getServletContext().getRequestDispatcher("/edit_customer.jsp").forward(request, response);
		
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
		
		
			

		            // Check if the insertion was successful
		          
		        
		            	String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=update&uuid="+id;
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

					       
					
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		response.sendRedirect("http://localhost:8080/customers_list/register");
	}

}
