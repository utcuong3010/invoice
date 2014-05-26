package com.bubanking.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;

import com.bubanking.infos.InvoiceInfo;


@Entity
@Table(name="tbl_invoices")
public class Invoice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4850831883290288740L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;	
	
	@Column(name="invoice_no")
	private String invoiceNo;	
	
	@Column(name="invoice_date")
	private Date invoiceDate;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="money")
	private Long money;
	
	
	
	@Column(name="type")
	private int type;
	
	@Column(name="status")
	private int status;
	
	@Column(name="note")
	private String note;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="modify_date")
	private Date modifyDate;
	
	@OneToOne	
	@JoinColumn(name="vendor_id",nullable=true)	
	private User vendor;
	
	@OneToOne
	@JoinColumn(name="center_vendor_id")	
	private User centerVendor;
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="processed_invoice_id")
	private Invoice processedInvoice;
	
	@OneToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@OneToOne
	@JoinColumn(name="author_id")
	private User author;
	
	
	public Invoice() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public User getVendor() {
		return vendor;
	}

	public void setVendor(User vendor) {
		this.vendor = vendor;
	}
			

	/**
	 * @return the centerVendor
	 */
	public User getCenterVendor() {
		return centerVendor;
	}

	/**
	 * @param centerVendor the centerVendor to set
	 */
	public void setCenterVendor(User centerVendor) {
		this.centerVendor = centerVendor;
	}

	/**
	 * @return the processedInvoice
	 */
	public Invoice getProcessedInvoice() {
		return processedInvoice;
	}

	/**
	 * @param processedInvoice the processedInvoice to set
	 */
	public void setProcessedInvoice(Invoice processedInvoice) {
		this.processedInvoice = processedInvoice;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString(){		
		return "invoice[id=" +id + ", invoiceNo=" + invoiceNo + "]";
	}
	
	public void copyProperties(InvoiceInfo info) {
		try {
			if(info != null) {				
				this.id = info.getId();
				this.createDate = info.getCreateDate();
				this.invoiceDate = info.getInvoiceDate();
				this.modifyDate = info.getModifyDate();
				this.invoiceNo = info.getInvoiceNo();				
				this.money = info.getMoney();
				this.note = info.getNote();
				this.productName = info.getProductName();
				this.status = info.getStatus();
				this.type = info.getType();
				if(info.getProcessedInvoiceInfo() != null) {	
					this.processedInvoice = new Invoice();
					this.processedInvoice.copyProperties(info.getProcessedInvoiceInfo());
				}
				//this.vendor
				if(info.getVendorInfo() != null){
					this.vendor = new User();
					this.vendor.copyProperties(info.getVendorInfo());
				}
				if(info.getCenterVendorInfo() != null) {
					this.centerVendor = new User();
					this.centerVendor.copyProperties(info.getCenterVendorInfo());
				}
				//check category
				if(info.getCategoryInfo() != null) {
					category = new Category();
					BeanUtils.copyProperties(category, info.getCategoryInfo());
				}//check author
				if(info.getAuthorInfo() != null){
					this.author = new User();
					this.author.copyProperties(info.getAuthorInfo());
				}
 			}
		} catch (Exception e) {
			
		}
		
	}
	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}
	
	
	

}
