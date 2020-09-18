<!--
//scroll,imgResize,vcode,cookie,style
//滚屏脚本




//***
//图片缩小
function ImgLoad(obj)
{
	var imgs = obj.getElementsByTagName("img");
	for(var i=0;i<imgs.length;i++){
		var o=imgs[i];
		if(o.complete){
			ImgLoaded(o);
		}else{
			o.onload=function(){ImgLoaded(this);};
		}
		// 点图片下一页
		var gp = document.getElementById("goPage");
		if (gp && imgs.length==1 && o.width>200){
			o.style.cursor="pointer";
			if (o.resized=="1"){
				o.setAttribute("title","ctrl+鼠标左键原大小");
				o.onclick=function(eventTag){var evt=eventTag||window.event;evt.ctrlKey ? ImgSize(this) : pager.Next()};
			}else{
				o.onclick=function(){pager.Next()};
			}
		}
	}
}

function ImgLoaded(o){
	if (o.width>imgMaxWidth){
		if (o.style.width){
			o.style.width="";
		}
		o.owidth=o.width;	// 保留原大小
		o.oheight=o.height;
		o.width=imgMaxWidth;
		o.removeAttribute("height");
		o.setAttribute("title","点击原始大小查看");
		o.style.cursor="pointer";
		o.style.display="block";
		o.vspace=5;
		o.resized=1;
		if (typeof(o.onclick)!="function"){
			o.onclick=ImgClick;
		}
//			o.onmousewheel=bbimg;
	}
}

function ImgSize(oImg){
	if (oImg.parentElement){
		if (oImg.parentElement.tagName=="A"){
			return;
		}
	}
	var imgDiv,bgDiv,bodyH,bodyW,winW,winH;
	bgDiv=document.getElementById("iwmsBImg");
	imgDiv=document.getElementById("iwmsFImg");
	bodyH=document.documentElement.scrollHeight;
	bodyW=document.documentElement.scrollWidth;
	if (self.innerHeight) { // all except Explorer
		winW = self.innerWidth;
		winH = self.innerHeight;
	}else if(document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
		winW = document.documentElement.clientWidth;
		winH = document.documentElement.clientHeight;
	}else if(document.body) { // other Explorers
		winW = document.body.clientWidth;
		winH = document.body.clientHeight;
	}
	
	if (bgDiv==undefined){
		bgDiv=document.createElement("DIV");
		bgDiv.id="iwmsBImg";
		bgDiv.style.position="absolute";
		bgDiv.style.left="0px";
		bgDiv.style.top="0px";
//		bgDiv.style.filter="alpha(opacity=85)";
//		bgDiv.style.opacity="0.85";
		bgDiv.style.height=bodyH+"px";
		bgDiv.style.width=bodyW+"px";
		bgDiv.style.background="#000";
		document.body.appendChild(bgDiv);
	}
	if (imgDiv==undefined){
		imgDiv=document.createElement("DIV");
		imgDiv.id="iwmsFImg";
		imgDiv.style.position="absolute";
		imgDiv.style.top="10px";
		imgDiv.style.border="1px solid #666"
		imgDiv.style.background="#000";
		imgDiv.style.padding="8px"
		imgDiv.onclick=function(){this.style.display='none';document.getElementById("iwmsBImg").style.display='none';};
		imgDiv.onmousewheel=bbimg;
		imgDiv.title="ctrl+鼠标滚轮缩放\r\n点击图片隐藏";
		document.body.appendChild(imgDiv);
	}
	imgDiv.style.display="";
	bgDiv.style.display="";
	if(bodyW>oImg.owidth){
		imgDiv.style.left=(bodyW-oImg.owidth)/2+"px";
	}else{
		imgDiv.style.left="0px";
	}
	if(winH>oImg.oheight+22){
		imgDiv.style.top=(document.documentElement.scrollTop+(winH-oImg.oheight)/2-11)+"px";
	}else{
		imgDiv.style.top=(document.documentElement.scrollTop+10)+"px";
	}
	
	imgDiv.innerHTML="<img src='"+ oImg.src +"'/>";
}

function ImgClick()
{
	ImgSize(this);
}

//图片缩放
function bbimg()
{
	if (event.ctrlKey){
		var zoom=parseInt(this.style.zoom, 10)||100;
		zoom+=event.wheelDelta/12;
		if (zoom>0) this.style.zoom=zoom+'%';
		return false;
	}else{
		return true;
	}
}

//***
//评论验证
function VCode(tt)
{
	var s = "";
	var v = 15;
	if (tt.length>10){
		tt = tt.substr(0, 10);
	}
	for(var i=0; i<tt.length; i++){
		if (i==v || i==tt.length-1 && v>=tt.length){
			s += v + tt.charCodeAt(i);
		}else{
			s += tt.charCodeAt(i);
		}
	}
	return s;
}
//***



function setCookie(name, value)		//cookies设置
{
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	if(expires!=null){
		var LargeExpDate = new Date ();
		LargeExpDate.setTime(LargeExpDate.getTime() + (expires*1000*3600*24));
	}
	document.cookie = name + "=" + escape (value) + "; path=/;" +((expires == null) ? "" : (" expires=" +LargeExpDate.toGMTString()));
}

function getCookie(Name)			//cookies读取
{
	var search = Name + "=";
	if(document.cookie.length > 0){
		offset = document.cookie.indexOf(search);
		if(offset != -1)	{
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if(end == -1){
				end = document.cookie.length;
			}
			return unescape(document.cookie.substring(offset, end));
		}
	}
	return "";
}
//jeson

function n()
{
	if (document.getElementById("tn").value!="1"){
    return encodeURI("51zxw.net版权所有_hi，"+ document.getElementById("tn").value);}
  else {
  	 return encodeURI("我要自学网51zxw.net版权所有");}
}


function f()
{
	if (document.getElementById("tn").value!="1"){
  return true;}
  else {
  return false;}
}


try{var old = event.x
	  var old2= event.y}
catch(e){}
function play_video_36()
{
if (old==event.x && old2==event.y )
{
document.getElementById("jvv").style.zIndex=-1;
}
if (old>event.x)
{
document.getElementById("jvv").style.zIndex=3;
}
if (old<event.x)
{
document.getElementById("jvv").style.zIndex=3;
}

if (old2>event.y)
{
document.getElementById("jvv").style.zIndex=3;
}
if (old2<event.y)
{
document.getElementById("jvv").style.zIndex=3;
}
old = event.x
old2=event.y
}

//jeson
//***

// 内容样式用户定义
function ContentSize(size)
{
	var obj=document.getElementById("content");
	obj.style.fontSize=size>0 ? size+"px" : "";
	if (arguments.length==1){
		setCookie("iwmsFontSize",size,size==0?-1:1);
	}
}
function ContentBg(color)
{
	var obj=document.getElementById("bodyTd");
	obj.style.backgroundColor=color;
	if (arguments.length==1){
		setCookie("iwmsContBg",color,color.length==0?-1:1);
	}
}
function InitContStyle()
{
	var v=getCookie("iwmsFontSize");
	if (v.length>0){
		ContentSize(v,false);
	}
	v=getCookie("iwmsContBg");
	if (v.length>0){
		ContentBg(v,false);
	}
}


//***

-->