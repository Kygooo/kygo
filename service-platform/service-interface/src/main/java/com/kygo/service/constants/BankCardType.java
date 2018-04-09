package com.kygo.service.constants;

// 银行卡类型（1：信用卡；2：借记卡）
public enum BankCardType {

	CREDIT_CARD(1),
	DEBIT_CARD(2);

	private Integer type;

	private BankCardType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}
	
	public static BankCardType instanceOf(Integer type) {
		for (BankCardType bankCardType: BankCardType.values()) {
			if (bankCardType.getType().equals(type)) {
				return bankCardType;
			}
		}
		return null;
	}
}
