<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${basePath}static/lib/layui-src/css/layui.css" media="all">
    <link rel="stylesheet" href="${basePath}static/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="${basePath}static/css/style.css" media="all">
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main width_60">
        <form class="layui-form">
            <input type="hidden" name="id" value="${teacher.id}">
            <div class="layui-form-item">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-block">
                    <input type="text" name="teacherName" value="${teacher.teacherName}" lay-verify="required" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" value="${teacher.name}" lay-verify="required" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="remark" value="${teacher.remark}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-primary layui-btn-sm data-add-btn">
                        <i class="fa fa-refresh"></i>
                        重置
                    </button>
                    <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-submit lay-filter="save">
                        <i class="fa fa-save"></i>
                        保存
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="${basePath}static/lib/layui-src/layui.js?<%=System.currentTimeMillis()%>" charset="utf-8"></script>
<script src="${basePath}static/js/lay-config.js?v=2.0.0" charset="utf-8"></script>
<script>
    layui.use(['form', 'jquery'], function () {
        var $ = layui.jquery,
            form = layui.form;
        // 获取窗口索引
        var index = parent.layer.getFrameIndex(window.name);

        // 监听form表单
        form.on('submit(save)', function (data) {
            $.ajax({
                url: "${basePath}teacher/update",
                type: "POST",
                dataType: 'json',
                data: data.field,
                success: function (data) {
                    layer.msg(data.msg, {time: 500},
                        function () {
                            parent.layer.close(index);
                        });
                }
            });
            return false;
        });
    });
</script>

</body>
</html>