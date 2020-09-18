// ==========================
//     Copyright BOOJOB
//===========================


//申请、收藏、浏览职位等操作按扭的事件
function JobFun(Param)
{
	var num = 0;
	var Text = "";
	var elem = myForm.elements;
	var Url;
	
	for(i=0;i<elem.length;i++)
	{   
	    //判断页面的控件为checkbox控件
		if(elem[i].type=='checkbox' && elem[i].id != 'chk_All')
		{
			if(elem[i].checked == true)
			{			
				Text += "," + elem[i].value;
				num += 1;
			}
		}		
	}
	
	if( num == 0 )
	{
		if( Param==1 ){ alert("请选择要申请的职位！"); }
		if( Param==2 ){ alert("请选择要收藏的职位！"); }
		if( Param==3 ){ alert("请选择要浏览的职位！"); }
		
		return false;
	}
	else
	{   
		//申请职位
		if( Param==1 ){ Url = "../Resume/Res_ToApply.aspx?Jobid="+ Text.substring(1);}
		
		//收藏职位
		if( Param==2 ){ Url = "../Resume/Res_ToFavorite.aspx?Jobid="+ Text.substring(1) + "&Prev=2";}
		
		//浏览职位
		if( Param==3 ){ Url = "../Company/Browse/JobMultiList.aspx?Param="+ Text.substring(1);}
		
		window.open(Url);
	}	
}

//全选/取消全选
function SetAllChecked(tempControl)
{
	var xStatus = tempControl.checked;
	elem = tempControl.form.elements;
	for(i=0;i<elem.length;i++)
	{
		if(elem[i].type=='checkbox' && elem[i].id != xStatus.id)
		{
			if(elem[i].checked != xStatus)
				elem[i].click();
		}
	}
}