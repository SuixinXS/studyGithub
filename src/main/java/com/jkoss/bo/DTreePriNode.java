package com.jkoss.bo;

import java.io.Serializable;

public class DTreePriNode implements Serializable {
	
	// {自己的编号, 父节点 ，      text ，    url}    //规定每层 max=99个节点       
		private int indexNo;
		private int prtNo;
		private String text;
		private String uri;
		
		public int getIndexNo() {
			return indexNo;
		}
		public void setIndexNo(int indexNo) {
			this.indexNo = indexNo;
		}
		public int getPrtNo() {
			return prtNo;
		}
		public void setPrtNo(int prtNo) {
			this.prtNo = prtNo;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getUri() {
			return uri;
		}
		public void setUri(String uri) {
			this.uri = uri;
		}
		

}
