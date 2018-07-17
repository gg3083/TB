<%--
  Created by IntelliJ IDEA.
  User: GG
  Date: 2018/5/26
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/index.css">
    <link rel="stylesheet" href="/static/css/bootstrap/bootstrap.min.css" />
    <link rel="stylesheet" href="/lib/layui/css/layui.css" />
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="container" id="main">
    <div class="row">
        <div class="col-md-1 col-sm-0" ></div>
        <div class="col-md-7 col-sm-8" >
            <div id="left" >
                <div id="title" >
                    1
                </div>

                <div id="content" >
                    <hr style="height:1px; border:none;border-bottom: 1px dotted #E4E6EB;background: #fff;" />
                    <div id="template1">

                    </div>
                    <div id="page1" style="float: right;">

                    </div>
                </div>
                <div id="wirte">
                    <div><span style="font-weight: bold;">发表新帖</span></div><br />
                    <form class="form-horizontal" role="form" id="myform">
                        <input  type="text" name="noteTitle" class="form-control" id="noteTitle" />
                        <br />
                        <textarea id="noteDesc" style="display: none;background: #fff;" name="noteDesc"></textarea>
                        <br />
                        <div style="float: right;">
                            <input type="checkbox">匿名
                            <button type="button" class="btn btn-default" style="color: blue" id="publish">发表</button></div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-4" >
            <div id="right" >
                <div id="info">
                    2
                </div>
                <div id="other">
                    2
                </div>

            </div>
        </div>
        <div class="col-md-1 col-sm-0"></div>
    </div>
</div>
</body>
<script type="text/javascript" src="/static/js/base.js"></script>
<script type="text/javascript" src="/lib/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/page.js"></script>
<script type="text/javascript" src="/static/js/index.js"></script>
<script type="text/javascript" src="/static/js/template-web.js"></script>

<script id="temp1" type="text/html">
    {{each list as value}}
    <div class="noteTitle">
        {{value.noteTitle}}
    </div>
    <div class="noteDesc">
        {{value.noteDescShort}}
    </div>
    <hr style="height:1px; border:none;border-bottom: 1px dotted #E4E6EB;background: #fff;" />
    {{/each}}
</script>
<script>

</script>
</html>
