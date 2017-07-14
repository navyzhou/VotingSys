package com.yc.votingsys.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * @author navy
 */
public class MyPro extends Properties{
	private static final long serialVersionUID = 1359733105485885147L;
	private static MyPro instance=new MyPro();
	
	private MyPro(){
		InputStream is=MyPro.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			this.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static MyPro getInstance(){
		return instance;
	}
}
