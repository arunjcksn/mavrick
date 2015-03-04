// JavaScript Document
function changeincr(s)
{
	var cnt;
	cnt= parseInt(document.getElementById('incr').value);
	if (s=="prev")
	cnt--;
	else
	cnt++;
	
	if (cnt==0)
	cnt=99;
	if (cnt>99)
	cnt=1;
	document.getElementById('incr').value=cnt;
}
function changeincr2(s)
{
	var cnt;
	cnt= parseInt(document.getElementById('incr2').value);
	if (s=="prev")
	cnt--;
	else
	cnt++;
	
	if (cnt==0)
	cnt=99;
	if (cnt>99)
	cnt=1;
	document.getElementById('incr2').value=cnt;
}
function changeincr3(s)
{
	var cnt;
	cnt= parseInt(document.getElementById('incr3').value);
	if (s=="prev")
	cnt--;
	else
	cnt++;
	
	if (cnt==0)
	cnt=99;
	if (cnt>99)
	cnt=1;
	document.getElementById('incr3').value=cnt;
}
function changeincr4(s)
{
	var cnt;
	cnt= parseInt(document.getElementById('incr4').value);
	if (s=="prev")
	cnt--;
	else
	cnt++;
	
	if (cnt==0)
	cnt=99;
	if (cnt>99)
	cnt=1;
	document.getElementById('incr4').value=cnt;
}
