package com.pk.service;

public interface IPurchaseOrder {
	public String purchase(String[] orders,Double[] prices, String fromMail,String[] toMails)throws Exception;
}
