<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="language" content="en" />
    
    <link rel="stylesheet" type="text/css" href="js/ext-4/resources/css/ext-all.css" />
    
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <script type="text/javascript" src="js/ext-4/ext-all-debug.js"></script>
    
    
    
    <script type="text/javascript">
    	
    	Ext.Loader.setConfig({enabled: true});
    	Ext.Loader.setPath("AM", "js/app");
    	Ext.onReady(function(){
    		var login = Ext.create("AM.view.user.Login",{
    			
    		});
    		
    	});    
    </script>
   
	<title> <fmt:message key="app.title" /></title>
</head>
<body>

</body>
</html>
