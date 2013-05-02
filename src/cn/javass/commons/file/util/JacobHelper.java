package cn.javass.commons.file.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/*
 * jacob操作类
 * wxy
 * 2012.01.09
 */
public class JacobHelper {

	private ActiveXComponent word; // word运行程序对象
	private Dispatch documents; // 所有word文档集合
	private Dispatch doc; // word文档
	private Dispatch selection; // 选定的范围或插入点
	private boolean saveOnExit = true;// 退出时保存文档

	/**
	 * 构造方法，实例化word运行程序对象和所有word文档集合 参数visible指文档是否看见，为true为可见，false为不可见
	 * 
	 * @param visible
	 */
	public JacobHelper(boolean visible) {
		if (word == null) {
			ComThread.InitSTA(); // 初始化com的线程，非常重要！！使用结束后要调用 realease方法
			word = new ActiveXComponent("Word.Application");
			word.setProperty("Visible", new Variant(visible));
			word.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
		}
		if (documents == null)
			documents = word.getProperty("Documents").toDispatch();
	}

	/**
	 * 设置退出时参数
	 * 
	 * @param saveOnExit
	 *            boolean true-退出时保存文件，false-退出时不保存文件
	 */
	public void setSaveOnExit(boolean saveOnExit) {
		this.saveOnExit = saveOnExit;
	}

	/**
	 * 创建一个新的word文档
	 * 
	 */
	public void createNewDocument() {
		doc = Dispatch.call(documents, "Add").toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	public void createNewDocument(String docPath) {
		doc = Dispatch.call(documents, "Add").toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
		Dispatch.call(
				(Dispatch) Dispatch.call(word, "WordBasic").getDispatch(),
				"FileSaveAs", docPath);
	}

	/**
	 * 打开一个已存在的文档 参数docPath为文档的路径
	 * 
	 * @param docPath
	 */
	public void openDocument(String docPath) {
		closeDocument();
		doc = Dispatch.invoke((Dispatch)documents, "Open",
				Dispatch.Method, new Object[] { docPath }, new int[1])
				.toDispatch();
	//	doc = Dispatch.call(documents, "Open", docPath).toDispatch();
		selection = Dispatch.get(word, "Selection").toDispatch();
	}

	/**
	 * 把选定的内容或插入点向上移动 参数pos指 移动的距离
	 * 
	 * @param pos
	 * 
	 */
	public void moveUp(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++)
			Dispatch.call(selection, "MoveUp");
	}

	/**
	 * 把选定的内容或者插入点向下移动 参数pos指移动的距离
	 * 
	 * @param pos
	 * 
	 */
	public void moveDown(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++)
			Dispatch.call(selection, "MoveDown");
	}

	/**
	 * 把选定的内容或者插入点向左移动 参数pos指移动的距离
	 * 
	 * @param pos
	 * 
	 */
	public void moveLeft(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++) {
			Dispatch.call(selection, "MoveLeft");
		}
	}

	/**
	 * 把选定的内容或者插入点向右移动 参数pos指移动的距离
	 * 
	 * @param pos
	 * 
	 */
	public void moveRight(int pos) {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		for (int i = 0; i < pos; i++)
			Dispatch.call(selection, "MoveRight");
	}

	/**
	 * 把插入点移动到文件首位置
	 * 
	 */
	public void moveStart() {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		Dispatch.call(selection, "HomeKey", new Variant(6));
	}

	/**
	 * 把插入点移动到文件末位置
	 */
	public void moveEnd() {
		if (selection == null)
			selection = Dispatch.get(word, "Selection").toDispatch();
		Dispatch.call(selection, "EndKey", new Variant(6));
	}

	/**
	 * 从选定内容或插入点开始查找文本 参数toFindText指 要查找的文本
	 * 
	 * @param toFindText
	 * @return boolean true-查找到并选中该文本，false-未查找到文本
	 */
	@SuppressWarnings("static-access")
	public boolean find(String toFindText) {
		if (toFindText == null || toFindText.equals(""))
			return false;
		// 从selection所在位置开始查询
		Dispatch find = word.call(selection, "Find").toDispatch();
		// 设置要查找的内容
		Dispatch.put(find, "Text", toFindText);
		// 向前查找
		Dispatch.put(find, "Forward", "True");
		// 设置格式
		Dispatch.put(find, "Format", "True");
		// 大小写匹配
		Dispatch.put(find, "MatchCase", "True");
		// 全字匹配
		Dispatch.put(find, "MatchWholeWord", "True");
		// 查找并选中
		return Dispatch.call(find, "Execute").getBoolean();
	}

	/**
	 * 把选定选定内容设定为替换文本
	 * 
	 * @param toFindText
	 *            查找字符串
	 * @param newText
	 *            要替换的内容
	 * @return
	 */
	public boolean replaceText(String toFindText, String newText) {
		if (!find(toFindText))
			return false;
		Dispatch.put(selection, "Text", newText);
		return true;
	}

	/**
	 * 全局替换文本 参数toFindText指查找字符串 参数newText指要替换的内容
	 * 
	 * @param toFindText
	 * @param newText
	 * 
	 */
	public void replaceAllText(String toFindText, String newText) {
		while (find(toFindText)) {
			Dispatch.put(selection, "Text", newText);
			Dispatch.call(selection, "MoveRight");
		}
	}

	/**
	 * 在当前插入点插入字符串 参数newText指要插入的新字符串
	 * 
	 * @param newText
	 * 
	 */
	public void insertText(String newText) {
		Dispatch.put(selection, "Text", newText);
	}

	/**
	 * 替换图片 参数toFindText指要查找的字符串 参数imagePath指图片路径
	 * 
	 * @param toFindText
	 * @param imagePath
	 * 
	 * @return
	 */
	public boolean replaceImage(String toFindText, String imagePath) {
		if (!find(toFindText))
			return false;
		Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
				"AddPicture", imagePath);
		return true;
	}

	/**
	 * 全局替换图片 参数toFindText指要查找的字符串 参数imagePath指图片路径
	 * 
	 * @param toFindText
	 * @param imagePath
	 * 
	 */
	public void replaceAllImage(String toFindText, String imagePath) {
		while (find(toFindText)) {
			Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
					"AddPicture", imagePath);
			Dispatch.call(selection, "MoveRight");
		}
	}

	/**
	 * 在当前文档拷贝数据
	 * 
	 * @param pos
	 */
	public void copy(String toCopyText) {
		moveStart();
		if (this.find(toCopyText)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Copy");
		}
	}

	/**
	 * 在当前文档粘帖剪贴板数据
	 * 
	 * @param pos
	 */
	public void paste(String pos) {
		moveStart();
		if (this.find(pos)) {
			Dispatch textRange = Dispatch.get(selection, "Range").toDispatch();
			Dispatch.call(textRange, "Paste");
		}
	}

	/**
	 * 在当前文档指定的位置拷贝来自另一个文档中的书签内容 参数anotherDocPath指另一个文档的磁盘路径
	 * 参数bookMarkKey指被拷贝的文档的书签名
	 * 
	 * @param anotherDocPath
	 * @param bookMarkKey
	 */
	public void copyBookMarkFromAnotherDoc(String anotherDocPath,
			String bookMarkKey) {
		Dispatch wordContent = Dispatch.get(doc, "Content").toDispatch(); // 取得当前文档的内容
		Dispatch.call(wordContent, "InsertAfter", "$selection$"); // 插入特殊符定位插入点
		copyBookMarkFromAnotherDoc(anotherDocPath, bookMarkKey, "$selection$");
	}

	public boolean isExistBookMark(String anotherDocPath, String bookMarkKey) {
		Dispatch doc2 = null;
		boolean bookMarkExist = false;
		try {
			doc2 = Dispatch.call(documents, "Open", anotherDocPath)
					.toDispatch();
			Dispatch bookMarks = Dispatch.call(doc2, "Bookmarks").toDispatch();

			// 查找此书签是否存在
//			bookMarkExist = Dispatch.call(bookMarks, "Exists", bookMarkKey).toBoolean();
			bookMarkExist = Dispatch.call(bookMarks, "Exists", bookMarkKey).getBoolean();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (doc2 != null) {
				Dispatch.call(doc2, "Close", new Variant(saveOnExit));
				doc2 = null;
			}
		}
		return bookMarkExist;
	}

	/**
	 * 在当前文档指定的位置拷贝来自另一个文档中的书签内容 参数anotherDocPath指另一个文档的磁盘路径
	 * 参数bookMarkKey指被拷贝的文档的书签名 参数pos指要插入的位置
	 * 
	 * @param anotherDocPath
	 * @param bookMarkKey
	 * @param pos
	 */
	public void copyBookMarkFromAnotherDoc(String anotherDocPath,
			String bookMarkKey, String pos) {
		Dispatch doc2 = null;
		try {
			doc2 = Dispatch.call(documents, "Open", anotherDocPath)
					.toDispatch();
			Dispatch bookMarks = Dispatch.call(doc2, "Bookmarks").toDispatch();
			Dispatch rangeItem = Dispatch.call(bookMarks, "Item", bookMarkKey)
					.toDispatch();
			Dispatch range = Dispatch.call(rangeItem, "Range").toDispatch();
			Dispatch.call(range, "Copy");
			if (this.find(pos)) {
				Dispatch textRange = Dispatch.get(selection, "Range")
						.toDispatch();
				Dispatch.call(textRange, "Paste");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (doc2 != null) {
				Dispatch.call(doc2, "Close", new Variant(saveOnExit));
				doc2 = null;
			}
		}
	}

	/**
	 * 将当前word文档转成html文档
	 * @param htmlFilePath
	 * @return
	 */
	public boolean doc2html(String htmlFilePath){
		boolean successful = false;
		try{
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method,
					new Object[]{htmlFilePath, new Variant(8)}, new int[1]); 
			successful = true;
		}catch(Exception ex){
			successful = false;
			ex.printStackTrace();
		}
		return successful;
	}
	
	/**
	 * 将当前word文档转成xml文档
	 * @param xmlFilePath
	 * @return
	 */
	public boolean doc2xml(String xmlFilePath){
		boolean successful = false;
		try{
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method,
					new Object[]{xmlFilePath, new Variant(11)}, new int[1]); 
			successful = true;
		}catch(Exception ex){
			successful = false;
			ex.printStackTrace();
		}
		return successful;
	}
	
	/**
	 * 设置当前选定内容的字体 参数fontSize为字体大小 参数fontName为 字体名称
	 * 
	 * @param fontSize
	 * 				字体大小,只能用数字，不能用中文。例如：10,10.5,11 
	 * @param fontName
	 * 
	 */
	public void setFont(String fontSize, String fontName) {
		Dispatch wordContent = Dispatch.get(doc, "Content").toDispatch(); // 取得word文件的内容
		Dispatch font = Dispatch.get(wordContent, "Font").toDispatch();
		Dispatch.put(font, "Name", new Variant(fontName));
		Dispatch.put(font, "Size", fontSize);
	}
	
	/**   
     * 设置选定内容的字体 注：在调用此方法前，选定区域对象selection必须存在   
     *   
     * @param fontName   
     *            字体名称，例如 "宋体"   
     * @param rgbColor   
     *            颜色，例如"255,255,255"   
     * @param fontSize   
     *            字体大小,只能用数字，不能用中文。例如：10,10.5,11   
     */   
    public void setFontScale(String fontSize, String fontName, String rgbColor) {      
    	Dispatch wordContent = Dispatch.get(doc, "Content").toDispatch(); // 取得word文件的内容
		Dispatch font = Dispatch.get(wordContent, "Font").toDispatch();
		Dispatch.put(font, "Size", fontSize); 
		Dispatch.put(font, "Name", new Variant(fontName));    
        Dispatch.put(font, "Color", rgbColor);    
    }
    
    /** 
     * 对当前段落进行格式化 
     * @param lineSpace 
     *            设置行间距 默认：1.0 0：1.0 1：1.5 2：2.0 3：最小值 4：固定值 
    */ 
    public void setParaFormat(int lineSpace){ 
        if(lineSpace <  0  || lineSpace >  4 ) { 
            lineSpace =  0 ; 
        } 

        Dispatch alignment = Dispatch.get(selection,  "ParagraphFormat" ).toDispatch(); 
        Dispatch.put(alignment,  "LineSpacingRule" ,  new  Variant(lineSpace)); 
    }
    /** 
     * 对当前段落进行格式化 
     *  
     * @param align 
     *            设置排列方式 默认：居左 0:居左 1:居中 2:居右 3:两端对齐 4:分散对齐 
     * @param lineSpace 
     *            设置行间距 默认：1.0 0：1.0 1：1.5 2：2.0 3：最小值 4：固定值 
    */ 
    public void setParaFormat(int align, int lineSpace){ 
        if(align <  0  || align >  4 ) { 
            align =  0 ; 
        } 
        if(lineSpace <  0  || lineSpace >  4 ) { 
            lineSpace =  0 ; 
        } 

        Dispatch alignment = Dispatch.get(selection,  "ParagraphFormat" ).toDispatch(); 
        Dispatch.put(alignment,  "Alignment" , align); 
        Dispatch.put(alignment,  "LineSpacingRule" ,  new  Variant(lineSpace)); 
    } 

	public String getText() {
		Dispatch wordContent = Dispatch.get(doc, "Content").toDispatch(); // 取得word文件的内容
		String text = Dispatch.get(wordContent, "Text").toString();;
		return text;
	}
	/**
	 * 文件保存或另存为 参数savePath指保存或另存为路径
	 * 
	 * @param savePath
	 * 
	 */
	public void save(String savePath) {
		Dispatch.call(
				(Dispatch) Dispatch.call(word, "WordBasic").getDispatch(),
				"FileSaveAs", savePath);
	}
	
	/**
	 * 文件另存为另一个doc文件 参数savePath指保存或另存为路径
	 * 
	 * @param savePath
	 * @author wxy 
	 * @since 2012-12-11
	 */
	public void saveAs(String savePath) {
		Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {savePath, 
				new Variant(0)}, new int[1]);

	}

	/**
	 * 关闭当前word文档
	 * 
	 */
	public void closeDocument() {
		if (doc != null) {
			Dispatch.call(doc, "Save");
			Dispatch.call(doc, "Close", new Variant(saveOnExit));
			doc = null;
		}
	}

	/**
	 * 关闭全部应用
	 * 
	 */
	public void close() {
		closeDocument();
		if (word != null) {
			Dispatch.call(word, "Quit");
			word = null;
		}
		selection = null;
		documents = null;
		ComThread.Release();
	}
	
	public boolean word2html(String srcFileName, String destFileName) {
		if (srcFileName == null || "".equals(srcFileName.trim()))
			throw new IllegalStateException("文件名不能为空");
		if (destFileName == null || "".equals(destFileName.trim()))
			throw new IllegalStateException("文件名不能为空");
		boolean flag = false;
		// 取得文件类型
		srcFileName = srcFileName.trim();
		destFileName = destFileName.trim();
		String srcFiletype = new String();
		srcFiletype = srcFileName.substring((srcFileName.length() - 3),
				srcFileName.length());
		String destFiletype = new String();
		destFiletype = destFileName.substring((destFileName.length() - 4),
				destFileName.length());
		// 判断源文件是否为doc文件
		if (!"doc".equals(srcFiletype))
			throw new IllegalStateException("错误的文件格式!");
		// 判断目录文件是否为html文件
		if (!"html".equals(destFiletype))
			throw new IllegalStateException("错误的文件格式!");
		// 打印doc文件名
		System.out.println(srcFileName.substring(0, (srcFileName.length() - 4)));
		// 启动word
		ActiveXComponent app = new ActiveXComponent("Word.Application");
		try {
			app.setProperty("Visible", new Variant(false));
			// 设置word不可见
			Object docs = app.getProperty("Documents").toDispatch();
			Object doc = Dispatch.invoke((Dispatch) docs, "Open",
					Dispatch.Method, new Object[] { srcFileName }, new int[1])
					.toDispatch();
			
			// 打开word文件
			Dispatch.invoke((Dispatch) doc, "SaveAs", Dispatch.Method,
					new Object[] { destFileName, new Variant(8) }, new int[1]);
			// 作为html格式保存到临时文件
			Variant f = new Variant(false);
			Dispatch.invoke((Dispatch) doc, "Close", Dispatch.Method,
					new Object[] { f }, new int[1]);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			app.invoke("Quit", new Variant[] {});
		}
		// System.out.println("转化完毕！");

		return flag;
	}
}