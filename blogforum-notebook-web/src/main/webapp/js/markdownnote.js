
$(function() {
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
	
        var editor = editormd("editormd", {
            path : "js/md/lib/", // Autoload modules mode, codemirror, marked... dependents libs path
			width   : "100%",
            height  : "100%",
	        toolbarIcons : function() {
	            // 自定义工具类 如果在中间加,"||",代表右边的会显示在右边
	            return ["undo", "redo", "|", "bold", "hr", "quote", "|", "list-ul", "list-ol", "hr", "|", "link", "myimage", "preformatted-text", "code-block", "table", "html-entities", "|", "watch","preview","search","help"]
	        },
	        //自定义上传图片
	        toolbarCustomIcons : {
	            myimage   : "<a title='上传图片' unselectable='on'><i class='fa fa-picture-o' onclick='upimage()' unselectable='on'></i></a>"
	        },
	        tocm: true,
	        tex: true,                   // 开启科学公式TeX语言支持，默认关闭
	        flowChart: true,             // 开启流程图支持，默认关闭
	        sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
            //启动本地图片上传功能
			imageUpload:true,
			imageFormats   : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
			saveHTMLToTextarea : true
        });
        
        //绑定粘贴事件图片上传七牛云
		document.getElementById("editormd").addEventListener('paste', function(event){
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
				    $.get('/qiniuyun/upload',null,function(data){
				   	 formData.append('token',data.data);
				   	 //上传七牛云
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
				   		var qiniuUrl = '![](http://ouqhxmwfh.bkt.clouddn.com/' + res.key + ')';
				   	 	editor.insertValue(qiniuUrl);
				   	 }).fail(function(res) {
				   		layer.close(loading);
				   		layer.msg("上传失败请重试！！！");
				   	 });
				    },null,null);   
		            
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
		$('#updateimage').change(function(e){
			var f = document.getElementById("updateimage").files;
			var file = f[0];
			var loading = layer.load(1, {shade: [0.1,'#fff']}); //0.1透明度的白色背景
		    var formData = new FormData();
		    formData.append('file', file);
		    //如果文件名为空代表是通过截图粘贴 则明明为随机字符串
		    if(!file.name){
		    	file.name = Math.random().toString(36).substr(2);
		    }
		    formData.append('key', currentTime() + file.name);
		    $.get('/qiniuyun/upload',null,function(data){
		   	 formData.append('token',data.data);
		   	 //上传七牛云
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
		   		var qiniuUrl = '![](http://ouqhxmwfh.bkt.clouddn.com/' + res.key + ')';
		   	 	editor.insertValue(qiniuUrl);
		   	 }).fail(function(res) {
		   		layer.close(loading);
		   		layer.msg("上传失败请重试！！！");
		   	 });
		    },null,null);   
		})

		document.upimage=function() {
			var image = document.getElementById("updateimage");
			return image.click();

		}
		//保存笔记
		$("#saveMd").click(function(){
			var loading = layer.load(1, {shade: [0.1,'#fff']}); //0.1透明度的白色背景
			var md = editor.getMarkdown();       // 获取 Markdown 源码
			var html = editor.getHTML();           // 获取 Textarea 保存的 HTML 源码
			var context = $(html).text();
			var title  = $(".noteRightTitileText").val();
			var label = $("#tags").attr("value");
			var id = $("#noteId").attr("value");
			var type = "markdown";
			var textType = $("#textType").val();
			var noteBookId =$("#noteBookId").attr("value");
		    $.post("/note/updateNote",{
				id:id,
				noteTitle:title,
				noteBody:html,
				context:context,
		    	mdNoteBody:md,
		    	type:type,
		    	label:label,
		    	noteBookId:noteBookId,
		    	textType:textType
				},
				function(data) {
					if(data.status != "200") {
						layer.close(loading);
						layer.msg(data.msg);
					}else{
						layer.close(loading);
						layer.msg("保存成功")
					}
					
		    });
		});

});
