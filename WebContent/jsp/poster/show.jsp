<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请详情</title>
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
$(function(){
    $(".img1").click(function(){
        var _this = $(this);//将当前的pimg元素作为_this传入函数
        imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
    });
});
 
function imgShow(outerdiv, innerdiv, bigimg, _this){
    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性
    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function(){
        var windowW = $(window).width();//获取当前窗口宽度
        var windowH = $(window).height();//获取当前窗口高度
        var realWidth = this.width;//获取图片真实宽度
        var realHeight = this.height;//获取图片真实高度
        var imgWidth, imgHeight;
        var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放
        if(realHeight>windowH*scale) {//判断图片高度
            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放
            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度
            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度
                imgWidth = windowW*scale;//再对宽度进行缩放
            }
        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度
            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放
            imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度   
        } else {//如果图片真实高度和宽度都符合要求，高宽不变
            imgWidth = realWidth;
            imgHeight = realHeight;
        }
 
        $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放
        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距
        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距
        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性
        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
 
    });
 
    $(outerdiv).click(function(){//再次点击淡出消失弹出层
        $(this).fadeOut("fast");
    });

}
</script>
</head>
<body>
<span>当前位置》》<a href="<c:url value='/poster/getAllRecords.action'/>">我的申请</a>》》</span><span style="color: red"><b>申请详情</b></span>
   <div class="container">
		<div class="col-sm-offset-2 col-md-offset-2"
			style="margin-top: 20px">
		
			<form id="show_form" class="form-horizontal text-center" role="form" 
			style="width: 600px; background-color: #FFFAFA; opacity: 0.9; padding-top: 1.875rem /* 30px */; border-radius: 10px;">
				<div class="form-group">
					<label for="posterUser" class="col-sm-2 control-label"><b
						style="color: red"></b>申请人:</label>
					<div class="col-sm-4">
						<input name="posterExtend.poster_user" type="text" class="form-control"
							readonly="readonly"  id="posterUser"  value="${param.poster_user }">
					</div>
					<label for="org" class="col-sm-2 control-label"><b
						style="color: red"></b>组织/社团:</label>
					<div class="col-sm-4">
						<input name="posterExtend.poster_org" type="text" class="form-control"
							readonly="readonly" id="org"  value="${param.poster_org }">
					</div>
				</div>
				<div class="form-group">
					<label for="posterPhone" class="col-sm-2 control-label"><b
						style="color: red"></b>联系电话:</label>
					<div class="col-sm-4">
						<input name="posterExtend.poster_phone" type="text" class="form-control"
							readonly="readonly" id="posterPhone"  value="${param.poster_phone }">
					</div>
					<label for="posterLocation" class="col-sm-2 control-label"><b
						style="color: red"></b>粘贴楼栋:</label>
					<div class="col-sm-4">
						<input name="posterExtend.poster_location" type="text" class="form-control"
							readonly="readonly" id="posterLocation"  value="${param.poster_location }">
					</div>
				</div>
				<div class="form-group">
				   <label for="posterTime" class="col-sm-2 control-label"><b
						style="color: red"></b>粘贴天数:</label>
					<div class="col-sm-3 text-left">
                          <input id="posterTime" value="${param.poster_time }" type="text" class="form-control" readonly="readonly" name="posterExtend.poster_time">
					</div>
					<span  class="col-sm-1 help-block text-left"
						 style="padding-left: 0px;">天</span>
					<label for="posterSupport" class="col-sm-2 control-label"><b
						style="color: red"></b>有无赞助:</label>
					<div class="col-sm-4 text-left">
						<label class="radio-inline">
                          <input type="radio" value="1" name="posterExtend.poster_support"  <c:if test='${param.poster_support eq 1 }'>checked</c:if> >有
                        </label>
                        <label class="radio-inline">
                          <input type="radio" value="0" name="posterExtend.poster_support" readonly="readonly" <c:if test='${param.poster_support eq 0 }'>checked</c:if>>无
                        </label>
					</div>
				</div>
				<div class="form-group">
			    </div>
			    <div class="form-group">
					<label for="posterContent" class="col-sm-2 control-label"><b
						style="color: red">*</b>海报内容:</label>
					<div class="col-sm-10 text-left">
						<textarea readonly="readonly" name="posterExtend.poster_content" id="posterContent" class="form-control " style="resize:none" rows="5">${param.poster_content }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="posterPic" class="col-sm-2 control-label"><b
						style="color: red">*</b>相关材料:</label>
					<span  class="col-sm-6 help-block text-right"
						 >(海报图片与盖章后申请表图片)</span>
				</div>
				<div class="form-group">
				<label for="pic" class="col-sm-2 control-label"></label>
				<div class="col-sm-8 text-left" id="show" style="height: 100px;">
					<img class="img1" style='float:left;margin-right:5px;border:1px gray solid;' width='100px' height='100px' src="${param.poster_pic }"/>
					<img class="img1" style='float:left;margin-right:5px;border:1px gray solid;' width='100px' height='100px' src="${param.poster_anotherpic }"/>
				</div>
				</div>
				<div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
                   <div id="innerdiv" style="position:absolute;">
                       <img id="bigimg" style="border:5px solid #fff;" src="" />
                  </div>
               </div>
			</form>
		</div>
	</div>
</body>
</html>