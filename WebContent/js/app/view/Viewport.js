
Ext.define('AM.view.Viewport',{
    extend: 'Ext.container.Viewport',
    requires: 'AM.view.Toolbar',
    layout: 'boder',
    style: {
        padding: '20px'
    },
    initComponent:function(){

        this.items = {
            dockedItems:[{
                docked: 'top',
                xtype:'mytoolbar'
            }]
        },
        
         this.callParent(arguments); 
    }
    
    
   
   
});