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

	@RequestMapping(value = "/download")
	public ModelAndView download(HttpServletRequest request,
			final HttpServletResponse response, @RequestParam("articleID")
			int artcileId) throws Exception {
		String fileName = null;
		Article article = articleService.get(artcileId);
		String name = null;
		String attachName = null;
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
			fileName = "Web/DFiles/" + article.getFilePath();
		} else {
			fileName = "JtyWxDfiles2/" + article.getFilePath();
		}

		int ftpInt = ftpClient.connectServer();
		if (ftpInt == 0) {
			return null;
		}
		int k = ftpClient.isExistFile(attachName, fileName);
		if (k > 0) {
			ftpClient.changeWorkingDirectory(fileName);
			ftpClient.loadFile(attachName, ftpClient.getTemp() + attachName);
		}
		ftpClient.closeConnect();
		if (k > 0) {
			File file = new File(ftpClient.getTemp() + attachName); // 打开下载文件
			byte[] buffer = new byte[1024];
			try {
				if (file.exists()) {
					OutputStream toClient = new BufferedOutputStream(response
							.getOutputStream());
					response.setCharacterEncoding("utf-8");
					response
							.setContentType("application/octet-stream; charset=utf-8");
					response.setHeader("Content-Disposition",
							"attachment;fileName="
									+ new String(name.getBytes("gb2312"), "iso8859-1"));
					FileInputStream fis = new FileInputStream(file);
					byte[] b = new byte[1024];
					int length;
					while ((length = fis.read(b)) > 0) {
						toClient.write(b, 0, length);
					}
					fis.close();
					toClient.flush();
					toClient.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
