
Ext.define('AM.view.invoice.CentralList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.invoiceCentralList', 
    initComponent: function(){      
        //for toolbar
        this.tbar = {
            items:[
                {
                    text: ADD,
                    itemId: 'add',
                    iconCls: 'icon-add',
                    disabled: !(authenticate.isAdminRole || authenticate.isCreateRole)
                    
                },'-',
                {
                    text: DELETE,
                     itemId: 'delete',
                     iconCls: 'icon-delete',
                     disabled: !(authenticate.isAdminRole || authenticate.isDeleteRole)
                },'-',
                {
                    text: EXPORT_EXCEL,
                    itemId: 'export-excel',
                    iconCls: 'icon-export-excel'
                },
                {
                    text: EXPORT_PDF,
                    itemId: 'export-pdf',
                    iconCls: 'icon-export-pdf'
                },'->',
                {
                    xtype: 'datefield',
                    name:'dateFrom',
                    itemId:'dateFrom',                        
                    fieldLabel: FROM_DATE,
                    labelAlign: 'right',
                    labelWidth: 40 ,
                    width: 160,
                    editable: false,
                    format: 'd/m/Y',
                    maxValue:new Date(),
                    value: new Date(new Date().getFullYear(),new Date().getMonth())
                                              
                },
                {
                	xtype: 'timefield',
                	name:'timeFrom',
                	itemId:'timeFrom',
                	minValue: '00:00',
                	maxValue:'23:59',
                	value: '06:00',
                	width: 80
                }, 
                {                   
                    xtype: 'datefield',
                    name: 'dateTo',
                    itemId:'dateTo',
                    fieldLabel: TO_DATE,
                    labelAlign: 'right',
                    labelWidth: 40 ,
                    width: 160,
                    editable: false,
                    format: 'd/m/Y',
                    maxValue:new Date(),
                    value: new Date()                                                               
                },
                {
                	xtype: 'timefield',
                	name:'timeTo',
                	itemId:'timeTo',
                	minValue: '00:00',
                	maxValue:'23:59',
                	value: '23:59',
                	width: 80
                }, 
                '-',
                {
                    xtype:'combo',
                    itemId:'searchField',
                    editable: false,
                    displayField:'name',
                    valueField: 'value',
                    queryMode:'local',
                    value: '0',
                    width: 100,
                    store: new Ext.data.Store({                       
                        fields:['name','value'],
                        data:[
                            {'name': ALL,'value':'0'},
                            {'name': CATEGORY_NAME,'value':'4'} ,                            
                            {'name': VENDOR_NAME,'value':'2'},
                            {'name': INVOICE_NO,'value':'1'},
                            {'name': PRODUCT_NAME,'value':'3'}  
                            
                        ]
                    })
                    
                },'-',
                {
                   xtype: 'textfield',
                   itemId: 'keySearch'
                   
                } ,'-',
                {
                    text: SEARCH,
                    itemId: 'searchBtn',
                    iconCls:'icon-search'
                }        
            ]
        };
                
        var ds = Ext.create('AM.store.Invoice');
        //set base param
        ds.on('beforeload', function(){      
            ds.getProxy().extraParams = this.params();
        }, this);
        
        this.store = ds;
        this.bbar = {
            xtype:'pagingtoolbar',
            store: ds,
            displayInfo: true
        };
 
        //config for expander
       
        this.columns = [       
            {header:'Id',width:30,hidden:true,dataIndex:'id'},
            {header:INVOICE_NO,dataIndex:'invoiceNo', flex:0.5},            
            {header:INVOICE_DATE,dataIndex:'invoiceDate', flex:0.5},
            {header:CATEGORY_NAME,dataIndex:'categoryName', flex:0.5},
            {header:CENTER_VENDOR_NAME,dataIndex:'centerVendorName', flex:0.5},
            {header:VENDOR_NAME,dataIndex:'vendorName', flex:1},
            {header:PRODUCT_NAME,dataIndex:'productName', flex:1},
            {header:MONEY,dataIndex:'money', align:'right',
                renderer:Ext.util.Format.numberRenderer('0,000')          
            },
            {header:PROCESSED_INVOICE_NO,dataIndex:'processedInvoiceNo', flex:0.5},
            {header:CREATE_DATE,dataIndex:'createDate', flex: 0.7},
            {header:AUTHOR,dataIndex:'authorName'},
        ];
        
        this.selModel = Ext.create('Ext.selection.CheckboxModel');
        this.callParent(arguments);
    },
    params: function(){    
        var dateFrom = this.query('#dateFrom')[0].getValue();
    	var timeFrom = this.query('#timeFrom')[0].getValue();
    	var timeTo = this.query('#timeTo')[0].getValue();
        var dateTo = this.query('#dateTo')[0].getValue();
        var keySearch = this.query('#keySearch')[0];
        var searchField = this.query('#searchField')[0]; 
        
        
        dateFrom.setHours(timeFrom.getHours(),timeFrom.getMinutes());
        dateTo.setHours(timeTo.getHours(),timeTo.getMinutes());
        
    	return {
    		 dateFrom: dateFrom,
             dateTo:dateTo,           
             searchKey:keySearch.getValue(),
             searchField: searchField.value,
             isSearch:false,
             type:1
    	};
    }
    
    

});