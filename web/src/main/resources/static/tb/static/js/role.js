layui.use([ 'layer','form'], function () {
    var layer = layui.layer;
    var form = layui.form;

    //URL
    var roleListUrl = "/admin/role/list";
    var menuListUrl = "/admin/menu/list";
    var menuAddUrl = "/admin/menu/add";

    //json
    var json = "json";
    var get = "get";
    var post = "post";
    
    var $add = $("#add");
    var $addModule = $("#addModule");
    var $addform = $("#addform");

    $.ajax({
        url:menuListUrl,
        type: post,
        dataType: json,
        data: {},
        success: function (data) {
            menuList(data);
        }
    })

    function  menuList(data) {
        var dataList = data;

        console.log(dataList);
    }

    $add.click(function () {
        console.log("add");
        open($addModule);
    })

    form.on('submit(addFilter)', function(data){
        submit(menuAddUrl,$addform,data);
    });

    function open($dom){
        layer.open({
            type: 1,
            title :"保存",
            skin: 'layui-layer-molv', //样式类名
            closeBtn: 2, //不显示关闭按钮
            shift: 2,
            anim:5,
            area: ['444px', '444px'], //宽高
            offset:'auto',
            shadeClose: true, //开启遮罩关闭
            content: $dom,
            cancel: function(index, layero){
                layer.closeAll();
                $dom.hide();
                return false;
            },
            end: function(index, layero){
                $dom.hide();
            },
        });
    }
    
    function submit(url,$form,data) {
        $.ajax({
            url : url,
            type : get,
            dataType : json,
            data:data.field,
            success : function(data) {
                if(data.code == 1){
                    layer.closeAll();
                    //layer.msg(data.msg);
                    setTimeout("location.reload()",1000);
                }else if(data.status == 0){
                    // 错误
                    $form[0].reset();
                    layer.closeAll();
                    layer.msg(data.msg);
                }
            }
        });
        return false;
    }

});

