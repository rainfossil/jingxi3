package cn.com.bean;

import java.io.Serializable;
public class OrderForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int flag;
	private String formNumber;
	private String cargo_receiver;
	private String consignee;
	private String phone;
	private String company;
	private String dateTime;
	private String zip_code;
	private String receipt;
	private String express;
	private String paymethod;
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	//alter table orderform add column(receipt varchar(3),express varchar(13),paymethod varchar(13));
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zipCode) {
		this.zip_code = zipCode;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getFormNumber() {
		return formNumber;
	}
	public void setFormNumber(String formNumber) {
		this.formNumber = formNumber;
	}
	public String getCargo_receiver() {
		return cargo_receiver;
	}
	public void setCargo_receiver(String cargoReceiver) {
		this.cargo_receiver = cargoReceiver;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
