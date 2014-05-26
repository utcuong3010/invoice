package com.bubanking.views;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.support.RequestContext;

import com.bubanking.commons.Commons;
import com.bubanking.infos.InvoiceInfo;


public class ExcelSupplierInvoicesView extends BaseExcelView{
	
	private final Logger log = Logger.getLogger(ExcelSupplierInvoicesView.class);
	
	private RequestContext context;
	public ExcelSupplierInvoicesView(RequestContext context) {
		super();
		this.context = context;
	}
	@Override
	protected void buildLayoutReport(HSSFSheet worksheet){
		// Set column widths
		 worksheet.setColumnWidth(0, 2000);
		  worksheet.setColumnWidth(1, 4000);
		  worksheet.setColumnWidth(2, 4000);
		  worksheet.setColumnWidth(3, 8000);
		  worksheet.setColumnWidth(4, 8000);
		  worksheet.setColumnWidth(5, 5000);
		  worksheet.setColumnWidth(6, 4000);
		  worksheet.setColumnWidth(7, 4000);
		  worksheet.setColumnWidth(8, 8000);
//		  worksheet.setColumnWidth(8, 9000);
		  // Build the title and date headers
		  buildTitle(worksheet);
		  // Build the column headers
		  buildHeaders(worksheet);
	}


	private void buildHeaders(HSSFSheet worksheet) {
		// Create font style for the headers
		  Font font = worksheet.getWorkbook().createFont();
		        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		 
		  //Create cell style for the headers
		  HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		  headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		  headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		  headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		  headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		  headerCellStyle.setWrapText(true);
		  headerCellStyle.setFont(font);
		  headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		   
		  // Create the column headers
		  HSSFRow rowHeader = worksheet.createRow((short) startRowIndex + 2);
		  rowHeader.setHeight((short) 500);
		  
		  HSSFCell cell1 = rowHeader.createCell(startColIndex + 0);
		  cell1.setCellValue(context.getMessage("label.invoice.no"));
		  cell1.setCellStyle(headerCellStyle);
		 
		  HSSFCell cell2 = rowHeader.createCell(startColIndex + 1);
		  cell2.setCellValue(context.getMessage("label.invoice.invoice_no"));
		  cell2.setCellStyle(headerCellStyle);
		 
		  HSSFCell cell3 = rowHeader.createCell(startColIndex + 2);
		  cell3.setCellValue(context.getMessage("label.invoice.invoice_date"));
		  cell3.setCellStyle(headerCellStyle);
		  
		  HSSFCell cellCategoryName = rowHeader.createCell(startColIndex + 3);
		  cellCategoryName.setCellValue(context.getMessage("label.invoice.category.name"));
		  cellCategoryName.setCellStyle(headerCellStyle);
		 
		  HSSFCell cell4 = rowHeader.createCell(startColIndex + 4);
		  cell4.setCellValue(context.getMessage("label.invoice.vendor.name"));
		  cell4.setCellStyle(headerCellStyle);
		 
		  HSSFCell cell5 = rowHeader.createCell(startColIndex + 5);
		  cell5.setCellValue(context.getMessage("label.invoice.product.name"));
		  cell5.setCellStyle(headerCellStyle);
		  
		  HSSFCell cell6 = rowHeader.createCell(startColIndex + 6);
		  cell6.setCellValue(context.getMessage("label.invoice.money"));
		  cell6.setCellStyle(headerCellStyle);
		  
		  HSSFCell cell7 = rowHeader.createCell(startColIndex + 7);
		  cell7.setCellValue(context.getMessage("label.invoice.status"));
		  cell7.setCellStyle(headerCellStyle);
		  
		  HSSFCell cell8 = rowHeader.createCell(startColIndex + 8);
		  cell8.setCellValue(context.getMessage("label.invoice.create_date"));
		  cell8.setCellStyle(headerCellStyle);
		
	}
	private void buildTitle(HSSFSheet worksheet) {		
		// Create  company title
        HSSFRow companyTitle = worksheet.createRow((short) startRowIndex++);
		HSSFCell cellCompany = companyTitle.createCell(startColIndex);
		cellCompany.setCellValue(context.getMessage("label.homepage.company.name"));
		//address company
		HSSFRow addressTitle = worksheet.createRow((short) startRowIndex++);
		HSSFCell cellAddress = addressTitle.createCell(startColIndex);
		cellAddress.setCellValue(context.getMessage("label.homepage.company.address"));
		//phone
		HSSFRow phoneTitle = worksheet.createRow((short) startRowIndex++);
		HSSFCell cellPhone = phoneTitle.createCell(startColIndex);
		cellPhone.setCellValue(context.getMessage("label.homepage.company.phone"));
		//man title
		// Create font style for the report title
		Font fontTitle = worksheet.getWorkbook().createFont();
		fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fontTitle.setFontHeight((short) 400);		  
        // Create cell style for the report title
        HSSFCellStyle cellStyleTitle = worksheet.getWorkbook().createCellStyle();
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleTitle.setWrapText(false);
        cellStyleTitle.setFont(fontTitle);
        
        //skip 2 lines
        startRowIndex = startRowIndex +2;
		   
		//Create report title
        HSSFRow rowTitle = worksheet.createRow((short) startRowIndex++);
        //rowTitle.setHeight((short) 500);
        HSSFCell cellTitle = rowTitle.createCell(startColIndex +3);
        cellTitle.setCellValue(context.getMessage("label.export.title"));
       
        cellTitle.setCellStyle(cellStyleTitle);
		   
		// Create merged region for the report title
        worksheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
		
	}
	
	@Override
	protected void fillReport(HSSFSheet worksheet,Map<String, Object> model) {			
		try {
			List<InvoiceInfo> invoiceInfos = (List<InvoiceInfo>)model.get("invoiceInfos");						
			if(invoiceInfos != null && invoiceInfos.size() > 0) {
				//format date
				SimpleDateFormat sdf = new SimpleDateFormat(Commons.DATE_FORMAT_DD_MM_YYYY);
				NumberFormat numberFormat =  NumberFormat.getNumberInstance();				
				// Row offset
				startRowIndex += 2;			   
				// Create cell style for the body
				HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
				bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
				bodyCellStyle.setWrapText(true);
				bodyCellStyle.setBorderBottom((short)1);
				bodyCellStyle.setBorderTop((short)1);
				bodyCellStyle.setBorderLeft((short)1);
				bodyCellStyle.setBorderRight((short)1);
				//right style 
				HSSFCellStyle rightStyle = worksheet.getWorkbook().createCellStyle();
				rightStyle.setWrapText(true);
				rightStyle.setBorderBottom((short)1);
				rightStyle.setBorderTop((short)1);
				rightStyle.setBorderLeft((short)1);
				rightStyle.setBorderRight((short)1);
				rightStyle.setAlignment(CellStyle.ALIGN_RIGHT);
				
				// Create body
				int i = 0;
				long totalMoney = 0l;
				for(InvoiceInfo invoiceInfo: invoiceInfos) {
					//get some infos
					totalMoney += invoiceInfo.getMoney();
					// Create a new row
					HSSFRow row = worksheet.createRow(++startRowIndex);
					
				   // Retrieve the id value
				   HSSFCell cell1 = row.createCell(startColIndex+0);
				   cell1.setCellValue(++i);
				   cell1.setCellStyle(bodyCellStyle);
				 
				   // Retrieve the getInvoiceNo value
				   HSSFCell cell2 = row.createCell(startColIndex+1);
				   cell2.setCellValue(invoiceInfo.getInvoiceNo());
				   cell2.setCellStyle(bodyCellStyle);
				 
				   // Retrieve the getInvoiceDate value
				   if(invoiceInfo.getInvoiceDate() != null) {
					   sdf.applyPattern(Commons.DATE_FORMAT_DD_MM_YYYY);
					   HSSFCell cell3 = row.createCell(startColIndex+2);
					   cell3.setCellValue(sdf.format(invoiceInfo.getInvoiceDate()));
					   cell3.setCellStyle(bodyCellStyle);
				   }
				   
				   //categoty name
				   HSSFCell cellCategory = row.createCell(startColIndex+3);
				   cellCategory.setCellValue(invoiceInfo.getCategoryInfo()!= null? invoiceInfo.getCategoryInfo().getName() +  "-" + invoiceInfo.getCategoryInfo().getDescription(): "");
				   cellCategory.setCellStyle(bodyCellStyle);
				   
				 
				   // Retrieve the vendor name
				   HSSFCell cell4 = row.createCell(startColIndex+4);
				   cell4.setCellValue(invoiceInfo.getVendorInfo().getUsername());
				   cell4.setCellStyle(bodyCellStyle);
				 
				   // Retrieve the getProductName value
				   HSSFCell cell5 = row.createCell(startColIndex+5);
				   cell5.setCellValue(invoiceInfo.getProductName());
				   cell5.setCellStyle(bodyCellStyle);
				   
				   // Retrieve the getMoney value
				   HSSFCell cell6 = row.createCell(startColIndex+6);
				   cell6.setCellValue(invoiceInfo.getMoney());
				   
				   cell6.setCellStyle(rightStyle);
				   
				   //retrieve the status
				   HSSFCell cell7 = row.createCell(startColIndex+7);
				   cell7.setCellValue(invoiceInfo.getStatus() == Commons.INVOICE_STATUS_PENDING? 
						   context.getMessage("label.invoice.status.pending"):
							   context.getMessage("label.invoice.status.proccessed"));				   
				   cell7.setCellStyle(bodyCellStyle);
				   
				   //Retrieve the create date
				   if(invoiceInfo.getCreateDate() != null) {
					   sdf.applyPattern(Commons.DATE_FORMAT_DD_MM_YYYY_HH_MM_SS);
					   HSSFCell cell8 = row.createCell(startColIndex+8);
					   cell8.setCellValue(sdf.format(invoiceInfo.getCreateDate()));
					   cell8.setCellStyle(bodyCellStyle);
				   }
				}	
				startRowIndex++;
				//append some footer
				HSSFRow rowTotal = worksheet.createRow(startRowIndex++);
				for(i = 0 ; i< 9; i++){
					HSSFCell cellTotal = rowTotal.createCell(startColIndex+i);
					if(i==4) {
						cellTotal.setCellValue(context.getMessage("label.invoice.total_money"));
						cellTotal.setCellStyle(rightStyle);
					} else if(i==6){
						cellTotal.setCellValue(totalMoney);
						cellTotal.setCellStyle(rightStyle);
					}					
					else {
						cellTotal.setCellValue("");
						cellTotal.setCellStyle(bodyCellStyle);
					}
					
				
					
					
				}
				//add date
				HSSFRow dateRow = worksheet.createRow(++startRowIndex);
				HSSFCell exportDateCell = dateRow.createCell(startColIndex+5);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				exportDateCell.setCellValue(context.getMessage("label.export.date",new Integer[]{
							calendar.get(Calendar.DATE),
							calendar.get(Calendar.MONTH),
							calendar.get(Calendar.YEAR)
							}));
				//manager
				HSSFRow managerRow = worksheet.createRow(++startRowIndex);
				//accounting
				HSSFCell accountingCell = managerRow.createCell(startColIndex+1);
				accountingCell.setCellValue(context.getMessage("label.export.accounting"));
				//product 
				HSSFCell productCell = managerRow.createCell(startColIndex+3);
				productCell.setCellValue(context.getMessage("label.export.product.manager"));
				//manage
				HSSFCell manageCell = managerRow.createCell(startColIndex+5);
				manageCell.setCellValue(context.getMessage("label.export.inventory.manager"));
			}
		}catch (Exception e) {
			log.error("Fill date with errors: "+ e);
		}
				
	}
	
}
