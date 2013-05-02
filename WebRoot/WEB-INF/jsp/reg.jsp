<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>淘题客注册</title>
<link href="${ctx}/css/login2010.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/unite_header_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/javascript/jquery.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-validate.min.js"></script>
<!-- view_source_begin -->
<script type="text/javascript">
<!--
$(function() {
	// 自定义校验规则codeConfirm，校验验证码是否正确
	$.validator.addMethod("codeConfirm", function(value) {
		return value === '2';
	}, '验证码错误，请重新填写');
	// 在鼠标聚焦到输入框的时候更改样式
	$('#register_form input').bind('focus',function() {
		var item = $(this).parent().parent();
		item.removeClass('wrong').addClass('hint');
		item.find('.login_form_hint').show();
		item.find('.login_form_ok').hide();
		item.find('.login_form_wrong').hide();
	}).bind('blur',function(){
		var item = $(this).parent().parent();
		item.removeClass('hint');
		item.find('.login_form_hint').hide();
	});
	var test = $("#register_form").validate(
	{
		focusInvalid : false,
		onkeyup : false,
		rules : {
			txtEmail : {
				email : true,
				required : true,
				// 服务端校验邮箱是否已经注册
				remote : '../../omRegValidateServlet'
			},
			txtPassword : {
				required : true,
				minlength : 6,
				maxlength : 20
			},
			txtRepeatPass : {
				required : true,
				minlength : 6,
				maxlength : 20,
				equalTo : '#txtPassword'
			},
			txtVerifyCode : {
				required : true,
				codeConfirm : true
			}
		},
		messages : {
			txtEmail : {
				email : 'Email格式错误，请重新填写。',
				required : '请填写您的Email地址。',
				remote : "<span>此Email地址已注册，</span><span>请更换其它地址</span>"
			},
			txtPassword : {
				required : "请输入密码",
				minlength : "<span>格式错误，请使用字母加数字</span><span>或符号的组合，6-20个字符</span>",
				maxlength : "<span>格式错误，请使用字母加数字</span><span>或符号的组合，6-20个字符</span>"
			},
			txtRepeatPass : {
				required : "请输入密码",
				minlength : "<span>格式错误，请使用字母加数字</span><span>或符号的组合，6-20个字符</span>",
				maxlength : "<span>格式错误，请使用字母加数字</span><span>或符号的组合，6-20个字符</span>",
				equalTo : "<span>两次输入的密码不一致</span><span>请重新输入</span>"
			},
			txtVerifyCode : {
				required : '请输入下方图片中的计算结果'
			}
		},
		submitHandler : function() {
			alert('提交成功！');
            $(this.currentForm).find('.login_form_ok').hide();
            this.currentForm.reset()
			return false;
		},
        showErrors: function(errorMap, errorList) {
            if(errorList && errorList.length > 0){
                $.each(errorList,function(index,obj){
                	var item = $(obj.element).parent().parent(); 
                	// 隐藏提示信息
        			item.find('.login_form_hint').hide();
                	// 隐藏输入框成功提示
        			item.find('.login_form_ok').hide();
                	// 显示出错信息
        			item.find('.login_form_wrong').show().html(obj.message);
                });
            }else{
            	var item = $(this.currentElements).parent().parent(); 
            	// 隐藏出错信息
    			item.find('.login_form_wrong').hide();
            	// 显示成功提示
    			item.find('.login_form_ok').css({display:'inline'});
            }
        }
	});
});
//-->
</script>
<!-- view_source_end -->
</head>
<body>
<!-- view_source_begin -->
<div class="login_content">
	<div class="login_content_dash">
		<span class="login_content_dasht"></span>
		<div class="login_content_border">
			<h2>
				<div class="reg_title">
					<img class="img_welcome"
						src="${ctx}/images/dangdang/title_welcome.gif" alt="注册当当网">
						<div id="email_reg_link" class="regprompt">
							<span><a class="txt_blue" name="mobile _register_link"
								href="#">使用手机号码注册</a>，</span>安全方便且<span
								class="txtred">完全免费</span>
						</div>
				</div>
			</h2>
			<div id="email_reg_title_info" class="login_title">
				<p class="one nonce">
					<span>1.填写注册信息</span>
				</p>
				<p class="two">
					<span>2.注册成功</span>
				</p>
			</div>

			<form name="register_form" method="post" onsubmit="return false;"
				id="register_form" class="login_form">
				<div></div>
				<p id="input_email">
					<label>Email地址：</label><span class="login_form_input"><input
						name="txtEmail" type="text" maxlength="40" id="txtEmail"
						class="input_text">
					</span><span class="login_form_ok" id="email_ok" style="display: none;"></span>
					<span class="login_form_wrong" id="emailValidMsg"
						style="display: none;">Email格式错误，请重新填写。</span>
					<span class="login_form_hint" id="emailValidMsg" style="display: none;"><span>请填写您常用的Email地址</span><span>方便您接收验证邮件、找回密码</span></span>
				</p>
				<p id="input_password">
					<label>登录密码：</label><span class="login_form_input"><input
						name="txtPassword" type="password" maxlength="20"
						id="txtPassword" class="input_text" onpaste="return false">
					</span><span class="login_form_ok" id="password_ok"
						style="display: none;"></span>
					<span class="login_form_wrong" id="passwordValidMsg" style="display: none;">请设置您的密码</span>
					<span class="login_form_hint" id="passwordValidMsg" style="display: none;"><span>密码可使用字母加数字或符号的组合</span><span>长度6-20个字符</span></span>
						
				</p>
				<p id="input_repassword">
					<label>确认密码：</label><span class="login_form_input"><input
						name="txtRepeatPass" type="password" maxlength="20"
						id="txtRepeatPass" class="input_text" onpaste="return false">
					</span><span class="login_form_ok" id="repassword_ok"
						style="display: none;"></span>
					<span class="login_form_wrong" id="repeatPassValidMsg" style="display: none;">请再次输入您设置的密码</span>
					<span class="login_form_hint" id="repeatPassValidMsg" style="display: none;">请再次输入您设置的密码</span>
				</p>
				<p id="input_vcode">
					<label>验证码：</label> <span class="login_form_input"> <input
						name="txtVerifyCode" type="text" maxlength="4" id="txtVerifyCode"
						class="validate" style="color: rgb(135, 135, 135); font-size: 12px; font-weight: normal;">
					</span><span class="login_form_ok" id="vcode_ok" style="display: none;"></span>
					<span class="login_form_wrong" id="vcodeValidMsg" style="display: none;">请输入下方图片中的计算结果</span>
					<span class="login_form_hint" id="vcodeValidMsg" style="display: none;">请输入下方图片中的计算结果</span>
				</p>
				<p class="form_validate">
					<label></label> <span class="login_form_validate"><a
						href="javascript:void(0)" name="change_code_img"><img
							src="${ctx}/images/dangdang/show_vcode_new.gif" title="点击更换验证码"
							id="imgVcode" width="220" height="50">
					</a>
					</span><span class="login_form_hint">看不清？<a
						href="javascript:void(0)" name="change_code_link">换张图</a>
					</span>
				</p>
				<p class="form_validate_btn">
					<button type="submit" class="form_refer"
						onmouseover="this.className='form_refer_02'"
						onmouseout="this.className='form_refer'" name="btn_register">提交注册</button>
				</p>
				<p class="form_copyright">
					<input name="xieyi" type="checkbox" value="" checked="checked"
						onclick="document.getElementById('btnClientRegister').style.display=this.checked?'none':'inline';"><span>我已阅读并同意《<a
							href="#">淘题客网管理条款</a>》和《<a href="#">淘题客网社区条款</a>》</span>
							<span class="login_form_wrong" id="btnClientRegister" style="display: none"><span>抱歉，您必须同意淘题客网的管理条款后，才能提交注册。</span>
					</span>
				</p>
				<input type="submit" name="btnRegister" value="" id="btnRegister" style="display: none">
			</form>
		</div>
	</div>
	<div class="login_content_dashb"></div>
</div>
</body>
</html>
