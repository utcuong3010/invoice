<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="language" content="en" />
    
    <link rel="stylesheet" type="text/css" href="js/ext-4/resources/css/ext-all.css" />
    
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <script type="text/javascript" src="js/ext-4/ext-all-debug.js"></script>
    
    <script type="text/javascript" src="js/ext-4/locale/ext-lang-vn.js"></script> 
    <script type="text/javascript" src="js/ext-4/locale/app-vn.js"></script> 
    
     <script type="text/javascript" src="js/app.js"></script>  
    
    <%
        	
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();    
    
    %>
    
    <script type="text/javascript">   
    var isAdminRole = false;
    var isCreateRole = false;
    var isEditRole = false;
    var isDeleteRole = false;
    
    var username = '<%=username%>';
   
    </script>

    
    <sec:authorize access="hasRole('ROLE_ADMIN')">	
   		<script type="text/javascript">   	
   			 isAdminRole = true;
		</script>
	</sec:authorize>
	 <sec:authorize access="hasRole('ROLE_EDIT')">	
   		<script type="text/javascript"> 
   			isEditRole = true;
		</script>
	</sec:authorize>
	 <sec:authorize access="hasRole('ROLE_CREATE')">	
   		<script type="text/javascript">
   			isCreateRole = true;
		</script>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_DELETE')">	
   		<script type="text/javascript">
   		isDeleteRole = true;
		</script>
	</sec:authorize>
	
	<script type="text/javascript">
		var authenticate = {
				isAdminRole: isAdminRole,
				isCreateRole: isCreateRole,
				isEditRole: isEditRole,
				isDeleteRole: isDeleteRole
		};
	</script>
    
    
        
	<title> <fmt:message key="app.title" /></title>
	
	
	
	
</head>
<body>



</body>
</html>
