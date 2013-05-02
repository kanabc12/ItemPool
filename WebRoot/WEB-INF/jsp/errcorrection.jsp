<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>纠错</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/layout.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/themes/apusic/om-all.css" />
<script type="text/javascript" src="${ctx}/javascript/jquery.js"></script>
<script>
$(document).ready(function(){
	$("input[name=radiobutton]").change(function(){
		//alert($(this).val());弹出选中项的值
		var radioValue = $(this).val();
		if(radioValue =="2" ||radioValue=="1"){
			$("#description").text("错误描述:");
		}else{
			$("#description").text("试题编号:");
		}
	});
})
</script>
</head>
<body>
	<form>
		<table>
			<tr>
				<td><label>错误类型:</label></td>
				<td><input type="radio" name="radiobutton" value="1" checked> 答案错误
					<input type="radio" name="radiobutton" value="2"> 解析错误
					<input type="radio" name="radiobutton" value="3"> 重题
				</td>
			</tr>
			<tr><td><label id="description">错误描述:</label></td>
				<td><textarea rows="3" cols="28"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button type="reset">取消</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button">确认</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>