Ext.define('AM.view.user.Login',{
    extend: 'Ext.window.Window',
    alias:'widget.loginform',
    closable: false,
    layout:'fit',
    autoShow: true,
    modal: true,
    width: 300,
    height: 200,
    border: false,
    resizable: false,    
    initComponent: function(){    	
    	var form = Ext.create("Ext.form.Panel",{
    		title: "Please login",
    		defaultType: 'textfield',
    		bodyPadding: 5, 
    		fieldDefaults:{
    			msgTarget: 'side',
    			autoFitErrors: false
    		},
    		items:[
    		       {
                       name: 'j_username',
                       fieldLabel:'Username',
                       allowBlank: false
                       
                   },
                   {
                       fieldLabel: 'Password',
                       name:'j_password',
                       inputType: 'password',
                       allowBlank: false,
                       enableKeyEvents:true,
                       listeners : {
	               			keypress:{
	               				scope:this,
	               				fn: function(ths,e){
	               					if(e.getKey() == e.ENTER){
	               						this.login(ths);
	               					}
	               				}
	               			}
               			}
                       
                   },
                   {
                       xtype: 'checkbox',
                       boxLabel:'Remember me next time',
                       name: 'LoginForm[rememberMe]'
                       
                   }
    		     ],
    		 buttons:[
    		          {
    		        	text: 'Login',
    		        	formBind: true,
    		        	handler: this.login
    		          }
    		         ]
    		
    		
    	
    	});
    	this.items = [form];            
        //call parent
        this.callParent(arguments);
    },     
    //login
    login:function(button){   
        var own = this;
        var win = button.up('window'),
        loginForm = win.down('form');
        //submit
        if(loginForm.getForm().isValid()) {
	        loginForm.getForm().submit({
	            method: 'POST',
	            waitTitle: 'Connecting',
	            waitMsg:'Sending data...',
	            url:'j_spring_security_check',
	            success: function(){
	              win.close();
	              window.location = "index.html?lang=vi";
	            },
	            failure: function(form, action){
	            	Ext.Msg.show({
	            		title: 'Login failure!',
	            		msg: 'Please, try it again',
	            		icon:Ext.Msg.ERROR
	            	});	            	
	            	form.reset();
	            }
	        });
        }
        
    }
});