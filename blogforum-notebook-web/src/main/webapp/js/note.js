$(function(){
	$(".simplenote").click(function(){
		$(".noteRightInfo").load("simplenote");
	});
	
	
	
	//标签
	$('#tags').tagsInput({
		'autocomplete':{selectFirst:true,width:'10px',autoFill:true},
		   'height':'100%',
		   'width':'100%',
		   'interactive':true,
		   'defaultText':'添加标签...',
		   'delimiter': [',',';'],   // Or a string with a single delimiter. Ex: ';'
		   'removeWithBackspace' : true,
		   'minChars' : 0,
		   'maxChars' : 0, // if not provided there is no limit
		   'placeholderColor' : '#d1d1d1'
		});
	$(".tagsinput").css("border","0px");
	$(".tagsinput").css("padding","0px 0px");
	
	//富文本编辑器
	
	var E = window.wangEditor;
	var editor = new E('.noteEditer');
	editor.customConfig.uploadImgServer = '/upload';  // 上传图片到服务器
	editor.create();
	$(".w-e-text-container").css("height","100%");
	
	
});