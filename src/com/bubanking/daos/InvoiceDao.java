package com.bubanking.daos;

import java.util.List;

import com.bubanking.models.Invoice;

public interface InvoiceDao {
	boolean save(Invoice invoice);
	boolean update(Invoice invoice);
	boolean detele(Invoice invoice);
	boolean deteleAll(List<Invoice> invoices);
	Invoice find(Long id);
	List<Invoice> find(String whereCond);
	List<Invoice> findAll(String whereCond);
	List<Invoice> find(String whereCond, int firstResult, int maxResult);
	int countInvoices(String whereCond);
	long sumProcessedInvoiceMoney(Long processedInvoiceId, Long exceptId);
	
}
