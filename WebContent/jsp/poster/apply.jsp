<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请海报页面</title>
<!--用百度的静态资源库的cdn安装bootstrap环境-->
<!-- Bootstrap 核心 CSS 文件 -->
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
<!--font-awesome 核心我CSS 文件-->
<link
	href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- 在bootstrap.min.js 之前引入 -->
<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
<!-- Bootstrap 核心 JavaScript 文件 -->
<script
	src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
function checkForm(){
	 //trigger 事件执行完后，浏览器会为submit按钮获得焦点
    $("input").trigger("blur");
	$("textArea").trigger("blur");
	$("#posterPic").trigger("change");
    var numError = $("b.error").length;
    if(numError){
        return false;
    }else return true;	
}


$(document).ready(function(){
//显示图片
$("#posterPic").change(function(){
	var size = this.files.length;
	if(size != 2){
		$("#show").empty();
		$("#show").append("<b class='error' style='color:red'>请一次选择两张图片</b>");
	}else {
		$("#show").empty();
		for(var i = 0;i<size;i++){
		var objUrl = getObjectURL(this.files[i]) ;
		var $img = $("<img style='float:left;margin-right:5px;border:1px gray solid;' width='100px' height='100px' src='"+objUrl+"'/>");
		$("#show").append($img);
	  }
	}
}) ; 

//得到上传图片的路径
function getObjectURL(file) {
  var url = null;
  if (window.createObjectURL != undefined) { // basic
      url = window.createObjectURL(file);
  } else if (window.URL != undefined) { // mozilla(firefox)
      url = window.URL.createObjectURL(file);
  } else if (window.webkitURL != undefined) { // webkit or chrome
      url = window.webkitURL.createObjectURL(file);
  }
  return url;
}

//进行表单数据校验
//校验真实姓名
$("#posterUser").blur( function () {
    var $msg = $("#msg_name");
    var nameVal = $("#posterUser").val();
    var regName = /^[0-9]+$/;
        if(nameVal == "" || regName.test(nameVal)){
            var errorMsg = "请填写真实姓名!";
            $msg.empty();
            $msg.append("<b class='error'>"+errorMsg+"</b>");
        }else {
        	$msg.empty();
        }
  } );
 //校验社团组织 
$("#org").blur( function () {
    var $msg = $("#msg_org");
    var orgVal = $("#org").val();
    var regName = /^[0-9]+$/;
        if(orgVal == "" || regName.test(orgVal)){
            var errorMsg = "请填写真实名字!";
            $msg.empty();
            $msg.append("<b class='error'>"+errorMsg+"</b>");
        }else{
            $msg.empty();
        }
  } );
	
//校验手机
$("#posterPhone").blur( function () {
   var $msg = $("#msg_phone");
   var phoneVal = $("#posterPhone").val();
   var regName = /^[\d]{5,20}$/;
       if(phoneVal == "" || phoneVal.length != 11 || !regName.test(phoneVal)){
           var errorMsg = "请输入有效手机号码！";
           $msg.empty();
           $msg.append("<b class='error'>"+errorMsg+"</b>");   
       }else{
           $msg.empty();
       }
 } );
 
//校验楼栋号
$("#posterLocation").blur( function () {
   var $msg = $("#msg_location");
   var locationVal = $("#posterLocation").val();
   var regName = /泰山区-[1-9]\d*栋/;
       if(locationVal == "" || !regName.test(locationVal)){
           var errorMsg = "请按格式填写楼栋！";
           $msg.empty();
           $msg.append("<b class='error'>"+errorMsg+"</b>");   
       }else{
           //异步请求,判断该楼栋是否位置已满
           $.post("<c:url value='/poster/getPosterNumber.action'/>", { "poster_location": locationVal },
        		   function(num){
               $msg.empty();
        	   if(num >= 10){
        	   var errorMsg = "该楼栋位置已满,请重新选择";
        	   $msg.append("<b class='error'>"+errorMsg+"</b>");     
        	   }
        	}, "json");
       }
 } );
 
//校验天数
$("#posterTime").blur( function () {
   var $msg = $("#msg_time");
   var timeVal = $("#posterTime").val();
   var regName = /^[1-7]{1}$/;
       if(timeVal == "" ||!regName.test(timeVal)){
           var errorMsg = "最多不超过7天！";
           $msg.empty();
           $msg.append("<b class='error'>"+errorMsg+"</b>");   
       }else{
           $msg.empty();
       }
 } );
 
//校验海报内容 
$("#posterContent").blur( function () {
	var $msg = $("#msg_content");
    var contentVal = $("#posterContent").val();
        if(contentVal == "" || contentVal.trim().length == 0){
            var errorMsg = "海报内容不能为空";
            $msg.empty();
            $msg.append("<b class='error'>"+errorMsg+"</b>");  
        }else{
            $msg.empty();
        }
  } );
 
});
</script>
</head>
<body>
<span>当前位置》》<span style="color: red"><b>申请海报</b></span></span>
   <div class="container">
		<div class="col-sm-offset-2 col-md-offset-2"
			style="margin-top: 20px">
		
			<form id="apply_form" class="form-horizontal text-center" role="form" action="<c:url value='/poster/applyPoster.action'/>" method="post"
			 enctype="multipart/form-data"	style="width: 600px; background-color: #FFFAFA; opacity: 0.9; padding-top: 1.875rem /* 30px */; border-radius: 10px;">
				<div class="form-group">
					<label for="posterUser" class="col-sm-2 control-label"><b
						style="color: red">*</b>申请人:</label>
					<div class="col-sm-4">
						<input name="posterExtend.poster_user" type="text" class="form-control"
							id="posterUser" placeholder="请务必填写真实名字" value="">
						<span id="msg_name" style="font-size: 5px;color:red"></span>
					</div>
					<label for="org" class="col-sm-2 control-label"><b
						style="color: red">*</b>组织/社团:</label>
					<div class="col-sm-4">
						<input name="posterExtend.poster_org" type="text" class="form-control"
							id="org" placeholder="请务必填写真实名字" value="">
						<span id="msg_org" style="font-size: 5px;color:red"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="posterPhone" class="col-sm-2 control-label"><b
						style="color: red">*</b>联系电话:</label>
					<div class="col-sm-4">
						<input name="posterExtend.poster_phone" type="text" class="form-control"
							id="posterPhone" placeholder="请务必填写有效电话" value="">
					    <span id="msg_phone" style="font-size: 5px;color:red"></span>
					</div>
					<label for="posterLocation" class="col-sm-2 control-label"><b
						style="color: red">*</b>粘贴楼栋:</label>
					<div class="col-sm-4">
						<input name="posterExtend.poster_location" type="text" class="form-control"
							id="posterLocation" placeholder="如:泰山区-xx栋" value="">
						<span id="msg_location" style="font-size: 5px;color:red"></span>
					</div>
				</div>
				<div class="form-group">
				   <label for="posterTime" class="col-sm-2 control-label"><b
						style="color: red">*</b>粘贴天数:</label>
					<div class="col-sm-3 text-left">
                          <input id="posterTime" type="text" class="form-control"  name="posterExtend.poster_time" placeholder="不超过7天">
					      <span id="msg_time" style="font-size: 5px;color:red"></span>
					</div>
					<span  class="col-sm-1 help-block text-left"
						 style="padding-left: 0px;">天</span>
					<label for="posterSupport" class="col-sm-2 control-label"><b
						style="color: red">*</b>有无赞助:</label>
					<div class="col-sm-4 text-left">
						<label class="radio-inline">
                          <input type="radio" value="1" name="posterExtend.poster_support" checked="checked">有
                        </label>
                        <label class="radio-inline">
                          <input type="radio" value="0" name="posterExtend.poster_support">无
                        </label>
					</div>
				</div>
				<div class="form-group">
					<label for="posterContent" class="col-sm-2 control-label"><b
						style="color: red">*</b>海报内容:</label>
					<div class="col-sm-10 text-left">
						<textarea name="posterExtend.poster_content" id="posterContent" class="form-control " style="resize:none" rows="5"></textarea>
						<span id="msg_content" style="font-size: 5px;color:red"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="posterPic" class="col-sm-2 control-label"><b
						style="color: red">*</b>相关材料:</label>
					<div class="col-sm-4 text-left">
						<input type="file" multiple="multiple"  id="posterPic" name="files">
					</div>
					<span  class="col-sm-6 help-block text-right"
						 >(海报图片与盖章后申请表图片)</span>
				</div>
				<div class="form-group">
				<label for="pic" class="col-sm-2 control-label"></label>
				<div class="col-sm-8 text-left" id="show" style="height: 100px;">
					
				</div>
				</div>
				<div class="form-group text-right">
				<input id="send" type="submit" value="提交" class="btn btn-success" onclick="return checkForm()"
							> <input type="reset"
							value="取消" class="btn btn-success">
				</div>
			</form>
		</div>
	</div>
</body>
</html>