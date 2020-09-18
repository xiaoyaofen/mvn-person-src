
$(function(){
	 var curWwwPath = window.document.location.href;
	    //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
	    var pathName = window.document.location.pathname;
	    var pos = curWwwPath.indexOf(pathName);
	    //获取主机地址，如： http://localhost:8080
	    var localhostPath = curWwwPath.substring(0, pos);
	    //获取带"/"的项目名，如：/ems
	    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	    //获取项目的basePath   http://localhost:8080/ems/
	    var basePath=localhostPath+projectName+"/";
	    basePath=$("#basepath").val();
	$("#personal").click(function(){
		basePath=$("#basepath").val();
		window.location.href=basePath+"personal/info";
	});
	
	$("#logc").click(function(){
		/*layer.open({
			type :2,
			title : '',
			area : [ '470px', '345px' ],
			offset: [150],
			skin: 'logbg',
			fix : true, // 不固定
			shadeClose: true, 
			closeBtn: 2,
			content : '/cymooc/usersPersonal/login.jsp'
		});*/
		basePath=$("#basepath").val();
		window.location.href=basePath+"login/login_login";
	});
	$("#regc").click(function(){
		layer.open({
			type : 2,
			title : '',
			area : [ '470px', '330px' ],
			offset: [150],
			skin: 'regbg',
			fix : true, // 不固定
			shadeClose: true, 
			closeBtn: 2,
			content : basePath+'usersPersonal/userReg.jsp'
		});
	});
	
	$('.person_center').hover(function(){
		$(this).siblings('.pc_form').show();
		},
		function(){
			$(this).siblings('.pc_form').hide();
			});
    $('.pc_form').hover(function(){
    	$(this).show();},
    	function(){
    		$(this).hide();
    		});   
	
    $("#logout").click(function(){
    	layer.confirm('确认注销？', {
    		btn : [ '确定', '取消' ], //按钮
    		offset: [200],
    		shade : false
    		//不显示遮罩
    	}, function() {
    		
    		$.ajax({
    			type : "POST",
    			url : basePath+"login/login_loginOut",
    			cache : false,
    			error : function() {
    				layer.tips('服务器没有返回数据，可能服务器忙，请重试', '吸附元素选择器', {
    					tips: [1, '#3595CC'],
    					time: 2000
    				});
    			},
    			success : function(data) {
    				layer.msg(data);
    				if(data==1){
    					layer.msg("退出成功");
    					window.parent.location.href=basePath+"index.jsp";
    				}else{
    					layer.msg("退出失败请重试");
    				}
    			}
    		});
    		
    	}, function() {
    	});
    });
    
    
});



