<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>试题详情</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/layout.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/themes/apusic/om-all.css" />
<script type="text/javascript" src="${ctx}/javascript/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-core.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-panel.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/om-accordion.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(function() {
			$("#accordionId").omAccordion({
				width : 'fit',
				height : 550
			});
		});
	});
</script>
</head>
<body>
	<div id="accordionId">
		<ul>
			<li><a href="../../etsdoc/2/quehtml/20111023/body1388628.html">题文</a>
			</li>
			<li><a href="../../etsdoc/2/quehtml/20111023/answer1388628.html">答案</a></li>
			<li><a href="../../etsdoc/2/quehtml/20111023/analysis1388628.html">解析</a>
			</li>
		</ul>
	</div>
</body>
</html>
