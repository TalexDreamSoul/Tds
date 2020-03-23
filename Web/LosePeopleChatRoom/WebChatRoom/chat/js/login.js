$(function() {
  var $username = $("#username"),    //用户名
      $password = $("#password"),   //注册密码
	  $login_btn = $("#login_btn");   //注册按钮
	  
  //获取所有输入框 并在获取焦点时 提示输入信息
  $("input[type='text'],input[type='password']").focus(function() {
    $(this).next("span").show();
  }).blur(function() {
    $(this).next("span").hide();
  })

  //按键弹起时判断用户名
  $username.keyup(function() {
    var reg = /^[\w]{5,9}$/;
	var result = reg.exec($(this).val());
    if($(this).val()) {
      if(!result) {
		  nextspan($(this), "用户名格式错误！", 1, 1);
	  }else{
		  nextspan($(this), "请输入用户名", 0, 0);
	  }
    }else{
	  nextspan($(this), "用户名不能为空！", 1, 1);
	}
  })
  
  
  //按键谈起时判断密码
  $password.keyup(function() {
    var reg = /^[a-zA-Z0-9]{6,16}$/;
	var result = reg.exec($(this).val());
    if($(this).val()) {
      if(!result) {
		  nextspan($(this), "密码格式错误！", 1, 1);
	  }else{
		  nextspan($(this), "请输入密码", 0, 0);
	  }
    }else{
	  nextspan($(this), "密码不能为空！", 1, 1);
	}
  })
  
  $login_btn.click(function() {     //提交时  所有验证通过时 执行if内的语句
    if(check()){
      $('#loginForm').submit();
    }
  })

  //用户名验证  5-9位 字母数字及下划线
  var reName = /^[\w]{5,9}$/;
  //密码验证  6-16位 字母数字及下划线
  var rePass = /^[a-zA-Z0-9]{6,16}$/;
  /**
   * 验证输入是否正确的函数 
   */
  function check() {
    if(!reName.test($username.val())) {         //用户名格式验证
      nextspan($username, "用户名格式错误！", 1, 1);
      return false;
    } else {
      nextspan($username, "请输入用户名", 0, 0);
    }

    if(rePass.test($password.val())) {        //密码格式验证
      nextspan($password, "请输入密码", 0, 0);
    } else {
      nextspan($password, "密码格式错误！", 1, 1);
      return false;
    }
    //所有验证通过时 返回true 用于判断
    return true;
  }
  /**
   * @param {Object} obj  要提示错误的对象
   * @param {String} msg  错误的信息
   * @param {Boolean} a   布尔值红色或蓝色提示框TRUE红色FALSE蓝色
   * @param {Boolean} b   控制显示隐藏  TRUE显示FALSE隐藏
   * 需要布局
   * author yu
   */
  function nextspan(obj, msg, a, b) {
    $(obj).next("span").text(msg).css({
      "background": a ? '#FA1A2C' : '#abcdef',
      "display": b ? "block" : 'none'
    });
  }
})