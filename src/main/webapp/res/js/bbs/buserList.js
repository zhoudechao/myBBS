layui.use(['form','layer','laydate','table','upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
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
        url : path + '/buser/buserData.do',
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
					field: 'userId',
					width: 70,
					title: 'ID',
					align : 'center',
					sort: true
				}, 
				{
					field : 'userName',
					title : '姓名',
					align : 'center',
					width : 100
				},
				{
					field : 'userEmail',
					title : '邮箱',
					align : 'center',
					width : 180
				},
				{
					field : 'userSex',
					title : '性别',
					width : 50,
					align : 'center'
				},
				{
					field : 'userPostnum',
					title : '发帖数量',
					align : 'center',
					width : 90
				},
				{
					field : 'userJoindate',
					title : '创建时间',
					align : 'center',
					width : 180,
					templet : function(d){
				        return dateFormat(d.userJoindate)
				      }
				},
				{
					field : 'userLastlogin',
					title : '上次登录时间',
					align : 'center',
					width : 180,
					templet : function(d){
				        return dateFormat(d.userLastlogin)
				      }
				},
				{
					field : 'userLogins',
					title : '登录次数',
					align : 'center',
					width : 90
				},
				{
					field : 'userBirthday',
					title : '生日',
					align : 'center',
					width : 150
				},
				{
					field : 'userDescription',
					title : '描述',
					align : 'center',
					width : 150
				},
				/*{
					field : 'startTime',
					title : '开始时间'
				},
				{
					field : 'endTime',
					title : '结束时间'
				},*/
				{
					title : '操作',
					width : 200,
					fixed : "right",
					align : "center",
					templet : '#flinkbar'
				}
        ]]
    });

    //搜索
    $(".search_btn").on("click",function(){
    	var userName=$(".userName").val();
    	var startTime= $(".startTime").val();
    	var endTime=$(".endTime").val();
    	debugger;
            table.reload("tables",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                	userName: userName,
                	startTime:startTime,
                	endTime:endTime
                }
            })
    });


    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('tables'),
            data = checkStatus.data,
            linkId = [];
        if(data.length > 0) {
            for (var i in data) {
                linkId.push(data[i].userId);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
            	var ajaxReturnData;
                $.ajax({
		            url: path + '/buser/deleteBatch.do',
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
            layer.msg("请选择需要删除的用户信息");
        }
    })

    //列表操作
    table.on('tool(tables)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            editLink(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                var ajaxReturnData;
		        $.ajax({
		            url: path + '/buser/delete.do',
		            type: 'post',
		            async: false,
		            data: {userId:data.userId},
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
		            url: path + '/buser/setUse.do', //设置为可用
		            type: 'post',
		            async: false,
		            data: {userId:data.userId},
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
		            url: path + '/buser/setUse.do',
		            type: 'post',
		            async: false,
		            data: {userId:data.userId},
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
    
    form.on('submit(addLink)', function (data) {
        var field = data.field;
        var formElem = $(data.form);
        var elem = $(data.elem);
        var tableId ="tables";
        debugger;
        // 重置勾选状态记录
        if (tableId) {
            // 处理之后的结果，如果需要处理的话
            //var whereTemp = {};
            //layui.each(field, function (index, value) {
                // 如果需要对数据进行处理
            //});

            table.reload(tableId, {
                where: field,
                page: {curr: 1}
            });
        } else {
            console.log('查询错误：查询按钮没有配置要查询的table id!');
        }

        return false;
    });
    
})