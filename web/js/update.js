//提交时校验
$("#updateForm").submit(function () {
    //1.发送数据到服务器
    if( checkEmail()&&checkName()&&checkTel()){
    //发送ajax请求，提交数据
    $.post("updateUserServlet",$(this).serialize(), function(data){
            if(data.flag){
                //修改成功，跳转成功页面
                location.href="login_ok.html";
             }
        })
    }
    return false;
})




$.get("/findUserServlet",{},function (data) {
    $("#username").val(data.username);
    $
    $("#name").val(data.name);
    $("#birthday").val(data.birthday);
    $("#telephone").val(data.telephone);
    $("#email").val(data.email);
})

$.get("/findUserServlet",{},function (data) {



})

//校验邮箱
function checkEmail(){
    //1.绑定邮箱
    var email=$("#email").val();
    //2.定义正则
    var reg_email=/^\w+@\w+\.\w+$/;
    //3.判断
    var flag=reg_email.test(email);
    if(flag){
        $("#email").css("border","2px solid green");
    }else {
        $("#email").css("border","2px solid red");
    }
    return flag;
}

//校验姓名
function checkName(){
    //1.绑定姓名
    var name=$("#name").val();
    //2.判断非空
    var flag
    if(name==''){
        $("#name").css("border","2px solid red");
        flag=false;
    }else{
        $("#name").css("border","2px solid green");
        flag=true;
    }
    return flag;
}

//校验手机号
function checkTel(){
    //1.绑定手机号
    var telephone=$("#telephone").val();
    //2.定义正则
    var reg_tel=/^\d{11}$/;
    //3.判断
    var flag=reg_tel.test(telephone);
    if(flag){
        $("#telephone").css("border","2px solid green");
    }else {
        $("#telephone").css("border","2px solid red");
    }
    return flag;
}

//校验邮箱
function checkEmail(){
    //1.绑定邮箱
    var email=$("#email").val();
    //2.定义正则
    var reg_email=/^\w+@\w+\.\w+$/;
    //3.判断
    var flag=reg_email.test(email);
    if(flag){
        $("#email").css("border","2px solid green");
    }else {
        $("#email").css("border","2px solid red");
    }
    return flag;
}





//失去焦点时校验
$("#email").blur(checkEmail);
$("#name").blur(checkName);
$("#telephone").blur(checkTel);

