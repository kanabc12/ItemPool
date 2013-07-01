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
import org.springframework.web.servlet.ModelAndView;

import cn.javass.commons.file.util.MiniFtp;

@Controller
@RequestMapping("/fileOperate")
public class DownloadController {
	@Autowired
	private MiniFtp ftpClient;
	
	@Autowired
	private IArticleService articleService;
	@RequestMapping(value="/download")
	public ModelAndView download(HttpServletRequest request,
			final HttpServletResponse response,
			@RequestParam("articleID") int artcileId) throws Exception {
		String fileName = null;
		Article article = articleService.get(artcileId);
		fileName = article.getFileName();
		int ftpInt = ftpClient.connectServer();
		if(ftpInt==0){
			return null;
		}
		int k=ftpClient.isExistFile(article.getAttach(),article.getFilePath());
		if(k>0){
			ftpClient.changeWorkingDirectory(article.getFilePath());
			ftpClient.loadFile(article.getAttach(), ftpClient.getTemp()+article.getAttach());
		}
		ftpClient.closeConnect();
		if(k>0){
			File file = new File(ftpClient.getTemp()+article.getAttach()); // 打开下载文件
			byte[] buffer=new byte[1024];
			try{
				if(file.exists()){
					OutputStream toClient=new BufferedOutputStream(response.getOutputStream());
					response.setCharacterEncoding("utf-8");  
					response.setContentType("application/octet-stream; charset=utf-8");  
			        response.setHeader("Content-Disposition", "attachment;fileName="+new String(fileName.getBytes("gb2312"), "iso8859-1"));  
			        long df=0;
			        FileInputStream fis=new FileInputStream(file);
					BufferedInputStream buff=new BufferedInputStream(fis);
					while(df<file.length()){
			            int j=buff.read(buffer,0,1024);
			            df+=j;
			            //将b中的数据写到客户端的内存
			            toClient.write(buffer,0,j);
			        }
					buff.close();
					toClient.flush();
					toClient.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}

}
