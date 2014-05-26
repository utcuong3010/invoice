package com.bubanking.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.bubanking.commons.Commons;
import com.bubanking.exceptions.MoneyException;
import com.bubanking.forms.SearchInvoiceForm;
import com.bubanking.infos.CategoryInfo;
import com.bubanking.infos.InvoiceInfo;
import com.bubanking.jsons.CategoriesInfoJson;
import com.bubanking.jsons.InvoiceInfoJson;
import com.bubanking.jsons.InvoiceListJson;
import com.bubanking.services.InvoiceService;
import com.bubanking.services.UserService;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private UserService userService;
	
	private final Logger log = Logger.getLogger(InvoiceController.class);
			
	@RequestMapping(value="/create.html", method=RequestMethod.POST)
	public @ResponseBody InvoiceListJson createInvoice(InvoiceInfoJson invoiceInfoJson,BindingResult result, HttpServletRequest request){
		InvoiceInfo invoiceInfo = new InvoiceInfo();
		
		InvoiceListJson invoiceListJson = new InvoiceListJson(new ArrayList<InvoiceInfo>());
		RequestContext context = new RequestContext(request);
		try {
			//check valid data			
			Map<String, String> errors = invoiceInfoJson.checkValid(userService, invoiceService,context);
			if(errors.isEmpty()) {			
				invoiceInfo.copyProperties(invoiceInfoJson);//copy bean
				boolean isCreated = invoiceService.createInvoice(invoiceInfo);
				invoiceListJson.setSuccess(isCreated);
				invoiceListJson.getInvoices().add(invoiceInfoJson);
				invoiceListJson.setTotalCount(1);
			} else {
				invoiceListJson.setSuccess(false);
				invoiceListJson.setErrors(errors);			
			}
			
		} catch (MoneyException e) {
			invoiceListJson.setSuccess(false);
			invoiceListJson.getErrors().put("money", context.getMessage("error.invoice.money"));
		} catch (Exception e) {
			log.error("Create invoice with errors:" + e);
		}
		
		return invoiceListJson;
	}	
	@RequestMapping(value="/deletes.html", method=RequestMethod.POST)
	public @ResponseBody InvoiceListJson deleteInvoices(@RequestBody String stringJson){	
		InvoiceListJson invoiceListJson = new InvoiceListJson();
		try {			
			List<InvoiceInfo> invoiceInfos = new ArrayList<InvoiceInfo>();
			//mapper
			ObjectMapper mapper = new ObjectMapper();
			List<InvoiceInfoJson> invoiceInfoJsons = null;
			try {
				//process invoice list
				invoiceInfoJsons = mapper.readValue(stringJson, new TypeReference<List<InvoiceInfoJson>>(){});
			} catch (Exception e) {
				log.error("Mapping object with errors:" + e);
			}			
			if(invoiceInfoJsons != null) {
				for(InvoiceInfoJson infoJson : invoiceInfoJsons) {
					InvoiceInfo invoiceInfo = new InvoiceInfo();
					invoiceInfo.copyProperties(infoJson);
					invoiceInfos.add(invoiceInfo);
				}
			}
			if(invoiceInfos.size() > 0) {				
				boolean result = invoiceService.deleteInvoices(invoiceInfos);
				//prepare response
				invoiceListJson.setSuccess(result);
			}					
		} catch (Exception e) {
			invoiceListJson.setSuccess(false);
			log.error("Delete invoices with errors: " + e);
		}		
		return invoiceListJson;
		
	}	
	@RequestMapping(value="/update.html", method=RequestMethod.POST)
	public @ResponseBody InvoiceListJson updateInvoice(InvoiceInfoJson invoiceInfoJson, BindingResult result, HttpServletRequest request){				
		InvoiceListJson invoiceListJson = new InvoiceListJson();	
		RequestContext context = new RequestContext(request);
		try {			
			Map<String, String> errors = invoiceInfoJson.checkValid(userService, invoiceService,context);
			if(errors.isEmpty()) {
				InvoiceInfo invoiceInfo = new InvoiceInfo();
				invoiceInfo.copyProperties(invoiceInfoJson);
				//update
				boolean isUpdated = invoiceService.updateInvoice(invoiceInfo);
				invoiceListJson.setSuccess(isUpdated);
				invoiceListJson.setTotalCount(1);
				invoiceListJson.getInvoices().add(invoiceInfoJson);
			} else {
				invoiceListJson.setSuccess(false);
				invoiceListJson.setErrors(errors);
			}
		} catch (MoneyException e) {
			invoiceListJson.setSuccess(false);
			invoiceListJson.getErrors().put("money", context.getMessage("error.invoice.money"));
		} catch (Exception e) {
			log.error("update invoice with error: " + e);
		}
		
		return invoiceListJson;
	}
	@RequestMapping(value="/list.html", method=RequestMethod.GET)
	public @ResponseBody InvoiceListJson getList(@ModelAttribute("form") SearchInvoiceForm searchForm){		
		InvoiceListJson list = null;
		try {
			List<InvoiceInfo> invoiceInfos = invoiceService.findInvoices(searchForm.createCondSearch(), searchForm.getStart(), searchForm.getLimit());
			list = new InvoiceListJson(invoiceInfos);
			list.setSuccess(true);
			list.setTotalCount(invoiceService.countInvoices(searchForm.createCondSearch()));
			
		} catch (Exception e) {
			log.error("Invoice list with errors :" + e);
			list = new  InvoiceListJson();
		}	
		return list;
	}	
	@RequestMapping(value="/lookupInvoices.html", method=RequestMethod.GET)
	public @ResponseBody InvoiceListJson lookupInvoices(String query){
		InvoiceListJson invoiceListJson = null;
		try {
			List<InvoiceInfo>invoiceInfos = invoiceService.lookupInvoices(StringUtils.trim(query), 0,10);
			invoiceListJson = new InvoiceListJson(invoiceInfos);
			invoiceListJson.setSuccess(true);
			invoiceListJson.setTotalCount(invoiceInfos.size());
			
		} catch (Exception e) {
			log.error("lookup invoices with erros: " + e);
			invoiceListJson = new  InvoiceListJson();
		}
		
		return invoiceListJson;
	}
	
	@RequestMapping(value="/processedInvoices.html")
	public String getProcessedInvoices(Long id, ModelMap model) {
		if(id != null){
			List<InvoiceInfo>invoiceInfos = invoiceService.getProcessedInvoices(id);
			//count total money
			long totalMoney = 0;
			for(InvoiceInfo invoiceInfo :invoiceInfos) {
				totalMoney += invoiceInfo.getMoney();
			}		
			model.addAttribute("invoiceInfos", invoiceInfos);
			model.addAttribute("totalMoney", totalMoney);
		}		
		return  "invoice/processedInvoices";
	}	
	@RequestMapping(value="/export.pdf", method=RequestMethod.GET)
	public ModelAndView pdfInvoiceReport(HttpServletResponse response,HttpServletRequest request, SearchInvoiceForm searchInvoiceForm, String output){
		return this.exportInvoices(response, request, searchInvoiceForm, output);
	}
	@RequestMapping(value="/export.xls", method=RequestMethod.GET)
	public ModelAndView excelInvoiceReport(HttpServletResponse response,HttpServletRequest request, SearchInvoiceForm searchInvoiceForm, String output){
		return this.exportInvoices(response, request, searchInvoiceForm, output);
	}
	public ModelAndView exportInvoices(HttpServletResponse response,HttpServletRequest request, SearchInvoiceForm searchInvoiceForm, String output){
		ModelAndView mad = null;		
		try {					
			List<InvoiceInfo>invoiceInfos = new ArrayList<InvoiceInfo>();	
			invoiceInfos =  invoiceService.findAllInvoices(searchInvoiceForm.createCondSearch());			
			//prepare for exporting
			Map<String, Object> model = new HashMap<String, Object>();
			JRDataSource datasource = new JRBeanCollectionDataSource(invoiceInfos);
			model.put("datasource", datasource);
			//get hour day month year 
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			model.put("hour", String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
			model.put("day", String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
			model.put("month", String.valueOf(cal.get(Calendar.MONTH) + 1));
			model.put("year", String.valueOf(cal.get(Calendar.YEAR)));
			
			
			if("pdf".equals(output)) {								
				mad = new ModelAndView(searchInvoiceForm.getType() == Commons.INVOICE_TYPE_SUPPLIER ? 
						Commons.PDF_SUPPLIER_REPORT_VIEW:Commons.PDF_CENTRAL_REPORT_VIEW,model);
			} else {
				mad = new ModelAndView(searchInvoiceForm.getType() == Commons.INVOICE_TYPE_SUPPLIER ? 
						Commons.XLS_SUPPLIER_REPORT_VIEW:Commons.XLS_CENTRAL_REPORT_VIEW,model);
			}					
		} catch (Exception e) {
			log.error("Export with errors:" + e);
		}
		return mad;		
	}
	//get categories list
	@RequestMapping(value="/lookupCategory.html", method=RequestMethod.GET)
	public @ResponseBody CategoriesInfoJson queryCategories(String query){
		CategoriesInfoJson  categoriesInfoJson = new CategoriesInfoJson();
		try {
			List<CategoryInfo> categoriesInfo = invoiceService.findCategoriesByName(StringUtils.trim(query));
			categoriesInfoJson.setSuccess(true);
			categoriesInfoJson.setTotalCount(categoriesInfo.size());
			categoriesInfoJson.setCategories(categoriesInfo);			
			
		} catch (Exception e) {
			log.error("Error when trying to get categories :" + e);
		}		
		return categoriesInfoJson;		
	}

}
