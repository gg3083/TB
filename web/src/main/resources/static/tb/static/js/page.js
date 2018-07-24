function list(url, elem, queryData, temp, $template) {
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
                        getListByPage(url, queryData, temp, $template);
                    }
                }
            });
            parseList(res.obj, queryData.pageNum, temp, $template);
        });
    });
}

//点击页数从后台获取数据
function getListByPage(url, queryData, temp, $template) {
    $.getJSON(url, queryData, function (res) {
        parseList(res.obj, queryData.pageNum, temp, $template);
    });
}

function parseList(res, currPage, temp, $template) {
    layui.use('laypage', function () {
        var form = layui.form;
        $template.html(temp(res));
        form.render();
    })
}
;