package cn.javass.commons.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 处理试题html文件
 * 
 * @author wxy
 * @since 2012-02-26
 * @version 1.0
 */
public class HtmlUtil {
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
                sbStr.append("\r\n");
            }
            ins.close();
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        return sbStr.toString();
    }

    /*
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
    * 将试题的html页面中和图片相关的路径全部进行修改。
    * 例如：将页面body4839.html中的body4839.files全部替换为quehtml/20110408/body4839.files
    * @param sfilePathAndName   String  要更新的html文件路径及名称  如c:/fqf.html
    * @param gfilePathAndName   String  更新后的html文件路径及名称  如c:/quehtml/fqf.html
    * @param from  要替换的字符串 如：body4839.files
    * @param to    要替换的目标字符串 如：quehtml/20110408/body4839.files
    */ 
    public static  void  updateHtmlImagePath(String sfilePathAndName,String gfilePathAndName,String from, String to)  {
    	//将html文件相关的.files文件复制到和gfilePathAndName相同的目录下
    	String sfilePathAndNameOfFiles = sfilePathAndName.substring(0, sfilePathAndName.length()-4)+"files";
    	String gfilePathAndNameOfFiles = gfilePathAndName.substring(0, gfilePathAndName.length()-4)+"files";
    	FileManager.copyFolder(sfilePathAndNameOfFiles, gfilePathAndNameOfFiles);
    	
    	String content = readTextFile(sfilePathAndName, "utf-8");
        String contentNew = replace(from,to,content);
        writeStringToFile(gfilePathAndName,contentNew,"utf-8");
    }
}
