/*
 * jQuery validation 验证类型扩展
 *
 * 扩展的验证类型：用户名，手机号码,电话号码
 * 
 * 2011-01-22 by kxt
 */       
// 手机号码验证    
jQuery.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/.test(value));
}, "请正确填写您的手机号码!");

// 电话号码验证
jQuery.validator.addMethod("isPhone", function(value, element) {
	var tel = /^\d{3,4}-\d{7,9}$/g;
	return this.optional(element) || (tel.test(value));
}, "请正确填写您的电话号码!");


// 用户名字符验证    
jQuery.validator.addMethod("userName", function(value, element) {
	return this.optional(element) || /^\w+$/.test(value);
}, "用户名只能包括英文字母、数字和下划线!");

// 联系电话(手机/电话皆可)验证   
jQuery.validator.addMethod("isTel", function(value,element) {
    var length = value.length;
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
    var tel = /^\d{3,4}-?\d{7,9}$/;
    return this.optional(element) || (tel.test(value) || mobile.test(value));
}, "请正确填写您的联系电话!");