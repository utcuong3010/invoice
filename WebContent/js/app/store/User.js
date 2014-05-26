Ext.define('AM.store.User', {
    extend: 'Ext.data.Store',
    model:'AM.model.User',
    autoLoad: false,
    pageSize: 20,    
    proxy:{
        type:'ajax',
        api:{
            read:'user/list.html',
            update: 'user/update.html'
        },     
        reader:{
            type:'json',
            root:'users',
            totalProperty: 'totalCount',         
            successProperty:'success'
        }
    }
});