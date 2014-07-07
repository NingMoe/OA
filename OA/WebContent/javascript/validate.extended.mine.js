/**
 * 关于此次项目的，对validatebox的扩展
 * @author 蔡彬文 2014.5.26
 */

$.extend($.fn.validatebox.defaults.rules, {
	DEPART_EXISTS:{
		validator: function (value, param) {
			//不检测原部门名称
			if(param && param[0] && value == param[0]) return true;
			prop = 'depart.departName';
			val = value;
			url = '/OA/depart/checkDepartName';
            return !prop_val_exists(prop,val,url);
        },
        message: '机构下已存在该部门,请另择部门名称!'
	},
	FILENAME:{
		validator: function (value, param) {
			var reg = new RegExp('^[^\\/:*?\]"<>|$');
            return reg.test(value);
        },
        message: '不能包含以下任何字符:   \\/:*?\"<>|!'
	}
});

var prop_val_exists=function(prop,val,url){
	var result=true;
	$.ajax({
        type: 'post', 
        url: url,
        data:prop+"="+val,
        dataType:'json',
        async: false,//是否异步执行
        success: function(data) {
        	result = data;
        }
	});
	return result;
};
