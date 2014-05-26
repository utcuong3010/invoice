Ext.define('AM.controller.Apps',{
    extend: 'Ext.app.Controller',
    views:['Toolbar'],    
    refs:[
        {
            ref: 'tabPanel',
            selector: 'tabpanel'
        }
    ],    
    init:function(){
        this.control({
        	
        	"mytoolbar #homeItem":{
        		click:function(){
        			var tabpanel = this.getTabPanel();
        	        var tab = tabpanel.child("#homeTab");
        	        tab.setHeight(500);
        	        tabpanel.setActiveTab(tab);
        	        tabpanel.doLayout();
        		}
        	},
            "mytoolbar #usersItem":{
                click: this.getUserList
            },
            "mytoolbar #supplierInvoiceItem":{
                click: this.getSupplierInvoices
            },
            "mytoolbar #centralInvoiceItem":{
                click: this.getCentralInvoices
            }
        });      
    },
    getUserList:function(){
        var tabpanel = this.getTabPanel();     
        var tab = tabpanel.child("#usersTab");        
        if(!tab) {
            tab = tabpanel.add({
            title: USERS,
            itemId:'usersTab',
            closable:true,
            xtype: 'userlist',
            autoScroll: true
            });           
        }        
        tab.show();
        tabpanel.setActiveTab(tab);
        tabpanel.doLayout();                             
    },
    getSupplierInvoices: function(){
        var tabpanel = this.getTabPanel();
        var tab = tabpanel.child("#supplierInvoiceTab");
        if(!tab) {
            tab = tabpanel.add({
                title: SUPPLIER_INVOICES,
                itemId: "supplierInvoiceTab",
                closable:true,
                xtype: 'invoiceSupplierList',
                autoScroll: true
           
            });
        }
        tab.show();        
    },
    getCentralInvoices:function(){
        var tabpanel = this.getTabPanel();
        var tab = tabpanel.child("#centralInvoiceTab");
        if(!tab) {
            tab = tabpanel.add({
                title: CENTRAL_INVOICES,
                itemId: 'centralInvoiceTab',
                closable: true,
                xtype: 'invoiceCentralList',
                autoScroll: true
              
            });
        }
        tab.show();        
    }
});