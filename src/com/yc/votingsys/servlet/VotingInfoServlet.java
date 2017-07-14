package com.yc.votingsys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.DBObject;
import com.yc.votingsys.bean.Option;
import com.yc.votingsys.bean.VoteInfo;
import com.yc.votingsys.dao.DBHelper;

public class VotingInfoServlet extends BaseServlet{
	private static final long serialVersionUID = 9074821612672838222L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		String op=req.getParameter("op");

		if("addOption".equals(op)){ //用户注册
			addOption(req,resp);
		}else if("findAllVoting".equals(op)){ //查询所有的投票主题
			findAllVoting(req,resp);
		}else if("findVoteById".equals(op)){ //查看指定的投票主题
			findVoteById(req,resp);
		}else if("voteOption".equals(op)){ //投票
			voteOption(req,resp);
		}
	}

	private void voteOption(HttpServletRequest req, HttpServletResponse resp) {
		Long vid=Long.parseLong(req.getParameter("vid"));
		Integer type=Integer.parseInt(req.getParameter("type"));
		String opts=req.getParameter("opts");
		Object object=req.getSession().getAttribute("currentLoginUser");
		int result;
		if(object==null){
			result=-1;
		}else{
			DBObject obj=(DBObject)object;
			Integer usid=Integer.parseInt(String.valueOf(obj.get("_id")));

			//查询条件的map
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("_id",vid);

			Map<String,Object> params=new HashMap<String,Object>();

			Map<String,Object> param1=new HashMap<String,Object>();
			param1.put("usid",usid);
			params.put("$addToSet",param1);

			Map<String,Object> param2=new HashMap<String,Object>();
			if(type==1){ //说明是单选
				param2.put("opts."+opts,1);
			}else{
				String[] strs=opts.split(",");
				for(String str:strs){
					param2.put("opts."+str,1);
				}
			}
			params.put("$inc",param2);
			DBHelper db=new DBHelper();

			result=db.update(map, params,"voting");
			if(result>0){
				result=1;
			}else{
				result=0;
			}
		}
		try {
			this.out(resp, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void findVoteById(HttpServletRequest req, HttpServletResponse resp) {
		Long vid=Long.parseLong( req.getParameter("vid") );

		Map<String,Object> map=new HashMap<String,Object>();
		map.put("_id",vid);
		DBHelper db=new DBHelper();
		DBObject obj=db.find(map,"voting");
		try {
			this.out(resp, obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void findAllVoting(HttpServletRequest req, HttpServletResponse resp) {
		DBHelper db=new DBHelper();
		List<DBObject> list=db.findAll(null, "voting");
		try {
			this.out(resp, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addOption(HttpServletRequest req, HttpServletResponse resp) {
		String title=req.getParameter("title");
		Integer type=Integer.parseInt(req.getParameter("type"));
		String[] opts=req.getParameterValues("options");

		List<Option> options=new ArrayList<Option>();
		for(String str:opts){
			options.add(new Option(str,0));
		}

		VoteInfo vote=new VoteInfo(title,type,options);
		DBHelper db=new DBHelper();
		int result=db.addObject(vote.getVoteInfoToMap(),"voting");
		try {
			this.out(resp, result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
