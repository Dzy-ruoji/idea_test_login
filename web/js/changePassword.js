//定义正则
var reg_password=/^[^\s]{6,20}$/

//校验密码
function checkPassword(){
    //1.获取密码
    var newPassword=$("#newPassword").val();
    var flag=reg_password.test(newPassword);
    if(flag){
        //密码格式正确
        $("#newPassword").css("border","2px solid green");
    }else{
        //密码格式错误
        $("#newPassword").css("border","2px solid red");
    }

    return flag;
}

//校验密码
function checkPassword2(){
    //1.获取密码
    var newPassword2=$("#newPassword2").val();
    var flag2=reg_password.test(newPassword2);
    if(flag2){
        //密码格式正确
        $("#newPassword2").css("border","2px solid green");
    }else{
        //密码格式错误
        $("#newPassword2").css("border","2px solid red");
    }

    return flag2;
}


//提交时校验
$("#changePasswordForm").submit(function () {
    //1.发送数据到服务器
    if( checkPassword()&&checkPassword2()){
        //校验通过，发送ajax请求，提交数据
        $.post("changePasswordServlet",$(this).serialize(), function(data){
            //处理服务器响应的数据 data (flag:true errorMsg:"注册")
            if(data.flag){
                //修改成功，跳转登录页面
                    alert("修改成功，请按确定重新登录");
                        location.href="login.html";
            }else{
                //修改失败
                $("#error_Msg").html(data.errorMsg);
            }
        })

    }


    return false;
    //如果这个方法没有返回值，或者放回true。则表单提交。如果返回false，则表单不提交
});

$("#return").onclick

$("#newPassword2").blur(checkPassword2);
$("#newPassword").blur(checkPassword);