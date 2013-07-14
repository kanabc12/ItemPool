package org.hxy.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hxy.model.Article;
import org.hxy.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.javass.commons.file.util.FTPClientTemplate;

@Controller
@RequestMapping("/fileOperate")
public class DownloadController {
	
	@Autowired
	private FTPClientTemplate ftpClientTemplate;

	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "/download")
	public void download(HttpServletRequest request,
			final HttpServletResponse response, @RequestParam("articleID")
			int artcileId) throws Exception {
		String filePath = null;
		Article article = articleService.get(artcileId);
		String name = null;
		String attachName = null;
		String remoteAbsoluteFile;
		String localAbsoluteFile;
		
		if (article != null && article.getAttach() != null) {
			if(article.getAttach().charAt(article.getAttach().length()-1)=='|'){
				attachName = article.getAttach().substring(0,
						article.getAttach().length() - 1);
			}else{
				attachName =  article.getAttach();
			}
		}
		
		if (article != null && article.getFileName() != null) {
			if(article.getFileName().charAt(article.getFileName().length()-1)=='|'){
				name = article.getFileName().substring(0,
						article.getFileName().length() - 1);
			}else{
				name =  article.getFileName();
			}
		}

		if (article.getDsk() == 0) {
			filePath = "Web/DFiles/" + article.getFilePath();
		} else {
			filePath = "JtyWxDfiles2/" + article.getFilePath();
		}
		if(filePath.charAt(filePath.length()-1)!='/'){
			remoteAbsoluteFile = filePath+"/"+attachName;
		}else{
			remoteAbsoluteFile = filePath+attachName;
		}
		localAbsoluteFile = new String((ftpClientTemplate.getTemp() + attachName).getBytes("GBK"),"ISO-8859-1");
		File file = new File(localAbsoluteFile); // 打开下载文件,如果本地有，就在本地取数据，如果本地没有就到FTP服务器上下载
		if(file.exists()&&file.length()!=0){
			ptintToClient(request,response,file,name);
		}else{
			if(ftpClientTemplate.get(remoteAbsoluteFile, localAbsoluteFile)){
				ptintToClient(request,response,file,name);
			}
		}
	}
	
	private void ptintToClient(HttpServletRequest request,
			final HttpServletResponse response,File file,String name){
		try{
			OutputStream toClient = new BufferedOutputStream(response
					.getOutputStream());
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition","attachment;fileName="+ new String(name.getBytes("gb2312"), "iso8859-1"));
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream input = new BufferedInputStream(fis);
			byte[] b = new byte[1024];
			int length;
			while ((length=input.read(b))!=-1) {                
                toClient.write(b, 0, length);  
            } 
			toClient.flush();
			toClient.close();
			input.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
