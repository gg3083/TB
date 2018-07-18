layui.use(['layedit','laypage','form','layer'],function() {
    var laypage = layui.laypage;
    var layedit = layui.layedit;
    var form = layui.form;
    var layer = layui.layer;

    //建立编辑器
    var index = layedit.build('noteDesc', layedit.set({
        uploadImage: {
            url: '' //接口url
            , type: '' //默认post
        }
    }));
    //json
    var json = "json";
    var get = "get";
    var post = "post";

    //url
    var listUrl = "/tb/note/list"; //列表
    var addUrl = "/tb/note/add"; //添加

    //分页
    var elem = 'page1';//显示上下一页的位置
    var temp1 = 'temp1';//模板1
    var $template1 = $('#template1');//数据显示的位置

    //dom
    var $publish = $('#publish');
    var $noteTitle = $('#noteTitle');
    var $noteDesc = $('#noteDesc');
    var $userParent = $('#user_parent');
    var $userChild = $('#user_child');

    //基本数据
    var queryData = {
        pageNum: 1,
        pageSize: 10
    }
    var reload = 1;
    var noReload = 0;

    $(function () {
        data_top();
        canvas();
        list(listUrl, elem, queryData, temp1, $template1);
        add();
        $userParent.hover(function () {
            $userChild.toggle();
        })
    })

    function login() {
        window.location.href = "/tb/login";
    }

    function add() {
        $publish.click(function () {
            //console.log(layedit.getSelection(index));//获取编辑器选中的文本 参数 index： 同上
            //console.log(layedit.getContent(index)); //获得编辑器的内容 参数 index： 即执行layedit.build返回的值
            //console.log(layedit.getText(index)); //获得编辑器的纯文本内容
            var addData = {
                "noteTitle": $noteTitle.val(),
                "noteDesc": layedit.getContent(index),
                "noteDescShort": layedit.getText(index)
            }
            console.log(addData);
            if (addData.noteTitle == null || addData.noteTitle == '') {
                layer.msg("标题不能为空");
                return
            }
            console.log(addData);
            $.ajax({
                url: addUrl,
                type: get,
                dataType: json,
                data: addData,
                success: function (data) {
                    msg(data, reload)
                }
            })
        })
    }

    function msg(data, reload) {
        if (data.code == 200) {
            layer.msg(data.msg);
            // alert(data.msg);
            if (reload == 1) {
                setTimeout("location.reload();", 1000)
            }
        } else if (data.code == 500) {

        }
    }

    function data_top() {
        var html = '';
        html += '<div >';
        html += ' <canvas id="canvas" style="position:fixed;z-index:-2;height:auto;width: 100%; "></canvas>';
        html += '</div>';
        html += '<div id="header" class="container bg-fff">';
        html += '  <div class="row">';
        html += '     <div class="col-md-1 col-sm-0"></div>';
        html += '     <div class="col-md-8 col-sm-10">';
        html += '         <nav class="navbar navbar-default bg-fff" role="navigation">';
        html += '         <div class="container-fluid">';
        html += '             <div>';
        html += '              <ul class="nav navbar-nav">';
        html += '                 <li>';
        html += '                     <div class="navbar-header">';
        html += '                         <a id="logo" class="navbar-brand" href="#">Logo</a>';
        html += '                      </div>';
        html += '                 </li>';
        html += '                 <li>';
        html += '                     <a href="#"><b>首页</b></a>';
        html += '                 </li>';
        html += '                 <li>';
        html += '                     <a href="#">广场</a>';
        html += '                 </li>';
        html += '                  <li>';
        html += '                      <a href="#wirte">发贴</a>';
        html += '                 </li>';
        html += '                 <li>';
        html += '                     <form class="navbar-form navbar-left" role="search">';
        html += '                     <div class="form-group">';
        html += '                         <input type="text" class="form-control" placeholder="Search" size="32">';
        html += '                     </div>';
        html += '                         <button type="button" class="btn btn-default" id="search">寻找</button>';
        html += '                      </form>';
        html += '                  </li>';
        html += '               </ul>';
        html += '             </div>';
        html += '           </div>';
        html += '       </nav>';
        html += '   </div>';
        html +='    <div class="col-md-2 col-sm-2">';
        html +='        <ul class="nav navbar-nav">';
        html +='            <li class="top_li"><span id="registerBtn">[&nbsp;&nbsp;注册&nbsp;&nbsp;]</span></li>';
        html +='            <li class="top_li"><span id="loginBtn" onclick="login()">[&nbsp;&nbsp;登录&nbsp;&nbsp;]</span></li>';
        html +='        </ul>';
        html +='      </div>';
        html +='     <div class="col-md-1 col-sm-0"></div>';
        html +='    </div>';
        html +='</div>';
        $(".header").html(html);
    }
});



    // <c:if test="${ not empty user}">
    //     <ul class="nav navbar-nav">
    //     <li class="top_li"><span >[&nbsp;&nbsp;欢迎：&nbsp;&nbsp;]</span></li>
    // <li class="top_li" id="user_parent">
    //     <span>[&nbsp;&nbsp;${user.userName}&nbsp;&nbsp;]</span>
    // <dl id="user_child">
    //     <dd>
    //     <a href="/admin/">个人管理</a>
    //     </dd>
    //     <dd>
    //     <a href="">安全退出</a>
    //     </dd>
    //     </dl>
    //     </li>
    //     </ul>
    //     </c:if>
    //     </div>
        // <script>
        // function login() {
        //     window.location.href="/login";
        // }
        // </script>

/****************************
 * 画布
 * @type {HTMLElement | null}
 */
function canvas(){
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var cw = canvas.width = window.innerWidth,
        cx = cw / 2;
    var ch = canvas.height = window.innerHeight,
        cy = ch / 2;

    ctx.fillStyle = "#000";
    var linesNum = 16;
    var linesRy = [];
    var requestId = null;

    function Line(flag) {
        this.flag = flag;
        this.a = {};
        this.b = {};
        if (flag == "v") {
            this.a.y = 0;
            this.b.y = ch;
            this.a.x = randomIntFromInterval(0, ch);
            this.b.x = randomIntFromInterval(0, ch);
        } else if (flag == "h") {
            this.a.x = 0;
            this.b.x = cw;
            this.a.y = randomIntFromInterval(0, cw);
            this.b.y = randomIntFromInterval(0, cw);
        }
        this.va = randomIntFromInterval(25, 100) / 100;
        this.vb = randomIntFromInterval(25, 100) / 100;

        this.draw = function() {
            ctx.strokeStyle = "#ccc";
            ctx.beginPath();
            ctx.moveTo(this.a.x, this.a.y);
            ctx.lineTo(this.b.x, this.b.y);
            ctx.stroke();
        }

        this.update = function() {
            if (this.flag == "v") {
                this.a.x += this.va;
                this.b.x += this.vb;
            } else if (flag == "h") {
                this.a.y += this.va;
                this.b.y += this.vb;
            }

            this.edges();
        }

        this.edges = function() {
            if (this.flag == "v") {
                if (this.a.x < 0 || this.a.x > cw) {
                    this.va *= -1;
                }
                if (this.b.x < 0 || this.b.x > cw) {
                    this.vb *= -1;
                }
            } else if (flag == "h") {
                if (this.a.y < 0 || this.a.y > ch) {
                    this.va *= -1;
                }
                if (this.b.y < 0 || this.b.y > ch) {
                    this.vb *= -1;
                }
            }
        }

    }

    for (var i = 0; i < linesNum; i++) {
        var flag = i % 2 == 0 ? "h" : "v";
        var l = new Line(flag);
        linesRy.push(l);
    }

    function Draw() {
        requestId = window.requestAnimationFrame(Draw);
        ctx.clearRect(0, 0, cw, ch);

        for (var i = 0; i < linesRy.length; i++) {
            var l = linesRy[i];
            l.draw();
            l.update();
        }
        for (var i = 0; i < linesRy.length; i++) {
            var l = linesRy[i];
            for (var j = i + 1; j < linesRy.length; j++) {
                var l1 = linesRy[j]
                Intersect2lines(l, l1);
            }
        }
    }

    function Init() {
        linesRy.length = 0;
        for (var i = 0; i < linesNum; i++) {
            var flag = i % 2 == 0 ? "h" : "v";
            var l = new Line(flag);
            linesRy.push(l);
        }

        if (requestId) {
            window.cancelAnimationFrame(requestId);
            requestId = null;
        }

        cw = canvas.width = window.innerWidth,
            cx = cw / 2;
        ch = canvas.height = window.innerHeight,
            cy = ch / 2;

        Draw();
    };

    setTimeout(function() {
        Init();

        addEventListener('resize', Init, false);
    }, 15);

    function Intersect2lines(l1, l2) {
        var p1 = l1.a,
            p2 = l1.b,
            p3 = l2.a,
            p4 = l2.b;
        var denominator = (p4.y - p3.y) * (p2.x - p1.x) - (p4.x - p3.x) * (p2.y - p1.y);
        var ua = ((p4.x - p3.x) * (p1.y - p3.y) - (p4.y - p3.y) * (p1.x - p3.x)) / denominator;
        var ub = ((p2.x - p1.x) * (p1.y - p3.y) - (p2.y - p1.y) * (p1.x - p3.x)) / denominator;
        var x = p1.x + ua * (p2.x - p1.x);
        var y = p1.y + ua * (p2.y - p1.y);
        if (ua > 0 && ub > 0) {
            markPoint({
                x: x,
                y: y
            })
        }
    }

    function markPoint(p) {
        ctx.beginPath();
        ctx.arc(p.x, p.y, 2, 0, 2 * Math.PI);
        ctx.fill();
    }

    function randomIntFromInterval(mn, mx) {
        return ~~(Math.random() * (mx - mn + 1) + mn);
    }
}
