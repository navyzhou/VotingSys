package com.yc.votingsys.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息
 * @author navy
 */
public class UserInfo {
	private Integer usid;
	private String uname;
	private String pwd;
	
	/**
	 * 以map的方式返回当前用户信息
	 * @return
	 */
	public Map<String,Object> getUserInfoToMap(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("_id",usid);
		map.put("uname",uname);
		map.put("pwd",pwd);
		
		return map;
	}
	
	@Override
	public String toString() {
		return "UserInfo [usid=" + usid + ", uname=" + uname + ", pwd=" + pwd
				+ "]";
	}

	public UserInfo(Integer usid, String uname, String pwd) {
		super();
		this.usid = usid;
		this.uname = uname;
		this.pwd = pwd;
	}

	public UserInfo() {
		super();
	}

	public Integer getUsid() {
		return usid;
	}

	public void setUsid(Integer usid) {
		this.usid = usid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((uname == null) ? 0 : uname.hashCode());
		result = prime * result + ((usid == null) ? 0 : usid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (uname == null) {
			if (other.uname != null)
				return false;
		} else if (!uname.equals(other.uname))
			return false;
		if (usid == null) {
			if (other.usid != null)
				return false;
		} else if (!usid.equals(other.usid))
			return false;
		return true;
	}
}
