layui.use(['table'], function() {
    initTable();
});

function initTable() {
    var form = layui.form;

    form.on('submit(readerExcel)', function(){
        showExcel();
    });
}

function showExcel() {
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#scriptTable'
            , url: '/excle/show'
            , cellMinWidth: 20 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
                {field: 'date', title: '日期'}
                , {field: 'location', title: '地区'}
                , {field: 'proportion', title: '单位'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                , {field: 'ss', title: '单位成本'}
                , {field: 'amount', title: '总额'}
            ]]
        });
    });
}