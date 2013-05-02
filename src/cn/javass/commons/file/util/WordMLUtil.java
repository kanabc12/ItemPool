package cn.javass.commons.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class WordMLUtil {
	/**
     * 读取一个文件到字符串里.
     * @param sFileName  文件名
     * @param sEncode   String
     * @return 文件内容
    */
    public static String readTextFile(String sFileName, String sEncode)
    {
        StringBuffer sbStr = new StringBuffer();
        try
        {
            File ff = new File(sFileName);
            InputStreamReader read = new InputStreamReader(new FileInputStream(ff),sEncode);
            BufferedReader ins = new BufferedReader(read);
            String dataLine = "";
            while (null != (dataLine = ins.readLine()))
            {
                sbStr.append(dataLine);
            }
            ins.close();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return sbStr.toString();
    }
    /**
     * 替换字符串 
     * @param from String 原始字符串  
     * @param to String 目标字符串  
     * @param source String 母字符串  
     * @return String 替换后的字符串  
    */  
    public static String replace(String from, String to, String source) {   
        if (source == null || from == null || to == null)   
        	return null;   
        StringBuffer bf = new StringBuffer("");   
        int index = -1;   
        while ((index = source.indexOf(from)) != -1) {   
        	bf.append(source.substring(0, index) + to);   
        	source = source.substring(index + from.length());   
        	index = source.indexOf(from);   
        }   
        bf.append(source);   
        return bf.toString();   
    } 

    /**  
     *  将字符串写入文件  
     *  @param  filePathAndName  String  文件路径及名称  如c:/fqf.txt  
     *  @param  fileContent  String  文件内容  
     *  @return  boolean  
    */  
    public static boolean writeStringToFile(String filePathAndName,String fileContent,String enc) {
        File file = new File(filePathAndName);
       
        try{
            if(file.isFile()){
                file.deleteOnExit();
                file = new File(file.getAbsolutePath());
            }
            OutputStreamWriter os = null;
            if(enc==null||enc.length()==0){
                os = new OutputStreamWriter(new FileOutputStream(file));
            }else{
                os = new OutputStreamWriter(new FileOutputStream(file),enc);
            }
            os.write(fileContent);
            os.close();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * 处理试题xml字符串，删除掉影响组卷的标签
     * @param queXMLData
     * @return String
     * @since 2012-6-29
     */
    public static String delTagsOfQueXML(String contentStr){
    	//需要删除的标签数组(有这些标签，打开doc文件会报错)
//    	String[] tagsToDel= {"<aml:annotation","<st1:chmetcnv","</st1:chmetcnv>",
//    			"<st1:chsdate","</st1:chsdate>","<st1:State","</st1:State>",
//    			"<st1:place","</st1:place>","<st1:City","</st1:City>"};
    	String[] tagsToDel= {"<aml:annotation","</aml:annotation","<st1:","</st1:",
    			"<wx:sub-section","</wx:sub-section","<st2:","</st2:"};
    	StringBuffer contentStrBf = new StringBuffer(contentStr);
    	if(null != contentStrBf && contentStrBf.length()>0){
    		for(String tagdel : tagsToDel){
        		while(contentStrBf.indexOf(tagdel)>=0){
    	        	StringBuffer temp = new StringBuffer();
        			String temp1 = contentStrBf.substring(0,contentStrBf.indexOf(tagdel));//标签前面部分字符串
    	        	String temp2 = contentStrBf.substring(contentStrBf.indexOf(tagdel));//标签之后字符串
    	        	temp.append(temp1);
    	        	temp.append(temp2.substring(temp2.indexOf(">")+1));//标签之后字符串，删掉标签
    	        	contentStrBf = new StringBuffer(temp);
            	}
        	} 
    	}
        return contentStrBf.toString();
    }
    
    /**
     * (ImportQuestionAction、ImportQuestionAnalysisAction、ImportQuestionPartAction调用)
     * 预处理试题xml文件，删除掉影响组卷的标签，重新为xml文件中pict进行编号，使编号在系统中唯一
     * @param queXMLData
     * @return String
     * @since 2012-6-23
     */
    public static String processQueXMLData(String queXMLStr,Long questionId){
    	String retStr = "";
    	String contentStr = queXMLStr.substring(queXMLStr.indexOf("<wx:sect>")+9,queXMLStr.indexOf("<w:sectPr"));
    	
    	//删除掉影响组卷的标签
    	retStr = delTagsOfQueXML(contentStr);
        
        //文档中图片的编号需要重新编写。编号整个文档要唯一。
    	String from = ".wmz";
    	String to = questionId+".wmz";
    	retStr = replace(from, to, retStr);
    	
    	from = ".png";
    	to = questionId+".png";
    	retStr = replace(from, to, retStr);
    	
    	from = ".gif";
    	to = questionId+".gif";
    	retStr = replace(from, to, retStr);
    	
    	//后期要加上此语句。
//    	from = ".jpg";
//    	to = questionId+".jpg";
//    	retStr = replace(from, to, retStr);
    	
        return retStr;
    }
    
    /** (ImportQuestionByMarkAction调用)
     * 预处理试题xml文件，删除掉影响组卷的标签，重新为xml文件中pict进行编号，使编号在系统中唯一
     * @param queXMLData  试题题文、答案、解析部分xml字符串
     * @param docXMLBefQue  上传doc文件此试题之前的xml字符串。为获取数学公式字符图片。
     * @return String
     * @since 2012-11-19
     */
    public static String processQueXMLDataBody(String queXMLStr,String docXMLBefQue,Long questionId,String disciplineCode){
    	if("02".equals(disciplineCode)||"04".equals(disciplineCode)||"05".equals(disciplineCode)||
    			"09".equals(disciplineCode)){//数学、物理、化学、地理
    		return processQueXMLDataBody(queXMLStr,docXMLBefQue,questionId);
    	}else{
    		return processQueXMLDataBody(queXMLStr,questionId);
    	}
    }
    /** (ImportQuestionByMarkAction调用，处理语文、英语、生物、政治、历史学科)
     * 预处理试题xml文件，删除掉影响组卷的标签，重新为xml文件中pict进行编号，使编号在系统中唯一
     * @param queXMLData  试题题文、答案、解析部分xml字符串
     * @return String
     * @since 2012-10-23
     */
    public static String processQueXMLDataBody(String queXMLStr,Long questionId){
    	String retStr = "";
      	//删除掉影响组卷的标签
    	retStr = delTagsOfQueXML(queXMLStr);
    	
        //文档中图片的编号需要重新编写。编号整个文档要唯一。
    	String from = ".wmz";
    	String to = questionId+".wmz";
    	retStr = replace(from, to, retStr);
    	
    	from = ".png";
    	to = questionId+".png";
    	retStr = replace(from, to, retStr);
    	
    	from = ".gif";
    	to = questionId+".gif";
    	retStr = replace(from, to, retStr);
    	
    	//后期要加上此语句。
//    	from = ".jpg";
//    	to = questionId+".jpg";
//    	retStr = replace(from, to, retStr);
    	
        return retStr;
    }
    
    /** (ImportQuestionByMarkAction调用，处理数学、物理、化学、地理学科)
     * 预处理试题xml文件，删除掉影响组卷的标签，重新为xml文件中pict进行编号，使编号在系统中唯一
     * @param queXMLData  试题题文、答案、解析部分xml字符串
     * @param docXMLBefQue  上传doc文件此试题之前的xml字符串。为获取数学公式字符图片。
     * @return String
     * @since 2012-10-23
     */
    public static String processQueXMLDataBody(String queXMLStr,String docXMLBefQue,Long questionId){
    	
    	String retStr = "";
    	try{
      	//删除掉影响组卷的标签
    	retStr = delTagsOfQueXML(queXMLStr);
    	
    	//将所有wmz图片检查一遍，对于缺少<w:binData...>...</w:binData>字段的图片，补充完整
    	//=======================begin====================================
    	String[] tagsToPro= {".wmz",".png",".gif",".jpg"};
    	StringBuffer contentReturnStr =  new StringBuffer("");//保存处理后的字符串
    	StringBuffer contentStrBf = new StringBuffer(retStr);
    	if(null != contentStrBf && contentStrBf.length()>0){
    		for(String tagpro : tagsToPro){
    			contentReturnStr =  new StringBuffer("");
        		while(contentStrBf.indexOf(tagpro)>=0){
        			int tagproIndex = contentStrBf.indexOf(tagpro);
        			String tagBefore = contentStrBf.substring(0, tagproIndex)+tagpro;//标签之前字符串
        			String tagAfter = contentStrBf.substring(tagproIndex+tagpro.length());//标签之后字符串
        			String wholeTag = tagBefore.substring(tagBefore.lastIndexOf("wordml://"));
        			String binDataTag = "<w:binData w:name=\""+wholeTag+"\">";
        			boolean hastag = retStr.contains(binDataTag);
        			if(!hastag){//没有对应<w:binData标签
        				//在docXMLBefQue中查询对应<w:binData标签
        				int tagStart = docXMLBefQue.indexOf(binDataTag);
        				String tagAfterStr = docXMLBefQue.substring(tagStart);
        				int tagEnd = tagAfterStr.indexOf("</w:binData>");
        				String tagFind =  tagAfterStr.substring(0, tagEnd)+"</w:binData>";//待插入的图片标签
        				int lastPictIndex = tagBefore.lastIndexOf("<w:pict>")+8;//插入tagFind的位置
        				String firstPart = tagBefore.substring(0, lastPictIndex);
        				String lastPart = tagBefore.substring(lastPictIndex);
        				String wholePart = firstPart + tagFind +lastPart;
        				contentReturnStr.append(wholePart);
        			}else{
        				contentReturnStr.append(tagBefore);
        			}
        			contentStrBf = new StringBuffer(tagAfter);
            	}
        		contentReturnStr.append(contentStrBf);
        		contentStrBf = contentReturnStr;
        	} 
    	}
    	retStr = contentReturnStr.toString();
    	//=======================end======================================
    	
        //文档中图片的编号需要重新编写。编号整个文档要唯一。
    	String from = ".wmz";
    	String to = questionId+".wmz";
    	retStr = replace(from, to, retStr);
    	
    	from = ".png";
    	to = questionId+".png";
    	retStr = replace(from, to, retStr);
    	
    	from = ".gif";
    	to = questionId+".gif";
    	retStr = replace(from, to, retStr);
    	
    	//后期要加上此语句。
//    	from = ".jpg";
//    	to = questionId+".jpg";
//    	retStr = replace(from, to, retStr);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return retStr;
    }
    
    /**
     * 处理试题xml字符串，删除掉影响组卷的标签。
     * 1）处理jpg格式图片
     * 2）删除<w:pPr>与<w:rPr>之间所有标签。去掉试卷中横线以及文字没有靠左对齐的方式。
     * @param queXMLData
     * @return String
     * @since 2012-8-21
     */
    public static String delTagsOfQueXMLForPaper(String contentStr,Long questionId){
    	//1.处理jpg格式图片
    	//文档中图片的编号需要重新编写。编号整个文档要唯一。
    	String from = ".jpg";
    	String to = questionId+".jpg";
    	contentStr = replace(from, to, contentStr);
    	
    	//2.需要删除中间内容的标签数组(删除<w:pPr>与<w:rPr>之间所有标签)
    	String tagsToDelStart= "<w:pPr";
    	String tagsToDelEnd = "<w:rPr";
    	StringBuffer returnStrBf = new StringBuffer();
    	
    	if(null != contentStr && contentStr.length()>0){
    		StringBuffer contentStrBf = new StringBuffer(contentStr);
    		while(contentStrBf.indexOf(tagsToDelStart)>=0){
	        	StringBuffer temp = new StringBuffer();
    			String temp1 = contentStrBf.substring(0,contentStrBf.indexOf(tagsToDelStart));//tagsToDelStart标签前面部分字符串
    			//<w:pPr> 和</w:pPr>之间字符串
	        	String temp2 = contentStrBf.substring(contentStrBf.indexOf(tagsToDelStart),contentStrBf.indexOf("</w:pPr"));
	        	String temp3 = contentStrBf.substring(contentStrBf.indexOf("</w:pPr"));//"</w:pPr"标签之后字符串 
	        	
	        	temp.append(temp1);
	        	temp.append(temp2.substring(0,temp2.indexOf(">")+1));//加上<w:pPr>或<w:pPr ...>标签
	        	if(temp2.indexOf(tagsToDelEnd)>0){
	        		temp.append(temp2.substring(temp2.indexOf(tagsToDelEnd)));//加上<w:rPr标签之后字符串
	        	}else{
	        		temp.append(temp2.substring(temp2.indexOf(">")+1));//加上<w:pPr>或<w:pPr ...>标签之后字符串
	        	}
	        	temp.append(temp3.substring(0,temp3.indexOf(">")+1));//加上</w:pPr>或</w:pPr...>标签
	        	returnStrBf.append(temp);
	        	
	        	//获取</w:pPr>之后字符串
	        	contentStrBf = new StringBuffer(temp3.substring(temp3.indexOf(">")+1));//</w:pPr>标签之后字符串，不包括</w:pPr>
        	}
    		returnStrBf.append(contentStrBf);
    	}
        return returnStrBf.toString();
    }
}
