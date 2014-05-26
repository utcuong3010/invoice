Ext.define('AM.view.user.EditUser',{
    extend: 'Ext.window.Window',     
    layout: 'fit',
    modal: true,
    autoShow:true,
    width: 500,
    resizable: false,
    title: EDIT_USER,
    initComponent: function(){
    	
        this.items = [
            {
                xtype: 'form',
                bodyStyle: 'padding:5px 5px 0',  
            	defaults: {
                     anchors:'100%'
                 },
                fieldDefaults:{
                	msgTarget:'side',
                	autoFitErrors: false,
                	width:400
                },
                items:[
                    {
                    	xtype:'fieldset',
                    	title:USER_INFO,
                    	collapsible: true,                    
                    	items:[
								{
									xtype:'textfield',
									name:'fullname',
									itemId:'fullname',
									fieldLabel:USER_FULLNAME
								},
								{
									xtype:'textfield',
									vtype:'email',
									name:'email',
									itemId:'email',
									fieldLabel: USER_EMAIL
								},
								{
									xtype:'textfield',
									name:'phone',
									itemId:'phone',
									fieldLabel: USER_PHONE
								},
								{
									xtype:'textarea',
									name:'address',
									itemId:'address',
									fieldLabel:USER_ADDRESS
								}
                    	 ]
                    }, 
                    {
                    	xtype:'fieldset',
                    	title:USER_LOGIN_INFO,
                    	items:[
							{
							    xtype: 'textfield',
							    name: 'username',
							    allowBlank: false ,
							    readOnly: true,
							    fieldLabel: USER_NAME                                              
							},
							{
							    xtype: 'textfield',
							    inputType:'password',
							    name: 'password',
							    id: 'pass',
							    allowBlank: false ,
							    fieldLabel: USER_PASSWORD
							},
							{
							    xtype: 'textfield',
							    inputType:'password',
							    name: 'rePassword',							
							    allowBlank: false ,
							    fieldLabel: USER_REPASSWORD,
							    initialPassField:'pass'
							},
							{
					            xtype: 'hiddenfield',
					            name:'id'					              
					          },
					          {
						         xtype: 'hiddenfield',
						         name:'status'					              
						     },
						     {
						         xtype: 'hiddenfield',
						         name:'type'					              
						     }
						
                    	]
                   }                    
                ] 
            }
        ];        
        this.buttons = [
            {
                text:SAVE,
                scope:this,
                handler: function(){
                	//update
                	 var form = this.down("form").getForm();
                	 var win = this;
                	 if(form.isValid()){
                		 //submit
                		 form.submit({
                			 url:'user/update.html',
                			 method:'POST',
                             success: function(form, action) {
                            	 win.close();
                             },
                             failure: function(form, action) {
                                
                             }
                         });
                	 }
                }
                	
            },
            {
                text: CANCEL,
                scope: this,
                handler: this.close
            }        
        ];        
        this.callParent(arguments);
    },
    loadData: function(username){
    	
    	Ext.Ajax.request({
    	    url: 'user/getUser.html',
    	    method:'GET',
    	    params: {
    	        username: username
    	    },
    	    success: function(response){
    	        var data = Ext.decode(response.responseText);
    	        var record = Ext.create("AM.model.User");
    	        record.data = data;
    	        // process server response here    	     
    	        var form = this.down("form");
    	        form.loadRecord(record);    	       
    	    }, 
    	    scope: this
    	});
    }
    
});
