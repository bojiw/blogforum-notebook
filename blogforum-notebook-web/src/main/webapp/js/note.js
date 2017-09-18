$(function(){

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
	var overallInsert;
	//上传图片时执行的命令
	editor.customConfig.customUploadImg = function (files, insert) {
		overallInsert = insert;
		if(files[0] == null){
			return;
		}
		//上传图片时显示过度图标
		var loading = layer.load(1, {shade: [0.1,'#fff']}); //0.1透明度的白色背景
	    var formData = new FormData();
	    formData.append('file', files[0]);
	    formData.append('key', currentTime() + files[0].name);
	    //上传图片到七牛云
	    updateqiniu(formData,loading);
	}

	
	
	//上传图片到七牛云
	function updateqiniu(formData,loading){
	    $.get('/qiniuyun/upload',null,function(data){
		   	 formData.append('token',data.data);
		   	 $.ajax({
		   	     url: 'http://upload.qiniu.com/',
		   	     type: 'POST',
		   	     cache: false,
		   	     data: formData,
		   	     processData: false,
		   	     contentType: false
		   	     
		   	 }).done(function(res) {
		   		 //关闭等待图标
		   		layer.close(loading);
		   		overallInsert("http://ouqhxmwfh.bkt.clouddn.com/"+res.key)
		   	 }).fail(function(res) {
		   		layer.close(loading);
		   		layer.msg("上传失败请重试！！！");
		   	 });
		    },null,null);   
		};
	editor.create();
	$(".w-e-text-container").css("height","100%");
	
    //绑定粘贴事件图片上传七牛云
	document.getElementById("editor").addEventListener('paste', function(event){
		var event = event || window.event;
		var text = paste(event);
	});
    //粘贴图片上传七牛云
	function paste(event) {
		var clipboardData = event.clipboardData || window.clipboardData;
	    var items, item, types;
	    if (clipboardData) {
	        items = clipboardData.items;
	        if (!items) {
	            return;
	        }
	        // 保存在剪贴板中的数据类型
	        types = clipboardData.types || [];
	        for (var i = 0; i < types.length; i++) {
	            if (types[i] === 'Files') {
	                item = items[i];
	                break;
	            }
	        }
	       // 判断是否为图片数据
	        if (item && item.kind === 'file' && item.type.match(/^image\//i)) {
	            // 读取该图片
	            var file = item.getAsFile();
				//上传图片时显示过度图标
	            var loading = layer.load(1, {shade: [0.1,'#fff']}); //0.1透明度的白色背景
			    var formData = new FormData();
			    formData.append('file', file);
			    //如果文件名为空代表是通过截图粘贴 则明明为随机字符串
			    if(!file.name){
			    	file.name = Math.random().toString(36).substr(2);
			    }
			    formData.append('key', currentTime() + file.name);
			    updateqiniu(formData,loading); 

       		}

	    }
	}
	//获取时间
	function currentTime(){
		var d = new Date(),
		 str = '';
		 str += d.getFullYear();
		 str  += d.getMonth() + 1;
		 var a = str + "/";
		 str  += d.getDate();
		 str += d.getHours(); 
		 str  += d.getMinutes(); 
		str+= d.getSeconds(); 
		return a + str;
	}

	
});
