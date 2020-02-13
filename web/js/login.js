//提交时校验
$("#loginForm").submit(function () {
    //1.发送数据到服务器
        //发送ajax请求，提交数据
        $.post("loginServlet",$(this).serialize(), function(data){
            //处理服务器响应的数据 data (flag:true errorMsg:"登录失败")
            if(data.flag){
                //登录成功，跳转成功页面
                location.href="login_ok.html?time="+new Date().getTime();
            }else{

                //登录失败
                $("#error_Msg").html(data.errorMsg);
            }
        })
    return false;
})



        function refreshCode() {
            //切换验证码
        //1.获取验证码的图片对象
        var img_check = document.getElementById("img_check")
        //2.设置其src属性，加时间戳
        img_check.src="/checkCodeServlet?time="+new Date().getTime();
    }


