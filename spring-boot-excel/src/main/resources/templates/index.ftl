<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>下载</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/lib/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/script.css">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <img src="/image/logo.gif" class="img-responsive center-block" alt="Cinque Terre">
            <h3 class="text-center">下载excel</h3>
        </div>
    </div>
</div>

<div class="leftright">
    <#--表单-->
    <table class="layui-hide" lay-size="sm" id="scriptTable"></table>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>Excel操作</legend>
    </fieldset>

    <div class="layui-upload">
        <div class="center-button">
            <a href="/excle/download" class="layui-btn layui-btn-normal" target="_blank">下载excel文件</a>
            <button type="button" class="layui-btn" lay-submit="" lay-filter="readerExcel">
                <i class="layui-icon">&#xe60a;</i>读取excel展示</button>
        </div>
    </div>

    <script src="/lib/layui/layui.js" charset="utf-8"></script>
    <script src="/js/index.js"></script>
</body>
</html>