<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>在线投票系统</title>
<link type="image/x-icon" href="images/yc.png" rel="shortcut icon"/>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript">
$(function(){
	var vid=location.hash.substring(1);
	
	$.post("votingInfo",{op:"findVoteById",vid:vid},function(data){
		var num=data.usid.length;
		var rate=0;
		var str="<h4>"+data.title+"</h4><p class='info'>共有 "+Object.getOwnPropertyNames(data.opts).length+" 个选项，已有  "+num+" 个网友参与了投票。</p><ol>";
		for(var item in data.opts){
			rate=Math.round((parseInt(data.opts[item])/num)*100);
			str+="<li>"+item+"<div class='rate'><div class='ratebg'><div class='percent' style='width:"+rate+"%'></div></div><p>"+data.opts[item]+"票<span>("+rate+"%)</span></p></div></li>";
		}
		str+='</ol><div class="goback"><a href="manager.jsp">返回投票列表</a></div>';
		
		$("#voteInfo").append( $(str) );
		
	},"json");
	
})
</script>
</head>

<body>
<div id="header" class="wrap">
	<img src="images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="profile">
		您好，${currentLoginUser.uname }
		<span class="return"><a href="manager.jsp">返回列表</a></span>
		<span class="addnew"><a href="add.jsp">添加新投票</a></span>
		<span class="modify"><a href="manager.jsp">维护</a></span>
	</div>
	<div class="search">
		<form method="post" action="Subject!search.action">
			<input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
</div>

<div id="vote" class="wrap">
	<h2>查看投票</h2>
	<ul class="list">
		<li id="voteInfo">
				
		</li>
	</ul>
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
</body>
</html>