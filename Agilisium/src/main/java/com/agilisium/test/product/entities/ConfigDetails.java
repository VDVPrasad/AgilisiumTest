package com.agilisium.test.product.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConfigDetails implements Serializable {
	@Id
	private int configId;
	private String productName;
	private String memory;
	private String storage;
	private String processor;
	private String monitor;
	
	
	public ConfigDetails(String productName,String memory, String storage, String processor ,String monitor) {
		this.productName=productName;
		this.memory=memory;
		this.storage=storage;
		this.monitor=monitor;
	    this.processor=processor;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public String getMonitor() {
		return monitor;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	

}
