package com.yc.votingsys.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 投票主题信息
 * @author navy
 */
public class VoteInfo {
	private String title;
	private Integer type; //1.单选，  2.多选
	private List<Option> opts;
	
	public Map<String,Object> getVoteInfoToMap(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("_id",new Date().getTime());
		map.put("title",title);
		map.put("type",type);
		map.put("usid",new String[]{});
		
		Map<String,Integer> opt=new HashMap<String,Integer>();
		for(Option op:opts){
			opt.put(op.getOpt(),op.getNum());		
		}
		map.put("opts",opt);
		return map;
	}
	
	@Override
	public String toString() {
		return "VoteInfo [title=" + title + ", type=" + type + ", opts=" + opts
				+ "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Option> getOpts() {
		return opts;
	}

	public void setOpts(List<Option> opts) {
		this.opts = opts;
	}

	public VoteInfo() {
		super();
	}

	public VoteInfo(String title, Integer type, List<Option> opts) {
		super();
		this.title = title;
		this.type = type;
		this.opts = opts;
	}
}
