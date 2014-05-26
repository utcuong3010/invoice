package com.bubanking.commons;



public  class Commons {
	
	public final static String DATE_FORMAT_DD_MM_YYYY  = "dd/MM/yyyy"; 
	public final static String DATE_FORMAT_DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	
	public final static String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS  = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_FORMAT_YYYY_MM_DD  = "yyyy-MM-dd"; 
	/***
	 * pending =0
	 * processed = 1
	 */
	public final static Integer INVOICE_STATUS_PENDING = 0;
	public final static Integer INVOICE_STATUS_PROCESSED = 1;
	public final static Integer INVOICE_STATUS_DELETED = 2;
	
	public final static String INVOICE_STATUS_STR_PENDING = "Pending";	
	public final static String INVOICE_STATUS_STR_PROCESSED = "Processed";
	
	/***
	 * type =0 : supplier
	 * type=1: central
	 */
	
	public final static Integer INVOICE_TYPE_SUPPLIER = 0;
	public final static Integer INVOICE_TYPE_CENTRAL = 1;
	
	
	
	public final static Integer USER_TYPE_OPERATOR = 0;
	public final static Integer USER_TYPE_VENDOR = 1;
	public final static Integer USER_TYPE_CENTER_VENDOR = 2;
	public final static Integer USER_TYPE_SUPER_USER = 3;
	
	
	public final static int USER_STATUS_DEACTIVATED = 0;
	public final static int USER_STATUS_ACTIVATED = 1;	
	public final static int USER_STATUS_DELETED = 2;
	
	
	public final static int SEARCH_FIELD_ALL = 0;
	public final static int SEARCH_FIELD_INVOICE_NO = 1;
	public final static int SEARCH_FIELD_VENDOR_NAME = 2;
	public final static int SEARCH_FIELD_PRODUCT_NAME = 3;
	public final static int SEARCH_FIELD_CATEGORY_NAME = 4;

	public final static int SEARCH_FIELD_USER_FULL_NAME = 1;
	public final static int SEARCH_FIELD_USER_NAME = 2;
	public final static int SEARCH_FIELD_USER_EMAIL = 3;
	public final static int SEARCH_FIELD_USER_PHONE = 4;

	
	//file name for export
	public final static String EXPORT_SUPPLIER_EXCEL_FILE_NAME  = "SupplierInvoices";
	public final static String EXPORT_CENTRAL_EXCEL_FILE_NAME  = "CentralInvoices";
	
	//center vendor name default
	public final static String CENTER_VENDOR_NAME_DEFAULT = "99902";
	
	//name of views
	public final static String PDF_SUPPLIER_REPORT_VIEW = "pdfSupplierInvoiceReport";
	public final static String PDF_CENTRAL_REPORT_VIEW = "pdfCentralInvoiceReport";
	public final static String XLS_SUPPLIER_REPORT_VIEW = "xlsSupplierInvoiceReport";
	public final static String XLS_CENTRAL_REPORT_VIEW = "xlsCentralInvoiceReport";

}
