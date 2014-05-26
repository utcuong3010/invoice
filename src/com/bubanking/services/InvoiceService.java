package com.bubanking.services;

import java.util.List;

import com.bubanking.exceptions.MoneyException;
import com.bubanking.infos.CategoryInfo;
import com.bubanking.infos.InvoiceInfo;

public interface InvoiceService {

	boolean createInvoice(InvoiceInfo invoiceInfo) throws MoneyException, Exception;	
	boolean updateInvoice(InvoiceInfo invoiceInfo)throws MoneyException, Exception;
	boolean deleteInvoice(InvoiceInfo invoiceInfo);
	boolean deleteInvoices(List<InvoiceInfo> invoiceInfos);
	
	InvoiceInfo findInvoice(Long id);
	InvoiceInfo findInvoiceByNo(String invoiceNo);
	List<InvoiceInfo> findAllInvoices(String whereCond);
	List<InvoiceInfo> findInvoices(String whereCond, int firstResult, int maxResult);
	int countInvoices(String whereCond);
	
	List<InvoiceInfo>lookupInvoices(String query, int firstResult, int maxResult);
	List<InvoiceInfo>getProcessedInvoices(Long id);
	
	CategoryInfo getCategoryByName(String name);
	List<CategoryInfo> findCategoriesByName(String query);
	

	
	
}
