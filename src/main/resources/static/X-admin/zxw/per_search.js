// ==========================
//     Copyright BOOJOB
//===========================


//���롢�ղء����ְλ�Ȳ�����Ť���¼�
function JobFun(Param)
{
	var num = 0;
	var Text = "";
	var elem = myForm.elements;
	var Url;
	
	for(i=0;i<elem.length;i++)
	{   
	    //�ж�ҳ��Ŀؼ�Ϊcheckbox�ؼ�
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
		if( Param==1 ){ alert("��ѡ��Ҫ�����ְλ��"); }
		if( Param==2 ){ alert("��ѡ��Ҫ�ղص�ְλ��"); }
		if( Param==3 ){ alert("��ѡ��Ҫ�����ְλ��"); }
		
		return false;
	}
	else
	{   
		//����ְλ
		if( Param==1 ){ Url = "../Resume/Res_ToApply.aspx?Jobid="+ Text.substring(1);}
		
		//�ղ�ְλ
		if( Param==2 ){ Url = "../Resume/Res_ToFavorite.aspx?Jobid="+ Text.substring(1) + "&Prev=2";}
		
		//���ְλ
		if( Param==3 ){ Url = "../Company/Browse/JobMultiList.aspx?Param="+ Text.substring(1);}
		
		window.open(Url);
	}	
}

//ȫѡ/ȡ��ȫѡ
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