<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>试题详情</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/layout.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/themes/apusic/om-all.css" />
<script type="text/javascript" src="${ctx}/javascript/jquery.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-core.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-panel.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/om-accordion.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(function() {
			$("#accordionId").omAccordion({
				width : 500,
				height : 250
			});
		});
	});
</script>
</head>
<body>
	<div id="accordionId">
		<ul>
			<li><a href="#accordion-2">题文</a>
			</li>
			<li><a href="#accordion-3">答案</a>
			</li>
		</ul>
		<div id="accordion-2">
			<p>${question.bodyText }</p>
		</div>
		<div id="accordion-3">
			<p>${question.answerText}</p>
		</div>
	</div>

</body>
</html>
