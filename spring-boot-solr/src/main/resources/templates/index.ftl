<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Solr</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/script.css">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <img src="/image/logo.gif" class="img-responsive center-block" alt="Cinque Terre">
            <h3 class="text-center">Solr搜索引擎</h3>
        </div>
    </div>
</div>

<div class="leftright">
    <table class="layui-hide" id="scriptTable" lay-filter="useruv"></table>
    <script type="text/html" id="switchTpl">
        <input type="checkbox" value="{{d.id}}" lay-filter="switchEnable" lay-skin="switch" lay-text="生效|失效" {{ d.status == 0 ? 'checked' : '' }}>
    </script>

    <div class="center-button">
        <button type="button" class="layui-btn" lay-submit lay-filter="init">初始化</button>
        <button type="button" class="layui-btn layui-btn-normal" lay-submit lay-filter="queryAll">查找全部</button>
        <button type="button" class="layui-btn layui-btn-warm" lay-submit lay-filter="group">分组查找</button>
        <button type="button" class="layui-btn layui-btn-danger" lay-submit lay-filter="rules">按条件查找</button>
    </div>
</div>

    <script src="/layui/layui.js" charset="utf-8"></script>
    <script src="/js/index.js"></script>
</body>
</html>