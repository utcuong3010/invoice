Ext.define('AM.controller.Invoices',{
    extend: 'Ext.app.Controller',
    
    views:[
       'invoice.SupplierList',
       'invoice.SupplierEdit',
       'invoice.CentralList',
       'invoice.CentralEdit'

     
    ],
    
    refs:[
        {
            ref:'supplierList',
            selector: 'invoiceSupplierList'
        },
        {
            ref:'centralList',
            selector: 'invoiceCentralList'
        }
    
    ],
                
    init: function(){
       this.control({
            "invoiceSupplierList":{
                itemdblclick: this.editSupplierInvoice,
                render:function(){
                    this.getSupplierList().getStore().load({
                        params:{
                            start:0                    
                        }
                    });
                },
                afterrender: function(){                
                    var expander = this.getSupplierList().getPlugin('expander');
                  
                    var me = this;
                    expander.view.on('expandbody', function(rowexpand, record, body,rowIndex){                                                            
                        Ext.Ajax.request({
                            url:'invoice/processedInvoices.html?id=' + record.getId(),
                            method: 'GET',
                            success: function(response){                                                                
                                body.innerHTML = response.responseText;                                                              
                                me.getSupplierList().doLayout(); 
                                                                                        
                            },
                            failure: function(){
                                
                            }
                        });
                        
                    }, this);
                                
               }  
                
            },            
            "invoiceSupplierList #add":{
                click: this.addSupplierFormInvoice
            },
            "invoiceSupplierList #delete":{
                click: this.deleteSupplierInvoice
            },
            "invoiceSupplierList #export-excel":{
                click: function(){
                	this.exportInvoices(0, 'xls');
                }
            },
            "invoiceSupplierList #export-pdf":{
                click: function(){
                	this.exportInvoices(0, 'pdf');
                }
            },
            "invoiceSupplierList #searchBtn":{
                click: this.searchSupplierInvoice
            },
            //edit form
            "invoiceSupplierEdit button[action=save]":{
                click:this.updateSupplierInvoice
            },            
            "invoiceCentralList":{
                itemdblclick: this.editCentralInvoice,
                render:function(){                    
                    this.getCentralList().getStore().load({
                        params:{
                            start:0                    
                        }
                    });                                                                              
                }                        
            },            
            "invoiceCentralList #add":{
                click: this.addCentralFormInvoice
            },
            "invoiceCentralList #delete":{
                click: this.deleteCentralInvoice
            },
            "invoiceCentralList #export-excel":{
                click: function(){
                	this.exportInvoices(1,'xls');
                }
            },
            "invoiceCentralList #export-pdf":{
                click: function(){
                	this.exportInvoices(1,'pdf');
                }
            },
            "invoiceCentralList #searchBtn":{
                click: this.searchCentralInvoice
            },
            //edit form
            "invoiceCentralEdit button[action=save]":{
                click:this.updateCentralInvoice
            }
            
       });
       
    },
    editSupplierInvoice: function(grid, record){
        var view = Ext.widget('invoiceSupplierEdit',{
        	isNewForm: false
        });
        view.down('form').loadRecord(record);
    },
    editCentralInvoice: function(grid, record){
        var view = Ext.widget('invoiceCentralEdit', {
        	isNew: false
        });
        view.down('form').loadRecord(record);
    },
    updateSupplierInvoice:function(button){
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();
            var isNew = record == null ?true: false;            
            if(form.getForm().isValid()){
            	form.getForm().submit({
            		url: isNew? "invoice/create.html" :"invoice/update.html",
            		scope:this,
            		success:function(){
            			 win.close();                        
            			 this.getSupplierList().getStore().load();                                              
                         
            		}
            	});
            }       
    },
    updateCentralInvoice:function(button){
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues();
            var isNew = record == null? true: false;
            if(form.getForm().isValid()){
            	//submit
            	form.getForm().submit({
            		url: isNew? "invoice/create.html": "invoice/update.html",
            		scope:this,
            		success:function(){
            			win.close();
            			this.getCentralList().getStore().load();
            		}
            	});
            }             
    },
    addSupplierFormInvoice:function(){
        Ext.widget('invoiceSupplierEdit');
    },
    addCentralFormInvoice:function(){
        Ext.widget('invoiceCentralEdit');
    },
    deleteSupplierInvoice: function(){
        //get all selected records
        var records = this.getSupplierList().selModel.getSelection();
        if(records.length == 0){
        	Ext.Msg.alert(DELETE_INVOICE_MSG_TITLE, DELETE_INVOICE_MSG_ALERT);
        } else {
            Ext.Msg.confirm(DELETE_INVOICE_MSG_TITLE, DELETE_INVOICE_MSG_CONFIRM, function(btn, text){               
                if(btn =='yes'){
                    //delete all selected records from here 
                   var param = new Array();
                   Ext.each(records, function(record){
                       param.push(record.data);
                	  // this.getSupplierList().getStore().remove(record);
                   }, this);
                   Ext.Ajax.request({
                	   url: "invoice/deletes.html?",
                	   method: 'POST',
                	   jsonData: param, 
                	   scope: this,
                	   success:function(){
                		   this.getSupplierList().getStore().load();
                	   },
                	   failure: function(){
                		   Ext.Msg.alert(DELETE_INVOICE_MSG_TITLE,DELETE_INVOICE_MSG_ERROR);
                	   }
                   });                               
                }
            }, this
            );
        }        
    },
    deleteCentralInvoice: function(){
        //get all selected records
        var records = this.getCentralList().selModel.getSelection();
        if(records.length == 0){
            Ext.Msg.alert(DELETE_INVOICE_MSG_TITLE, DELETE_INVOICE_MSG_ALERT);
        } else {
            Ext.Msg.confirm(DELETE_INVOICE_MSG_TITLE, DELETE_INVOICE_MSG_CONFIRM, function(btn, text){               
                if(btn =='yes'){
                    //delete all selected records from here
                	var param = new Array();
                    Ext.each(records, function(record){
                        param.push(record.data);
                 	  // this.getCentralList().getStore().remove(record);
                    }, this);
                    Ext.Ajax.request({
                 	   url: "invoice/deletes.html?",
                 	   method: 'POST',
                 	   jsonData: param, 
                 	   scope: this,
                 	   success:function(){
                 		   this.getCentralList().getStore().load();
                 	   },
                 	   failure: function(){
                 		  Ext.Msg.alert(DELETE_INVOICE_MSG_TITLE,DELETE_INVOICE_MSG_ERROR);
                 	   }
                    });
                    
                }
            }, this
            );
        }        
    },
    exportInvoices: function(type, output){    
    	//get param from form search
    	var paramsObj = {};
    	var url = "";
    	if(type == 0) {
    		paramsObj = this.getSupplierList().params(); 
    	} else {
    		paramsObj = this.getCentralList().params();
    	}
    	
    	//paramsObj.dateTo = Ext.JSON.encodeDate(paramsObj.dateTo);
    	//paramsObj.dateFrom = Ext.JSON.encodeDate(paramsObj.dateFrom);
    	
    	var params = "";
    	for(var name in paramsObj) {
    		if(name=="dateTo" || name=="dateFrom") {
    			params += "&" + name + "=" + Ext.encode(paramsObj[name]).replace(/\"/g, "");
    		} else {
    			params += "&" + name + "=" + paramsObj[name];
    		}
    		
    	}
    	
    	window.open('invoice/export.' + output +'?output=' +output  + "&" + params,'popup','menubar=no, status=no, scrollbars=yes');
              
    },
    searchSupplierInvoice: function(){
        this.getSupplierList().store.load({params:{
            start:0,
            page:1
        }});
    },
    searchCentralInvoice: function(){
        this.getCentralList().store.load({params:{
            start:0,
            page:1
        }});
    }   
});