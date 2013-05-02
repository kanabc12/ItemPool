<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<%@ page contentType="text/html;charset=utf-8"%> 
<!DOCTYPE HTML>
<html>
<head>
<title>试题详情</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/layout.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/themes/default/om-all.css" />
<script type="text/javascript" src="${ctx}/javascript/jquery.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-core.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-panel.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/om-accordion.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(function() {
			$("#accordionId").omAccordion({
				width : 'fit',
				height : 400
			});
		});
	});
</script>
</head>
<body>
	<div id="accordionId">
		<ul>
			<li><a href="#tab1">题文</a>
			</li>
			<li><a href="#tab2">答案</a>
			</li>
			<li><a href="#tab3">解析</a>
			</li>
		</ul>
		<div id="tab1">
			<iframe border=0 frameBorder="no" src="../../${docSource}/${code}/quehtml/${regTime}/body${questionId}.html" width="100%" height="100%"></iframe>
		</div>
		<div id="tab2">
			<iframe border=0 frameBorder="no" src="../../${docSource}/${code}/quehtml/${regTime}/answer${questionId}.html" width="100%" height="100%"></iframe>
		</div>
		<div id="tab3">
			<iframe border=0 frameBorder="no" src="../../${docSource}/${code}/quehtml/${regTime}/analysis${questionId}.html" width="100%" height="100%"></iframe>
		</div>
	</div>

</body>
</html>
