package cn.javass.commons.htmlparser;

import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.beans.LinkBean;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;
import org.htmlparser.visitors.NodeVisitor;
import org.htmlparser.visitors.ObjectFindingVisitor;

public class HtmlParserTest {
	// 得到页面源代码
	public void printHtmlString(String src) throws Exception {
		Parser parser = new Parser();
		// 这样返回的list里面包含网页中的所有节点
		NodeList parse = parser.parse(null);
		System.out.println(parse.toHtml());
	}

	// 使用TagNode的getAttribute("src")得到所有图片的路径
	public void findAllImgTagSrc(String src) throws Exception {
		Parser parser = new Parser();
		NodeFilter fiter = new NodeClassFilter(ImageTag.class);
		NodeList list = parser.extractAllNodesThatMatch(fiter);
		System.out.println("IMG tags number :" + list.size());
		SimpleNodeIterator iterator = list.elements();
		while (iterator.hasMoreNodes()) {
			// 这个地方需要记住
			Node node = iterator.nextNode();
			TagNode tagNode = new TagNode();
			// 一旦得到了TagNode ， 就可以得到其中的属性值
			tagNode.setText(node.toHtml());
			System.out.println(tagNode.getAttribute("src"));
		}
	}

	// 使用ImageTag的getURL得到所有的图片路径
	public void easyFildImagePath(String src) throws Exception {
		Parser parser = new Parser(src);
		NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(
				ImageTag.class));
		for (int i = 0; i < list.size(); i++) {
			Node node = list.elementAt(i);
			ImageTag imgTag = new ImageTag();
			imgTag.setText(node.toHtml());
			System.out.println(imgTag.getAttribute("src"));
		}
	}

	// 使用visitor模式得到所有图片路径
	public void visitorModeFindImgSrc(String src) throws Exception {
		Parser parser = new Parser(src);
		NodeVisitor visitor = new NodeVisitor() {
			public void visitTag(Tag tag) {
				if (tag.getClass() == ImageTag.class) {
					System.out.println(tag.getAttribute("src"));
				}
			}

		};
		parser.visitAllNodesWith(visitor);
	}

	// Node可以传唤成它实际属于的类型
	public void couldGetNodeWithItsType(String src) throws Exception {
		Parser parser = new Parser(src);
		NodeList nodes = parser.extractAllNodesThatMatch(new NodeClassFilter(
				ImageTag.class));
		for (int i = 0; i < nodes.size(); i++) {
			ImageTag tag = (ImageTag) nodes.elementAt(i);
			System.out.println(tag.getImageURL());
		}
	}

	// 得到某个网页的纯文本
	public void getPureTextOfSite(String src) {
		StringBean textBean = new StringBean();
		textBean.setURL(src);
		String pureText = textBean.getStrings();
		System.out.println(pureText);
	}

	// 使用ObjectFindingVisitor得到所有的图片路径
	public void testImageVisitor(String src) throws Exception {
		Parser parser = new Parser(src);
		ObjectFindingVisitor visitor = new ObjectFindingVisitor(ImageTag.class);
		parser.visitAllNodesWith(visitor);
		Node[] tags = visitor.getTags();
		for (int i = 0; i < tags.length; i++) {
			ImageTag tag = (ImageTag) tags[i];
			System.out.println(tag.getImageURL());
		}
	}

	// 使用TagNameFilter得到所有图片
	public void getImgSrcWithTagNameFilter(String src) throws Exception {
		Parser parser = new Parser(src);
		NodeFilter filter = new TagNameFilter("IMG");
		NodeList list = parser.extractAllNodesThatMatch(filter);
		for (int i = 0; i < list.size(); i++) {
			ImageTag tag = (ImageTag) list.elementAt(i);
			System.out.println(tag.getImageURL());
		}
	}

	// 从指定字符串解析html
	public void parseHtmlWithString() throws Exception {
		Parser parser = new Parser();
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<img src='http://51jsp.cn/post/1.jpg' />");
		sb.append("<img src='http://51jsp.cn/post/2.jpg' />");
		sb.append("<img src='http://51jsp.cn/post/3.jpg' />");
		sb.append("</body>");
		sb.append("</html>");
		parser.setInputHTML(sb.toString());
		NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(
				ImageTag.class));
		for (int i = 0; i < list.size(); i++) {
			ImageTag imgTag = (ImageTag) list.elementAt(i);
			System.out.println(imgTag.getImageURL());
		}
	}

	// 使用或者策略的Filter
	public void OrFilterTest(String src) throws Exception {
		Parser parser = new Parser(src);
		OrFilter orFilter = new OrFilter(new NodeClassFilter(ImageTag.class),
				new NodeClassFilter(LinkTag.class));
		NodeList list = parser.extractAllNodesThatMatch(orFilter);
		for (int i = 0; i < list.size(); i++) {
			Node node = list.elementAt(i);
			if (node instanceof ImageTag) {
				ImageTag imgTag = (ImageTag) node;
				System.out.println("image : " + imgTag.getImageURL());
			}
			if (node instanceof LinkTag) {
				LinkTag linkTag = (LinkTag) node;
				System.out.println("link : " + linkTag.getLink());
			}
		}
	}

	// 读取表格内容
	public void readTableContent() throws Exception {
		Parser parser = new Parser();
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>1</td>");
		sb.append("<td>2</td>");
		sb.append("<td>3</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>One</td>");
		sb.append("<td>Two</td>");
		sb.append("<td>Three</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</body>");
		sb.append("</html>");
		parser.setInputHTML(sb.toString());
		NodeFilter filter = new NodeClassFilter(TableTag.class);
		NodeList list = parser.extractAllNodesThatMatch(filter);
		for (int i = 0; i < list.size(); i++) {
			TableTag table = (TableTag) list.elementAt(i);
			for (int j = 0; j < table.getRowCount(); j++) {
				TableRow row = table.getRow(j);
				TableColumn[] columns = row.getColumns();
				for (int k = 0; k < columns.length; k++) {
					System.out.println(columns[k].toPlainTextString());
				}
			}
		}
	}

	// LinkBean类使用
	public void LinkBeanTest(String src) throws Exception {
		LinkBean linkBean = new LinkBean();
		linkBean.setURL(src);
		URL[] links = linkBean.getLinks();
		for (int i = 0; i < links.length; i++) {
			System.out.println(links[i]);
		}
	}
}
