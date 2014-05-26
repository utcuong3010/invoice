package com.bubanking.jsons;

import java.util.ArrayList;
import java.util.List;

import com.bubanking.infos.InvoiceInfo;

public class InvoiceListJson extends BaseJson{
	//private boolean success;
	
	private List<InvoiceInfoJson> invoices;
	
	public InvoiceListJson() {
		super();
		invoices = new ArrayList<InvoiceInfoJson>();
	}
	
	public InvoiceListJson(List<InvoiceInfo> invoiceInfos) {
		invoices = new ArrayList<InvoiceInfoJson>();
		for(InvoiceInfo invoiceInfo : invoiceInfos){
			InvoiceInfoJson invoiceInfoJson = new InvoiceInfoJson();
			invoiceInfoJson.copyProperties(invoiceInfo);
			//add list
			invoices.add(invoiceInfoJson);
		}
	}


	public List<InvoiceInfoJson> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<InvoiceInfoJson> invoices) {
		this.invoices = invoices;
	}
	
}
