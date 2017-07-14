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
	$.post("votingInfo",{op:'findVoteById',vid:vid},function(data){
		var str='<h4>'+data.title+'</h4><p class="info">共有 '+Object.getOwnPropertyNames(data.opts).length+' 个选项，已有 '+data.usid.length+' 网友参与了投票。</p>';
		str+='<ol>';
		if(data.type==1){ //说明是单选
			for(var opt in data.opts){
				str+='<li><input type="radio" name="options" value="'+opt+'"/>'+opt+'</li>';
			}
		}else{ //说明是多选
			for(var opt in data.opts){
				str+='<li><input type="checkbox" name="options" value="'+opt+'"/>'+opt+'</li>';
			}
		}
		str+='</ol><p class="voteView"><input type="image" src="images/button_vote.gif" onclick="return voteOption(\''+data._id+'\','+data.type+')"/>';
		str+='<a href="view.jsp#'+data._id+'" target="_blank"><img src="images/button_view.gif" /></a></p>';
		
		$("#voteInfo").append($(str));
		
	},"json");
});

function voteOption(vid,type){
	var opts="";
	if(type==1){ //说明是单选
		opts=$("input:radio:checked").val();
	}else{ //多选
		$.each($("input:checkbox"),function(index,item){
			if(item.checked==true){
				opts+=item.value+",";
			}
		});
		opts=opts.substring(0,opts.lastIndexOf(","));
	}

	$.post("votingInfo",{op:"voteOption",vid:vid,type:type,opts:opts},function(data){
		data=parseInt( $.trim(data) );
		if(data==-1){
			alert("请先登录...");
			location.href="login.html";
		}else if(data==1){
			location.href="manager.jsp";
		}else{
			alert("投票失败，请稍后重试...");
		}
	},"text");

	return false;
}
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
		<form method="post" action="#">
			<input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
</div>

<div id="vote" class="wrap">
	<h2>参与投票</h2>
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
