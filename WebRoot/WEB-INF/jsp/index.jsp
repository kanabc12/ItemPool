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
	var xk = [{text:'语文',value:11},
              {text:'数学',value:10},
              {text:'英语',value:9},
              {text:'物理',value:8},
              {text:'化学',value:7},
              {text:'生物',value:6},
              {text:'政治',value:5},
              {text:'历史',value:4},
              {text:'地理',value:3},
              {text:'文综',value:2},
              {text:'理综',value:1}];
	var dq = [
				{text:'全国',value:35},	
	             {text:'北京',value:1},
	              {text:'天津',value:2},
	              {text:'河北',value:3},
	              {text:'山西',value:4},
	              {text:'内蒙古',value:5},
	              {text:'辽宁',value:6},
	              {text:'吉林',value:7},
	              {text:'黑龙江',value:8},
	              {text:'上海',value:9},
	              {text:'江苏',value:10},
	              {text:'浙江',value:11},
	              {text:'安徽',value:12},
	              {text:'福建',value:13},
	              {text:'江西',value:14},
	              {text:'山东',value:15},
	              {text:'河南',value:16},
	              {text:'湖北',value:17},
	              {text:'湖南',value:18},
	              {text:'广东',value:19},
	              {text:'广西',value:20},
	              {text:'海南',value:21},
	              {text:'重庆',value:22},
	              {text:'四川',value:23},
	              {text:'贵州',value:24},
	              {text:'云南',value:25},
	              {text:'西藏',value:26},
	              {text:'陕西',value:27},  
	              {text:'甘肃',value:28}, 
	              {text:'青海',value:29}, 
	              {text:'宁夏',value:30}, 
	              {text:'新疆',value:31}, 
	              {text:'香港',value:32}, 
	              {text:'澳门',value:33}, 
	              {text:'台湾',value:34}];
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
        $('#make-articleTab').omTabs({
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
	    $('#combo3').omCombo({
	    	value:'',
	    	emptyText:'请选择-----',
            dataSource : xk
        });
	    $('#combo4').omCombo({
	    	value:'',
	    	emptyText:'请选择-----',
            dataSource : dq
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
		var xkValue = $("#combo3").val()==""?0:$("#combo3").val();
		var dqValue = $("#combo4").val()==""?0:$("#combo4").val();
		/*if(answerText==""){
			alert("请输入标题!");
			$("#answer1").focus();
			return false;
		}*/
	    $('#articleResult').omGrid({
	    	limit:10,
	    	width:'fit',
	    	height:300,
	    	dataSource : '${ctx}/article/getArticlesByTitle',
	    	extraData:{
	    		'title':answerText,
	    		'xk':xkValue,
	    		'dq':dqValue
	    	},
            colModel : [ {header : '主题', name : 'title', width : 780, align : 'left',renderer:function(colValue, rowData, rowIndex){
            	if(rowData.postTime!=""){
            		var n1 = new Date().getTime();
            		var n2 = new Date(rowData.postTime).getTime();
            		if(((n1-n2)/(24*60*60*1000))<=10){
                		return '<a href="fileOperate/download?articleID='+rowData.id+'" target="_blank">'+colValue + '</a><img src="${ctx}/images/new.gif" />';
            		}else{
            			return  '<a href="fileOperate/download?articleID='+rowData.id+'" target="_blank">'+colValue+ '</a>';
            		}
            	}else{
            		return '<a href="fileOperate/download?articleID='+rowData.id+'" target="_blank">'+colValue+ '</a>';
            	}
            }}, 
                         		{header : '日期', name : 'postTime', align : 'center', width : 100}
                         ]     
                          
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
		//$("#know").val("");
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
		var url = "questionData/showData?questionId="+questionId+"&discipline="+disciplineId+"&regTime="+regTime+"&date="+new Date();
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
				<li><a href="#">网站导航</a>
					</li>
					<li><a href="#">联系我们</a>
					</li>
					<li><a href="#">手机版</a></li>
					<li><a href="#">IPAD版</a></li>
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
		<table id="knowGrid"></table>
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
					<h3>根据题文找答案</h3>
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
			
			
			<div class="nav"></div>
			<div class="guang">
				<div class="tit">
					<h3>根据标题找试卷</h3>
				</div>
				<div id="paperDiv">
					<div id="leftMargin2"></div>
					<div id="answerSerach2">
						<table>
							<tr>
								<td>地区：</td>
								<td><input id="combo4" name="dq" /></td>
								<td>学科：</td>
								<td><input id="combo3" name="xk" /></td>
								<td>标题：</td>
								<td><input type="text" id="answer1" name="answer"
									value="" onFocus="test1()" /></td>
								<td><input id="btn" type="button" value="搜索"
									onclick="reserch1()" /></td>
							</tr>
						</table>
					</div>
				</div>
				
			</div>
			<table id="articleResult"></table>
			<div class="nav"></div>
			<div id="tabs1">
				<div class="tit">
					<h3>各学科最新试卷</h3>
				</div>
				<div id="make-articleTab">
					<ul>
						<c:forEach items="${disciplines}" var="discipline">
							<li><a href="article/get20thArticles/${discipline.id}">${discipline.name}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				</div>
				 <a
				href="http://221.122.71.45:8099/mxsj/mxsj.aspx" target="_blank"> <img
				src="${ctx}/images/tmxsj.jpg" border="0">
			</a>
		
		</div>	
		<div id="logo1">
		<div class="nav"></div>
		<div id="logo1">
			<img src="${ctx}/images/czbf.jpg" border="0">
		</div>	
 <p align="center">技术支持：思派讯网络科技有限公司 &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;思派讯版权所有 &copy;2013-2018 All Right Reserved.
  &nbsp;
          </p>
          <p align="center">经营许可证编号：赣B2-20130026号    
          &nbsp;&nbsp;&nbsp;&nbsp; 赣ICP备13003175号
          </p>
		</div>
	</div>

</body>
</html>
