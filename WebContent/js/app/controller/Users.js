Ext.define('AM.controller.Users',{
    extend: 'Ext.app.Controller',    
    views:[
       'user.List',
       'user.Edit' 
    ],
    models: ['User'],
    stores: ['User'],
    refs: [
        {
            ref: 'userList',
            selector: 'userlist'      
        }  
    ],
    init: function(){
        this.control({           
           'userlist':{
                itemdblclick: function(grid, record){
                	var view = Ext.widget('useredit');                	
                	view.setTitle(USER_UPDATE_FORM_TITLE);
                	
                    view.down('form').loadRecord(record);
                    //load groups
                    view.groupsStore.load({
                    	scope:this,
                    	callback: function(records,operation,success){
                    		if(success){
                    			view.selectedGroups(record.data.groupIds);
                    		}
                    	 }
                    });
                },
                render: function(){
                	this.getUserList().getStore().load({
                		params:{
                			start:0
                		}
                	});
                }
           },
           //create new user
           'userlist #add':{
        	   click:function(){
        		   var form = Ext.widget('useredit');
        		   form.setTitle(USER_ADD_FORM_TITLE);
        		   form.groupsStore.load();
        	   }
           },
           'useredit button[action=save]':{
                click: this.updateUser
           },
           //delete users
           'userlist #delete':{
        	   click: function(){
        		 //get all selected records
        	        var records = this.getUserList().selModel.getSelection();
        	        if(records.length == 0){
        	        	Ext.Msg.alert(DELETE_USER_MSG_TITLE, DELETE_USER_MSG_ALERT);
        	        } else {
        	            Ext.Msg.confirm(DELETE_USER_MSG_TITLE, DELETE_USER_MSG_CONFIRM, function(btn, text){               
        	                if(btn =='yes'){
        	                    //delete all selected records from here 
        	                   var param = new Array();
        	                   Ext.each(records, function(record){
        	                       param.push(record.data.id);        	                	 
        	                   }, this);
        	                   Ext.Ajax.request({
        	                	   url: "user/status.html?",
        	                	   method: 'POST',
        	                	   params: {
        	                		   userIdList:param,
        	                		   status: 2
        	                	   }, 
        	                	   scope: this,
        	                	   success:function(){
        	                		   this.getUserList().getStore().load();
        	                	   },
        	                	   failure: function(){
        	                		   Ext.Msg.alert(DELETE_USER_MSG_TITLE,DELETE_USER_MSG_ERROR);
        	                	   }
        	                   });                               
        	                }
        	            }, this
        	            );
        	        }    
        	   }
           },
           'userlist #unlock':{
        	   click: function(){

          		 	//get all selected records
          	        var records = this.getUserList().selModel.getSelection();
          	        if(records.length == 0){
          	        	Ext.Msg.alert(ACTIVATE_USER_MSG_TITLE, ACTIVATE_USER_MSG_ALERT);
          	        } else {
          	            Ext.Msg.confirm(ACTIVATE_USER_MSG_TITLE, ACTIVATE_USER_MSG_CONFIRM, function(btn, text){               
          	                if(btn =='yes'){
          	                    //delete all selected records from here 
          	                   var param = new Array();
          	                   Ext.each(records, function(record){
          	                       param.push(record.data.id);        	                	 
          	                   }, this);
          	                   Ext.Ajax.request({
          	                	   url: "user/status.html?",
          	                	   method: 'POST',
          	                	   params: {
          	                		   userIdList:param,
          	                		   status: 1//activate
          	                	   }, 
          	                	   scope: this,
          	                	   success:function(){
          	                		   this.getUserList().getStore().load();
          	                	   },
          	                	   failure: function(){
          	                		   Ext.Msg.alert(ACTIVATE_USER_MSG_TITLE,ACTIVATE_USER_MSG_ERROR);
          	                	   }
          	                   });                               
          	                }
          	            }, this
          	            );
          	        } 
        	   }
           },
           'userlist #lock':{
        	   click: function(){
        		 //get all selected records
         	        var records = this.getUserList().selModel.getSelection();
         	        if(records.length == 0){
         	        	Ext.Msg.alert(DEACTIVATE_USER_MSG_TITLE, DEACTIVATE_USER_MSG_ALERT);
         	        } else {
         	            Ext.Msg.confirm(DEACTIVATE_USER_MSG_TITLE, DEACTIVATE_USER_MSG_CONFIRM, function(btn, text){               
         	                if(btn =='yes'){
         	                    //delete all selected records from here 
         	                   var param = new Array();
         	                   Ext.each(records, function(record){
         	                       param.push(record.data.id);        	                	 
         	                   }, this);
         	                   Ext.Ajax.request({
         	                	   url: "user/status.html?",
         	                	   method: 'POST',
         	                	   params: {
         	                		   userIdList:param,
         	                		   status: 0//deactivate
         	                	   }, 
         	                	   scope: this,
         	                	   success:function(){
         	                		   this.getUserList().getStore().load();
         	                	   },
         	                	   failure: function(){
         	                		   Ext.Msg.alert(DEACTIVATE_USER_MSG_TITLE,DEACTIVATE_USER_MSG_ERROR);
         	                	   }
         	                   });                               
         	                }
         	            }, this
         	            );
         	        }
        	   }
           },
           'userlist #searchBtn':{
        	   click: function(){
        		   this.getUserList().store.load({params:{
        	            start:0,
        	            page:1
        	        }});
        	   }
           }
        });              
    },  
    updateUser:function(button){
    	var win = button.up('window'),
        form = win.down('form'),
        record = form.getRecord(),
        values = form.getValues();
        var isNew = record == null ?true: false;            
        if(form.getForm().isValid()){
        	form.getForm().submit({
        		url: isNew? "user/create.html" :"user/update.html",        				
        		scope:this,
        		params: win.extraParams(),
        		success:function(){
        			 win.close();                        
        			 this.getUserList().getStore().load();                                              
                     
        		}
        	});
        }    
    }   
});