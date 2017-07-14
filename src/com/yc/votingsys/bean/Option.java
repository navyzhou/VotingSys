package com.yc.votingsys.bean;

/**
 * 选项
 * @author navy
 */
public class Option {
	private String opt;
	private Integer num;
	
	@Override
	public String toString() {
		return "Option [opt=" + opt + ", num=" + num + "]";
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Option() {
		super();
	}

	public Option(String opt, Integer num) {
		super();
		this.opt = opt;
		this.num = num;
	}
}