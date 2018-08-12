package com.jkoss.tool;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 分页标签
 * 
 * 
 */
public class PageTag extends SimpleTagSupport {

	private int pageNo;// 当前页
	private int totalPage;// 总页数
	private int totalRecord;// 总项目数

	private String url;// 采用url的方式

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		StringBuffer sb = new StringBuffer();

		url = getFormatUrl(url);

		if (this.pageNo == 1) {
			// sb.append("首页");
		} else {
			sb.append("<a href=\"" + url + "pageNo=1\">首页</a>");
		}
		sb.append("&nbsp;&nbsp;");
		if (this.pageNo == 1) {
			// sb.append("上一页");
		} else {
			sb.append("<a href=\"" + url + "pageNo="
					+ (pageNo - 1) + "\">上一页</a>");
		}
		sb.append("&nbsp;&nbsp;");
		if (totalPage <= this.pageNo) {
			// sb.append("下一页");
		} else {
			sb.append("<a href=\"" + url + "pageNo="
					+ (pageNo + 1) + "\">下一页</a>");
		}
		sb.append("&nbsp;&nbsp;");
		if (totalPage <= this.pageNo) {
			// sb.append("尾页");
		} else {
			sb.append("<a href=\"" + url + "pageNo=" + totalPage
					+ "\">尾页</a>");
		}

		sb.append("&nbsp;&nbsp;" + pageNo + "/" + totalPage );

		try {
			out.println(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	private String getFormatUrl(String url) {
		if (url.indexOf("?") == -1) {
			return url.concat("?");
		} else {
			return url.concat("&");
		}

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getpageNo() {
		return pageNo;
	}

	public void setpageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int gettotalPage() {
		return totalPage;
	}

	public void settotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int gettotalRecord() {
		return totalRecord;
	}

	public void settotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

}
