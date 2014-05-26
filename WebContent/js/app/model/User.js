Ext.define('AM.model.User', {
    extend: 'Ext.data.Model',
    fields:[
            'id',
            'fullname',
            'email',
            'phone',
            'address',
            'username',            
            'status',
            'groupNames',
            'groupIds',
            {name:'createDateStr'},
            'createDate',
            'modifyDateStr'
           ]
});