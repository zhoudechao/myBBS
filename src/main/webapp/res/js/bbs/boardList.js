layui.use(['form','layer','laydate','table','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        table = layui.table;

    //字典列表
    var tableIns = table.render({
        elem: '#list',
        url : path + '/board/boardData.do',
        page : true,
        cellMinWidth : 95,
        height : "full-104",
        limit : 5,
        limits : [5,10,15,20,25],
        id : "tables",
        cols : [[
				 {
					type : "checkbox",
					fixed : "left",
					width : 50
				},
				{
					field: 'boardId',
					width: 80,
					title: 'ID',
					sort: true
				}, 
				{
					field : 'boardName',
					title : '版块名称',
					width : 120
				},
				{
					field : 'boardPostnum',
					title : '发帖数量',
					width : 90
				},
				{
					field : 'boardTodaynum',
					title : '今日发帖数',
					width : 120,
					align : 'center'
				},
				{
					field : 'boardDescription',
					title : '描述',
					align : 'center',
					width : 250
				},
				{
					field : 'boardCreatetime',
					title : '版块创建时间',
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
            table.reload("tables",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	boardName: $(".boardName").val() //版块名字
                }
            })
    });

    //跳转到添加版块页面
    function addLink(edit){
        var index = layer.open({
            title : "添加版块信息",
            type : 2,
			area: ['540px', '550px'],
            content : path + "/board/add.do"
        })
    }
  //版块信息修改
    function editLink(edit){
        var index = layer.open({
            title : "修改版块信息",
            type : 2,
			area: ['540px', '550px'],
            content : path + "/board/edit.do?boardId="+edit.boardId
        })
    }
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
    
 
})