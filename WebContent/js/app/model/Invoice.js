Ext.define('AM.model.Invoice',{
    extend: 'Ext.data.Model',
    fields:[
        'id',
        'invoiceNo',
        'invoiceDate', 
        'centerVendorName',
        'vendorName',
        'vendorId',
        {name:'productName',type:'string'},
        'money',
        'status',
        'statusString',
        'type',
        'note',
        'createDate',
        'processedInvoiceId',
        'processedInvoiceNo',
        'categoryName',
        'authorName',
        'authorId'
    ],
	proxy:{
		type: 'ajax',
		api:{
			read: 'invoice/list.html',
			update: 'invoice/update.html',
			create: 'invoice/create.html',
			destroy: 'invoice/deletes.html'
		},
		reader:{
			type: 'json',
			root: 'invoices',
			successProperty: 'success',
			totalProperty: 'totalCount'
			
		}
	}
});