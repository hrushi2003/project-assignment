package net.login.web;

import org.json.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.customer_list.loginbean.*;


@WebServlet("/register")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public void init() {
		
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
	     
		
			   ArrayList<customer> customerLt = new ArrayList<customer>();
		
			String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
		       // String token = "<token_received_in_authentication_API_call>";

		        try {
		            // Create an instance of HttpClient
		            HttpClient httpClient = HttpClient.newHttpClient();
		            HttpSession session = request.getSession();
		           

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
		            
		            
		            String bearerToken = "{data:"+responseApi.body() +"}";
	                String jsonString = bearerToken;
	                
	                JSONObject jsnobject = new JSONObject(jsonString);
	                JSONArray jsonArray = jsnobject.getJSONArray("data");
	                for (int i = 0; i < jsonArray.length(); i++){
	                    JSONObject explrObject = jsonArray.getJSONObject(i);
	                    String id = explrObject.getString("uuid");
	                    String first_name = explrObject.getString("first_name");
	                    String last_name = explrObject.getString("last_name");
	                    String street = explrObject.getString("street");
	                    String address = explrObject.getString("address");
	                    String city = explrObject.getString("city");
	                    String state = explrObject.getString("state");
	                    String email = explrObject.getString("email");
	                    String phone = explrObject.getString("phone");
	                    customer Customer = new customer();
	        				Customer.setId(id);
	        				Customer.setFirst_name(first_name);
	        				Customer.setLast_name(last_name);
	        				Customer.setStreet(street);
	        				Customer.setAddress(address);
	        				Customer.setCity(city);
	        				Customer.setState(state);
	        				Customer.setEmail(email);
	        				Customer.setPhone(phone);
	        				customerLt.add(Customer);
	                   
	                }

		            // Print the headers
		           // HttpHeaders headers = responseApi.headers();
		           // headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			request.setAttribute("data", customerLt);
			
		 getServletContext().getRequestDispatcher("/customers_data.jsp").forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		loginBean loginBean = new loginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		
				//HttpSession session = request.getSession();
				// session.setAttribute("username",username);
				 String authUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

			        try {
			            // Create an instance of HttpClient
			            HttpClient httpClient = HttpClient.newHttpClient();

			            // Build the authentication request
			            HttpRequest authRequest = HttpRequest.newBuilder()
			                    .uri(URI.create(authUrl))
			                    .header("Content-Type", "application/json")
			                    .POST(HttpRequest.BodyPublishers.ofString("{\"login_id\":\""+loginBean.getUsername()+"\",\"password\":\""+loginBean.getPassword()+"\"}"))
			                    .build();

			            // Send the authentication request and get the response
			            HttpResponse<String> authResponse = httpClient.send(authRequest, BodyHandlers.ofString());

			            if (authResponse.statusCode() == 200) {
			                // Extract the bearer token from the response
			                String bearerToken = authResponse.body();
			                String jsonString = bearerToken;
		
			                JSONObject obj = new JSONObject(jsonString);
			                String token = obj.getString("access_token");
			                HttpSession session = request.getSession();
			                session.setAttribute("token", token);
			                
			     
			            } else {
			                System.out.println("Authentication failed. Status Code: " + authResponse.statusCode());
			            }

			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			        doGet(request,response);
			
	}
}
