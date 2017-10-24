$(function	()	{
	
	//scrollable sidebar
	$('.scrollable-sidebar').slimScroll({
		height: '100%',
		size: '0px'
	});
	
	//Collapsible Sidebar Menu
	$('.sidebar-menu .openable > a').click(function()	{
		
		if(!$('aside').hasClass('sidebar-mini') || Modernizr.mq('(max-width: 991px)'))	{
			if( $(this).parent().children('.submenu').is(':hidden') ) {
				$(this).parent().siblings().removeClass('open').children('.submenu').slideUp(200);
				$(this).parent().addClass('open').children('.submenu').slideDown(200);
			}
			else	{
				$(this).parent().removeClass('open').children('.submenu').slideUp(200);
			}
		}
		
		return false;
	});

	//Open active menu
	if(!$('.sidebar-menu').hasClass('sidebar-mini') || Modernizr.mq('(max-width: 767px)'))	{
		$('.openable.open').children('.submenu').slideDown(200);
	}
	
	//Toggle User container on sidebar menu
	$('#btn-collapse').click(function()	{
		$('.sidebar-header').toggleClass('active');
	});
	
	//theme setting
	$("#theme-setting-icon").click(function()	{ 
		if($('#theme-setting').hasClass('open'))	{
			$('#theme-setting').removeClass('open');
			$('#theme-setting-icon').removeClass('open');
		}
		else	{
			$('#theme-setting').addClass('open');
			$('#theme-setting-icon').addClass('open');
		}

		return false;
	});
	//点击菜单栏右侧弹出隐藏
	$('#sidebarToggleLG').click(function()	{
		if($('.wrapper').hasClass('display-right'))	{
			$('.wrapper').removeClass('display-right');
			$('.sidebar-right').removeClass('active');
		}
		else	{
			//$('.nav-header').toggleClass('hide');
			$('.top-nav').toggleClass('sidebar-mini');
			$("#noteLeft").css("width","");
			$('aside').toggleClass('sidebar-mini');
			if($('aside').hasClass('sidebar-mini')){
				$("#noteSplit").css("left","0px");
				$("#noteRight").css("padding-left","60px");
			}else{
				$("#noteSplit").css("left","240px");
				$("#noteRight").css("padding-left","240px");
			}
			
			$('footer').toggleClass('sidebar-mini');
			$('.main-container').toggleClass('sidebar-mini');
			$('.main-menu').find('.openable').removeClass('open');
			$('.main-menu').find('.submenu').removeAttr('style');
		}		
	});
	
	$('#sidebarToggleSM').click(function()	{
		$('aside').toggleClass('active');
		$('.wrapper').toggleClass('display-left');
	});
	
	$('.sidebarRight-toggle').click(function()	{
		$('.sidebar-right').toggleClass('active');
		$('.wrapper').toggleClass('display-right');
		$('footer').toggleClass('display-right');

		return false;
	});
	
	$('.dropdown-menu input').click(function(e) {
        e.stopPropagation(); //This will prevent the event from bubbling up and close the dropdown when you type/click on text boxes.
    });
	
	//to do list
	$('.task-finish').click(function()	{ 
		if($(this).is(':checked'))	{
			$(this).parent().parent().addClass('selected');					
		}
		else	{
			$(this).parent().parent().removeClass('selected');
		}
	});

	//Delete to do list
	$('.task-del').click(function()	{			
		var activeList = $(this).parent().parent();

		activeList.addClass('removed');
				
		setTimeout(function() {
			activeList.remove();
		}, 1000);
			
		return false;
	});
	
	var $activeWidget = '';
	var $activeWidgetHeader;
	var $headerHeight;
	var $screenHeight;
	var $widgetHeight;
	var $borderHeight = 3;

	//Smart Widget

	// Refresh Widget
	$('.widget-refresh-option').click(function()	{

		$activeWidget = $(this).parent().parent().parent();
		
		var $activeSpinIcon = $activeWidget.find('.refresh-icon-animated').fadeIn();

		setTimeout(function() {
			$activeSpinIcon.fadeOut();
		},2000);

		return false;
	});

	// Collasible Widget
	$('.widget-collapse-option').click(function()	{
		$activeWidget = $(this).parent().parent().parent();

		$activeWidget.find('.smart-widget-inner').slideToggle();
		$activeWidget.toggleClass('smart-widget-collapsed');

		var $activeSpinIcon = $activeWidget.find('.refresh-icon-animated').fadeIn();

		setTimeout(function() {
			$activeSpinIcon.fadeOut();
		},500);

		$activeWidget = '';

		return false;
	});

	//Changing Widget Color
	$('.widget-toggle-hidden-option').click(function()	{
		$activeWidget = $(this).parent().parent().parent();

		$activeWidget.find('.smart-widget-hidden-section').slideToggle();	

		var $activeSpinIcon = $activeWidget.find('.refresh-icon-animated').fadeIn();

		setTimeout(function() {
			$activeSpinIcon.fadeOut();
		},500);


		$activeWidget = '';			

		return false;
	});

	//Changing Widget Header Background
	$('.widget-color-list li').click(function()	{
		$activeWidget = $(this).parent().parent().parent().parent();
		$selectedColor = $(this).data('color');

		$activeWidget.removeClass('widget-light-grey');
		$activeWidget.removeClass('widget-dark');
		$activeWidget.removeClass('widget-dark-blue');
		$activeWidget.removeClass('widget-blue');
		$activeWidget.removeClass('widget-green');
		$activeWidget.removeClass('widget-yellow');
		$activeWidget.removeClass('widget-orange');
		$activeWidget.removeClass('widget-red');
		$activeWidget.removeClass('widget-purple');

		if($selectedColor != 'reset')
			$activeWidget.addClass($selectedColor);
		
		return false;
	});

	// Remove Widget
	$('.widget-remove-option').click(function()	{

		$activeWidget = $(this).parent().parent().parent();

		$('#deleteWidgetConfirm').popup('show');

		return false;

	});

	$('.remove-widget-btn').click(function()	{
		$('#deleteWidgetConfirm').popup('hide');
		$activeWidget.fadeOut();

		$activeWidget = '';

		return false;
	});

	//Scroll to Top
	$(".scroll-to-top").click(function()	{
		$("html, body").animate({ scrollTop: 0 }, 600);
		 return false;
	});

	// Popover
    $("[data-toggle=popover]").popover();
	
	// Tooltip
    $("[data-toggle=tooltip]").tooltip();
	$("[rel=tooltip]").tooltip();
});


$(window).load(function() {
	$('body').removeClass('overflow-hidden');

	//Enable animation
	$('.wrapper').removeClass('preload');

	//Chat Notification on top navigation
	setTimeout(function() {
		$('.chat-notification').find('.badge').addClass('active');
		$('.chat-alert').addClass('active');
	}, 3000);

	setTimeout(function() {
		$('.chat-alert').removeClass('active');
	}, 8000);
});

// Toggle Scroll to Top button
$(window).scroll(function(){
		
	 var position = $(window).scrollTop();
	
	 if(position >= 200)	{
		$('.scroll-to-top').addClass('active')
	 }
	 else	{
		$('.scroll-to-top').removeClass('active')
	 }
});



/**div拖拽自动变换宽度*/
window.onload = function() {
var oBoxBody = document.getElementById("boxBody"), oNoteLeft = document.getElementById("noteLeft"), oNoteRight = document.getElementById("noteRight"), oNoteSqlit = document.getElementById("noteBookSplit");
	oNoteSqlit.onmousedown = function(e) {
	 var disX = (e || event).clientX;
	 oNoteSqlit.left = oNoteSqlit.offsetLeft;
	 document.onmousemove = function(e) { 
	  var iT = oNoteSqlit.left + ((e || event).clientX - disX);
	 var e=e||window.event,tarnameb=e.target||e.srcElement;
	  var maxT = oBoxBody.clientWight - oNoteSqlit.offsetWidth;
	  oNoteSqlit.style.margin = 0;
	  iT < 0 && (iT = 0);
	  iT > maxT && (iT = maxT);
	  oNoteSqlit.style.left = oNoteLeft.style.width = iT + "px";
	  oNoteRight.style.paddingLeft = iT + "px";
	  return false
	 }; 
	 document.onmouseup = function() {
	  document.onmousemove = null;
	  document.onmouseup = null; 
	  oNoteSqlit.releaseCapture && oNoteSqlit.releaseCapture()
	 };
	oNoteSqlit.setCapture && oNoteSqlit.setCapture();
	return false
	};
};



//笔记点击变色
$(function(){

	$('.node-body-ul-li').click(function(){
		var li = $(this);
		li.removeClass("node-body-ul-li");
		li.addClass("node-body-ul-li-active");
		li.siblings().removeClass("node-body-ul-li-active");
		li.siblings().addClass("node-body-ul-li");
	});

});

$(function(){
	//新建笔记
	//第一次进入页面 默认选中笔记本的id为第一个笔记本
	var selectedBook = $("#listbooks").find(".noteBookName").first();
	var selectedBookId = selectedBook.attr("value");
	var bookName = selectedBook.html();
	//设置当前选中的笔记本的id和name 方便其他地方获取
	$("#selectedBook").attr("value",selectedBookId);
	$("#selectedBook").attr("name",bookName);
	
	$(".noteRightInfo").load("nullnote");
	//点击新建普通笔记时建立新笔记
	$(".simplenote").click(function(){
		if(selectedBookId == null){
			layer.msg("没有选中笔记本   无法创建笔记");
			return;
		}
		var noteId = addNote(selectedBookId,bookName);
		if(noteId != "0"){
			//拼接第一次创建的html代码
			var lis = initNoteHtml(bookName,noteId);
			//把新加的笔记html代码放到第一个位置
			var html = $(".node-body-ul").html();
			$(".node-body-ul").html(lis+html);
			//修改笔记本的笔记数量+1
			var count = $(".clickBookNote").children('span').eq(3).html();
			$(".clickBookNote").children('span').eq(3).html(parseInt(count) + 1);
			$(".noteRightInfo").load("simplenote",{noteBookName:bookName,noteBookId:selectedBookId,noteId:noteId});
		}
	});
	//点击新建markdown笔记时建立新笔记
	$(".markdownnote").click(function(){
		if(selectedBookId == null){
			layer.msg("没有选中笔记本   无法创建笔记");
			return;
		}
		var noteId = addNote(selectedBookId,bookName);
		if(noteId != "0"){
			$("#selectedNoteId").attr("value",noteId);
			//拼接第一次创建的html代码
			var lis = initNoteHtml(bookName,noteId);
			//把新加的笔记html代码放到第一个位置
			var html = $(".node-body-ul").html();
			$(".node-body-ul").html(lis+html);
			//修改笔记本的笔记数量+1
			var count = $(".clickBookNote").children('span').eq(3).html();
			$(".clickBookNote").children('span').eq(3).html(parseInt(count) + 1);
			$(".noteRightInfo").load("markdownnote",{noteBookName:bookName,noteBookId:selectedBookId,noteId:noteId});
		}
	});
	
	
	//添加笔记
	function addNote(noteBookId,noteBookName){
		 var noteId = "0";
		 $.ajax({  
	         type : "post",  
	          url : "/note/addNote",  
	          data : {noteBookId:noteBookId,label:noteBookName},  
	          async : false,  
	          success : function(data){  
					if(data.status != "200") {
						layer.msg(data.msg);
					}else{
						noteId = data.data.id;
					}
	          }  
	     }); 
		 return noteId;
	}
	
	//拼接note的html代码
	function initNoteHtml(noteBookName,noteId){
		var lis = "<li class='node-body-ul-li'>"
		lis += "<span class='noteId' value=";
		lis += noteId;
		lis += "/><div class='item-desc'><p class='item-title'> ";
		lis += "</p><p class='item-info'><i class='fa fa-book'></i><span class='note-notebook'> ";
		if(noteBookName != null){
			lis += noteBookName;
		}
		lis += " </span><i class='fa fa-clock-o'> </i> <span class='updated-time'> ";
		lis += dateToString(new Date());
		lis += "</p><p class='desc'>";
		lis += "</p></div></li>";
		return lis;
		
	}
	
	//点击笔记方法
	var clickNote = function(){
		var noteId = $(this).children('span').eq(0).attr("value");
		$("#selectedNoteId").attr("value",noteId);
		
	}
	
	function noteClick(noteId){
		
	}
	
	
	
	//鼠标经过出现设置按钮
	showsetting();
	//给获取笔记本元素绑定点击事件
	$('.glyphicon-chevron-right').on("click",getBooks);
	//设置第一个笔记本为默认选中
	$("#listbooks").find(".showsetting").first().addClass("clickBookNote");
	//设置笔记本点击事件
	var clickBookNote = function(){
		$(".showsetting").removeClass("clickBookNote");
		$(this).addClass("clickBookNote");
		selectedBook = $(this).find(".noteBookName");
		noteBookClick(selectedBook);
	}
	function noteBookClick(selectedBook){
		selectedBookId = selectedBook.attr("value");
		bookName = selectedBook.html();
		//设置当前选中的笔记本的id和name 方便其他地方获取
		$("#selectedBook").attr("value",selectedBook.attr("value"));
		$("#selectedBook").attr("name",selectedBook.html());
		$(".node-body-ul").html("");
		$("#loading").addClass("spinner");
		$.get("note/getBookList", {
			noteBookId: selectedBookId
			},function(data){
			if(data.status != "200"){
				$("#loading").removeClass("spinner");
				layer.msg(data.msg);
			}else{
				var html="";
				//为了防止有人点击笔记本太快出现请求两次 第二次先返回结果 第一次后返回结果 导致最终显示的笔记为第一次点击获取的笔记 加一个判断获取的笔记对应的笔记本是否是用户最终选择的笔记本 如果不是则不用运行
				var isExect = true;
				jQuery.each(data.data.list,function(i,item){
					if(item.noteBookId != selectedBookId){
						isExect = false;
						return false;
					}
					var lis = getNoteHtml(item);
					html += lis;
				});
				if(isExect){
					$("#loading").removeClass("spinner");
					$(".node-body-ul").html(html);
					$("#notePageNo").attr("value",data.data.pageNo);
					$("#notePageSize").attr("value",data.data.pageSize);
					$("#noteCount").attr("value",data.data.count);
					$("#noteLastPage").attr("value",data.data.lastPage);
				}

			}
		});
	}
	function getNoteHtml(item){
		var lis = "<li class='node-body-ul-li'><div class='item-desc'><p class='item-title'> ";
		if(item.noteTitle != null){
			lis += item.noteTitle;
		}
		lis += "</p><p class='item-info'><i class='fa fa-book'></i><span class='note-notebook'> ";
		if(item.noteBookName != null){
			lis += item.noteBookName;
		}
		lis += " </span><i class='fa fa-clock-o'> </i> <span class='updated-time'> ";
		lis += dateToString(new Date(item.updateDate));
		lis += "</p><p class='desc'>";
		if(item.context != null){
			if(item.context.length > 80){
				lis += item.context.substring(0,80);
				lis += "...";
			}else{
				lis += item.context;
			}
		}
		lis += "</p></div></li>";
		return lis;
		
	}
	
	//获取时间
	  function dateToString(date){  
		    var year = date.getFullYear();  
		    var month =(date.getMonth() + 1).toString();  
		    var day = (date.getDate()).toString();  
		    var hour = (date.getHours()).toString();  
		    var minute = (date.getMinutes()).toString();  
		    var second = (date.getSeconds()).toString();  
		    if (month.length == 1) {  
		        month = "0" + month;  
		    }  
		    if (day.length == 1) {  
		        day = "0" + day;  
		    }  
		    if (hour.length == 1) {  
		        hour = "0" + hour;  
		    }  
		    if (minute.length == 1) {  
		        minute = "0" + minute;  
		    }  
		    if (second.length == 1) {  
		        second = "0" + second;  
		    }  
		     var dateTime = year + "-" + month + "-" + day +" "+ hour +":"+minute+":"+second;  
		     return dateTime;  
	  }  
	refreshMenu();
	
	
	//笔记滚动条
	$('#innerDiv').slimScroll({  
		width: 'auto', //可滚动区域宽度
		height: '98%', //可滚动区域高度
	
	});
	//笔记滚动条滚到到最底时执行的代码
	$('#innerDiv').slimScroll().bind('slimscroll', function(e, pos){  
		if(pos=='bottom'){
		   if($("#noteLastPage").attr("value") == "false"){

			   var html = $(".node-body-ul").html();
			   $("#loading").addClass("spinner");
			   var pageNo = parseInt($("#notePageNo").attr("value")) + 1;
				$.get("note/getBookList", {
					noteBookId: selectedBookId,
					pageNo: pageNo
					
					},function(data){
					if(data.status != "200"){
						layer.msg(data.msg);
					}else{
						jQuery.each(data.data.list,function(i,item){
							var lis = getNoteHtml(item);
							html += lis;
						});
						$(".node-body-ul").html(html);
						$("#notePageNo").attr("value",data.data.pageNo);
						$("#notePageSize").attr("value",data.data.pageSize);
						$("#noteCount").attr("value",data.data.count);
						$("#noteLastPage").attr("value",data.data.lastPage);
					}
					$("#loading").removeClass("spinner");
				});
		   }
		   
		}
	});  
	
	
	//左侧获取笔记本方法
	function getBooks(){
		var clickObject = $(this);
		var parentElm = clickObject.parent().parent();
		var parentClickObjeck = clickObject.parent();
		if(clickObject.hasClass("glyphicon-chevron-right")){
			$.get("/noteBook/getNoteBook/" + clickObject.next().attr("value"),function(data){
				if(data.status != "200"){
					layer.msg(data.msg);
				}else{
					var html="";
					jQuery.each(data.data,function(i,item){
						var lis = getBookHtml(item);
						html += lis;
					});
					parentClickObjeck.next().html(html);
					//关闭所有获取笔记本元素的点击事件
					$('.glyphicon-chevron-right').off("click");
					//给获取笔记本元素绑定点击事件
					$('.glyphicon-chevron-right').on("click",getBooks);
					//鼠标经过出现设置按钮
					showsetting();
				    //刷新笔记本右键和设置菜单
				    refreshMenu();
				}
			});
			clickObject.removeClass("glyphicon-chevron-right");
			clickObject.addClass("glyphicon-chevron-down");
			parentElm.children('.subtree').show();
		}else{
			clickObject.removeClass("glyphicon-chevron-down");
			clickObject.addClass("glyphicon-chevron-right");
			parentElm.children('.subtree').hide();

		}
		return false;
	}
	
	
	function getBookHtml(item){
		var lis="<li class='openable'><a href='#' class='showsetting'>";
		if(item.haveNode){
			lis += "<span class='glyphicon glyphicon-chevron-right m-right-xs folder-icon'></span>";
		}else{
			lis += "<span class='glyphicon glyphicon-chevron-right m-right-xs folder-icon' style='opacity:0'></span>";
		}
		lis += "<span class='noteBookName' value="
		lis += item.id;
		lis += " parentId="
		lis += item.parentId
		lis += ">";
		lis += item.name
		lis += "</span>";
		lis += "<span title='设置' class='booksetting glyphicon glyphicon-cog'></span>";
		lis += "<span title='笔记数量' style='float:right;font-size:14px'>" + item.noteCount +"</span>";
		lis += "</a><ul class='subtree'></ul></li>";
		return lis;
	}
	
	
	
	//添加笔记本
	$('#addnotebook').click(function(){
		layer.prompt({title: '输入笔记本名，并确认', formType: 3}, function(name, index){
			  layer.close(index);
			  $.post("/noteBook/addNoteBook",{
				name:name
				},
				function(data) {
				if(data.status != "200") {
				layer.msg(data.msg);
				} else {
					  //设置内容
					  var text=getBookHtml(data.data);
					  //把内容对象转换会jQuery对象
					  var bookli = $(text);
					  //获取ul
					  var listbooks = $("#listbooks");
					  //获取ul下的第一个li 在第一个li前加上text中的内容
					  bookli.insertBefore(listbooks.children().first());
					  showsetting();
					  //刷新笔记本右键和设置菜单
					  refreshMenu();
				}
	
			  });

		});
	});
	function showsetting(){
		//鼠标经过出现设置按钮
		$(".showsetting").hover(function(){
			$(this).find(".booksetting").eq(0).css("opacity","100");
		},function(){
			$(this).find(".booksetting").eq(0).css("opacity","0");
		});
	}
	var book;
	var onClick = function(e) {
		//获取notebook这个元素
		book = $(this).prev();
		if(book.attr('class') != "noteBookName"){
			book = $(this).find(".noteBookName");
		}
		var items = [
						{ title: '添加子笔记本', icon: 'ion-plus-round', fn: addNodeNoteBook },
						{ title: '重命名', icon: 'ion-edit', fn: rename },
						{ title: '删除笔记本', icon: 'ion-trash-a', fn: deleteNoteBook },
					]

					basicContext.show(items, e.originalEvent)
				
				//添加子笔记本
				function addNodeNoteBook(){
					layer.prompt({title: '输入笔记本名，并确认', formType: 3}, function(name, index){
						  layer.close(index);
						  $.post("/noteBook/addNoteBook",{
							name:name,
							parentId:book.attr("value")
							},
							function(data) {
							if(data.status != "200") {
							layer.msg(data.msg);
							} else {
								  var html ="";
								  //设置内容
								  var text= getBookHtml(data.data);
								  html += text;
								  //获取笔记名前面的三角
								  var triangle = book.prev();
								  //获取subtree这个元素
								  var subtree = book.parent().next();
								  //如果三角属性为透明 则设置为不透明并且样式为下拉样式
								  if(triangle.css("opacity") == "0"){
									  triangle.css("opacity","100");
									  triangle.removeClass("glyphicon-chevron-right");
									  triangle.addClass("glyphicon-chevron-down");
									  subtree.html(html);
									  subtree.show();
									  //关闭所有获取笔记本元素的点击事件
									  $('.glyphicon-chevron-right').off("click");
									  //给获取笔记本元素绑定点击事件
									  $('.glyphicon-chevron-right').on("click",getBooks);
									  //鼠标经过出现设置按钮
									  showsetting();
									  //刷新笔记本右键和设置菜单
									  refreshMenu();
								  }else{
									  triangle.removeClass("glyphicon-chevron-right");
									  triangle.addClass("glyphicon-chevron-down");
									  subtree.html(html + subtree.html());
									  subtree.show();
									  //关闭所有获取笔记本元素的点击事件
									  $('.glyphicon-chevron-right').off("click");
									  //给获取笔记本元素绑定点击事件
									  $('.glyphicon-chevron-right').on("click",getBooks);
									  //鼠标经过出现设置按钮
									  showsetting();
									  //刷新笔记本右键和设置菜单
									  refreshMenu();
								  }
								  
							}
				
						  });

					});
				}
				
				//删除笔记本
				function deleteNoteBook(){
					//获取笔记数量
					var noteSum = book.next().next().text();
					if(noteSum != "0"){
						layer.msg("该笔记本下有笔记不可删除!!!");
						return;
					}
					//获取小三角的透明度
					var isNote = book .prev().css("opacity");
					if(isNote != "0"){
						layer.msg("该笔记本下有子笔记不可删除!!!");
						return;
					}
					
					layer.confirm('确定要删除该笔记本吗,如果有子笔记本或者有笔记无法删除', {
						  btn: ['确定','取消'] //按钮
						}, function(index){
							 $.post("/noteBook/deleteNoteBook",{
									id:book.attr("value"),
									parentId:book.attr("parentId")
									},
									function(data) {
									if(data.status != "200") {
										layer.msg(data.msg);
									} else {
										if(book.attr("parentId") == "0"){
											book.parent().parent().remove();
										}else{
											//如果父笔记本已经没有子笔记本
											if(!data.data){
												//获取父笔记本的三角设置透明度为0
												book.parent().parent().parent().prev().find(".folder-icon").first().css("opacity","0");
												book.parent().parent().remove();
											}else{
												book.parent().parent().remove();
											}
										}
									}
							 });
							 layer.close(index);
						});
				}
					
				//重命名笔记本
				function rename(){
					layer.prompt({title: '输入新笔记本名，并确认', formType: 3}, function(name, index){
						  layer.close(index);
						  $.post("/noteBook/updateNoteBook",{
							id:book.attr("value"),
							name:name
							},
							function(data) {
							if(data.status != "200") {
							layer.msg(data.msg);
							} else {
								book.html(data.data.name);
							}
				
						  });

					});
				}
	}
	//笔记本右键设置按钮
	$('.showsetting').on('contextmenu', onClick);
	//笔记本设置按钮
	$('.booksetting').on('click', onClick);
	
	//刷新笔记本右键和设置菜单
	function refreshMenu(){
		  //关闭所有笔记设置按钮的点击事件 后面重新加上
		  $('.showsetting').off('click')
		  $('.showsetting').off('contextmenu');
		  //关闭所有笔记设置按钮的点击事件 后面重新加上
		  $('.booksetting').off('click')
		  //绑定点击笔记本按钮触发事件
		  $('.showsetting').on('click',clickBookNote);
		  //笔记本右键设置按钮
		  $('.showsetting').on('contextmenu', onClick);
		  //笔记本设置按钮
		  $('.booksetting').on('click', onClick);
	}
	

});

