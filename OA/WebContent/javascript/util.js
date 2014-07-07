/**
 * commen javascript
 */

changeTheme = function(themeName) { /* 更换主题 */
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName
			+ '/easyui.css';
	$easyuiTheme.attr('href', href);
	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for (vari = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}
	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
};

$.extend($.fn.validatebox.defaults.rules, {
	less : {
		validator : function(value, param) {
			var date = $(param[0]).datebox('getValue');
			return value <= date;
		},
		message : '开始日期大于结束日期'
	},
	larger : {
		validator : function(value, param) {
			var date = $(param[0]).datebox('getValue');
			return value >= date;
		},
		message : '结束日期小于开始日期'
	},
	date : {
		validator : function(value, param) {
			return /^(\d{4})-(\d{2})-(\d{2})$/.test(value);
		},
		message : '日期类型不正确'
	}
});

$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]]
					&& data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

$.fn.treegrid.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().treegrid.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'name';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]]
					&& data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['name'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['name'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

/**
 * 定制用户按键的处理
 * 
 * @param 需要绑定的控件
 * @param 用户ENTER时控件
 * @param 用户ESC时控件
 */
var onPressKey = function(formWidget, submitWidget, closeWidget) {
	formWidget.bind('keypress', function(event) {
		if (formWidget) {
			if (submitWidget && event.keyCode == 13) {// 回车按下
				submitWidget.click();
			} else if (closeWidget && event.keyCode == 27) {// ESC按下
				closeWidget.click();
			}
		}
	});
};