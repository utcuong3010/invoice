<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

{
    success: true,
    totalCount: <c:out value="${total}"/>
    invoices: [
     <c:if test="${!empty invoiceInfos}">     	   
     	<c:forEach items="${invoiceInfos}" var="invoiceInfo" varStatus="status">
     		{
	     		id: <c:out value="${invoiceInfo.id}"/>
	     		
     		}<c:if test="${!status.isLast()}">,</c:if> 		     	
     	</c:forEach>  
     </c:if>
    ]
}