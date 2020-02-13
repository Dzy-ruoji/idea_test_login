var getCount= function(){
    $.get("/onlineUsersServlet",{},function (data) {
        $("#count").html(data.data)
        if(data.flag==false){
            alert("你的号已在异地登录，请按确定退出");
            location.href="/html/login.html";
        }
    })
}

$.get("/findUserServlet", {}, function (data) {
    $("#tUsername").html(data.username);
    $("#tGender").html(data.gender);
    $("#tTelephone").html(data.telephone);
    $("#tName").html(data.name);
    $("#tBirthday").html(data.birthday);
    $("#tEmail").html(data.email);
    getCount;
})

var getMessage=function(){
    try {
        $.get("/findUserServlet", {}, function (data) {
            $("#tUsername").html(data.username);
            $("#tGender").html(data.gender);
            $("#tTelephone").html(data.telephone);
            $("#tName").html(data.name);
            $("#tBirthday").html(data.birthday);
            $("#tEmail").html(data.email);
            getCount;
        })
    } catch (e) {
        location.href="html/login.html";
        return null;
    }
}

setInterval(getCount, 3000);

