Ext.define('AM.view.invoice.CentralEdit',{
    extend: 'Ext.window.Window',
    alias:'widget.invoiceCentralEdit',
    title: CENTRAL_INVOICE,
    layout:'fit',
    modal: true,
    autoShow:true,
    width: 500,
    resizable: false,
    initComponent:function(){    	
    	//lookup vendor
        var vendorStore = Ext.create('AM.store.User',{
            pageSize: 20,
            proxy:{
                type: 'ajax',
                api:{
                	read:'user/lookupVendors.html?type=1'
                },
                reader:{
                    type:'json',
                    root:'users',
                    successProperty:'success',
                    totalProperty:'totalCount'
                }   
            }
        }); 
        //looup proccessed invoices
        var invoiceStore = Ext.create('AM.store.Invoice',{
            proxy:{
                type: 'ajax',
                api:{
                    read:'invoice/lookupInvoices.html'
                },
                reader:{
                    type:'json',
                    root:'invoices',
                    successProperty:'success',
                    totalProperty:'totalCount'
                }
                
            }
        });
        //lookup for center vendor
        var centerVendorStore = Ext.create("AM.store.User",{
        	pageSize: 20,
            proxy:{
                type: 'ajax',
                api:{
                	read:'user/lookupVendors.html?type=2'
                },
                reader:{
                    type:'json',
                    root:'users',
                    successProperty:'success',
                    totalProperty:'totalCount'
                }   
            }
        });
        //store for category
	   var categoryStore = Ext.create('Ext.data.Store',{
		   fields:["name", "description"],
           proxy:{
               type: 'ajax',
               api:{
                   read:'invoice/lookupCategory.html'
               },
               reader:{
                   type:'json',
                   root:'categories',
                   successProperty:'success',
                   totalProperty:'totalCount'
               }
               
           }
       });
        //create form
        var form = Ext.create('Ext.form.Panel',{
        	bodyStyle: 'padding:5px 5px 0',  
        	defaults: {
                 anchors:'100%'
             },
            fieldDefaults:{
            	msgTarget:'side',
            	autoFitErrors: false
            },
            items:[
		        {
		            xtype: 'textfield',
		            name: 'invoiceNo',
		            fieldLabel: INVOICE_NO,
		            width: 400,
		            allowBlank:false
		        },
		        {
               	 xtype:'fieldcontainer',
               	 fieldLabel:INVOICE_DATE,
               	 layout:'hbox',         
               	 defaults:{
               		 hideLabel:true
               	 },
               	 items:[
						{
						    xtype: 'datefield',
						    name: 'invoiceDate',						
		                	msgTarget:'side',
		                	autoFitErrors: false,		                	
						    maxValue: new Date(),
						    format:'d/m/Y',
						    allowBlank: false,
						    plugins:[
						        new Ext.ux.InputTextMask('99/99/9999')     
						     
						     ]
						},{
							xtype:'label',
							text: DATE_EXAMPLE,
							margin: '2 0 0 10'
							//alignTo:'c-b'
						} 
               	  ]               	   
                  },
                  {
    		            xtype: 'combo',
    		            name: 'categoryName',
    		            fieldLabel: CATEGORY_NAME,
    		            width: 400,
    		            allowBlank: false,
    		            hideTrigger: true,
    		            typeAhead: false,
    		            minChars: 1,
    		            store: categoryStore,
    		            listConfig:{
    		                getInnerTpl: function(){
    		                    return '<div class="search-item">'+
    		                    		'<p><b>{name}</b></p>'+
    		                    		'<p><i>({description})</i>'+
    		                    '</div>';
    		                }
    		            },
    		            listeners:{
    		                scope: this,
    		                select:function(combo, selection){
    		                    var category = selection[0];
    		                    if(category){
    		                    	 combo.setValue(category.data.name);    		                    	
    		                    }
    		                }
    		            }  		            
    		     }, 	 
                 {
  		            xtype: 'combo',
  		            name: 'centerVendorName',
  		            fieldLabel: CENTER_VENDOR_NO,
  		            width: 400,
  		            hideTrigger: true,
  		            typeAhead: false,
  		            minChars: 1,
  		            store: centerVendorStore,
  		            listConfig:{
  		                getInnerTpl: function(){
  		                    return '<div class="search-item">'+
  		                    		'<p><b>{username}</b></p>'+
  		                    		'<p><i>({address})</i>'+
  		                    '</div>';
  		                }
  		            },
  		            listeners:{
  		                scope: this,
  		                select:function(combo, selection){
  		                    var vendor = selection[0];
  		                    if(vendor){
  		                    	 combo.setValue(vendor.data.username);
  		                    	 //check center vendor 99902
  		                    	 //disable processed and vendor name
  		                    	var vendorNameField = this.child('form').child('#vendorName');
  		                    	var processedInvoiceNoField = this.child('form').child('#processedInvoiceNo');
  		                    	 if(vendor.data.username == '99902') {  		                    	
  		                    		processedInvoiceNoField.disable(true);
  		                    		processedInvoiceNoField.setValue("");
  			                        vendorNameField.disable(true);
  			                        vendorNameField.setValue("");
  		                    	 } else {
  		                    		processedInvoiceNoField.enable(false);  		                    		
  			                        vendorNameField.enable(false);
  		                    	 }
  		                    }
  		                }
  		            }  		            
  		        }, 	        
		        {
		            xtype: 'combo',
		            name: 'processedInvoiceNo',
		            itemId:"processedInvoiceNo",
		            fieldLabel: PROCESSED_INVOICE_NO,
		            width: 400,
		            hideTrigger: true,
		            typeAhead: false,
		            minChars: 1,
		            store: invoiceStore,
		            listConfig:{
		                getInnerTpl: function(){
		                    return '<div class="search-item">{invoiceNo}</div>';
		                }
		            },
		            listeners:{
		                scope: this,
		                select:function(combo, selection){
		                    var invoice = selection[0];
		                    if(invoice){
		                        combo.setValue(invoice.data.invoiceNo);
		                        //set value for vendor name and product name
		                        var vendorNameField = this.child('form').child('#vendorName');
		                        vendorNameField.setValue(invoice.data.vendorName);		                    
		                        //product
		                        var productField = this.child('form').child('#productName');
		                        productField.setValue(invoice.data.productName);		                      
		                    }
		                }
		            }
		            
		        },  
		        {
		            xtype:'combo',
		            fieldLabel: VENDOR_NAME,
		            name:'vendorName',
		            itemId:'vendorName',        
		            width: 400,
		            hideTrigger: true,
		            typeAhead: false,
		            minChars:1,
		            store: vendorStore,		         	                                                       
		            listConfig:{
		                getInnerTpl: function(){
		                    return '<div class="search-item">'+
		                        '{username}' +
		                    '<div>';
		                }
		                
		            },
		            listeners:{
		                scope: this,
		                select:function(combo, selection){		                    
		                    var user = selection[0];
		                    if(user){                                    
		                        combo.setValue(user.data.username);
		                        //set id for vendor
		                        var vendorIdField = this.child('form').child('#vendorId');
		                        vendorIdField.setValue(user.data.id);
		                    }
		                }
		            }
		        },
		        /*
		        {
		          xtype: 'hiddenfield',
		          name:'vendorId',
		          itemId: 'vendorId'
		          
		        },
		        {
		          xtype: 'hiddenfield',
		          name:'processedInvoiceId',
		          itemId: 'processedInvoiceId'  
		        },
		        */
		        {
		            xtype: 'hiddenfield',
		            name:'type',
		            value: 1  
		          },
		        {
		            xtype:'textfield',
		            fieldLabel: PRODUCT_NAME,
		            name:'productName',
		            itemId:'productName',
		            width: 400,
		            allowBlank: false
		        },
		         {
		            xtype:'textfield',
		            fieldLabel: MONEY,
		            name:'money',
		            width: 400,
		            allowBlank: false,		            
                    listeners:{
                 	   change:function(field,value){
                 		   //remove before
                 		   value = value.replace(/\./gm, "");                    		 
                 		   value = Ext.util.Format.number(value,'0,000');                    		
                 		   field.setValue(value);
                 	   }
                    }
		        },
		        {
		            xtype:'textarea',
		            fieldLabel: NOTE,
		            name:'note',
		            width: 400,
		            height: 100,
		            maxlength: 100
		            
		        },
		        {
		        	xtype:'hiddenfield',
		        	name:'id'
		        },
		        {
		        	xtype: 'hiddenfield',
		        	name:'createDate'
		        }, {
		        	xtype: 'hiddenfield',
		        	name:'authorId'
		        }
                   
            ],
           buttons:[
				{
				    text:SAVE,
				    formBind:true,
				    action:'save'
				},
				{
				    text: CANCEL,
				    scope: this,
				    handler: this.close
				}   
            ]
            
        });
    
        this.items = [form];                       
        this.callParent(arguments);   
    }
});