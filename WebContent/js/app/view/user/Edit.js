Ext.define('AM.view.user.Edit',{
    extend: 'Ext.window.Window',
    alias: 'widget.useredit',     
    layout: 'fit',
    modal: true,
    autoShow:true,
    width: 500,
    resizable: false,
    initComponent: function(){
    	this.groupsStore = Ext.create('Ext.data.Store', {
    		autoDestroy: true,
    		storeId: "groupsId",
    		autoLoad: false,
    	    fields: ['id', 'name', 'description'],
	    	proxy:{
	    	        type:'ajax',
	    	        api:{
	    	            read:'user/groups.html'	    	          
	    	        },     
	    	        reader:{
	    	            type:'json',
	    	            root:'groups',
	    	            totalProperty: 'totalCount',         
	    	            successProperty:'success'
	    	        }
	    	    }   	   
    	});    
    	var tplData = new Ext.XTemplate(
    		    '<tpl for=".">',
    		        '<div id="box{id}" class="databox" onclick="oncheck({id})">',
    		        	'<input name="groups" type="checkbox" id="group{id}" value="{id}"> ',
    		        	'&nbsp;{name} - {description}</div>',
    		    '</tpl>',
    		    '<div class="x-clear"></div>'
    	);
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
							  //  vtype:'password',
							    allowBlank: false ,
							    fieldLabel: USER_REPASSWORD,
							    initialPassField:'pass'
							}
							,
							{
								xtype:"fieldcontainer",
								fieldLabel: GROUP_NAME,
								layout:'hbox',
								items:[
								     {
								    	 xtype:"label"								    	 					    		
								     },								     
								     {
										xtype:"dataview",
										name:"groups",
										autoScroll: true,								
										store: this.groupsStore,
										tpl:tplData,
										height: 200,
										width:295,
										style: "border:1px solid #99BBE8;background:#fff;"	,
										itemSelector: 'div.thumder'
									}								    
								]
							
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
                action:'save'
            },
            {
                text: CANCEL,
                scope: this,
                handler: this.close
            }        
        ];        
        this.callParent(arguments);
    },
	//more method below
    extraParams: function(){
    	var checkboxs = Ext.query("input[id*=group]:checked");
    	var groups = [];
    	Ext.each(checkboxs, function(checkbox){
    		groups.push(checkbox.value);
    	});
    	return {'groupIds': [groups]};
    },
    selectedGroups:function(groupIds){
    	Ext.each(groupIds, function(groupId){
    		oncheck(groupId);
    	});
    }
});
function oncheck(str) {
    if (Ext.get('box' + str).hasCls('checked')) {
        Ext.get('box' + str).removeCls('checked');
        Ext.get("group" + str).dom.checked = false;
    } else {
        Ext.get('box' + str).addCls('checked');
        Ext.get("group" + str).dom.checked = true;
    }
}