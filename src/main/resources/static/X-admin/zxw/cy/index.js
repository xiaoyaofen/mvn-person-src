$(function(){
	//移入首页9块课程库，图标上移；移出后，图标下移
	$('.courseBg').mouseenter(function(){
		$(this).find('img').animate({marginTop:"30px"});
	}).mouseleave(function(){
		$(this).find('img').animate({marginTop:"50px"});
	});
	/*-----------------------课程晋级喜报-----------------------*/
	var levelUpLongCount = $('.levelLong').length;
	for(var liCount = 0;liCount<levelUpLongCount;liCount++){
		var levelEveryCount = $('.levelLong').eq(liCount).children().length;
		// $('.levelLong').eq(liCount).css('width',levelEveryCount*247+"px");
		setInterval("levelUpGo("+liCount+")",3000);
	}
	/*---------------------高校合作俱乐部---------------------*/
	//判断每个生成的banner的图片个数，如果大于4则显示左右按钮；否则隐藏
	//动态计算worksBannerLong的长度
	var imgCount = $('.schoolLong').find('.schoolJob').length;
	var bannerLength = imgCount*(275+5);
	$('.schoolLong').css('width',bannerLength+'px');
	if($('.schoolLong').find('.schoolJob').length<=4){
		$('.btnLeft').css('display','none');
		$('.btnRight').css('display','none');
	}
	//点击向右按钮
	$('.btnRight').click(function(){
		var imgCount =  $(this).prev().prev().find('.schoolJob').length;
		var index = parseInt($(this).prev().prev().attr('idx'));
		index++;
		if(index>imgCount-4){
			index = 0;
		}
		$(this).prev().prev().attr('idx',index);
		var left = index*(275+5);
		$(this).prev().prev().animate({left:-left+"px"},1000,'swing');
		
	});
	//点击向左按钮
	$('.btnLeft').click(function(){
		var imgCount =  $(this).prev().find('.schoolJob').length;
		var index = parseInt($(this).prev().attr('idx'));
		index--;
		if(index==-1){
			index = 0;
		}
		$(this).prev().attr('idx',index);
		var left = index*(275+5);
		$(this).prev().animate({left:-left+"px"},1000,'swing');
	});
	//-------------新闻资讯-----------------
	//判断每个生成的banner的图片个数，如果大于4则显示左右按钮；否则隐藏
		//动态计算worksBannerLong的长度
		var imgCount = $('.newsLong').children().length;
		var bannerLength = imgCount*(270+10);	
		$('.newsLong').css('width',bannerLength+'px')
		if($('.newsLong').find('.new_list').length<=4){
			$('.div_left').css('display','none');
			$('.div_right').css('display','none');
		}
	//点击向右按钮
	$('.div_right').click(function(){
		var imgCount =  $(this).prev().prev().children().eq(0).children().length;
		var index = parseInt($(this).prev().prev().children().eq(0).attr('idx'));
		index++;
		if(index>imgCount-4){
			index = 0;
		}
		$(this).prev().prev().children().eq(0).attr('idx',index);
		var left = index*(270+15);
		$(this).prev().prev().children().eq(0).animate({left:-left+"px"},1000,'swing');
		
	});
	//点击向左按钮
	$('.div_left').click(function(){
		var imgCount =  $(this).prev().children().eq(0).find('.new_list').length;
		var index = parseInt($(this).prev().children().eq(0).attr('idx'));
		index--;
		if(index==-(imgCount-4)){
			index = 0;
		}
		$(this).prev().children().eq(0).attr('idx',index);
		var left = index*(270+10);
		$(this).prev().children().eq(0).animate({left:-left+"px"},1000,'swing');
	});
	//-------------新闻资讯---------------
})

//-------------更多学校俱乐部-----------------
	//判断每个生成的banner的图片个数，如果大于4则显示左右按钮；否则隐藏
		//动态计算worksBannerLong的长度
$(function(){
		var imgCount = $('.newsLongs').children().length;
		var bannerLength = imgCount*(185+30);	
		$('.newsLongs').css('width',bannerLength+'px')
//		if(imgCount<=4){
//			$('.school_left').css('display','none');
//			$('.school_right').css('display','none');
//		}
	//点击向右按钮
	$('.school_right').click(function(){
		var imgCount =  $(this).prev().prev().children().eq(0).children().length;
		var index = parseInt($(this).prev().prev().children().eq(0).attr('idx'));
		index++;
		if(index>imgCount-4){
			index = 0;
		}
		$(this).prev().prev().children().eq(0).attr('idx',index);
		var left = index*(205+15);
		$(this).prev().prev().children().eq(0).animate({left:-left+"px"},1000,'swing');
		
	});
	//点击向左按钮
	$('.school_left').click(function(){
		var imgCount =  $(this).prev().children().eq(0).find('.newSchoolClub').length;
		var index = parseInt($(this).prev().children().eq(0).attr('idx'));
		index--;
		if(index==-(imgCount-4)){
			index = 0;
		}
		$(this).prev().children().eq(0).attr('idx',index);
		var left = index*(205+10);
		$(this).prev().children().eq(0).animate({left:-left+"px"},1000,'swing');
	});
})	
	//-------------更多学校俱乐部---------------
//当前是第几个课程，当前显示div下标
function levelUpGo(levelindex){
	var count = $('.levelLong').eq(levelindex).children().length;
	var idx = parseInt($('.levelLong').eq(levelindex).attr('idx'));
	idx++;
	if(idx>count-3){
		idx = 0;
	}
	var top = idx*(70);
	$('.levelLong').eq(levelindex).attr('idx',idx);
	$('.levelLong').eq(levelindex).animate({top:-top+"px"},2000,'swing');
}
//程序语言详情
$(function(){
 	$('.courseTop1').mouseenter(function(){
    	$(this).find('.videoDescribe').stop(false, true).animate({top:"40px"});
    }).mouseleave(function(){
    	$(this).find('.videoDescribe').stop(false, true).animate({top:"185px"});
    });
    $('.courseTop2').mouseenter(function(){
        $(this).find('.videoDescribe').stop(false, true).animate({top:"40px"});
    }).mouseleave(function(){
        $(this).find('.videoDescribe').stop(false, true).animate({top:"185px"});
    });
    $('.courseTop3').mouseenter(function(){
        $(this).find('.videoDescribe').stop(false, true).animate({top:"40px"});
    }).mouseleave(function(){
        $(this).find('.videoDescribe').stop(false, true).animate({top:"185px"});
    });
});
//俱乐部

$(function(){
 	$('.schoolCourseTop1').mouseenter(function(){
    	$(this).find('.schoolVideoDescribe').stop(false, true).animate({top:"40px"});
    }).mouseleave(function(){
    	$(this).find('.schoolVideoDescribe').stop(false, true).animate({top:"185px"});
    });
    $('.schoolCourseTop2').mouseenter(function(){
        $(this).find('.schoolVideoDescribe').stop(false, true).animate({top:"40px"});
    }).mouseleave(function(){
        $(this).find('.schoolVideoDescribe').stop(false, true).animate({top:"185px"});
    });
    $('.schoolCourseTop3').mouseenter(function(){
        $(this).find('.schoolVideoDescribe').stop(false, true).animate({top:"40px"});
    }).mouseleave(function(){
        $(this).find('.schoolVideoDescribe').stop(false, true).animate({top:"185px"});
    });
});

function courseDetial(a,b){
	if(!a){
		layer.msg("暂无数据");
	}else{
        var courseurl = "course_CourseRe_index_course?prodid="+a+"&circle_id="+b;
        parent.location.href =courseurl;
    }
//    circleCheck(courseurl,b);
}

function courseDetials(a,b){
	if(!a){
		layer.msg("暂无数据");
	}else{
        var courseurl = "course_CourseRe_indexClub_course?prodid="+a+"&circle_id="+b;
        parent.location.href =courseurl;
//        circleCheck(courseurl,b);
    }
//    circleCheck(courseurl,b);
}

function quickJoin(a,b){
//	if(!a){
//		layer.msg("暂无数据");
//	}else{
		var courseurl = "video_VideoRe_checkSessionAndCircles1_video?prodid="+a+"&circle_id="+b;
		circleChecks(courseurl,b);
//    }
//    circleCheck(courseurl,b);
}
function grade(b){
	parent.location.href ="schoolClub#"+b;
}
function welcome(a){
	top.layer.msg("敬请期待");
}
function circleCheck(surl,circleid) {
    $.ajax({
        type: "post",
        url: "video_VideoRe_checkSessionAndCirclesEnter_video?circle_id="+circleid,
        async:false,
        success: function (data) {
            if(data=="1"){
            	top.layer.msg("暂时无法进入该俱乐部");
            }else if(data == "0"){ //未登录
                top.layer.open({
                    type:2,
                    anim: 0,
                    title:false,
                    closeBtn:true,
                    area: ['550px', '325px'],
                    content:ctx+'/page/video/login.jsp'
                })
                //top.layer.msg("登录过期，请重新登录后再试");
            }else if(data=="2"){  //进入
            	top.layer.msg("你未加入该俱乐部，点击右侧入口加入我们吧！");	
            }else if(data == "3"){
            	 parent.location.href =surl;
//                top.layer.msg("请进入已加入的俱乐部");
			}
            else if(data == "4"){
            	top.layer.msg("选择对应校区的俱乐部进入");
//               top.layer.msg("请进入已加入的俱乐部");
			}
        },
        error: function () {
            top.layer.msg("系统异常，请稍后再试");
        }
    });
}

function circleChecks(surl,circleid) {
    $.ajax({
        type: "post",
        url: "video_VideoRe_checkSessionAndCirclesQuickJoin_video?circle_id="+circleid,
        async:false,
        success: function (data) {
        	 var newData=eval("("+data+")");
            if(data=="1"){//（初级圈子，或有权限）
            	top.layer.msg("暂时无法进入该俱乐部");
            }else if(data == "0"){ //未登录
                top.layer.open({
                    type:2,
                    anim: 0,
                    title:false,
                    closeBtn:true,
                    area: ['550px', '325px'],
                    content:ctx+'/page/video/login.jsp'
                })
                //top.layer.msg("登录过期，请重新登录后再试");
            }else if(data=="2"){  
            	 top.layer.msg("你未加入任何俱乐部，请选择左边俱乐部进入");
//				top.layer.open({
//                    type:2,
//                    anim: 0,
//                    title:false,
//                    closeBtn:true,
//                    area: ['300px', '180px'],
//                    content:'video_VideoRe_gradeOption_video?circle_id='+circleid
//                })
//					 $("#option").css({"display":"block"});
           }else if(newData.index=="3"){
        	   top.layer.msg("你未申请,请前往申请");
           }else if(newData.index=="4"){
               parent.location.href ="course_CourseRe_indexClub_course?prodid="+newData.prodid+"&circle_id="+newData.circle_id;
           }else if(data=="5"){
        	   top.layer.msg("系统异常，请联系管理员");
           }
//           else{       	   
//           	var courseurl = "course_CourseRe_index1_course?prodid="+data.prodid;
//           	alert(data.prodid);
//       	        parent.location.href =courseurl;
//           top.layer.msg("请进入已加入的俱乐部");
//		}
        },
        error: function () {
            top.layer.msg("系统异常，请稍后再试");
        }
    });
}

$('#yes').click(function(){
	if($('#select').val()=="0"){
		top.layer.msg("请选择一个俱乐部");
		return;
	}
//	alert($('#select').val());
	var courseurl = "video_VideoRe_applyCircle_video?prodid="+1+"&circle_id="+$('#select').val();
	$.ajax({
		type : "post",
		url :courseurl,
		cache : false,
		success : function(data) {
			if(data == "1"){
				top.layer.msg("申请成功");
				//window.parent.location.reload();
                var index = top.layer.getFrameIndex(window.name);
                top.layer.close(index);
            }
	},
	error : function() {
		layer.tips('服务器没有返回数据，可能服务器忙，请重试', '吸附元素选择器', {
			  tips: [1, '#3595CC'],
			  time: 2000
			});
	}});	
});
$('#no').click(function (){ 
	var index = top.layer.getFrameIndex(window.name);
    top.layer.close(index);
})
