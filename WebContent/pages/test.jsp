<%@ page contentType="application/vnd.ms-excel" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>Inserting data in Excel Sheet Using JSP</title>
</head>
<body>
<br>
<br>
<fmt:message key="label.homepage.company.name" />
<table border="1px solid">
  <tr><th colspan="4"><b>RoseIdia Pvt Ltd.
 Team detail</b></th></tr>
 <tr>
  <th>Name</th>
  <th>Designation </th>
  <th>Contact Number</th>
  <th>Email Id</th>
  </tr>
  <tr>
  <td>Rajesh kumar</td>
  <td>Software Deveploper</td>
  <td>9891589173</td>
  <td>rajeshpatel_04@yahoo.co.in</td>
  </tr>
  <tr>
  <td>Santosh Kumar</td>
  <td>Web Designer</td>
  <td>9350534522</td>
  <td>skashyapshan@gmail.com</td>
  </tr>
  <tr>
  <td>Chandan</td>
  <td>Team Leader</td>
  <td>9911544678</td>
  <td>chandan3verma@gmail.com</td>
  </tr>
</table>
</body>
</html>