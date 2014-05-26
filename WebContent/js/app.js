
Ext.Loader.setConfig({enabled:true});
Ext.Loader.setPath('Ext.ux', 'js/app/plugins/');
Ext.require('Ext.container.Viewport');

Ext.application({
    name:'AM',
    appFolder: 'js/app',
    autoCreateViewport:false,
    controllers:['Apps','Users','Invoices'],
    layout:'fit',
    width: '100%',
    height:'100%',
    
    launch: function(){
        Ext.create('Ext.container.Viewport',{
           items:{
                dockedItems:[
                    {
                        xtype:'mytoolbar'
                    }
                ],
                items:[
                    {
                        xtype:'tabpanel',
                       // region:'center',
                        //split:true,
                        //height: 500,
                        items:[{
                            title: HOME,
                            xtype:'container',
                            itemId:'homeTab',
                            loader: {
                            	url:'content.html?name=home',
                            	autoLoad: true,
                            	scope: this,
                            	//height: 400,
                            	success: function(){
                            		//layout
                            		//this.doLayout();
                            	}
                            }
                           
                        }]                                           
                    }
                   
                ]
           }            
        });
    }
});