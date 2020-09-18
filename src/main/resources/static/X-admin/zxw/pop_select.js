﻿// ==========================
//     Copyright BOOJOB
//===========================

//下拉列表Select
function shwSelect(menuID,showID,Forminput)
{
    var Form = "#myForm";
	$(menuID).click(function(){
		$(menuID).blur();
		$(menuID).parent("div").css("position","relative");
		$(showID).slideToggle("fast");
		
		//生成背景
		$(menuID).parent("div").before("<div class=\"inpListbg\"></div>");
		$(".inpListbg").height($(document).height());
		$(".inpListbg").css({ width: $(document).width(), position: "absolute", left: "0", top: "0" , "z-index": "0", "background-color": "#ffffff"});
		$(".inpListbg").css("opacity","0");
		//生成背景结束
		
		$(showID+" li").live("click",function(){
			$(menuID).val($(this).attr("title"));
			//$(Forminput).val($(this).attr("value"));
			$(Forminput).val($(this).attr("value"));
			$(".inpListbg").hide();
			$(showID).hide();
			$(menuID).parent("div").css("position","");	
			$(this).css("background-color","");			
					if (Form && Forminput)
					{
					    $(Form).validate().element(Forminput);
					}
		});

				$(".inpListbg").click(function(){
					$(".inpListbg").hide();
					$(showID).hide();
					$(menuID).parent("div").css("position","");
				});
				
				
		$(showID+" li").unbind("hover").hover(
		function()
		{
		$(this).css("background-color","#EAF7FD");
		},
		function()
		{
		$(this).css("background-color","");

		}
		);
	});
}