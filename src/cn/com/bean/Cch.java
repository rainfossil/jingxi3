package cn.com.bean;

import java.io.Serializable;
import java.sql.SQLException;

import cn.com.detail.DetailManager;

public class Cch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int flag;
	private String orderNumber;
	private String userId;
	private String wareNumber;
	private int wareCount;
	private int status;
	private String dateTime;
	private Ware we;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWareNumber() {
		return wareNumber;
	}
	public void setWareNumber(String wareNumber) {
		this.wareNumber = wareNumber;
		DetailManager dm=new DetailManager();
		try {
			we=dm.getSingleWare(wareNumber);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Ware getWe() {
		return we;
	}
	public int getWareCount() {
		return wareCount;
	}
	public void setWareCount(int wareCount) {
		this.wareCount = wareCount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
}
