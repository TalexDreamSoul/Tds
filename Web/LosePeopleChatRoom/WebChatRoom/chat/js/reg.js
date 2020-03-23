$(function() {
    var $name = $("#petname"),    //聊天昵称
        $user = $("#user"),     //注册用户名
        $pass = $("#password"),   //注册密码
        $repass = $("#repassword"),   //确认密码
        $reg_btn = $("#reg-btn"),   //注册按钮
        $img = $("#reg-head"),  //头像
        $pass_info = $(".pass-info"),  //密码提示信息

    //按键弹起时判断昵称
    $name.keyup(function() {
        var reg = /^[0-9]{5,10}$/;
        var result = reg.exec($(this).val());
        // document.getElementById("reg-haed").src="http://q1.qlogo.cn/g?b=qq&nk=" + result + "&s=640";
        if($(this).val()) {
            if(!result) {
                nextspan($(this), "无效的QQ号码", 1, 1);
            }else{
                nextspan($(this), "QQ号码只能由数字组成", 0, 0);
            }
        }else{
            nextspan($(this), "QQ号码只能由数组组成", 0, 1);
        }
    })

    //按键弹起时判断用户名
    $user.keyup(function() {
        var reg = /^[\w]{5,9}$/;
        var result = reg.exec($(this).val());
        if($(this).val()) {
            if(!result) {
                nextspan($(this), "用户名格式错误！", 1, 1);
            }else{
                //用户名格式合法
                nextspan($(this), "用户名为5-9位数字、字母下划线组合", 0, 0);
                var username = $(this).val(); //获取用户输入的用户名
                $.ajax({
                    type: "POST",  //参数传递的方式（get/post）
                    url: "check.php",  //服务器端的响应文件地址
                    data: {"username":username}, //参数
                    success: function(msg){  //服务器响应成功之后调用的回调函数
                        if(msg == 1){
                            nextspan($user,'用户名可用',0,1);
                        }else{
                            nextspan($user,'用户名已被占用',1,1);
                        }
                    }
                });
            }
        }else{
            nextspan($(this), "用户名为5-9位数字、字母下划线组合", 0, 1);
        }
    })

    //按键谈起时判断密码
    $pass.keyup(function() {
        var reg = /^[a-zA-Z0-9]{6,16}$/;
        var result = reg.exec($(this).val());
        if($(this).val()) {
            if(!result) {
                nextspan($(this), "密码格式错误！", 1, 1);
            }else{
                nextspan($(this), "密码为6-16位数字、字母组合", 0, 0);
            }
        }else{
            nextspan($(this), "密码为6-16位数字、字母组合", 0, 1);
        }
    })

    //按键弹起时 验证两次密码是否一致
    $repass.keyup(function() {
        if($pass.val() != $repass.val()) {    //密码与确认密码是否相等
            nextspan($repass, "密码不一致", 1, 1);
        } else {
            nextspan($repass, "确认密码", 0, 0);
        }
    })

    $reg_btn.click(function() {     //提交时  所有验证通过时 执行if内的语句
        if(check()){
            $('#reg-form').submit();
        }
    })

    //昵称验证
    var nickName = /^[0-9]{5,10}$/;
    //用户名验证  5-9位 字母数字及下划线
    var reName = /^[\w]{5,9}$/;
    //密码验证  6-16位 字母数字及下划线
    var rePass = /^[a-zA-Z0-9]{6,16}$/;
    /**
     * 验证输入是否正确的函数
     */
    function check() {
        if(!nickName.test($name.val())) {         //用户名格式验证
            nextspan($name, "无效的QQ号码", 1, 1);
            return false;
        } else {
            nextspan($name, "QQ号码只能是10位以内的纯数字", 0, 0);
        }
        if(!reName.test($user.val())) {         //用户名格式验证
            nextspan($user, "用户名格式错误！", 1, 1);
            return false;
        } else {
            nextspan($user, "输入5-9位的英文数字组合", 0, 0);
        }

        if(rePass.test($pass.val())) {        //密码格式验证
            nextspan($pass, "输入6-16位的数字、字母组合", 0, 0);
        } else {
            nextspan($pass, "密码格式错误！", 1, 1);
            return false;
        }

        if($pass.val() != $repass.val()) {    //检查两次密码是否一致
            nextspan($repass, "密码不一致！", 1, 1);
            return false;
        }else{
            nextspan($repass, "重复密码", 0, 0);
        }

        //所有验证通过时 返回true 用于判断
        return true;
    }
    /**
     * @param {Object} obj  要提示错误的对象
     * @param {String} msg  错误的信息
     * @param {Boolean} a   布尔值红色或蓝色提示框TRUE红色FALSE蓝色
     * @param {Boolean} b   控制显示隐藏  TRUE显示FALSE隐藏
     */
    function nextspan(obj, msg, a, b) {
        $(obj).next("span").text(msg).css({
            "background": a ? '#FA1A2C' : '#abcdef',
            "display": b ? "block" : 'none'
        });
    }

})