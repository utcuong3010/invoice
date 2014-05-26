package com.bubanking.daos.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bubanking.commons.Commons;
import com.bubanking.daos.InvoiceDao;
import com.bubanking.models.Invoice;


public class InvoiceDaoImpl extends HibernateDaoSupport implements InvoiceDao {

	private Logger log = Logger.getLogger(InvoiceDaoImpl.class);
	
	public InvoiceDaoImpl() {
		log.info("InvoiceDaoImpl initialize....");
	}	
	public boolean save(Invoice invoice) {
		boolean  result = true;
		try {
			getHibernateTemplate().save(invoice);
		} catch (DataAccessException e) {
			result = false;
			log.error(e.toString());			
		}
		
		return result;
	}

	public boolean update(Invoice invoice) {
		boolean result = true;
		try {
			getHibernateTemplate().saveOrUpdate(invoice);
		} catch (DataAccessException e) {
			result = false;
			log.error(e.toString());
		}
		return result;
	}
	
	public boolean detele(Invoice invoice) {		
		boolean result = true;
		try {
			getHibernateTemplate().delete(invoice);					
		
		} catch(DataAccessException e) {
			log.error(e.toString());
			result = false;
		}
		return result;		
	}
	public boolean deteleAll(List<Invoice> invoices) {
		boolean result = true;
		try {
			
			getHibernateTemplate().saveOrUpdateAll(invoices);
			getHibernateTemplate().flush();
		} catch(DataAccessException e) {
			log.error(e.toString());
			result = false;
		}
		return result;
	
	}
	public Invoice find(Long id) {		
		return getHibernateTemplate().load(Invoice.class, id);
	}
	
	public List<Invoice> find(String whereCond) {
		String	hql = "from Invoice ";
		if(StringUtils.isNotBlank(whereCond)) {
			hql = hql+ " where " + whereCond;
		}
		List<Invoice> list = getHibernateTemplate().find(hql);			
		return list;
	}
	public List<Invoice> findAll(String whereCond){	
		String hql = "select invoice from Invoice invoice left join invoice.vendor vendor";		
		if(StringUtils.isNotBlank(whereCond)) {
			hql = hql+ " where " + whereCond;
		}
		List<Invoice> list = getHibernateTemplate().find(hql);
		return list;
	}
	
	public int countInvoices(String whereCond) {
		String hql = "select count(*) from Invoice invoice left join invoice.vendor vendor";
		if(StringUtils.isNotBlank(whereCond)) {
			hql = hql+ " where " + whereCond;
		}
		return DataAccessUtils.intResult(getHibernateTemplate().find(hql));		
	}
	public long sumProcessedInvoiceMoney(Long processedInvoiceId, Long exceptId) {
		long sum = 0;
		try {
			String hql = "select sum(money) from Invoice where processedInvoice.id=" + processedInvoiceId +
			  " and id !=" + exceptId + " and status !=" + Commons.INVOICE_STATUS_DELETED;
			sum =  DataAccessUtils.longResult(getHibernateTemplate().find(hql));
		} catch (Exception e) {
			log.error("sumProcessedInvoiceMoney:" + e);
		}
		
		return sum;
	}
	public List<Invoice> find(String whereCond, int firstResult, int maxResult) {
		List<Invoice> result = new ArrayList<Invoice>();
		try {
			String hql = "select invoice from Invoice invoice left join invoice.vendor vendor ";
			if(StringUtils.isNotBlank(whereCond)) {
				hql = hql+ " where " + whereCond;
			}
			//create Query
			Query query = getSession().createQuery(hql);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			result = (List<Invoice>)query.list();
		} catch (Exception e) {
			log.error("Find invoice with errors:" + e);
		}
		return result;
	}
	

}
