<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="java.util.ArrayList"%>
   <%@page import="net.customer_list.loginbean.customer"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customers data</title>
</head>
<body>
   <form action="<%=request.getContextPath()%>/create" method="get">
     <input type ="submit" value ="createCustomer">
   </form>
   <br />
    
   <table border = "1px" width ="1000">
   <tr>
   <th>First Name</th>
   <th>Last Name</th>
   <th>Address</th>
   <th>City</th>
   <th>State</th>
   <th>Email</th>
   <th>phone</th>
   <th>Action</th>
   </tr>
    <%ArrayList<customer> data =  
            (ArrayList<customer>)request.getAttribute("data"); 
        for(customer s:data){%> 
        <%-- Arranging data in tabular form 
        --%> 
            <tr> 
                <td><%=s.getFirst_name() %></td> 
                <td><%=s.getLast_name() %></td> 
                <td><%=s.getStreet() %></td> 
                 <td><%=s.getAddress() %></td> 
                  <td><%=s.getCity() %></td> 
                   <td><%=s.getEmail() %></td> 
                    <td><%=s.getPhone() %></td> 
                   
                   
                      <td>
                      <form action="<%=request.getContextPath()%>/EditServlet" method="get">
                      <input type = "hidden" name="customer_id" value = "<%= s.getId() %>">
                       <input type ="submit" value ="edit">
                         </form>
                         <form action= "<%=request.getContextPath()%>/DeleteServlet" method="get">
                         <input type = "hidden" name="customer_id" value="<%= s.getId() %>">
                         <input type ="submit" width="20" value ="delete">
                           </form>
                      </td> 
            </tr> 
            <%}%> 
   </table>
</body>
</html>