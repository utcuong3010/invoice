package com.bubanking.views;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.CharSet;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bubanking.commons.Commons;
import com.bubanking.infos.InvoiceInfo;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfInvoicesReportView extends AbstractPdfView{
	
	private Logger logger = Logger.getLogger(PdfInvoicesReportView.class);
	
	private int type;
	
	public PdfInvoicesReportView(int type) {
		this.type = type;
	}
	
	private static Font headerFont = new Font(Font.TIMES_ROMAN, 12,
			Font.BOLD);
	private static Font titleFont = new Font(Font.TIMES_ROMAN , 15,
			Font.BOLD);
	private static Font headerTableFont = new Font(Font.TIMES_ROMAN,10,
			Font.BOLD);
	private static Font smallBold = new Font(Font.TIMES_ROMAN, 12,
			Font.BOLD);
	
	
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RequestContext context = new RequestContext(request);
		List<InvoiceInfo>invoiceInfos = (List<InvoiceInfo>)model.get("invoiceInfos");
		
		//create header
		createHeader(document,context);
		//content
		createContent(document,context,invoiceInfos);
		//create footer
		createFooter(document,context);
	}
	//create header contains some info
	private void createHeader(Document document, RequestContext context) {
		try {
			//String vnFont = "ARIALBD.TTF";
			//BaseFont helvetia = BaseFont.createFont(vnFont,"Cp1252",BaseFont.EMBEDDED);
			//Font font = new Font(helvetia, 12, Font.NORMAL);
			
			
			Paragraph header = new Paragraph();			
			header.add(new Paragraph(context.getMessage("label.homepage.company.name"),headerFont));					
			header.add(new Paragraph(context.getMessage("label.homepage.company.address"),headerFont));
			header.add(new Paragraph(context.getMessage("label.homepage.company.phone"),headerFont));
			
			//String test = new String(context.getMessage("label.homepage.company.address").getBytes(), "UTF-8");
			//test
			//Paragraph testChunk = new Paragraph("T\u00ean \u0111\u0103ng nh\u1eadp \u0111\u00e3 t\u1ed3n t\u1ea1i trong h\u1ec7 th\u1ed1ng",font);
			// testChunk1 = new Paragraph(context.getMessage("label.homepage.company.address"),font);
			
			//header.add(testChunk);
			//header.add(testChunk1);
			//header.add(new Paragraph(test));
			//header.add(new Paragraph(test,headerFont));
			//header.
			
			//add line
			this.addEmptyLine(header, 2);
			//add document
			document.add(header);
			
			//title
			PdfPTable titleTbl = new PdfPTable(2);
			titleTbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
			PdfPCell titleCell = new PdfPCell(new Paragraph(context.getMessage("label.export.title"), titleFont));
			titleCell.setBorder(Rectangle.NO_BORDER);
			titleCell.setColspan(2);
			titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			titleCell.setPadding(5.0f);
			titleTbl.addCell(titleCell);			
			
			document.add(titleTbl);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 
	 * @param document
	 */
	private void createContent(Document document,RequestContext context,List<InvoiceInfo>invoiceInfos) {
		
		if(type == Commons.INVOICE_TYPE_SUPPLIER) {
			createSuppilerContent(document,context, invoiceInfos);
		} else {
			createCentralContent(document,context, invoiceInfos);
		}
		
	}
	private void createCentralContent(Document document,
			RequestContext context, List<InvoiceInfo> invoiceInfos) {
		try {
			//header table			
			PdfPTable table = new PdfPTable(10);
			table.setWidthPercentage(100);
			table.setWidths(new int[]{5,20,30,30,20,20,20,20,20,30});
	
			//stt
			PdfPCell sttCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.no"),headerTableFont));
			
			sttCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			//sttCell.set
			table.addCell(sttCell);		
			//invoice no
			PdfPCell invoiceNoCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.invoice_no"),headerTableFont));
			invoiceNoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(invoiceNoCell);
			//invoice date
			PdfPCell invoiceDateCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.invoice_date"),headerTableFont));
			invoiceDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(invoiceDateCell);
			//category.name
			PdfPCell categoryNameCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.category.name"),headerTableFont));
			categoryNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(categoryNameCell);
			//vendor center name
			PdfPCell vendorCenterNameCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.center.vendor.name"),headerTableFont));
			vendorCenterNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(vendorCenterNameCell);
			//vendor name
			PdfPCell vendorNameCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.vendor.name"),headerTableFont));
			vendorNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(vendorNameCell);
			//product name
			PdfPCell productNameCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.product.name"),headerTableFont));
			productNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(productNameCell);
			//money
			PdfPCell moneyCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.money"),headerTableFont));
			moneyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(moneyCell);
			
			//Processed invoice
			PdfPCell processedInvoiceCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.proccessed_invoice"),headerTableFont));
			processedInvoiceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(processedInvoiceCell);
			
			//create date
			PdfPCell createDateCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.create_date"),headerTableFont));
			createDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(createDateCell);
			
			//for each to render rows
			int i = 0;
			long totalMoney = 0l;
			SimpleDateFormat sdf = new SimpleDateFormat(Commons.DATE_FORMAT_DD_MM_YYYY);
			NumberFormat numberFormat =  NumberFormat.getNumberInstance();	
			for(InvoiceInfo invoiceInfo: invoiceInfos) {
				//get some infos
				totalMoney += invoiceInfo.getMoney();
				//fill data
				table.addCell(String.valueOf(i++));//stt
				table.addCell(invoiceInfo.getInvoiceNo());//invoice no
				//invoice date
				sdf.applyPattern(Commons.DATE_FORMAT_DD_MM_YYYY);
				table.addCell(sdf.format(invoiceInfo.getInvoiceDate()));//invoice date
				table.addCell(invoiceInfo.getCategoryInfo()!= null? invoiceInfo.getCategoryInfo().getName() +  "-" + invoiceInfo.getCategoryInfo().getDescription(): "");//categoty name
				table.addCell(invoiceInfo.getCenterVendorInfo() != null? invoiceInfo.getCenterVendorInfo().getUsername(): "");//vendor center
				table.addCell(invoiceInfo.getVendorInfo().getUsername());//vendor name
				table.addCell(invoiceInfo.getProductName());//product name
				table.addCell(numberFormat.format(invoiceInfo.getMoney()));//money
				if(invoiceInfo.getProcessedInvoiceInfo() != null) {
					 table.addCell(invoiceInfo.getProcessedInvoiceInfo().getInvoiceNo());
				   } else {
					  table.addCell("");
				}
				sdf.applyPattern(Commons.DATE_FORMAT_DD_MM_YYYY_HH_MM_SS);
				table.addCell(sdf.format(invoiceInfo.getCreateDate()));//processed invoice
				
				
			}
			//add last row
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell(context.getMessage("label.invoice.total_money"));
			table.addCell("");
			table.addCell(numberFormat.format(totalMoney));
			table.addCell("");
			table.addCell("");
			
			//add document
			document.add(table);
			
		}catch (Exception e) {
			logger.error("Render PDF with errors :" + e);
		}
		
		
	}
	private void createSuppilerContent(Document document,
			RequestContext context, List<InvoiceInfo> invoiceInfos) {
		try {
			//header table
			PdfPTable table = new PdfPTable(9);
			table.setWidthPercentage(100);
			table.setWidths(new int[]{5,20,30,30,30,20,20,20,30});
	
			//stt
			PdfPCell sttCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.no"),headerTableFont));
			
			sttCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			//sttCell.set
			table.addCell(sttCell);		
			//invoice no
			PdfPCell invoiceNoCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.invoice_no"),headerTableFont));
			invoiceNoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(invoiceNoCell);
			//invoice date
			PdfPCell invoiceDateCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.invoice_date"),headerTableFont));
			invoiceDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(invoiceDateCell);
			//category.name
			PdfPCell categoryNameCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.category.name"),headerTableFont));
			categoryNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(categoryNameCell);
			//vendor name
			PdfPCell vendorNameCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.vendor.name"),headerTableFont));
			vendorNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(vendorNameCell);
			//product name
			PdfPCell productNameCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.product.name"),headerTableFont));
			productNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(productNameCell);
			//money
			PdfPCell moneyCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.money"),headerTableFont));
			moneyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(moneyCell);
			
			//status invoice
			PdfPCell statusInvoiceCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.status"),headerTableFont));
			statusInvoiceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(statusInvoiceCell);
			
			//create date
			PdfPCell createDateCell = new PdfPCell(new Phrase(context.getMessage("label.invoice.create_date"),headerTableFont));
			createDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(createDateCell);
			
			//for each to render rows
			int i = 0;
			long totalMoney = 0l;
			SimpleDateFormat sdf = new SimpleDateFormat(Commons.DATE_FORMAT_DD_MM_YYYY);
			NumberFormat numberFormat =  NumberFormat.getNumberInstance();	
			for(InvoiceInfo invoiceInfo: invoiceInfos) {
				//get some infos
				totalMoney += invoiceInfo.getMoney();
				//fill data
				table.addCell(String.valueOf(i++));//stt
				table.addCell(invoiceInfo.getInvoiceNo());//invoice no
				//invoice date
				sdf.applyPattern(Commons.DATE_FORMAT_DD_MM_YYYY);
				table.addCell(sdf.format(invoiceInfo.getInvoiceDate()));//invoice date
				table.addCell(invoiceInfo.getCategoryInfo()!= null? invoiceInfo.getCategoryInfo().getName() +  "-" + invoiceInfo.getCategoryInfo().getDescription(): "");//categoty name				
				table.addCell(invoiceInfo.getVendorInfo().getUsername());//vendor name
				table.addCell(invoiceInfo.getProductName());//product name
				table.addCell(numberFormat.format(invoiceInfo.getMoney()));//money
				
				table.addCell((invoiceInfo.getStatus() == Commons.INVOICE_STATUS_PENDING? 
							   context.getMessage("label.invoice.status.pending"):
								   context.getMessage("label.invoice.status.proccessed")));
				   
				sdf.applyPattern(Commons.DATE_FORMAT_DD_MM_YYYY_HH_MM_SS);
				table.addCell(sdf.format(invoiceInfo.getCreateDate()));//processed invoice
				
				
			}
			//add last row
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");			
			table.addCell(context.getMessage("label.invoice.total_money"));
			table.addCell("");
			table.addCell(numberFormat.format(totalMoney));
			table.addCell("");
			table.addCell("");
			
			//add document
			document.add(table);
			
		}catch (Exception e) {
			logger.error("Render PDF with errors :" + e);
		}
		
	}
	private void createFooter(Document document,RequestContext context){
		try {
			Paragraph footer = new Paragraph();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			Paragraph place = new Paragraph(context.getMessage("label.export.date",new Integer[]{
					calendar.get(Calendar.DATE),
					calendar.get(Calendar.MONTH)+1,
					calendar.get(Calendar.YEAR)
					}), new Font(Font.TIMES_ROMAN, 12, Font.ITALIC) );
			place.setAlignment(Element.ALIGN_RIGHT);
			footer.add(place);
			//table 1
			Table table1 = new Table(2);
			table1.setBorder(Rectangle.NO_BORDER);
			table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			table1.addCell("");
			table1.addCell(footer);
		
			//add document
			document.add(table1);
			
			//last row
			Table lastRow = new Table(3);
			lastRow.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			lastRow.setBorder(Rectangle.NO_BORDER);
			lastRow.addCell(context.getMessage("label.export.accounting"));
			lastRow.addCell(context.getMessage("label.export.product.manager"));
			lastRow.addCell(context.getMessage("label.export.inventory.manager"));
			document.add(lastRow);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	

}
