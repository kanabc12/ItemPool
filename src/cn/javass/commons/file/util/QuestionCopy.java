package cn.javass.commons.file.util;

import java.util.Date;

import org.hxy.model.QuestionXMLData;

import cn.javass.commons.date.util.DateUtil;

public class QuestionCopy {
	/**
	 * 当试题html文件不存在时，由试题的xml大字段数据生成试题的doc文件和html文件
	 * 新生成的试题html页面文件存放到Tomcat6\webapps目录下的文件夹etsdoc下面，按学科存放
	 * @param question
	 * @param disciplineCode 试题所属学科
	 * @return
	 * @since 2012-7-1
	*/
	public static boolean genQuestionDocAndHtml(QuestionXMLData question,String disciplineCode,Date regTime){
		boolean success = true;//生成html页面成功
		Integer questionId  = question.getQuestionId();
		//=============新建保存试题doc、html文件的文件夹begin===================
		String root = FileManager.getUrlRootPath();
		String rootPath = root.substring(0, root.length()-9);//去掉路径中etsClient
		String docFolderPath = rootPath+"/etsdoc/"+disciplineCode+"/doc/"+DateUtil.DateToString(regTime,"yyyyMMdd");
		String htmlFolderPath = rootPath+"/etsdoc/"+disciplineCode+"/quehtml/"+DateUtil.DateToString(regTime,"yyyyMMdd");
		//文件夹不存在，则新建文件夹
		FileOperateUtils.createDirectory(docFolderPath);
		FileOperateUtils.createDirectory(htmlFolderPath);
		//=============新建保存试题doc、html文件的文件夹end===================
		String bodyWordFile = docFolderPath+"/body"+question.getQuestionId()+".doc";//当前文档的保存路径
		String bodyHtmlFile= htmlFolderPath+"/body"+question.getQuestionId()+".html";//需转换的html文档的保存路径
		String answerWordFile = docFolderPath+"/answer"+question.getQuestionId()+".doc";
		String answerHtmlFile= htmlFolderPath+"/answer"+question.getQuestionId()+".html";
		String analysisWordFile = docFolderPath+"/analysis"+question.getQuestionId()+".doc";
		String analysisHtmlFile= htmlFolderPath+"/analysis"+question.getQuestionId()+".html";
		//如果存在试题的doc文档和html文件，则删除
		FileOperateUtils.delFile(bodyWordFile);
		FileOperateUtils.delFile(answerWordFile);
		FileOperateUtils.delFile(analysisWordFile);
		FileOperateUtils.delFile(bodyHtmlFile);
		FileOperateUtils.delFile(answerHtmlFile);
		FileOperateUtils.delFile(analysisHtmlFile);
 		String oldbodyHtmlFileOfFiles = bodyHtmlFile.substring(0, bodyHtmlFile.length()-4)+"files";
 		String oldanswerHtmlFileOfFiles = answerHtmlFile.substring(0, answerHtmlFile.length()-4)+"files";
 		String oldanalysisHtmlFileOfFiles = analysisHtmlFile.substring(0, analysisHtmlFile.length()-4)+"files";
 		FileOperateUtils.deleteDirectory(oldbodyHtmlFileOfFiles);
 		FileOperateUtils.deleteDirectory(oldanswerHtmlFileOfFiles);
 		FileOperateUtils.deleteDirectory(oldanalysisHtmlFileOfFiles);
 		//=======================由试题xml数据生成试题html页面begin=============================
 		String templateFile  =FileManager.getUrlRootPath()+"/templet/question/blank.xml"; // 模板文件
		String queTempsource = WordMLUtil.readTextFile(templateFile, "utf-8");
 		String preQuePart = queTempsource.substring(0, queTempsource.indexOf("<w:p/>"));//获取可插入试题内容标记前部字符串
		String aftQuePart = queTempsource.substring(queTempsource.indexOf("<w:p/>"));//获取可插入试题内容标记后部字符串
		//临时存放试题html页面的文件夹
 		String tmpHtmlFolderPath = FileManager.getUrlRootPath()+"/questionhtml/";
 		String tmpbodyHtmlFile= tmpHtmlFolderPath+"/body"+questionId+".html";
		String tmpanswerHtmlFile= tmpHtmlFolderPath+"/answer"+questionId+".html";
		String tmpanalysisHtmlFile= tmpHtmlFolderPath+"/analysis"+questionId+".html";
		//生成试题题文html文件
		StringBuffer queBodyBuf = new StringBuffer("");
		String bodyXmlStr = question.getZquestionBody();
		queBodyBuf.append(preQuePart);
		queBodyBuf.append(bodyXmlStr);
		queBodyBuf.append(aftQuePart);
		WordMLUtil.writeStringToFile(bodyWordFile,queBodyBuf.toString(),"utf-8");//由试题xml数据生成试题doc文件
		JacobHelper jacobHelper = new JacobHelper(false);
		try{
			jacobHelper.openDocument(bodyWordFile);//打开试题题文文件
			jacobHelper.doc2html(bodyHtmlFile); //将当前文档转成html文件  
		 	jacobHelper.closeDocument();//关闭当前文档
//			HtmlUtil.updateHtmlImagePath(tmpbodyHtmlFile,bodyHtmlFile, bodyHtmlreplaceStr, 
//						"../../etsdoc/"+htmlDirectory+"/quehtml/"+
//						DateTimeUitl.getStringPureDateArchive(question.getRegTime())+"/"+bodyHtmlreplaceStr);
		}catch(Exception e) {
 			jacobHelper.closeDocument();//关闭当前文档	
 			success = false;
 		}
		//生成试题答案html文件
		StringBuffer queAnswerBuf = new StringBuffer("");
		String answerXmlStr = question.getZanswer();
		queAnswerBuf.append(preQuePart);
		queAnswerBuf.append(answerXmlStr);
		queAnswerBuf.append(aftQuePart);
		WordMLUtil.writeStringToFile(answerWordFile,queAnswerBuf.toString(),"utf-8");//由试题xml数据生成试题doc文件
		try{
			jacobHelper.openDocument(answerWordFile);//打开试题题文文件
			jacobHelper.doc2html(answerHtmlFile);   		
	     	jacobHelper.closeDocument();
//			HtmlUtil.updateHtmlImagePath(tmpanswerHtmlFile,answerHtmlFile, answerHtmlreplaceStr, 
//						"../../etsdoc/"+htmlDirectory+"/quehtml/"+
//						DateTimeUitl.getStringPureDateArchive(question.getRegTime())+"/"+answerHtmlreplaceStr);
		}catch(Exception e) {
 			jacobHelper.closeDocument();//关闭当前文档	
 			success = false;
 		}
		//生成试题解析html文件
		if(null != question.getZanalysis()){
			StringBuffer queAnalysisBuf = new StringBuffer("");
			String analysisXmlStr = question.getZanalysis();
			queAnalysisBuf.append(preQuePart);
			queAnalysisBuf.append(analysisXmlStr);
			queAnalysisBuf.append(aftQuePart);
			WordMLUtil.writeStringToFile(analysisWordFile,queAnalysisBuf.toString(),"utf-8");//由试题xml数据生成试题doc文件
			try{
				jacobHelper.openDocument(analysisWordFile);//打开试题题文文件
				jacobHelper.doc2html(analysisHtmlFile);
				jacobHelper.closeDocument();
//				HtmlUtil.updateHtmlImagePath(tmpanalysisHtmlFile,analysisHtmlFile, analysisHtmlreplaceStr, 
//						"../../etsdoc/"+htmlDirectory+"/quehtml/"+
//						DateTimeUitl.getStringPureDateArchive(question.getRegTime())+"/"+analysisHtmlreplaceStr);
			}catch(Exception e) {
	 			jacobHelper.closeDocument();//关闭当前文档	
	 			success = false;
	 		}
		}
		jacobHelper.close();
		//**********************************由试题xml数据生成试题html页面end*********************************
		
		//如果存在试题的doc文件，则删除
		FileOperateUtils.delFile(bodyWordFile);
		FileOperateUtils.delFile(answerWordFile);
		FileOperateUtils.delFile(analysisWordFile);
 		
 		//删除临时html文件及.files文件夹
		FileOperateUtils.delFile(tmpbodyHtmlFile);
		FileOperateUtils.delFile(tmpanswerHtmlFile);
		FileOperateUtils.delFile(tmpanalysisHtmlFile);
 		String tmpbodyHtmlFileOfFiles = tmpbodyHtmlFile.substring(0, tmpbodyHtmlFile.length()-4)+"files";
 		String tmpanswerHtmlFileOfFiles = tmpanswerHtmlFile.substring(0, tmpanswerHtmlFile.length()-4)+"files";
 		String tmpanalysisHtmlFileOfFiles = tmpanalysisHtmlFile.substring(0, tmpanalysisHtmlFile.length()-4)+"files";
 		FileOperateUtils.deleteDirectory(tmpbodyHtmlFileOfFiles);
 		FileOperateUtils.deleteDirectory(tmpanswerHtmlFileOfFiles);
 		FileOperateUtils.deleteDirectory(tmpanalysisHtmlFileOfFiles);

		return success;
	}
	
	public static boolean genQuestionDocAndHtml(QuestionXMLData question,String disciplineCode,String regTime){
		boolean success = true;//生成html页面成功
		String templateFile  =FileManager.getUrlRootPath()+"/templet/question/blank.xml"; // 模板文件
		String root = FileManager.getUrlRootPath();
		String rootPath = root.substring(0, root.length()-9);//去掉路径中etsClient
		String docFolderPath = rootPath+"/etsdoc/"+disciplineCode+"/doc/"+regTime;
		String htmlFolderPath = rootPath+"/etsdoc/"+disciplineCode+"/quehtml/"+regTime;
		FileOperateUtils.createDirectory(htmlFolderPath);
		FileOperateUtils.createDirectory(docFolderPath);
		String bodyHtmlFile= htmlFolderPath+"/body"+question.getQuestionId()+".html";//需转换的html文档的保存路径
		String answerHtmlFile= htmlFolderPath+"/answer"+question.getQuestionId()+".html";
		String analysisHtmlFile= htmlFolderPath+"/analysis"+question.getQuestionId()+".html";
		String bodyWordFile = docFolderPath+"/body"+question.getQuestionId()+".doc";//当前文档的保存路径
		String answerWordFile = docFolderPath+"/answer"+question.getQuestionId()+".doc";
		String analysisWordFile = docFolderPath+"/analysis"+question.getQuestionId()+".doc";
		String queTempsource = WordMLUtil.readTextFile(templateFile, "utf-8");
 		String preQuePart = queTempsource.substring(0, queTempsource.indexOf("<w:p/>"));//获取可插入试题内容标记前部字符串
		String aftQuePart = queTempsource.substring(queTempsource.indexOf("<w:p/>"));//获取可插入试题内容标记后部字符串
		//生成试题题文html文件
		StringBuffer queBodyBuf = new StringBuffer("");
		String bodyXmlStr = question.getZquestionBody();
		queBodyBuf.append(preQuePart);
		queBodyBuf.append(bodyXmlStr);
		queBodyBuf.append(aftQuePart);
		WordMLUtil.writeStringToFile(bodyWordFile,queBodyBuf.toString(),"utf-8");//由试题xml数据生成试题doc文件
		JacobHelper jacobHelper = new JacobHelper(false);
		try{
			jacobHelper.openDocument(bodyWordFile);//打开试题题文文件
			jacobHelper.doc2html(bodyHtmlFile); //将当前文档转成html文件  
		 	jacobHelper.closeDocument();//关闭当前文档
		}catch(Exception e) {
 			jacobHelper.closeDocument();//关闭当前文档	
 			success = false;
 		}
		//生成试题答案html文件
		StringBuffer queAnswerBuf = new StringBuffer("");
		String answerXmlStr = question.getZanswer();
		queAnswerBuf.append(preQuePart);
		queAnswerBuf.append(answerXmlStr);
		queAnswerBuf.append(aftQuePart);
		WordMLUtil.writeStringToFile(answerWordFile,queAnswerBuf.toString(),"utf-8");//由试题xml数据生成试题doc文件
		try{
			jacobHelper.openDocument(answerWordFile);//打开试题题文文件
			jacobHelper.doc2html(answerHtmlFile);   		
	     	jacobHelper.closeDocument();
		}catch(Exception e) {
 			jacobHelper.closeDocument();//关闭当前文档	
 			success = false;
 		}
		//生成试题解析html文件
		if(null != question.getZanalysis()){
			StringBuffer queAnalysisBuf = new StringBuffer("");
			String analysisXmlStr = question.getZanalysis();
			queAnalysisBuf.append(preQuePart);
			queAnalysisBuf.append(analysisXmlStr);
			queAnalysisBuf.append(aftQuePart);
			WordMLUtil.writeStringToFile(analysisWordFile,queAnalysisBuf.toString(),"utf-8");//由试题xml数据生成试题doc文件
			try{
				jacobHelper.openDocument(analysisWordFile);//打开试题题文文件
				jacobHelper.doc2html(analysisHtmlFile);
				jacobHelper.closeDocument();
			}catch(Exception e) {
	 			jacobHelper.closeDocument();//关闭当前文档	
	 			success = false;
	 		}
		}
		jacobHelper.close();
		//如果存在试题的doc文件，则删除
		FileOperateUtils.delFile(bodyWordFile);
		FileOperateUtils.delFile(answerWordFile);
		FileOperateUtils.delFile(analysisWordFile);
		return success;
	}
}
