package com.yc.votingsys.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseServlet extends HttpServlet{
	private static final long serialVersionUID = -6634679942951198777L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	/**
	 * 以json的格式回送数据
	 * @param resp
	 * @param obj
	 * @throws IOException
	 */
	public void out(HttpServletResponse resp,Object obj) throws IOException{
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter pw=resp.getWriter(); //获取一个输出流
		
		Gson gson=new GsonBuilder().create();
		String json=gson.toJson(obj);
		pw.println(json);
		pw.flush();
		pw.close();
	}
	
	/**
	 * easyui data
	 * @param resp
	 * @param obj
	 * @throws IOException
	 */
	public void out(HttpServletResponse resp,Object obj,Integer total) throws IOException{
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter pw=resp.getWriter(); //获取一个输出流
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("total",total);
		map.put("rows",obj);
		
		Gson gson=new GsonBuilder().create();
		String json=gson.toJson(map);
		pw.println(json);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 回送一个标识符
	 * @param resp
	 * @param result
	 * @throws IOException
	 */
	public void out(HttpServletResponse resp,int result) throws IOException{
		PrintWriter pw=resp.getWriter(); //获取一个输出流
		pw.println(result);
		pw.flush();
		pw.close();
	}
}
