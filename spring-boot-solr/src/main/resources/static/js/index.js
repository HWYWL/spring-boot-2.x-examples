layui.use(['table','form'], function() {
    $ = layui.jquery;
    table = layui.table;
    tableIns = initTable();
});

function initTable() {
    var table = layui.table,form = layui.form;

    layui.use('table', function(){
        table.render({
            elem: '#scriptTable'
            ,url:'/solr/queryAll'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: 'ID'}
                ,{field:'desc', title: '文档说明'}
                ,{field:'tag', title: '标签'}
                ,{field:'good', title: '点赞'}
                ,{field:'bad', title: '鄙视'}
                ,{field:'name', title: '作者名称'}
                ,{field:'gender', title: '作者性别'}
                ,{field:'goldCoin', title: '金币打赏'}
                ,{field:'status'+' checked', title: '书本状态', templet: '#switchTpl', unresize: true}
            ]]
        });
    });

    // 失效状态
    form.on('switch(switchEnable)', function(obj){
        var status = obj.elem.checked?0:-1;
        var id = obj.elem.value;
        // var scriptInfo = '{"id":"' + id + '",' + '"status":"' + status + '"}';
        // layer.alert(scriptInfo, {icon: 1});
        $.ajax({
            url: "/solr/updata",
            type: "POST",
            data : {"id":id, "status":status},
            async : false,
            success: function(data){
                if(data.code==0){
                    layer.msg(data.msg,{icon: 1});
                }else{
                    layer.msg(data.msg,{icon: 2});
                }
            }

        });
    });

    // 按照规则搜索
    form.on('submit(rules)', function(){
        table.render({
            elem: '#scriptTable'
            ,url:'/solr/queryName'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: 'ID'}
                ,{field:'desc', title: '文档说明'}
                ,{field:'tag', title: '标签'}
                ,{field:'good', title: '点赞'}
                ,{field:'bad', title: '鄙视'}
                ,{field:'name', title: '作者名称'}
                ,{field:'gender', title: '作者性别'}
                ,{field:'goldCoin', title: '金币打赏'}
                ,{field:'status'+' checked', title: '书本状态', templet: '#switchTpl', unresize: true}
            ]]
        });
    });

    // 搜索全部
    form.on('submit(queryAll)', function(){
        table.render({
            elem: '#scriptTable'
            ,url:'/solr/queryAll'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: 'ID'}
                ,{field:'desc', title: '文档说明'}
                ,{field:'tag', title: '标签'}
                ,{field:'good', title: '点赞'}
                ,{field:'bad', title: '鄙视'}
                ,{field:'name', title: '作者名称'}
                ,{field:'gender', title: '作者性别'}
                ,{field:'goldCoin', title: '金币打赏'}
                ,{field:'status'+' checked', title: '书本状态', templet: '#switchTpl', unresize: true}
            ]]
        });
    });

    // 分组
    form.on('submit(group)', function(){
        table.render({
            elem: '#scriptTable'
            ,url:'/solr/group'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: 'ID'}
                ,{field:'desc', title: '文档说明'}
                ,{field:'tag', title: '标签'}
                ,{field:'good', title: '点赞'}
                ,{field:'bad', title: '鄙视'}
                ,{field:'name', title: '作者名称'}
                ,{field:'gender', title: '作者性别'}
                ,{field:'goldCoin', title: '金币打赏'}
                ,{field:'status'+' checked', title: '书本状态', templet: '#switchTpl', unresize: true}
            ]]
        });
    });

    // 数据初始化，在程序第一次启动之后，应该点击此按钮插入数据
    form.on('submit(init)', function(){
        table.render({
            elem: '#scriptTable'
            ,url:'/solr/init'
            ,cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', title: 'ID'}
                ,{field:'desc', title: '文档说明'}
                ,{field:'tag', title: '标签'}
                ,{field:'good', title: '点赞'}
                ,{field:'bad', title: '鄙视'}
                ,{field:'name', title: '作者名称'}
                ,{field:'gender', title: '作者性别'}
                ,{field:'goldCoin', title: '金币打赏'}
                ,{field:'status'+' checked', title: '书本状态', templet: '#switchTpl', unresize: true}
            ]]
        });
    });

}