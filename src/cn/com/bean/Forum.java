package cn.com.bean;

import java.io.Serializable;

public class Forum implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int flag;
	private String contentnumber;
	private String id;
	private String content;
	private String dateTime;
	private int times;
	
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getContentnumber() {
		return contentnumber;
	}
	public void setContentnumber(String contentnumber) {
		this.contentnumber = contentnumber;
	}
	public String getId() {
		return id;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


}
