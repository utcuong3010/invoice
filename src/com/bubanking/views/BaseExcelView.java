package com.bubanking.views;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * 
 * @author cuong.truong
 *
 */
public abstract class BaseExcelView {
	
	private final Logger log = Logger.getLogger(BaseExcelView.class);
	
	protected int startRowIndex = 0;
	protected int startColIndex = 0;
	

	public void buildExcelDocument(HttpServletResponse response, Map<String, Object> model,HSSFWorkbook workbook, String sheetname) throws Exception {
		try {				
			//1. create sheet
			HSSFSheet worksheet = workbook.createSheet(sheetname);			
			//2. build layout
			this.buildLayoutReport(worksheet);			
			//3. fill report
			this.fillReport(worksheet, model);						
					
		} catch (Exception e) {
			log.error("Generate excel with errors:"  + e);
		}
	};	
	protected abstract void buildLayoutReport(HSSFSheet worksheet);
	protected abstract void fillReport(HSSFSheet worksheet,Map<String, Object> model);	
}
