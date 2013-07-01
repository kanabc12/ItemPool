package cn.javass.commons.file.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.TreeSet;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.io.SocketInputStream;

/**
 * 其实JDK里面也有支持FTP操作的包【jre/lib下的rt.jar】，但是SUN的DOC里面并没有提供相应文档，
 * 因为这里面的包，不被官方支持，建议不要使用。我们可以使用第三方提供的包apache.commons。 apache.commons的包，都有文档，方便使用
 * 另外IBM也有提供一个ftp包，我没有用过，有兴趣的可以去研究一下
 * 
 * @commons-net：http://apache.mirror.phpchina.com/commons/net/binaries/commons-net-1.4.1.zip
 * @jakarta-oro：http://mirror.vmmatrix.net/apache/jakarta/oro/source/jakarta-oro-2.0.8.zip
 * @commons-io：http://apache.mirror.phpchina.com/commons/io/binaries/commons-io-1.3.2-bin.zip
 */
public class MiniFtp {
	private String username;

	private String password;

	private String ip;

	private int port;

	private String temp;

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	private FTPClient ftpClient = new FTPClient();



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void main(String[] args) {

		connectServer();
		changeWorkingDirectory("张鹏");// 进入文件夹webroot
		uploadFile("d:/Doc1.doc", "2.doc");// 上传文件woxingwosu.xml，重新命名为myfile.xml
		closeConnect();// 关闭连接
	}
	
	/** 
     * 查找指定目录是否存在 
     * @param String filePath 要查找的目录 
     * @return boolean:存在:true，不存在:false 
     * @throws IOException 
     */ 
	public void checkPathExist(String filePath) throws IOException{ 
		//boolean existFlag = false; 
		filePath=filePath.replaceAll("/","\\\\");
		try{ 
			filePath=new String(filePath.getBytes("GBK"),"iso-8859-1");//先转换一下
			if(!ftpClient.changeWorkingDirectory(filePath)){ 
				if(!filePath.equals(""))
				{
					String cfPath="";
					String[] arrP=filePath.split("\\\\");
					for(int i=0;i<arrP.length;i++)
					{
						cfPath+="\\"+arrP[i];
						if(!ftpClient.changeWorkingDirectory(cfPath)){ 
							ftpClient.makeDirectory(cfPath); 
						}
					}
				}
			} 
			ftpClient.changeWorkingDirectory("\\");
		}catch(Exception e){ 
			e.printStackTrace(); 
		}
		//return existFlag; 
	} 


	/**
	 * 上传文件
	 * 
	 * @param localFilePath--本地文件路径
	 * @param newFileName--新的文件名
	 */
	public void uploadFile(String localFilePath, String newFileName) {
		connectServer();
		// 上传文件
		BufferedInputStream buffIn = null;
		try {
			buffIn = new BufferedInputStream(new FileInputStream(localFilePath));
			
			ftpClient.storeFile(newFileName, buffIn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffIn != null)
					buffIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param localFilePath--本地文件路径
	 * @param newFileName--新的文件名
	 */
	public void uploadFile(File file, String newFileName) {
		// 上传文件
		BufferedInputStream buffIn = null;
		try {
			newFileName=new String(newFileName.getBytes("GBK"),"iso-8859-1");
			buffIn = new BufferedInputStream(new FileInputStream(file));
			ftpClient.storeFile(newFileName, buffIn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffIn != null)
					buffIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param remoteFileName
	 *            --服务器上的文件名
	 * @param localFileName--本地文件名
	 */
	public void loadFile(String remoteFileName, String localFileName) {		
	// 下载文件
		
		BufferedOutputStream buffOut = null;
		try {
			localFileName=new String(localFileName.getBytes("GBK"),"iso-8859-1");
			buffOut = new BufferedOutputStream(new FileOutputStream(
					localFileName));
			ftpClient.retrieveFile(remoteFileName, buffOut);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffOut != null)
					buffOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 下载文件
	 * 
	 * @param remoteFileName
	 *            --服务器上的文件名
	 * @param localFileName--本地文件名
	 */
	public SocketInputStream loadFileForStream(String remoteFileName) {		
	// 下载文件
		
		SocketInputStream stream=null;
			
			try {
				stream=(SocketInputStream) ftpClient.retrieveFileStream(remoteFileName);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		return stream;	

	}
	//判断文件是否存在
	public int isExistFile(String fileName,String url){
		url=url.replaceAll("/","\\\\");
		int i=0;
		try {
			url=new String(url.getBytes("GBK"),"iso-8859-1");
			String[] strs=ftpClient.listNames(fileName);
			if(strs!=null && strs.length>0)
			i=1;
		} catch (IOException e) {
			
			e.printStackTrace();
		}     
		return i;
	}

	/**
	 * 列出服务器上所有文件及目录
	 */
	public void listAllRemoteFiles() {
		listRemoteFiles("\\\\");
	}

	public void listRemoteFiles(String regStr) {
		try {
			FTPFile[] files = ftpClient.listFiles(regStr);
			// System.out.println("系统名称:"+ftpClient.getSystemName());
			// System.out.println(files.length);
			if (files == null || files.length == 0){}
				//System.out.println("There has not any file!");
			else {
				TreeSet<FTPFile> fileTree = new TreeSet<FTPFile>(
						new Comparator<Object>() {
							// 先按照文件的类型排序(倒排)，然后按文件名顺序排序
							public int compare(Object objFile1, Object objFile2) {
								if (objFile1 == null)
									return -1;
								else if (objFile2 == null)
									return 1;
								else {
									FTPFile file1 = (FTPFile) objFile1;
									FTPFile file2 = (FTPFile) objFile2;
									if (file1.getType() != file2.getType())
										return file2.getType()
												- file1.getType();
									else
										return file1.getName().compareTo(
												file2.getName());
								}
							}
						});
				for (FTPFile file : files)
					fileTree.add(file);
				//System.out.printf("%-35s%-10s%15s%15s\n", "名称", "类型", "修改日期",
						//"大小");
				for (FTPFile file : fileTree) {
					//System.out.printf("%-35s%-10s%15s%15s\n", iso8859togbk(file
					//		.getName()), FILE_TYPES[file.getType()], dateFormat
					//		.format(file.getTimestamp().getTime()), FileUtils
					//		.byteCountToDisplaySize(file.getSize()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 */
	public void closeConnect() {
		try {

			ftpClient.logout();
			ftpClient.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置传输文件的类型[文本文件或者二进制文件]
	 * 
	 * @param fileType--BINARY_FILE_TYPE、ASCII_FILE_TYPE
	 */
	public void setFileType(int fileType) {
		try {
			ftpClient.setFileType(fileType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 扩展使用
	 * 
	 * @return
	 */
	protected FTPClient getFtpClient() {

		return ftpClient;
	}

	/**
	 * 设置参数
	 * 
	 * @param configFile
	 *            --参数的配置文件
	 
	private void setArg(String configFile) {
		Configuration cfg = Configuration.getInstance();
		username = cfg.getValue("ftpusername");
		password = cfg.getValue("ftppassword");
		ip = cfg.getValue("ftpip");
		port = Integer.parseInt(cfg.getValue("ftpport"));
	}*/

	/**
	 * 连接到服务器
	 */
	public int connectServer() {
		int reply;
		int i=0;
		try {
//			setArg(configFile);
			ftpClient.setDefaultPort(port);
			ftpClient.configure(getFtpConfig());
			ftpClient.connect(ip);
			ftpClient.login(username, password);
			ftpClient.setDefaultPort(port);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			reply = ftpClient.getReplyCode();
			
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				//System.err.println("FTP server refused connection.");
			}else{
				i=1;
			}
		} catch (Exception e) {
			//System.err.println("登录ftp服务器【" + ip + "】失败");
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 进入到服务器的某个目录下
	 * 
	 * @param directory
	 */
	public void changeWorkingDirectory(String directory) {
		directory=directory.replaceAll("/","\\\\");
		try {
			directory=new String(directory.getBytes("GBK"),"iso-8859-1");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * 返回到上一层目录
	 */
	public void changeToParentDirectory() {
		try {

			ftpClient.changeToParentDirectory();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * 删除文件
	 */
	public void deleteFile(String filename) {
		try {

			ftpClient.deleteFile(filename);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * 重命名文件
	 * 
	 * @param oldFileName
	 *            --原文件名
	 * @param newFileName
	 *            --新文件名
	 */
	public void renameFile(String oldFileName, String newFileName) {
		try {
			connectServer();
			ftpClient.rename(oldFileName, newFileName);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * 设置FTP客服端的配置--一般可以不设置
	 * 
	 * @return
	 */
	private FTPClientConfig getFtpConfig() {
		FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_NT);// 这个很关键，一定要选对windows是sys_nt
		ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
		// ftpConfig.setServerLanguageCode("gb2312");
		return ftpConfig;
	}


}
