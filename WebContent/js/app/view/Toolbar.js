Ext.define('AM.view.Toolbar',{
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.mytoolbar',    
    initComponent: function(){
        this.items = [
            {
              text: HOME,
              tooltip: HOME,
              height: 30 ,
              itemId:"homeItem",
              scale:'medium',           
              iconCls:'icon-home' 
            }, '-',
            {
                text: SUPPLIER_INVOICES,
                tooltip: SUPPLIER_INVOICES,
                height: 30 ,
                itemId:'supplierInvoiceItem'
            },'-',            
            {
                text: CENTRAL_INVOICES,
                tooltip: CENTRAL_INVOICES,                           
                height: 30 ,
                itemId: 'centralInvoiceItem'
                
            },'-',
            {
                text: USERS,
                tooltip: USERS_TOOLTIP,
                height: 30,
                scale:'medium',
                iconCls:'icon-users',
                itemId:'usersItem' ,
                hidden: !authenticate.isAdminRole
                
            },'->',
            {
                text: 'Hi! ' + username,
                iconCls: 'icon-user',
                scale: 'medium',
                iconAlign: 'right',
                menu: {
                    xtype: 'menu',
                    plain: true,
                    items: {
                        xtype: 'buttongroup',                       
                        columns: 2,
                        defaults: {
                            xtype: 'button',
                            scale: 'medium',
                            iconAlign: 'left'
                        },
                        items: [{
                            text: EDIT_USER,
                            iconCls: 'icon-edit-user',                            
                            colspan: 2,
                            width: 130,
                            handler: function(){
                            	//create form for editing                            	
                            	var form = Ext.create('AM.view.user.EditUser');
                            	form.loadData(username);
                            }
                        },
                        {
                        	xtype:'menuseparator',
                        	colspan: 2,
                            width: 130
                        }
                        ,{
                            colspan: 2,
                            text: LOGOUT,                            
                            width: 130,
                            iconCls:'icon-logout',
                            iconAlign: 'left',
                            handler:this.logout
                        }]
                    }
                }
            }
                
        ];        
        
        this.callParent(arguments);
    },
    logout: function(btn){
        window.location = "logout.html";
    }
    
});