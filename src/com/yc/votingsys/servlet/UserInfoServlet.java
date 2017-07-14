package com.yc.votingsys.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.DBObject;
import com.yc.votingsys.bean.UserInfo;
import com.yc.votingsys.dao.DBHelper;

public class UserInfoServlet extends BaseServlet{
	private static final long serialVersionUID = 9074821612672838222L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		String op=req.getParameter("op");

		if("userReg".equals(op)){ //用户注册
			userReg(req,resp);
		}else if("userLogin".equals(op)){ //用户登录
			userLogin(req,resp);
		}
	}

	private void userLogin(HttpServletRequest req, HttpServletResponse resp) {
			Integer usid=Integer.parseInt(req.getParameter("usid"));
			String pwd=req.getParameter("pwd");
			
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("_id",usid);
			map.put("pwd",pwd);
			
			DBHelper db=new DBHelper();
			DBObject obj=db.find(map,"users");
			
			int result=0;
			if(obj!=null){
				result=1;
				req.getSession().setAttribute("currentLoginUser", obj);
			}
			
			try {
				this.out(resp, result);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	

	/**
	 * 用户注册
	 * @param req
	 * @param resp
	 */
	private void userReg(HttpServletRequest req, HttpServletResponse resp) {
		String usid=req.getParameter("usid");
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");

		UserInfo uf=new UserInfo(Integer.parseInt(usid),uname,pwd);
		DBHelper db=new DBHelper();
		int result=db.addObject(uf.getUserInfoToMap(),"users");
		try {
			this.out(resp, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
