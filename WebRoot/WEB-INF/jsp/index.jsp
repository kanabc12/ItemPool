<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<title>淘题客</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/layout.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/themes/default/om-all.css" />
<script type="text/javascript" src="${ctx}/javascript/jquery.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-core.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-panel.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-tabs.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-slider.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-combo.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/om-messagebox.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/om-draggable.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-mouse.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/om-position.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-button.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/om-resizable.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-grid.min.js"></script>
<script type="text/javascript" src="${ctx}/javascript/om-dialog.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/om-accordion.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/om-grid-rowexpander.min.js"></script>
<script type="text/javascript">
	var comboxData = [];
	var discipline = eval(${disciplineStr});
	function formComboxData(discipline){
		for(var i=0;i<discipline.length;i++){
			var obj = {};
			obj.text = discipline[i].name;
			obj.value = discipline[i].id;
			comboxData[i] = obj;
		}
	}
	
	$(document).ready(function() {
		formComboxData(discipline);
	    $('#slider').omSlider({
	        animSpeed : 100,
	        effect : 'slide-h',
	        onBeforeSlide : function(index,event){
	        }
	    });
	    $('#make-tab').omTabs({
            width: 'fit',
            height: '330px',
            closable : false,
            lazyLoad : true,
            scrollable: true
        });
	    $('#combo1').omCombo({
	    	value:'',
	    	emptyText:'请选择-----',
            dataSource : comboxData
        });
	    $('#combo2').omCombo({
	    	value:'',
	    	emptyText:'请选择-----',
            dataSource : comboxData
        });
	});
	function test(){
		var text = $('#answer').val("");
	}
	function test1(){
		var text = $('#answer1').val("");
	}
	function test2(){
		var text = $('#know').val("");
	}
	function recoverValue(){
		var text = $('#s').val();
		if(text !="" || text != "Search"){
			$('#s').val("Search");
		}
	}
	function reserch(){
		var answerText = jQuery.trim($("#answer").val());
		var discipline = $("#combo2").val();
		if(answerText==""){
			alert("请输入您要查找的题文关键字!");
			$("#answer").focus();
			return false;
		}
	    $('#reserchResult').omGrid({
	    	limit:10,
	    	width:'fit',
	    	height:300,
	    	dataSource : '${ctx}/question/getQuestionByAnswer',
	    	extraData:{
	    		'discipline':discipline,
	    		'answerText':answerText
	    	},
            colModel : [ {header : '知识点', name : 'knowledgeName', width : 200, align : 'left'}, 
                         {header : '题文', name : 'bodyText', width : 200, align : 'left'}, 
                         {header : '主题', name : 'topicName', width : 200, align : 'left'}, 
                         {header : '试题来源', name : 'queSoruce', align : 'left', width : 180},
                         {header : '查看', name : 'operation', width: "autoExpand", align:'center', renderer:function(colValue, rowData, rowIndex){
                        	 var data = rowData;
                        	 return '<a href="javascript:void(0);" style="text-decoration: none;"  onClick="showRowdata('+rowIndex+',event)">详情</button>&nbsp;<a href="javascript:void(0);" style="text-decoration: none;" onClick="errorCorrect('+rowIndex+',event,2)">纠错</a>';
                         }}]
	        });
	}
	
	function reserch1(){
		var answerText = jQuery.trim($("#answer1").val());
		if(answerText==""){
			alert("请输入标题!");
			$("#answer1").focus();
			return false;
		}
	    $('#articleResult').omGrid({
	    	limit:10,
	    	width:'fit',
	    	height:300,
	    	dataSource : '${ctx}/article/getArticlesByTitle',
	    	extraData:{
	    		'title':answerText
	    	},
            colModel : [ {header : '主题', name : 'title', width : 300, align : 'left'}, 
                         {header : '日期', name : 'postTime', align : 'left', width : 180}]
	        });
	}
	
	 function showRowdata(index, e){
     	var data = $("#reserchResult").omGrid("getData").rows[index];
     	var url = "questionData/addSearchNum?questionId="+data.id+"&discipline="+data.disciplineId+"&regTime="+data.regTime+"&date="+new Date();
     	showModalDialog(url,window,
        'dialogwidth:600px;dialogheight:400px;help:0;center:yes;resizable:0;status:0;scroll:yes');
	   	if(e&&e.stopPropagation){
            //因此它支持W3C的stopPropagation()方法
            e.stopPropagation();
	   	}else{
            //否则，我们需要使用IE的方式来取消事件冒泡
            window.event.cancelBubble = true;
	   	}
     }
	function validate(){
		var know = $("#know").val();
		var discipline = $("#combo1").val();
		if(discipline==""){
			alert("请选择学科!");
			return false;
		}
		if(know==""){
			alert("请输入您要查找的知识点!");
			$("know").focus();
			return false;
		}
	    $('#knowGrid').omGrid({
	    	limit:10,
	    	width:'fit',
	    	height:300,
	    	dataSource : '${ctx}/question/getQuestion',
	    	extraData:{
	    		'discipline':discipline,
	    		'know':jQuery.trim(know)
	    	},
            colModel : [ {header : '知识点', name : 'knowledgeName', width : 200, align : 'left'}, 
                         {header : '题文', name : 'bodyText', width : 200, align : 'left'}, 
                         {header : '主题', name : 'topicName', width : 200, align : 'left'}, 
                         {header : '试题来源', name : 'queSoruce', align : 'left', width : '185'},
                         {header : '操作', name : 'operation', width: "autoExpand", align:'center', renderer:function(colValue, rowData, rowIndex){
                        	 var data = rowData;
                        	 return '<a href="javascript:void(0);" style="text-decoration: none;" onClick="showRowdata1('+rowIndex+',event)">详情</a> &nbsp;<a href="javascript:void(0);" style="text-decoration: none;" onClick="errorCorrect('+rowIndex+',event,1)">纠错</a>';
                         }} ]
	    });
		$("#know").val("");
	}
	
	 function showRowdata1(index, e){
	     	var data = $("#knowGrid").omGrid("getData").rows[index];
	     	var url = "questionData/showData?questionId="+data.id+"&discipline="+data.disciplineId+"&regTime="+data.regTime+"&date="+new Date();
	     	showModalDialog(url,window,
	        'dialogwidth:600px;dialogheight:400px;help:0;center:yes;resizable:0;status:0;scroll:yes');
		   	if(e&&e.stopPropagation){
	            //因此它支持W3C的stopPropagation()方法
	            e.stopPropagation();
		   	}else{
	            //否则，我们需要使用IE的方式来取消事件冒泡
	            window.event.cancelBubble = true;
		   	}
	     }
	 function showPage(questionId,disciplineId,regTime){
		var url = "questionData/showData?questionId="+questionId+"&discipline="+disciplineId+"&regTime="+regTime+"&date="+new Date();;
		//var url = "questionData/showData?questionId=2790958&discipline=21&regTime=2012-8-9&date="+new Date();
		showModalDialog(url,window,'dialogwidth:600px;dialogheight:400px;help:0;center:yes;resizable:0;status:0;scroll:yes');
	}
	
	function errorCorrect(index,e,grid){
		var data ;
		if(grid==1){
			data = $("#knowGrid").omGrid("getData").rows[index];
		}else{
			data = $("#reserchResult").omGrid("getData").rows[index];
		}
		var url = "question/errorCorrect?questionId="+data.id+"&discipline="+data.disciplineId+"&regTime="+data.regTime+"&date="+new Date();
		showModalDialog(url,window,'dialogwidth:500px;dialogheight:250px;help:0;center:yes;resizable:0;status:0;scroll:yes');
	}

	function login(){
		var url = "question/login";
		showModalDialog(url,window,'dialogwidth:800px;dialogheight:400px;help:0;center:yes;resizable:0;status:0');
	}
	function register(){		
		var url = "question/register";
		showModalDialog(url,window,'dialogwidth:800px;dialogheight:500px;help:0;center:yes;resizable:0;status:0');
	}
	
	</script>
</head>
<body>
	<div class="top">
		<div class="top_1">
		<div class="top_left">
			<ul class="ul_1">
				<li class="sp"><a href="javascript:void(0);"
					onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://localhost:8080/ItemPool/index.html');">设为主页</a>
				</li>
				<li><a
					href="#>网站导航</a>
					</li>
					<li><a href="#">联系我们</a>
					</li>
					<li><a href="#">手机版</a></li>
					<li><a href="#">IPD版</a></li>
				</ul>
			</div>
			<div class="top_right">
				<ul class="ul_2">
					<li class="txt">用户名：<input type="text" size="10" id="username" /></li>
					<li class="txt">密码：<input type="password" size="10" id="pwd" /></li>
					<li class="btn"><a href="#">登陆</a></li>
					<li class="btn"><a href="#">注册</a></li>
				</ul>
			</div>
			</div>
	</div>
	<div id="container">
		<div id="header">
			<div id="logo"></div>
			<div class="nav"></div>
			<div class="guang">
				<div class="tit">
					<h3 id="h3">总试题数：${totalQuestion},今日更新数：${totayQuestion}</h3>
				</div>
				<div id="answerDiv1">
					<div id="leftMargin1"></div>
					<div id="answerSerach1">
						<table>
							<tr>
								<td>学科：</td>
								<td><input id="combo1" name="combo1" /></td>
								<td>知识点：</td>
								<td><input type="text" id="know" value="输入知识点关键字"
									name="know" onFocus="test2()" />
								<td><input id="btn" type="button" value="搜索"
									onclick="validate()" /></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="knowGrid"></div>
		<div class="nav"></div>
		<div id="sliderDemo">
			<div id="slider" class="slider-demo">
				<img src="${ctx}/images/gg1.jpg" /> <img
					src="${ctx}/images/gg2.jpg" /> <img src="${ctx}/images/gg3.jpg" />
				<img src="${ctx}/images/gg4.jpg" /> <img
					src="${ctx}/images/gg5.jpg" />
			</div>
			<div id="recentResult">
				<div class="tit">
					<h3>热门搜索</h3>
				</div>
				<div class="om-grid om-widget om-widget-content serchResult">
					<div class="hDiv om-state-default">
						<div class="hDivBox">
							<table cellpadding="0" cellspacing="0">
								<thead>
									<tr>
										<th axis="indexCol" align="center" class="indexCol"><div
												class="indexheader" style="text-align:center;width:10px;"></div>
										</th>
										<th axis="col0" class="col0" align="left"><div
												style="text-align: left; width: 170px; "
												class="om-resizable">
												知识点
												<div class="om-resizable-handle om-resizable-e" style=""></div>
											</div></th>
										<th axis="col1" class="col1" align="left"><div
												style="text-align: left; width: 170px; "
												class="om-resizable">
												试题来源
												<div class="om-resizable-handle om-resizable-e" style=""></div>
											</div></th>
										<th axis="col2" class="col2" align="left"><div
												style="text-align: center; width: 45px; "
												class="om-resizable" align="center">
												搜索次数
												<div class="om-resizable-handle om-resizable-e" style=""></div>
											</div></th>
										<th axis="col3" class="col3" align="center"
											style="display: table-cell; "><div
												style="text-align: center; width: 30px; "
												class="om-resizable">
												详情
												<div class="om-resizable-handle om-resizable-e" style=""></div>
											</div></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<div class="bDiv" style="width: auto; height: 247px; ">
						<table cellpadding="0" cellspacing="0" border="0">
							<tbody>
								<c:forEach items="${hotSearch}" var="question"
									varStatus="status">
									<tr>
										<td>
											<div align="center" class="innerCol" style="width:10px">${status.count}</div>
										</td>
										<td>
											<div align="left" class="innerCol " style="width:170px">
												<c:choose>
													<c:when test="${fn:length(question.knowledgeName)>15 }">
														<c:out
															value="${fn:substring(question.knowledgeName,0,15)}..." />
													</c:when>
													<c:otherwise>
														<c:out value="${question.knowledgeName}" />
													</c:otherwise>
												</c:choose>
											</div>
										</td>
										<td><div align="left" class="innerCol "
												style="width:170px">
												<c:choose>
													<c:when test="${fn:length(question.queSoruce)>15 }">
														<c:out value="${fn:substring(question.queSoruce,0,15)}..." />
													</c:when>
													<c:otherwise>
														<c:out value="${question.queSoruce}" />
													</c:otherwise>
												</c:choose>
											</div></td>
										<td><div class="innerCol" align="center"
												style="width:45px">${question.searchNum}</div></td>
										<td><div class="innerCol" align="center"
												style="width:30px">
												<a style="text-decoration: none;" href="javascript:void(0)"
													onclick="showPage('${question.id}','${question.disciplineId}','${question.regTime}')">查看
												</a>
											</div></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="nav"></div>
		<div id="main">
			<div class="guang">
				<div class="tit">
					<h3>根据试题找答案</h3>
				</div>
				<div id="answerDiv">
					<div id="leftMargin"></div>
					<div id="answerSerach">
						<table>
							<tr>
								<td>学科：</td>
								<td><input id="combo2" name="combo2" /></td>
								<td>题文：</td>
								<td><input type="text" id="answer" name="answer"
									value="输入找题关键字" onFocus="test()" /></td>
								<td><input id="btn" type="button" value="搜索"
									onclick="reserch()" /></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<table id="reserchResult"></table>
			<div class="nav"></div>
			<div id="logo1">
				<a href="http://sj.jtywx.com/sj/cxh.aspx" target="_blank"><img
					src="${ctx}/images/tmxsj.jpg" border="0" alt="中国教育部"> </a>
			</div>
			<div class="nav"></div>
						<div class="guang">
				<div class="tit">
					<h3>根据标题找文章</h3>
				</div>
				<div id="answerDiv">
					<div id="leftMargin"></div>
					<div id="answerSerach">
						<table>
							<tr>
								<td>标题：</td>
								<td><input type="text" id="answer1" name="answer"
									value="输入标题" onFocus="test1()" /></td>
								<td><input id="btn" type="button" value="搜索"
									onclick="reserch1()" /></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<table id="articleResult"></table>
			<div class="nav"></div>
			<div id="tabs">
				<div class="tit">
					<h3>各学科最新试题</h3>
				</div>
				<div id="make-tab">
					<ul>
						<c:forEach items="${disciplines}" var="discipline">
							<li><a href="question/get20thQuestions/${discipline.id}">${discipline.name}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="nav"></div>
		<div id="logo2">
			<img src="${ctx}/images/zsj.jpg" border="0" alt="中国教育部"> <a
				href="http://sj.jtywx.com/sj/cxh.aspx" target="_blank"> <img
				src="${ctx}/images/tmxsj.jpg" border="0">
			</a>
		</div>
		<div class="nav"></div>
		<div id="logo1">
			<img src="${ctx}/images/czbf.jpg" border="0">
		</div>
	</div>
</body>
</html>
