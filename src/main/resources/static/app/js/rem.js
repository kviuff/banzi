//适配字体大小
function setFontSize(){
	var width = document.documentElement.clientWidth;
	var fontSize = (width / 750) * 100;
	document.getElementsByTagName("html")[0].style.fontSize = fontSize + "px";
}
//区别对待IOS和Android
function  isuserAgent(){
	var u = navigator.userAgent;
	var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
	var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	if(isiOS){
		$("header h3").html("");
	}
}
$(window).on("resize",setFontSize);
setFontSize();
isuserAgent();
