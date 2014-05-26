package com.bubanking.commons;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.bubanking.daos.InvoiceDao;
import com.bubanking.models.Invoice;
import com.bubanking.models.User;
import com.bubanking.services.InvoiceService;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger log = Logger.getLogger(App.class);
		String configLocation = "classpath:applicationContext.xml";
		ApplicationContext appContext = 
			new FileSystemXmlApplicationContext(configLocation);
		
		//InvoiceDao dao = (InvoiceDao)appContext.getBean("invoiceDao");
		//UserDao userDao = (UserDao)appContext.getBean("userDao");	
		/*Invoice invoice = new Invoice();
		invoice.setInvoiceNo("eeeee");
		invoice.setInvoiceDate(new Date());
		invoice.setProductName("tets");
		invoice.setMoney(2000l);
		invoice.setCreateDate(new Date());
		invoice.setType(1);
		invoice.setNote("test node");
		invoice.setStatus(1);
		
		//save vendor
		User vendor = new User();
		vendor.setId(8l);
		
		invoice.setVendor(vendor);
		
		dao.save(invoice);
		
	
		
		Invoice invoice = dao.find(74l);
		System.err.println(invoice.getInvoiceNo());
		
		System.err.println(invoice.getVendor().getUsername());
		
		
		//User user = userDao.find(7l);
		//System.err.println(user.getUsername());
	
		InvoiceService invoiceService = (InvoiceService) appContext.getBean("invoiceService");
		
		InvoiceInfo info = invoiceService.findInvoice(74l);
		System.out.println(info);
		*/
		
		InvoiceService invoiceService = (InvoiceService) appContext.getBean("invoiceService");
		
	
		//List<InvoiceInfo>invoiceInfos = invoiceService.findInvoices("type=1");
		//System.err.println(invoiceInfos.size());
		
		//InvoiceDao invoiceDao = (InvoiceDao)appContext.getBean("invoiceDao");
		//List<Invoice>invoices = invoiceDao.find("from Invoice");
		//int i = invoiceService.countInvoices("type=1");
		//System.err.println(i);
		
//		InvoiceDao dao = (InvoiceDao)appContext.getBean("invoiceDao");
		
		//Invoice invoice = dao.find(82l);
		
		//System.err.println(invoice.getProcessedInvoice());
		//long sum = dao.sumProcessedInvoiceMoney(83l);
		
	///	System.err.println(sum);
//		App app = new App();
//		app.createInvoice(dao);
//		app.createInvoice(dao);

	}
	public void createInvoice(InvoiceDao dao){
		Invoice invoice = new Invoice();
		invoice.setInvoiceDate(new Date());
		invoice.setInvoiceNo("ddddddddd");
		//vender
		User vendor = new User();
		vendor.setId(2L);
		
		invoice.setVendor(vendor);
		
		
		dao.save(invoice);
		
	}

}
