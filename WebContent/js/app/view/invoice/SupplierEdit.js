
Ext.require('Ext.ux.InputTextMask');
Ext.define('AM.view.invoice.SupplierEdit',{
    extend: 'Ext.window.Window',
    alias:'widget.invoiceSupplierEdit',   
    title: SUPPLIER_INVOICE,
    layout:'fit',
    modal: true,
    autoShow:true,
    width: 500,
    resizable: false, 
    isNewForm: true,
    initComponent:function(){
        var ds = Ext.create('AM.store.User',{
            pageSize: 10,
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
        //form
        var form = Ext.create("Ext.form.Panel",{        	
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
                       allowBlank: false
                      
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
                       xtype:'combo',
                       fieldLabel: VENDOR_NAME,
                       name:'vendorName',        
                       width: 400,
                       allowBlank: false,
                       hideTrigger: true,
                       typeAhead: false,
                       minChars:1,
                       store: ds,                                                                   
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
                               }
                           }
                       }
                   },                   
                   {
                       xtype:'textfield',
                       fieldLabel: PRODUCT_NAME,
                       name:'productName',
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
                       xtype: 'hiddenfield',
                       name:'type',
                       value: 0  
                    },
                    {
                        xtype: 'hiddenfield',
                        name:'id'                     
                     },
                    {
                        xtype: 'hiddenfield',
                        name:'createDate'                     
                     },
                     {
     		        	xtype: 'hiddenfield',
     		        	name:'authorId'
     		        }
               ],
            //buttons   
           buttons:[
                      {
                          text:SAVE,
                          formBind:true,
                          action:'save',
                          
                          hidden: !(authenticate.isAdminRole || authenticate.isEditRole
                        		  	|| (authenticate.isCreateRole && this.isNewForm))
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