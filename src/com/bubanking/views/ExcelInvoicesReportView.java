package com.bubanking.views;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.bubanking.commons.Commons;

public class ExcelInvoicesReportView extends AbstractExcelView{
	
	private int type;
	
	public ExcelInvoicesReportView(int type) {
		this.type = type;
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		RequestContext context = new RequestContext(request);
		BaseExcelView view =  null;
		if(this.type == Commons.INVOICE_TYPE_CENTRAL) {
			view = new ExcelCentralInvoicesView(context);
		} else {
			view = new ExcelSupplierInvoicesView(context);
		}
		//create view
		view.buildExcelDocument(response, model, workbook, context.getMessage("label.export.name"));
		
		
		
	}

}
