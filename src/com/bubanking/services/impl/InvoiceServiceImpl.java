package com.bubanking.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bubanking.commons.Commons;
import com.bubanking.daos.CategoryDao;
import com.bubanking.daos.InvoiceDao;
import com.bubanking.daos.UserDao;
import com.bubanking.exceptions.MoneyException;
import com.bubanking.infos.CategoryInfo;
import com.bubanking.infos.InvoiceInfo;
import com.bubanking.models.Category;
import com.bubanking.models.Invoice;
import com.bubanking.models.SecurityUser;
import com.bubanking.models.User;
import com.bubanking.services.InvoiceService;

public class InvoiceServiceImpl implements InvoiceService {

	private final Logger log = Logger.getLogger(InvoiceServiceImpl.class);
	
	private InvoiceDao invoiceDao;
	public void setInvoiceDao(InvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
	}
		
	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public boolean createInvoice(InvoiceInfo invoiceInfo) throws MoneyException, Exception {
		boolean isCreated = false;
		//set create date and modify date are current
		invoiceInfo.setCreateDate(new Date());
		invoiceInfo.setModifyDate(new Date());
		//status is pending
		invoiceInfo.setStatus(Commons.INVOICE_STATUS_PENDING);		
		Invoice invoice = new Invoice();
		invoice.copyProperties(invoiceInfo);
		
		//set author
		SecurityUser userDetail = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		invoice.setAuthor(new User());
		invoice.getAuthor().setId(userDetail.getUser().getId());
		
	 	
		//required invoice no and money and vendor id
		if(StringUtils.isNotBlank(invoice.getInvoiceNo())
				/*&& invoice.getVendor() != null && invoice.getVendor().getId() != null
				&& invoice.getMoney() != null*/){
			
			//update status for invoice if it is a central invoice
			if(invoice.getType() == Commons.INVOICE_TYPE_CENTRAL
					&& invoice.getProcessedInvoice() != null) {
				
				long sumProcessedMoney = invoiceDao.sumProcessedInvoiceMoney(invoice.getProcessedInvoice().getId(), 0l);
				sumProcessedMoney += invoice.getMoney();
				Invoice processedInvoice = invoiceDao.find(invoice.getProcessedInvoice().getId());
				//update status and money processed
				long totalProcessedMoney = processedInvoice.getMoney();
				log.info("sumProcessedMoney :" + sumProcessedMoney);
				log.info("totalProcessedMoney :" + totalProcessedMoney);
				if( sumProcessedMoney  <= totalProcessedMoney) {
					//allow insert new invoice
					isCreated = invoiceDao.save(invoice);						
					//update processed invoice
					if(sumProcessedMoney  == totalProcessedMoney) {
						processedInvoice.setStatus(Commons.INVOICE_STATUS_PROCESSED );
						//update
						invoiceDao.update(processedInvoice);
					}						
					
				} else {			
					//error double 
					throw new MoneyException(sumProcessedMoney, totalProcessedMoney);
				}
													
			} else {
				//check if vendor is owner to set processed
				if(invoiceInfo.getVendorInfo()!= null) {
					User vendor = userDao.findById(invoiceInfo.getVendorInfo().getId());
					if(vendor != null && BooleanUtils.isTrue(vendor.getIsOwner())) {
						invoice.setStatus(Commons.INVOICE_STATUS_PROCESSED);
					}
				}
				isCreated = invoiceDao.save(invoice);
			}
		} 			
		return isCreated;
	}
	

	
	public boolean updateInvoice(InvoiceInfo invoiceInfo) throws MoneyException, Exception{
		boolean isUpdated = false;		
		//set modify is current day
		invoiceInfo.setModifyDate(new Date());
		//copy properties
		Invoice invoice = new Invoice();
		invoice.copyProperties(invoiceInfo);
		//processing reference invoice
		if(invoice.getType() == Commons.INVOICE_TYPE_CENTRAL
				&& invoice.getProcessedInvoice() != null){
			Invoice processedInvoice = invoiceDao.find(invoice.getProcessedInvoice().getId());
			long totalProcessedMoney = processedInvoice.getMoney();
			//get all money had processed
			long sumProcessedMoney = invoiceDao.sumProcessedInvoiceMoney(invoice.getProcessedInvoice().getId(), invoice.getId());
			sumProcessedMoney = sumProcessedMoney + invoice.getMoney();
			log.info("sumProcessedMoney :" + sumProcessedMoney);
			log.info("totalProcessedMoney :" + totalProcessedMoney);
			
			if(totalProcessedMoney >= sumProcessedMoney) {
				//allow create this invoice
				isUpdated = invoiceDao.update(invoice);
				//update status for processed invoice
				if(totalProcessedMoney == sumProcessedMoney) {
					processedInvoice.setStatus(Commons.INVOICE_STATUS_PROCESSED);
											
				} else {
					processedInvoice.setStatus(Commons.INVOICE_STATUS_PENDING);
				}
				invoiceDao.update(processedInvoice);
			} else {
				//error double 
				throw new MoneyException(sumProcessedMoney, totalProcessedMoney);
			}
			
		} else  {
			//check if vendor is owner to set processed
			if(invoiceInfo.getVendorInfo()!= null) {
				User vendor = userDao.findById(invoiceInfo.getVendorInfo().getId());
				if(vendor != null && BooleanUtils.isTrue(vendor.getIsOwner())) {
					invoice.setStatus(Commons.INVOICE_STATUS_PROCESSED);
				}
			}
		
			isUpdated = invoiceDao.update(invoice);
		}
		return isUpdated;
		
	}
	public boolean deleteInvoice(InvoiceInfo invoiceInfo) {
		Invoice invoice = new Invoice();
		invoice.setId(invoiceInfo.getId());
		return invoiceDao.detele(invoice);
	}
	public boolean deleteInvoices(List<InvoiceInfo> invoiceInfos) {
		boolean result = false;		
		//List<Invoice>supplierInvoices = new ArrayList<Invoice>();
		//List<Invoice>centerInvoices = new ArrayList<Invoice>();
		
		for(InvoiceInfo info: invoiceInfos) {
			info.setStatus(Commons.INVOICE_STATUS_DELETED);
			info.setMoney(0l);
			try {
				result = this.updateInvoice(info);
			} catch (Exception e) {
				// TODO: handle exception
			}
//			updateInvoice
//			if(info.getType() == Commons.INVOICE_TYPE_CENTRAL) {
//				//set delete flag
//				
//				//add list
//				
//			} else {
//				Invoice invoice = new Invoice();
//				invoice.copyProperties(info);
//				//add 
//				//set flag is deleted
//				invoice.setStatus(Commons.INVOICE_STATUS_DELETED);
//				supplierInvoices.add(invoice);
//				//result = invoiceDao.update(invoice);
//			}
		}
		//delete all		
//		if(supplierInvoices.size()> 0) {			
//			result = invoiceDao.deteleAll(supplierInvoices);
//		}
		return result;
	}
	public InvoiceInfo findInvoice(Long id) {
		InvoiceInfo info = new InvoiceInfo();
		Invoice invoice = invoiceDao.find(id);
		if(invoice != null) {
			info.copyProperties(invoice);
			return info;
		}		
		return null;
	}
	
	public InvoiceInfo findInvoiceByNo(String invoiceNo) {
		InvoiceInfo info = null;
		List<InvoiceInfo> invoiceInfos = this.findAllInvoices(" invoice.invoiceNo='" + invoiceNo + "' and invoice.status !=" + Commons.INVOICE_STATUS_DELETED);
		if(invoiceInfos != null && invoiceInfos.size() > 0) {
			info = invoiceInfos.get(0);
		}		
		return info;
	}
	public List<InvoiceInfo> findAllInvoices(String whereCond) {
		List<InvoiceInfo>invoiceInfos = new ArrayList<InvoiceInfo>();
		List<Invoice>invoices = invoiceDao.findAll(whereCond);
		if(invoices != null && invoices.size() > 0){
			//loop mapping to info
			for(Invoice invoice: invoices){
				InvoiceInfo info = new InvoiceInfo();
				info.copyProperties(invoice);
				//add array
				invoiceInfos.add(info);				
			}
		}
		return invoiceInfos;
	}
	
	public List<InvoiceInfo> findInvoices(String whereCond, int firstResult, int maxResult) {
		List<InvoiceInfo>invoiceInfos = new ArrayList<InvoiceInfo>();
		try {			
			List<Invoice>invoices = invoiceDao.find(whereCond, firstResult, maxResult);
			if(invoices != null && invoices.size() > 0){
				//loop mapping to info
				for(Invoice invoice: invoices){
					InvoiceInfo info = new InvoiceInfo();
					info.copyProperties(invoice);
					//add array
					invoiceInfos.add(info);				
				}
			}
		} catch (Exception e) {
			log.error("Find invoice with errors: " + e);
		}
		return invoiceInfos;
	}

	public int countInvoices(String whereCond) {

		return invoiceDao.countInvoices(whereCond);
	}
	public List<InvoiceInfo> lookupInvoices(String query, int firstResult, int maxResult) {
		try {
			String whereCond = " invoice.status=" + Commons.INVOICE_STATUS_PENDING +
			 " and invoice.invoiceNo like '%" + query + "%' and invoice.type=" + Commons.INVOICE_TYPE_SUPPLIER ;
			return findInvoices(whereCond,firstResult,maxResult);
			
		} catch (Exception e) {
			log.error("Lookup invoices with error:" + e);
		}
		return null;
	}
	
	public List<InvoiceInfo>getProcessedInvoices(Long id) {
		List<InvoiceInfo>invoiceInfos = null;
		try {
			String whereCond = " invoice.processedInvoice.id=" + id +" and invoice.status !="+ Commons.INVOICE_STATUS_DELETED+" order by invoice.createDate desc";
			invoiceInfos = findAllInvoices(whereCond);
		} catch (Exception e) {
			log.error("Get processed invoices with errors: " + e);
		}
		return invoiceInfos;
	}
	
	public List<CategoryInfo> findCategoriesByName(String query) {
		List<CategoryInfo> categoryInfos = new  ArrayList<CategoryInfo>();		
		try {
			List<Category>categories = categoryDao.findCategoriesByName(query);
			CategoryInfo  info ;
			for(Category category: categories) {
				info =  new CategoryInfo();
				BeanUtils.copyProperties(info, category);
				//add
				categoryInfos.add(info);
			}			
		} catch (Exception e) {
			log.error("Error when trying to get categories list:"+  e);
		}
		
		return categoryInfos;
		
	}
	
	
	public CategoryInfo getCategoryByName(String name) {
		CategoryInfo categoryInfo = null;
		List<Category> categories = categoryDao.findAllByProperty("name", name);
		if(categories.size() >0) {
			categoryInfo = new CategoryInfo();
			categoryInfo.copyProperties(categories.get(0));
		}
		return categoryInfo;
	}
	
	
}
