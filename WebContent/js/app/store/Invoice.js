Ext.define('AM.store.Invoice',{
    extend: 'Ext.data.Store',
    model: 'AM.model.Invoice',    
    autoLoad: false,
    pageSize: 20
   
    
});
//override syn
Ext.data.Store.override({
	mySync: function(options){
		if(Ext.isObject(options)) {
			this.on("write", function(store, operation){
				if(Ext.isDefined(options.scope)){
					options.callback.call(options.scope);
				}
				else 
				{
					options.callback.call(this);
				}
				store.un("write", options.callback);
			});
		}
		this.sync();
	}
});