Ext.define('AM.view.user.List',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.userlist',
    initComponent: function(){      
        //for toolbar
        this.tbar = {
            items:[
                {
                    text: ADD_USER,
                    itemId: 'add',
                    iconCls: 'icon-add'
                    
                },'-',
                {
                    text: DELETE_USER,
                     itemId: 'delete',
                     iconCls: 'icon-delete'
                },'-',
                {
                     text: ACTIVATE_USER,
                     itemId: 'unlock',
                     iconAlign: 'right',
                     iconCls: 'icon-unlock'
                },
                {
                    text: DEACTIVATE_USER,
                     itemId: 'lock',
                     iconCls: 'icon-lock'
                },
                
                '->',               
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
                    value: new Date(new Date().getFullYear()-1,new Date().getMonth())
                                              
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
                            {'name': USER_FULLNAME,'value':'1'} , 
                            {'name': USER_NAME,'value':'2'} ,                            
                            {'name': USER_EMAIL,'value':'3'} ,
                            {'name': USER_PHONE,'value':'4'} ,
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
        var ds = Ext.create('AM.store.User');
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
            {header:USER_NAME,dataIndex:'username', flex:1},            
            {header:USER_EMAIL,dataIndex:'email', flex:1},            
            {header:GROUP_NAME,dataIndex:'groupNames', flex:1},            
            {header:MODIFY_DATE,dataIndex:'modifyDateStr',flex: 0.5},
            {header:CREATE_DATE,dataIndex:'createDateStr',flex: 0.5},
            {header:USER_STATUS,dataIndex:'status',
            	renderer: function(status){
            		return status == 1? STATUS_ACTIVATED: STATUS_BLOCKED;
            	}}                     
        ];        
        this.selModel = Ext.create('Ext.selection.CheckboxModel');
        this.callParent(arguments);
    },
    params: function(){    
        var dateFrom = this.query('#dateFrom')[0].getValue();
        var dateTo = this.query('#dateTo')[0].getValue();
        var keySearch = this.query('#keySearch')[0];
        var searchField = this.query('#searchField')[0];         
      
        dateFrom.setHours(0,0);
        dateTo.setHours(23,59);
        
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