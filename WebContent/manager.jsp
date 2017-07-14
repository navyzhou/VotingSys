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
	$.post("votingInfo",{op:"findAllVoting"},function(data){
		var str="";
		$.each(data,function(index,item){
			if(index%2==0){
				str+='<li class="odd"><h4>';
			}else{
				str+='<li><h4>';
			}
			
			str+='<a href="#">'+item.title+'</a></h4>';
			
			if( $.inArray( parseInt($.trim($("#usid").val())),item.usid )==-1){
				str+='<div class="join"><a href="vote.jsp#'+item._id+'" target="_blank">我要参与</a></div>';
			}else{
				str+='<div class="join"><a href="view.jsp#'+item._id+'" target="_blank">查看结果</a></div>';
			}
			str+='<p class="info">共有 '+Object.getOwnPropertyNames(item.opts).length+' 个选项，已有 '+item.usid.length+' 个网友参与了投票。</p></li>';	
		});
		$("#datalist").append($(str));
	},"json");
});

</script>
</head>
<body>

<div id="header" class="wrap">
	<img src="images/logo.gif" />
</div>
<div id="navbar" class="wrap">
	<div class="profile">
		您好，${currentLoginUser.uname }
		<input type="hidden" value="${currentLoginUser._id }" id="usid"/>
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
	<h2>投票列表</h2>
	<ul class="list" id="datalist">
		
	</ul>
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
</body>
</html>