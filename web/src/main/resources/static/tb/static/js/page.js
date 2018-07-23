function list(url, elem, queryData, temp, templateId) {
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        var form = layui.form;
        $.getJSON(url, queryData, function (res) {
            laypage.render({
                elem: elem,
                count: res.obj.total, //总条数
                limit: queryData.pageSize, //每页条数
                theme: '#048F74', //自定义颜色
                jump: function (obj, first) {
                    if (!first) { //首次则不进入
                        queryData.pageNum = obj.curr;
                        getListByPage(url, queryData, temp, templateId);
                    }
                }
            });
            parseList(res.obj, queryData.pageNum, temp, templateId);
        });
    });
}

//点击页数从后台获取数据
function getListByPage(url, queryData, temp, templateId) {
    $.getJSON(url, queryData, function (res) {
        parseList(res.obj, queryData.pageNum, temp, templateId);
    });
}

function parseList(res, currPage, temp, templateId) {
    layui.use('laypage', function () {
        var form = layui.form;
        //var html = template(temp, res);
        console.log( temp(res.list) );
        templateId.html(temp(res.list));
        form.render();
    })
}
;