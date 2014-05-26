/***
*	main toolbar
*
*/

var HOME = "Trang ch\u1ee7";
var SUPPLIER_INVOICES  = "Nh\u00e0 Cung C\u1ea5p";
var CENTRAL_INVOICES = "Trung T\u00e2m ph\u00e2n ph\u1ed1i";
var USERS = "Qu\u1ea3n l\u00fd T\u00e0i Kho\u1ea3n"
var USERS_TOOLTIP = "Qu\u1ea3n l\u00fd T\u00e0i kho\u1ea3n"

var LOGOUT = "Tho\u00e1t";

/*** list **/
var ADD = "T\u1ea1o h\u00f3a \u0111\u01a1n";
var DELETE = "X\u00f3a h\u00f3a \u0111\u01a1n";
var EXPORT_EXCEL = "Xu\u1ea5t Excel";
var EXPORT_PDF = "Xu\u1ea5t Pdf";
/**** users **/
var ADD_USER = "T\u1ea1o ng\u01b0\u1eddi d\u00f9ng";
var DELETE_USER = "X\u00f3a ng\u01b0\u1eddi d\u00f9ng";

var FROM_DATE = "T\u1eeb";
var TO_DATE = "\u0110\u1ebfn";

var ALL = "T\u1ea5t c\u1ea3";
var INVOICE_NO = "S\u1ed1 h\u00f3a \u0111\u01a1n";
var CATEGORY_NAME = "Ng\u00e0nh h\u00e0ng";
var VENDOR_NAME = "Nh\u00e0 Cung C\u1ea5p";
var CENTER_VENDOR_NAME = "M\u00e3 s\u1ed1 Trung T\u00e2m";
var PRODUCT_NAME = "S\u1ea3n ph\u1ea9m";
var SEARCH = "T\u00ecm ki\u1ebfm";
//header
var INVOICE_DATE = "Ng\u00e0y h\u00f3a \u0111\u01a1n";
var MONEY = "Ti\u1ec1n";
var STATUS = "Tr\u1ea1ng th\u00e1i";
var CREATE_DATE = "Ng\u00e0y t\u1ea1o";
var MODIFY_DATE = "Ng\u00e0y ch\u1ec9nh s\u1eeda";
var PROCESSED_INVOICE_NO = "H\u00f3a \u0111\u01a1n x\u1eed l\u00fd";
var CENTER_VENDOR_NO = "M\u00e3 s\u1ed1 Trung T\u00e2m"
/*** edit form **/
var SUPPLIER_INVOICE = "H\u00f3a \u0111\u01a1n Nh\u00e0 Cung C\u1ea5p";
var CENTRAL_INVOICE = "H\u00f3a \u0111\u01a1n Trung T\u00e2m";
var NOTE = "Ghi ch\u00fa";
var SAVE = "L\u01b0u";
var CANCEL = "H\u1ee7y";


/***delete invoice***/
var DELETE_INVOICE_MSG_TITLE = "X\u00f3a h\u00f3a \u0111\u01a1n";
var DELETE_INVOICE_MSG_ALERT = "H\u00e3y ch\u1ecdn h\u00f3a \u0111\u01a1n b\u00ean d\u01b0\u1edbi tr\u01b0\u1edbc khi x\u00f3a?";
var DELETE_INVOICE_MSG_CONFIRM = "B\u1ea1n c\u00f3 ch\u1eafc mu\u1ed1n x\u00f3a h\u00f3a \u0111\u01a1n hay kh\u00f4ng?";
var DELETE_INVOICE_MSG_ERROR = "C\u00f3 l\u1ed5i trong qu\u00e1 tr\u00ecnh x\u00f3a h\u00f3a \u0111\u01a1n.B\u1ea1n h\u00e3y th\u1eed l\u1ea1i";
var DATE_EXAMPLE = "v\u00ed d\u1ee5: 20/10/2012...";

/*** user management***/
var USER_INFO = "Th\u00f4ng tin c\u00e1 nh\u00e2n";
var USER_LOGIN_INFO = "Th\u00f4ng tin \u0111\u0103ng nh\u1eadp";
var USER_NAME = "T\u00ean \u0111\u0103ng nh\u1eadp";
var USER_EMAIL = "Email";
var GROUP_NAME = "T\u00ean nh\u00f3m";
var USER_STATUS = "Tr\u1ea1ng th\u00e1i";
var USER_FULLNAME = "H\u1ecd v\u00e0 T\u00ean";
var USER_PHONE = "S\u1ed1 \u0111i\u1ec7n tho\u1ea1i";
var USER_ADDRESS = "\u0110\u1ecba ch\u1ec9";
var USER_PASSWORD = "M\u1eadt kh\u1ea9u";
var USER_REPASSWORD = "Nh\u1eadp l\u1ea1i M\u1eadt Kh\u1ea9u";
var USER_ADD_FORM_TITLE = "Th\u00eam ng\u01b0\u1eddi d\u00f9ng";
var USER_UPDATE_FORM_TITLE = "C\u1eadp nh\u1eadt th\u00f4ng tin";

var STATUS_ACTIVATED = "\u0110ang ho\u1ea1t \u0111\u1ed9ng";
var STATUS_DELETED = "\u0110\u00e3 x\u00f3a";
var STATUS_BLOCKED = "\u0110\u00e3 b\u1ecb kh\u00f3a";
//user
var DELETE_USER_MSG_TITLE = "X\u00f3a T\u00e0i Kho\u1ea3n";
var DELETE_USER_MSG_ALERT = "H\u00e3y ch\u1ecdn T\u00e0i Kho\u1ea3n b\u00ean d\u01b0\u1edbi tr\u01b0\u1edbc khi x\u00f3a?";
var DELETE_USER_MSG_CONFIRM = "B\u1ea1n c\u00f3 ch\u1eafc mu\u1ed1n x\u00f3a T\u00e0i Kho\u1ea3n hay kh\u00f4ng?";
var DELETE_USER_MSG_ERROR = "C\u00f3 l\u1ed5i trong qu\u00e1 tr\u00ecnh x\u00f3a.B\u1ea1n h\u00e3y th\u1eed l\u1ea1i";

//activate
var ACTIVATE_USER_MSG_TITLE = "Ph\u1ee5c h\u1ed3i t\u00e0i kho\u1ea3n";
var ACTIVATE_USER_MSG_ALERT = "H\u00e3y ch\u1ecdn T\u00e0i Kho\u1ea3n b\u00ean d\u01b0\u1edbi tr\u01b0\u1edbc khi ph\u1ee5c h\u1ed3i?";
var ACTIVATE_USER_MSG_CONFIRM = "B\u1ea1n c\u00f3 ch\u1eafc mu\u1ed1n ph\u1ee5c h\u1ed3i T\u00e0i Kho\u1ea3n hay kh\u00f4ng?";
var ACTIVATE_USER_MSG_ERROR = "C\u00f3 l\u1ed5i trong qu\u00e1 tr\u00ecnh ph\u1ee5c h\u1ed3i t\u00e0i kho\u1ea3n.B\u1ea1n h\u00e3y th\u1eed l\u1ea1i";

//deactivate
var DEACTIVATE_USER_MSG_TITLE = "Kh\u00f3a t\u00e0i kho\u1ea3n";
var DEACTIVATE_USER_MSG_ALERT = "H\u00e3y ch\u1ecdn T\u00e0i Kho\u1ea3n b\u00ean d\u01b0\u1edbi tr\u01b0\u1edbc khi Kh\u00f3a t\u00e0i kho\u1ea3n?";
var DEACTIVATE_USER_MSG_CONFIRM = "B\u1ea1n c\u00f3 ch\u1eafc mu\u1ed1n Kh\u00f3a T\u00e0i Kho\u1ea3n hay kh\u00f4ng?";
var DEACTIVATE_USER_MSG_ERROR = "C\u00f3 l\u1ed5i trong qu\u00e1 tr\u00ecnh Kh\u00f3a T\u00e0i Kho\u1ea3n.B\u1ea1n h\u00e3y th\u1eed l\u1ea1i";
//search
var DEACTIVATE_USER = "Kho\u00e1 t\u00e0i kho\u1ea3n";
var ACTIVATE_USER = "Ph\u1ee5c h\u1ed3i t\u00e0i kho\u1ea3n";

var EDIT_USER = "Ch\u1ec9nh s\u1eeda th\u00f4ng tin";
var AUTHOR = "Ng\u01b0\u1eddi t\u1ea1o";
var INVOICE_STATUS_APPENDING = "\u0110ang ch\u1edd x\u1eed l\u00fd";
var INVOICE_STATUS_PROCESSED = "\u0110\u00e3 x\u1eed l\u00fd";


