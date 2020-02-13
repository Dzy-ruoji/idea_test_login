/*
    表单校验(正则表达式)
        1.用户名，长度8到20位
        2.密码：单词字符，长度8到20位
        3.邮箱：邮件格式
        4.姓名： 非空
        5.手机号：手机号格式
        6.出生日期： 非空
        7.验证码：
        表单提交时，调用所有校验方法
        某一组件失去焦点时，调用对应的校验方法
*/
//校验用户名,
function checkUsername() {
    //1.获取用户名
    var username=$("#username").val();
    var flag
    if(username!=''){
        //用户名合法
        $("#username").css("border","3px solid green");
        flag=true;
    }else{
        //用户名非法
        $("#username").css("border","3px solid red");
        flag=false;
    }

    return flag;
}
//校验密码
function checkPassword(){
    //1.获取密码
    var password=$("#password").val();
    //2.定义正则
    var reg_password=/^[^\s]{6,20}$/
    //3.判断，给出提示信息
    var flag=reg_password.test(password);
    if(flag){
        //密码格式正确
        $("#password").css("border","2px solid green");
    }else{
        //密码格式错误
        $("#password").css("border","2px solid red");
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

//提交时校验
$("#registerForm").submit(function () {
 //1.发送数据到服务器
 if( checkUsername()&&checkPassword()&&checkEmail()&&checkName()&&checkTel()){
     //校验通过，发送ajax请求，提交数据
     $.post("registerUserServlet",$(this).serialize(), function(data){
        //处理服务器响应的数据 data (flag:true errorMsg:"注册")
         if(data.flag){
             //注册成功，跳转成功页面
             location.href="login.html";
         }else{
             //注册失败
                $("#error_Msg").html(data.errorMsg);
         }
     })
 }


  return false;
    //如果这个方法没有返回值，或者放回true。则表单提交。如果返回false，则表单不提交
});
//失去焦点时校验
$("#username").blur(checkUsername);
$("#password").blur(checkPassword);
$("#email").blur(checkEmail);
$("#name").blur(checkName);
$("#telephone").blur(checkTel);


function refreshCode() {
    //切换验证码
    //1.获取验证码的图片对象
    var img_check = document.getElementById("img_check")
    //2.设置其src属性，加时间戳
    img_check.src="/checkCodeServlet?time="+new Date().getTime();
}
