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
var isIE = !!document.all;
function AddOption()
{
	var voteoptions = document.getElementById("voteoptions");
	var _p = document.createElement("p");
	var _input = document.createElement("input");
	_input.type = "text";
	_input.className = "input-text";
	_input.setAttribute("name", "options");
	_p.appendChild(_input);
	var _a = document.createElement("a");
	_a.className = "del";
	_a.setAttribute("href", "javascript:;");
	if(isIE) {
		_a.attachEvent("onclick", DelOption);
	} else {
		_a.addEventListener("click", DelOption, false);
	}
	_a.appendChild(document.createTextNode("删除"));
	_p.appendChild(_a);
	voteoptions.appendChild(_p);
}
function DelOption(e)
{
	if(!e) e = window.event;
	var a = e.srcElement || e.target;
	var obj = a.parentNode;
	obj.parentNode.removeChild(obj);
}

function addOptions(){
	var title=$.trim( $("#title").val() );
	if(title==""){
		return;
	}
	$.post("votingInfo",$("#myform").serialize(),function(data){
		data=parseInt( $.trim(data) );
		if(data!=0){
			location.href="manager.jsp";
		}else{
			alert("账号或密码错误，请确认后重新登录...");
		}
	},"text")
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
		<form method="post" action="">
			<input type="text" name="keywords" class="input-text" value=""/><input type="submit" name="submit" class="input-button" value="" />
		</form>
	</div>
</div>

<div id="voteManage" class="box">
	<h2>添加新投票</h2>
	<div class="content">
	<form method="post" action="" id="myform">
			<dl>
				<dt>投票内容：</dt>
				<dd>
				   <input type="hidden" name="op" value="addOption"/>
				   <input type="text" class="input-text" name="title"  value="" id="title"/>
				</dd>
				<dt>投票类型：</dt>
				<dd>
		  		   <input type="radio" name="type"  checked="checked" value="1" />单选
				   <input type="radio" name="type" value="2" />多选
				</dd>
				<dt>投票选项：</dt>
				
				<dd id="voteoptions">
					<p><input type="text" class="input-text" name="options" /></p>
					<p><input type="text" class="input-text" name="options" /></p>
				</dd>
				
				
				<dt></dt>
				<dd class="button" style="height:40px;padding-top:20px">
					<a href="javascript:addOptions()" 
					style="background:url('images/button_submit.gif') no-repeat;display:inline-block;float:left;width:76px;height:31px;"></a>
					<a href="javascript:;" onclick="AddOption()" style="float:left;;line-height:31px">增加选项</a>
					<a href="#" style="display:inline-block;height:31px;line-height:31px;float:left;">取消操作</a>
				</dd>
			</dl>
		</form>
	</div>
</div>
<div id="footer" class="wrap">
	源辰信息 &copy; 版权所有
</div>
</body>
</html>