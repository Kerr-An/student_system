<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>专业管理</title>
</head>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">
        <div style="margin: 10px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="name" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">所属院系</label>
                        <div class="layui-input-inline">
                            <input type="text" name="college" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-primary" lay-submit lay-filter="search-btn"><i
                                class="layui-icon"></i> 搜 索
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">
                    <i class="fa fa-plus"></i>
                    添加
                </button>
                <button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="update">
                    <i class="fa fa-pencil"></i>
                    修改
                </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete">
                    <i class="fa fa-remove"></i>
                    删除
                </button>
            </div>
        </script>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
    </div>
</div>

<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
        table.render({
            elem: '#currentTableId',
            url: '${basePath}teacher/query',
            method: "post",
            toolbar: '#toolbar',
            defaultToolbar: ['filter', 'exports', 'print'],
            page: true,
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID'},
                {field: 'teacherName', title: '帐号'},
                {field: 'name', title: '姓名'},
                {field: 'remark', title: '备注'}
            ]],
            skin: 'line'
        });

        // 1.执行表单查询
        // 2.表格reload
        form.on('submit(search-btn)', function (data) {
            console.log(data.field);
            table.reload('currentTableId', {
                where: data.field
            });
            return false;
        });

        /**
         * toolbar事件监听
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            // 添加监听操作
            if (obj.event === 'add') {
                var index = layer.open({  // 弹窗方式打开
                    title: '添加专业',
                    type: 2,
                    shade: 0.2,
                    shadeClose: false,
                    area: ['50%', '50%'],
                    content: '${basePath}teacher/add',
                    end: function () {
                        table.reload('currentTableId');
                    }
                });

                // 监听删除操作
            } else if (obj.event === 'delete') {
                var checkStatus = table.checkStatus('currentTableId'); // 拿到表格的选中状态
                var data = checkStatus.data;
                var arr = []; // 创建数组为了存放多个data
                for (index in data) {
                    arr.push(data[index].id); // 将id全部放到数组里
                }
                layer.confirm('确定要删除吗？', function () {
                    $.ajax({
                        url: "${basePath}teacher/delete",
                        type: "POST",
                        dataType: 'json',
                        data: "ids=" + arr.join(","),
                        success: function (data) {
                            layer.msg(data.msg, {time: 500},
                                function () {
                                    table.reload("currentTableId");
                                });
                        }
                    });
                });

                // 监听修改操作
            } else if (obj.event === 'update') {
                // 首先也得获取选中状态
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if (data.length != 1) {
                    layer.msg("请选择一行数据修改",{time:1000});
                    return;
                }

                var index = layer.open({
                    title: '修改专业',
                    type: 2,
                    shade: 0.2,
                    shadeClose: false,
                    area: ['50%', '50%'],
                    content: '${basePath}teacher/detail/'+data[0].id,
                    end:function(){
                        table.reload('currentTableId');
                    }
                });
            }
        });
    });
</script>
</body>
</html>


