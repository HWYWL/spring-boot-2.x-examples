<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>文件系统</title>
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
            <h3 class="text-center">文件系统</h3>
        </div>
    </div>
</div>

<div class="leftright">

    <#--FastDFS-->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>FastDFS：多文件列表上传</legend>
    </fieldset>

    <div class="layui-upload">
        <div class="layui-upload-list">
            <table class="layui-table">
                <thead>
                <tr><th>文件名</th>
                    <th>大小</th>
                    <th>状态</th>
                    <th>地址</th>
                    <th>操作</th>
                </tr></thead>
                <tbody id="fastdfsFileList"></tbody>
            </table>
        </div>
        <div class="center-button">
            <button type="button" class="layui-btn layui-btn-normal" id="fastdfsSelectList">FastDFS选择多文件</button>
            <button type="button" class="layui-btn" id="fastdfsFileListAction">
                <i class="layui-icon"></i>上传文件</button>
        </div>
    </div>

    <#--阿里OSS-->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>阿里OSS：多文件列表上传</legend>
    </fieldset>

    <div class="layui-upload">
        <div class="layui-upload-list">
            <table class="layui-table">
                <thead>
                <tr><th>文件名</th>
                    <th>大小</th>
                    <th>状态</th>
                    <th>地址</th>
                    <th>操作</th>
                </tr></thead>
                <tbody id="ossFileList"></tbody>
            </table>
        </div>
        <div class="center-button">
            <button type="button" class="layui-btn layui-btn-normal" id="ossSelectList">OSS选择多文件</button>
            <button type="button" class="layui-btn" id="ossFileListAction">
                <i class="layui-icon"></i>上传文件</button>
        </div>
    </div>

    <#--七牛云-->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>七牛云：多文件列表上传</legend>
    </fieldset>

    <div class="layui-upload">
        <div class="layui-upload-list">
            <table class="layui-table">
                <thead>
                <tr><th>文件名</th>
                    <th>大小</th>
                    <th>状态</th>
                    <th>地址</th>
                    <th>操作</th>
                </tr></thead>
                <tbody id="qiniuFileList"></tbody>
            </table>
        </div>
        <div class="center-button">
            <button type="button" class="layui-btn layui-btn-normal" id="qiniuSelectList">七牛选择多文件</button>
            <button type="button" class="layui-btn" id="qiniuFileListAction">
                <i class="layui-icon"></i>上传文件</button>
        </div>
    </div>

    <script src="/layui/layui.js" charset="utf-8"></script>
    <script src="/js/fastdfs.js"></script>
    <script src="/js/oss.js"></script>
    <script src="/js/qiniu.js"></script>
</body>
</html>