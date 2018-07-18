
document.onkeydown = function(e){
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
        login();
    }
}
$(function () {
    createCode();
})

$("#bs").click(function () {
    var loginDate=$("#myform").serialize();
    $.ajax({
        url:"/",
        data:loginDate,
        type:"post",
        datetype:"json",
        success:function(data){
            window.location.href="/";
        }
    })
})

function showCheck(a){
    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");
    ctx.clearRect(0,0,1000,1000);
    ctx.font = "80px 'Microsoft Yahei'";
    ctx.fillText(a,0,100);
    ctx.fillStyle = "white";
}
var code ;
function createCode(){
    code = "";
    var codeLength = 4;
    var selectChar = new Array(1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','j','k','l','m','n','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');
    for(var i=0;i<codeLength;i++) {
        var charIndex = Math.floor(Math.random()*60);
        code +=selectChar[charIndex];
    }
    if(code.length != codeLength){
        createCode();
    }
    showCheck(code);
}

function validate () {
    var inputCode = document.getElementById("J_codetext").value.toUpperCase();
    var codeToUp=code.toUpperCase();
    if(inputCode.length <=0) {
        document.getElementById("J_codetext").setAttribute("placeholder","验证码");
        createCode();
        return false;
    }
    else if(inputCode != codeToUp ){
        document.getElementById("J_codetext").value="";
        document.getElementById("J_codetext").setAttribute("placeholder","验证码");
        createCode();
        return false;
    }
    else {
        window.open(document.getElementById("J_down").getAttribute("data-link"));
        document.getElementById("J_codetext").value="";
        createCode();
        return true;
    }

}
/**********************/
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