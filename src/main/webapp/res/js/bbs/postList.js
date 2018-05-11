layui.use(['form','layer','laydate','table','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
       // formSelects=layui.formSelects,
        table = layui.table;

  //这个部分是时间范围选择的时候用到
    var nowTime = new Date().valueOf();
    var max = null;
    var start = laydate.render({
        elem: '.startTime',
        type: 'datetime',
        max: nowTime,
        btns: ['clear', 'confirm'],
        done: function(value, date){
            endMax = end.config.max;
            end.config.min = date;
            end.config.min.month = date.month -1;
        }
    });
    var end = laydate.render({
        elem: '.endTime',
        type: 'datetime',
        max: nowTime,
        done: function(value, date){
            if($.trim(value) == ''){
                var curDate = new Date();
                date = {'date': curDate.getDate(), 'month': curDate.getMonth()+1, 'year': curDate.getFullYear()};
            }
            start.config.max = date;
            start.config.max.month = date.month -1;
        }
    });
    
    //字典列表
    var tableIns = table.render({
        elem: '#list',
        url : path + '/post/postData.do',
        page : true,
        cellMinWidth : 95,
        height : "full-104",
        limit : 10,
        limits : [5,10,15,20,25],
        id : "tables",
        cols : [[
				 {
					type : "checkbox",
					fixed : "left",
					width : 50
				},
				{
					field: 'postId',
					width: 80,
					title: 'ID',
					sort: true
				}, 
				{
					field : 'postTopic',
					title : '标题',
					width : 120
				},
				{
					field : 'postContent',
					title : '内容',
					width : 90
				},
				{
					field : 'postTypename',
					title : '所属类型',
					width : 120,
					align : 'center'
				},
				{
					field : 'postBoardname',
					title : '所属版块',
					width : 120,
					align : 'center'
				},
				{
					field : 'postUsername',
					title : '所属用户',
					width : 120,
					align : 'center'
				},
				{
					field : 'postDescription',
					title : '描述',
					align : 'center',
					width : 250
				},
				{
					field : 'post_createTime',
					title : '创建时间',
					align : 'center',
					width : 200,
					templet : function(d){
				        return dateFormat(d.boardCreatetime)
				      }
				},
				/*{
					field : 'status',
					title : '状态',
					align : 'center',
					width : 200
				},*/
				{
					title : '操作',
					width : 250,
					fixed : "right",
					align : "center",
					templet : '#flinkbar'
				}
        ]]
    });

    //搜索
    $(".search_btn").on("click",function(){
    	var boardName=$("#bName option:selected").val();
    	var typeName=$("#tName option:selected").val();
    	debugger;
            table.reload("tables",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	postTopic:$(".postTopic").val(),
                	postUsername:$(".postUsername").val(),
                	startTime1:$(".startTime").val(),
                	endTime1:$(".endTime").val(),
                	postTypename:typeName,
                	postBoardname:boardName
                }
            })
    });

    //跳转到添加版块页面
   /* function addLink(edit){
        var index = layer.open({
            title : "添加帖子信息",
            type : 2,
			area: ['540px', '550px'],
            content : path + "/post/add.do"
        })
    }*/
  //版块信息修改
   /* function editLink(edit){
        var index = layer.open({
            title : "修改版块信息",
            type : 2,
			area: ['540px', '550px'],
            content : path + "/post/edit.do?postId="+edit.postId
        })
    }*/
    //绑定添加友情链接事件
    $(".addLink_btn").click(function(){
        addLink();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('tables'),
            data = checkStatus.data,
            linkId = [];
        if(data.length > 0) {
            for (var i in data) {
                linkId.push(data[i].boardId);
            }
            layer.confirm('确定删除选中的版块？', {icon: 3, title: '提示信息'}, function (index) {
            	var ajaxReturnData;
                $.ajax({
		            url: path + '/board/deleteBatch.do',
		            type: 'post',
		            async: false,
		            data: {ids:linkId.toString()},
		            success: function (data) {
		                ajaxReturnData = data;
		              //删除结果
				        if (ajaxReturnData == '1') {
				            table.reload('tables');
				            layer.msg('删除成功', {icon: 1});
				        } else {
				        	layer.msg('删除失败', {icon: 5});
				        }
		            }
		        });
            })
        }else{
            layer.msg("请选择需要删除的版块信息");
        }
    })

    //列表操作
    table.on('tool(tables)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            editLink(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此版块？',{icon:3, title:'提示信息'},function(index){
                var ajaxReturnData;
		        $.ajax({
		            url: path + '/board/delete.do',
		            type: 'post',
		            async: false,
		            data: {boardId:data.boardId},
		            success: function (data) {
		                ajaxReturnData = data.status;
		            }
		        });
		        //删除结果
		        if (ajaxReturnData == '1') {
		            table.reload('tables');
		            layer.msg('删除成功', {icon: 1});
		        } else {
		        	layer.msg('删除失败', {icon: 5});
		        }
				
				layer.close(index);
            });
        }else if (obj.event === 'disable') {
			layer.confirm('真的禁用这个版块么', function(index) {
				var ajaxReturnData;
		        $.ajax({
		            url: path + '/board/setUse.do', //设置为可用
		            type: 'post',
		            async: false,
		            data: {boardId:data.boardId},
		            success: function (data) {
		                ajaxReturnData = data.status;
		            }
		        });
		        //删除结果
		        if (ajaxReturnData == '1') {
		            table.reload('tables');
		            layer.msg('操作成功', {icon: 1});
		        } else {
		        	layer.msg('操作失败', {icon: 5});
		        }
				
				layer.close(index);
				
			});
		}else if (obj.event === 'able') {
			layer.confirm('真的将该版块置为可用么', function(index) {
				var ajaxReturnData;
		        $.ajax({
		            url: path + '/board/setUse.do',
		            type: 'post',
		            async: false,
		            data: {boardId:data.boardId},
		            success: function (data) {
		                ajaxReturnData = data.status;
		            }
		        });
		        //删除结果
		        if (ajaxReturnData == '1') {
		            table.reload('tables');
		            layer.msg('操作成功', {icon: 1});
		        } else {
		        	layer.msg('操作失败', {icon: 5});
		        }
				
				layer.close(index);
				
			});
		}
    });
    
    form.on("submit(addLink)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        var ajaxReturnData;
        //登陆验证
        $.ajax({
            url: path + '/board/save.do',
            type: 'post',
            async: false,
            data: data.field,
            success: function (data) {
                ajaxReturnData = data.status;
            }
        });
        //结果回应
        if (ajaxReturnData == '1') {
        	top.layer.close(index);
        	top.layer.msg('保存成功', {icon: 1});
        	 layer.closeAll("iframe");
             //刷新父页面
             $(".layui-tab-item.layui-show",parent.document).find("iframe")[0].contentWindow.location.reload();
        } else {
        	top.layer.msg('保存失败', {icon: 5});
        }
        return false;
    })
    
    /*异步加载出版块信息*/
    $("#bName").next('.layui-unselect').find('.layui-select-title').click(function(){
    	$.ajax({
            url: path + '/board/selectAllForMap.do',
            type: 'post',
            async: false,
            success: function (data) {
            	$("#bName").append("<option value=''>不选择</option>");
            	for(var i=0;i<data.length;i++){
            		$("#bName").append("<option value='" + data[i].boardName + "'>"+data[i].boardName + "</option>");
            	}
            	//form.render(null,'postBoardname');
            	//form.render('select');
    		},
    		error : function(data) {
    			layer.msg(data.msg, {icon: 5});
    		}
        })
    });
    
    $("#tName").next('.layui-unselect')
    .find('.layui-select-title').click(function(){
    	//$("#tName").empty();
    	$.ajax({
            url: path + '/type/selectAllForMap.do',
            type: 'post',
            async: false,
            success: function (data) {
            	$("#tName").append("<option value=''>不选择</option>");
            	for(var i=0;i<data.length;i++){
            		$("#tName").append("<option value='" + data[i].typeName + "'>"+data[i].typeName + "</option>");
            	}
            	//form.render(null,'tName');
            	form.render('select');
    		},
    		error : function(data) {
    			layer.msg(data.msg, {icon: 5});
    		}
        })
    });
    
})


