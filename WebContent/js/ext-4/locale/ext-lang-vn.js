/*

This file is part of Ext JS 4

Copyright (c) 2011 Sencha Inc

Contact:  http://www.sencha.com/contact

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.sencha.com/contact.

*/
/**
 * List compiled by mystix on the extjs.com forums.
 * Thank you Mystix!
 * Vietnamese translation
 * By bpmtri
 * 12-April-2007 04:06PM
 */
Ext.onReady(function(){
    if(Ext.Updater){
        Ext.Updater.defaults.indicatorText = '<div class="loading-indicator">\u0110ang t\u1ea3i...</div>';
    }

    if(Ext.view.View){
       Ext.view.View.prototype.emptyText = "";
    }

    if(Ext.grid.Panel){
       Ext.grid.Panel.prototype.ddText = "{0} d\u00f2ng \u0111\u01b0\u1ee3c ch\u1ecdn";
    }

    if(Ext.TabPanelItem){
       Ext.TabPanelItem.prototype.closeText = "\u0110\u00f3ng th\u1ebb n\u00e0y";
    }

    if(Ext.form.field.Base){
       Ext.form.field.Base.prototype.invalidText = "Gi\u00e1 tr\u1ecb c\u1ee7a \u00f4 n\u00e0y kh\u00f4ng h\u1ee3p l\u1ec7.";
    }

    if(Ext.LoadMask){
        Ext.LoadMask.prototype.msg = "\u0110ang t\u1ea3i...";
    }
    
    if(Ext.Date){
        Ext.Date.monthNames = [
           "Th\u00e1ng 1",
           "Th\u00e1ng 2",
           "Th\u00e1ng 3",
           "Th\u00e1ng 4",
           "Th\u00e1ng 5",
           "Th\u00e1ng 6",
           "Th\u00e1ng 7",
           "Th\u00e1ng 8",
           "Th\u00e1ng 9",
           "Th\u00e1ng 10",
           "Th\u00e1ng 11",
           "Th\u00e1ng 12"
        ];

        Ext.Date.dayNames = [
           "Ch\u1ee7 nh\u1eadt",
           "Th\u1ee9 hai",
           "Th\u1ee9 ba",
           "Th\u1ee9 t\u01b0",
           "Th\u1ee9 n\u0103m",
           "Th\u1ee9 s\u00e1u",
           "Th\u1ee9 b\u1ea3y"
        ];
    }
    
    if(Ext.MessageBox){
       Ext.MessageBox.buttonText = {
          ok     : "\u0110\u1ed3ng \u00fd",
          cancel : "H\u1ee7y b\u1ecf",
          yes    : "C\u00f3",
          no     : "Kh\u00f4ng"
       };
    }

    if(Ext.util.Format){
        Ext.apply(Ext.util.Format, {
            thousandSeparator: '.',
            decimalSeparator: ',',
            currencySign: '\u20ab',  // Vietnamese Dong
            dateFormat: 'd/m/Y'
        });
    }

    if(Ext.picker.Date){
       Ext.apply(Ext.picker.Date.prototype, {
          todayText         : "H\u00f4m nay",
          minText           : "Ng\u00e0y n\u00e0y nh\u1ecf h\u01a1n ng\u00e0y nh\u1ecf nh\u1ea5t",
          maxText           : "Ng\u00e0y n\u00e0y l\u1edbn h\u01a1n ng\u00e0y l\u1edbn nh\u1ea5t",
          disabledDaysText  : "",
          disabledDatesText : "",
          monthNames	: Ext.Date.monthNames,
          dayNames		: Ext.Date.dayNames,
          nextText          : 'Th\u00e1ng sau (Control+Right)',
          prevText          : 'Th\u00e1ng tr\u01b0\u1edbc (Control+Left)',
          monthYearText     : 'Ch\u1ecdn m\u1ed9t th\u00e1ng (Control+Up/Down \u0111\u1ec3 thay \u0111\u1ed5i n\u0103m)',
          todayTip          : "{0} (Spacebar - Ph\u00edm tr\u1eafng)",
          format            : "d/m/y"
       });
    }

    if(Ext.toolbar.Paging){
       Ext.apply(Ext.PagingToolbar.prototype, {
          beforePageText : "Trang",
          afterPageText  : "of {0}",
          firstText      : "Trang \u0111\u1ea7u",
          prevText       : "Trang tr\u01b0\u1edbc",
          nextText       : "Trang sau",
          lastText       : "Trang cu\u1ed1i",
          refreshText    : "T\u1ea3i l\u1ea1i",
          displayMsg     : "Hi\u1ec3n th\u1ecb {0} - {1} c\u1ee7a {2}",
          emptyMsg       : 'Kh\u00f4ng c\u00f3 d\u1eef li\u1ec7u \u0111\u1ec3 hi\u1ec3n th\u1ecb'
       });
    }

    if(Ext.form.field.Text){
       Ext.apply(Ext.form.field.Text.prototype, {
          minLengthText : "Chi\u1ec1u d\u00e0i t\u1ed1i thi\u1ec3u c\u1ee7a \u00f4 n\u00e0y l\u00e0 {0}",
          maxLengthText : "Chi\u1ec1u d\u00e0i t\u1ed1i \u0111a c\u1ee7a \u00f4 n\u00e0y l\u00e0 {0}",
          blankText     : "\u00d4 n\u00e0y c\u1ea7n ph\u1ea3i nh\u1eadp gi\u00e1 tr\u1ecb",
          regexText     : "",
          emptyText     : null
       });
    }

    if(Ext.form.field.Number){
       Ext.apply(Ext.form.field.Number.prototype, {
          minText : "Gi\u00e1 tr\u1ecb nh\u1ecf nh\u1ea5t c\u1ee7a \u00f4 n\u00e0y l\u00e0 {0}",
          maxText : "Gi\u00e1 tr\u1ecb l\u1edbn nh\u1ea5t c\u1ee7a \u00f4 n\u00e0y l\u00e0  {0}",
          nanText : "{0} h\u00f4ng ph\u1ea3i l\u00e0 m\u1ed9t s\u1ed1 h\u1ee3p l\u1ec7"
       });
    }

    if(Ext.form.field.Date){
       Ext.apply(Ext.form.field.Date.prototype, {
          disabledDaysText  : "V\u00f4 hi\u1ec7u",
          disabledDatesText : "V\u00f4 hi\u1ec7u",
          minText           : "Ng\u00e0y nh\u1eadp trong \u00f4 n\u00e0y ph\u1ea3i sau ng\u00e0y {0}",
          maxText           : "Ng\u00e0y nh\u1eadp trong \u00f4 n\u00e0y ph\u1ea3i tr\u01b0\u1edbc ng\u00e0y {0}",
          invalidText       : "{0} kh\u00f4ng ph\u1ea3i l\u00e0 m\u1ed9t ng\u00e0y h\u1ee3p l\u1ec7 - ph\u1ea3i c\u00f3 d\u1ea1ng {1}",
          format            : "d/m/y"
       });
    }

    if(Ext.form.field.ComboBox){
       Ext.apply(Ext.form.field.ComboBox.prototype, {
          valueNotFoundText : undefined
       });
        Ext.apply(Ext.form.field.ComboBox.prototype.defaultListConfig, {
            loadingText       : "\u0110ang t\u1ea3i..."
        });
    }

    if(Ext.form.field.VTypes){
       Ext.apply(Ext.form.field.VTypes, {
          emailText    : 'Gi\u00e1 tr\u1ecb c\u1ee7a \u00f4 n\u00e0y ph\u1ea3i l\u00e0 m\u1ed9t \u0111\u1ecba ch\u1ec9 email c\u00f3 d\u1ea1ng nh\u01b0 "ten@abc.com"',
          urlText      : 'Gi\u00e1 tr\u1ecb c\u1ee7a \u00f4 n\u00e0y ph\u1ea3i l\u00e0 m\u1ed9t \u0111\u1ecba ch\u1ec9 web(URL) h\u1ee3p l\u1ec7, c\u00f3 d\u1ea1ng nh\u01b0 "http:/'+'/www.example.com"',
          alphaText    : '\u00d4 n\u00e0y ch\u1ec9 \u0111\u01b0\u1ee3c nh\u1eadp c\u00e1c k\u00ed t\u1ef1 v\u00e0 g\u1ea1ch d\u01b0\u1edbi(_)',
          alphanumText : '\u00d4 n\u00e0y ch\u1ec9 \u0111\u01b0\u1ee3c nh\u1eadp c\u00e1c k\u00ed t\u1ef1, s\u1ed1 v\u00e0 g\u1ea1ch d\u01b0\u1edbi(_)'
       });
    }

    if(Ext.grid.header.Container){
       Ext.apply(Ext.grid.header.Container.prototype, {
          sortAscText  : "T\u0103ng d\u1ea7n",
          sortDescText : "Gi\u1ea3m d\u1ea7n",
          lockText     : "Kh\u00f3a c\u1ed9t",
          unlockText   : "B\u1ecf kh\u00f3a c\u1ed9t",
          columnsText  : "C\u00e1c c\u1ed9t"
       });
    }

    if(Ext.grid.PropertyColumnModel){
       Ext.apply(Ext.grid.PropertyColumnModel.prototype, {
          nameText   : "T\u00ean",
          valueText  : "Gi\u00e1 tr\u1ecb",
          dateFormat : "j/m/Y"
       });
    }

    if(Ext.layout.BorderLayout && Ext.layout.BorderLayout.SplitRegion){
       Ext.apply(Ext.layout.BorderLayout.SplitRegion.prototype, {
          splitTip            : "K\u00e9o gi\u1eef chu\u1ed9t \u0111\u1ec3 thay \u0111\u1ed5i k\u00edch th\u01b0\u1edbc.",
          collapsibleSplitTip : "K\u00e9o gi\u1eef chu\u1ed9t \u0111\u1ec3 thay \u0111\u1ed5i k\u00edch th\u01b0\u1edbc. Nh\u1ea5p \u0111\u00fap \u0111\u1ec3 \u1ea9n \u0111i."
       });
    }
});
