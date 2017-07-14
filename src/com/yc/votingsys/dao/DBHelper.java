package com.yc.votingsys.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

public class DBHelper {
	private static Mongo  mongo=null;
	private DB db=null;
	
	
	static{
		try {
			mongo=new Mongo(MyPro.getInstance().getProperty("ip"),
					Integer.parseInt(MyPro.getInstance().getProperty("port")));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @param dbName
	 * @return
	 */
	public void getDb(String dbName){
		if(dbName!=null && !"".equals(dbName)){
			db=mongo.getDB(dbName);
		}else{
			db=mongo.getDB(MyPro.getInstance().getProperty("dbName"));
		}
	}
	
	/**
	 * 关闭连接的方法
	 * @param db
	 */
	public void close(DB db){
		if(db!=null){
			db.requestDone();
		}
	}
	
	/**
	 * 获取集合的方法
	 * @param collectionName
	 * @param dbName
	 * @return
	 */
	public DBCollection getDBCollection(String collectionName,String dbName){
		getDb(null);
		if(collectionName!=null && !"".equals(collectionName)){
			return db.getCollection(collectionName);
		}else{
			return db.getCollection(MyPro.getInstance().getProperty("collectionName"));
		}
	}
	
	/**
	 * 添加对象的方法
	 * @param map
	 * @param collectionName
	 * @return
	 */
	public int addObject(Map<String,Object> map,String collectionName){
		WriteResult result=null;
		try {
			DBCollection collection=this.getDBCollection(collectionName,null);
			result=collection.save(new BasicDBObject(map));
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.close(db);
		}
		return result.getN();
	} 
	
	/**
	 * 查询单个对象
	 * @param map
	 * @param collectionName
	 * @return
	 */
	public DBObject find(Map<String,Object> map,String collectionName){
		DBObject object=null;
		
		try {
			DBCollection collection=this.getDBCollection(collectionName,null);
			if(map!=null && map.size()>0){
				object=collection.findOne(new BasicDBObject(map));
			}else{
				collection.findOne();
			}
		} catch (MongoException e) {
			e.printStackTrace();
		}finally{
			this.close(db);
		}
		return object;
	}
	
	/**
	 * 查询多个
	 * @param map
	 * @param collectionName
	 * @return
	 */
	public List<DBObject> findAll(Map<String,Object> map,String collectionName){
		List<DBObject> objs=new ArrayList<DBObject>();
		try {
			DBCollection collection=this.getDBCollection(collectionName,null);
			DBCursor c=null;
			if(map!=null && map.size()>0){
				c=collection.find(new BasicDBObject(map));
			}else{
				c=collection.find();
			}
			
			while(c.hasNext()){
				objs.add(c.next());
			}
		} catch (MongoException e) {
			e.printStackTrace();
		}finally{
			this.close(db);
		}
		return objs;
	}
	
	/**
	 * 修改
	 * @param map
	 * @param params
	 * @param collectionName
	 * @return
	 */
	public int update(Map<String,Object> map,Map<String,Object> params,String collectionName){
		WriteResult result=null;
		try {
			DBCollection collection=this.getDBCollection(collectionName,null);
			result=collection.update(new BasicDBObject(map),new BasicDBObject(params));
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.close(db);
		}
		return result.getN();
	}
}
