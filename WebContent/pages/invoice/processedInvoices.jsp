<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${! empty invoiceInfos }">
<td colspan="2" >&nbsp;</td>
<td class="x-grid-cell-rowbody" colspan="8">

	<table cellspacing="0" cellpadding="0" border="0" class="table_list">
	  <tbody>
	    <tr class="head">
	      <td width="1%" class="head_bor"><fmt:message key="label.invoice.no" /></td>
	      <td width="3%" class="head_bor"><fmt:message key="label.invoice.invoice_no" /></td>
	      <td width="6%" class="head_bor"><fmt:message key="label.invoice.invoice_date" /></td>
	      <td width="6%" class="head_bor"><fmt:message key="label.invoice.vendor.name" /></td>
	      <td width="5%" class="head_bor"><fmt:message key="label.invoice.product.name" /></td>
	      <td width="5%" class="head_bor"><fmt:message key="label.invoice.money" /></td>
	      <td width="5%" class="head_bor"><fmt:message key="label.invoice.create_date" /></td>
	    </tr>
	 	<% long totalProcessedMoney = 0; %>
	 	<c:forEach items="${invoiceInfos}" var="invoiceInfo" varStatus="status">
		 	<tr class="row<c:out value="${status.count%2}" />">	
		 	  <td align="left"><c:out value="${status.count}" /></td>
		      <td align="left"><c:out value="${invoiceInfo.invoiceNo}" /></td>
		      <td align="left"><fmt:formatDate value="${invoiceInfo.invoiceDate}" pattern="dd/MM/yyyy"/></td>
		      <td align="left"><c:out value="${invoiceInfo.vendorInfo.username}"/></td>
		      <td align="left"><c:out value="${invoiceInfo.productName}"/></td>
		      <td align="right"><fmt:formatNumber pattern="#,###" value="${invoiceInfo.money}"/></td>
		      <td align="left"><fmt:formatDate value="${invoiceInfo.createDate}" pattern="dd/MM/yyyy HH:mm:ss"/></td>		      
		    </tr>
	 	</c:forEach>
	 	    
	    <tr class="row2">
	      <td align="left"></td>
	      <td align="left"></td>
	      <td align="left"></td>
	      <td align="left"></td>
	      <td align="left"></td>
	      <td align="right"><b><fmt:message key="label.invoice.total_money" />      <fmt:formatNumber value="${totalMoney}" pattern="#,###"/></b></td>
	      <td align="left"></td>
	    </tr>
	  </tbody>
	</table>

</td>
</c:if>
