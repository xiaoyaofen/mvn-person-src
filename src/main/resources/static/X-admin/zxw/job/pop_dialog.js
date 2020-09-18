// ==========================
//     Copyright BOOJOB
//===========================

function div_Show(caption, url) {
	try {
		$("body").append("<div id='div_overlay'></div><div id='div_window'></div>");
		$(window).resize(div_position);
		$("body").append("<div id='div_load'><div id='div_loadContent'><img src='../images/pop/loading.gif' /></div></div>");
		$("#div_overlay").show();
		var urlString = /.aspx|.jpg|.jpeg|.png|.gif|.html/g;
		var urlType = url.match(urlString);
		
		if(urlType == '.aspx' || urlType == '.html')
		{
		    //显示页面
			var queryString = url.replace(/^[^\?]+\??/,'');
			var params = parseQuery( queryString );
			
			div_WIDTH = (params['width']*1) + 30;
			div_HEIGHT = (params['height']*1) + 40;
			ajaxContentW = div_WIDTH - 30;
			ajaxContentH = div_HEIGHT - 60;
			$("#div_window").append("<div id='div_closeAjaxWindow'><span>"+ caption +"</span><a href='#' id='div_closeWindowButton'><img src='../images/pop/close.gif' /></a></div><div id='div_ajaxContent' style='width:"+ajaxContentW+"px;height:"+ajaxContentH+"px;'></div>");
			$("#div_closeWindowButton").click(div_remove);
			
			$("#div_ajaxContent").load(url, function(){
			    div_position();
			    $("#div_load").remove();
			    $("#div_window").slideDown("normal");
			});
		}
		
		if(urlType == '.jpg' || urlType == '.jpeg' || urlType == '.png' || urlType == '.gif')
		{
            //显示图片
			var imgPreloader = new Image();
			imgPreloader.onload = function(){

			// Resizing large images added by Christian Montoya
			var de = document.documentElement;
			var x = (self.innerWidth || (de&&de.clientWidth) || document.body.clientWidth) - 50;
			var y = (self.innerHeight || (de&&de.clientHeight) || document.body.clientHeight) - 80;
			if(imgPreloader.width > x) { 
				imgPreloader.height = imgPreloader.height * (x/imgPreloader.width); 
				imgPreloader.width = x; 
				if(imgPreloader.height > y) { 
					imgPreloader.width = imgPreloader.width * (y/imgPreloader.height); 
					imgPreloader.height = y; 
				}
			} 
			else if(imgPreloader.height > y) { 
				imgPreloader.width = imgPreloader.width * (y/imgPreloader.height); 
				imgPreloader.height = y; 
				if(imgPreloader.width > x) { 
					imgPreloader.height = imgPreloader.height * (x/imgPreloader.width); 
					imgPreloader.width = x;
				}
			}
			// End Resizing
            
			div_WIDTH = imgPreloader.width + 16;
			div_HEIGHT = imgPreloader.height + 48;
			if(imgPreloader.width < 95){ div_WIDTH = 114; }
			if(imgPreloader.height < 80){ div_HEIGHT = 128; }
			
			$("#div_window").append("<div id='div_ImageContent' style='width:auto;height:auto;'><img id='div_Image' src='"+url+"' width='"+imgPreloader.width+"' height='"+imgPreloader.height+"' alt='"+caption+"'/>"+ "<div id='div_closeAjaxWindow'><span>"+caption+"</span><a href='#' id='div_closeWindowButton'><img src=../images/pop/close.gif /></a></div></div>"); 
			$("#div_closeWindowButton").click(div_remove);
			$("#div_Image").click(div_remove); // close when image clicked added by Christian Montoya
			div_position();
			$("#div_load").remove();
			$("#div_window").slideDown("normal");
			}
	  
			imgPreloader.src = url;
		}
		
	} catch(e) {
		alert( e );
	}
}


function div_remove() //关闭
{
	$("#div_window").fadeOut("fast",function(){$('#div_window,#div_overlay,#div_load').remove();}); 
	return false;
}

function div_position() {
	var de = document.documentElement;
	var w = self.innerWidth || (de&&de.clientWidth) || document.body.clientWidth;
	var h = self.innerHeight || (de&&de.clientHeight) || document.body.clientHeight;
  
  	if (window.innerHeight && window.scrollMaxY) {	
		yScroll = window.innerHeight + window.scrollMaxY;
	} else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
		yScroll = document.body.scrollHeight;
	} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
		yScroll = document.body.offsetHeight;
  	}
	
	$("#div_window").css({width:div_WIDTH+"px",height:div_HEIGHT+"px",
	left: ((w - div_WIDTH)/2)+"px", top: ((h - div_HEIGHT)/2)+"px" });
	$("#div_overlay").css("height",yScroll +"px");
}

function parseQuery ( query ) {
   var Params = new Object ();
   if ( ! query ) return Params; // return empty object
   var Pairs = query.split(/[;&]/);
   for ( var i = 0; i < Pairs.length; i++ ) {
      var KeyVal = Pairs[i].split('=');
      if ( ! KeyVal || KeyVal.length != 2 ) continue;
      var key = unescape( KeyVal[0] );
      var val = unescape( KeyVal[1] );
      val = val.replace(/\+/g, ' ');
      Params[key] = val;
   }
   return Params;
}
