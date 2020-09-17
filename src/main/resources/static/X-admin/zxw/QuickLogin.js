function quickLogin(url, closeBtn) {
    layer.closeAll();
    url = url != null && $.trim(url) != "" ? $.trim(url) : "/";

    var Ajax = $.ajax({
        url: "/login/Shared/AjaxForCheckIE",
        type: "POST",
        data: {},
        dataType: "jsonp",
        success: function (data) {

            var content = "<div id=\"quickLogin\" style=\"width: 400px;box-sizing: border-box;color: #636363;\">" +
    "    <form method=\"post\" onsubmit=\"return false;\">" +
    "        <div style=\"height: 40px;background-color: #8cc63e;padding: 0px 10px 0px 10px;color: #fff\"><span style=\"float: left;line-height: 40px;\">用户登录</span>" + (closeBtn == false ? "" : "<a id=\"closeQuickLogin\" href=\"javascript:;\" style=\"color: #fff;float: right;line-height: 40px;text-decoration: none\"><i class=\"layui-icon\">&#x1006;</i></a>") + "</div>" +
    "        <div style=\"height: 40px;padding: 0px 30px 0px 30px;margin-top: 20px;\">" +
    "            <input id=\"uid\" name=\"uid\" type=\"text\" " + (data.isIE ? "" : "readonly onfocus=\"this.removeAttribute('readonly');\"") + " class=\"layui-input\" style=\"color: #636363;height: 38px;line-height: 38px;line-height: 38px\9;border-width: 1px;border-style: solid;border-color: #e6e6e6;background-color: #fff;border-radius: 2px;width: 100%;padding-left: 10px;\" placeholder=\"用户名 / 邮箱 / 手机号\"/>" +
    "        </div>" +
    "        <div style=\"height: 40px;padding: 0px 30px 0px 30px;margin-top: 20px;\">" +
    //"<input type=\"hidden\">"+
    "            <input id=\"psw\" name=\"psw\" type=\"password\" autocomplete=\"off\" " + (data.isIE ? "" : "readonly onfocus=\"this.removeAttribute('readonly');\"") + " class=\"layui-input\" style=\"color: #636363;height: 38px;line-height: 38px;line-height: 38px\9;border-width: 1px;border-style: solid;border-color: #e6e6e6;background-color: #fff;border-radius: 2px;width: 100%;padding-left: 10px;\" " + (data.isIE ? "" : "placeholder=\"登录密码\"") + " />" +
    "        </div>" +
    "        <div style=\"height: 40px;padding: 0px 30px 0px 30px;margin-top: 20px;\">" +
    "            <button type=\"submit\" style=\"width: 100%;height: 40px;background-color: #8cc63e;color: #fff;font-size: 15px;padding: 0px;margin: 0px;border: none;border-radius: 3px;\">登 录</button>" +
    "        </div>" +
    "        <div style=\"height: 40px;padding: 0px 30px 0px 30px;margin-top: 20px;\">" +
    "            <div style=\"float: left\"><input type=\"checkbox\" id=\"rememberLogin\" value=\"true\" style=\"vertical-align: middle;margin-top: 2px;\"> <label for=\"rememberLogin\" style=\"vertical-align: middle;margin-top: 2px;\">下次自动登录</label></div>" +
    "        <div style=\"float: right\"><a href=\"/login/newlogin/Register?redirect_url=" + encodeURIComponent(url) + "\" style=\"margin: 0px 5px 0px 5px;color: #8cc63e;text-decoration: none;display:inline-block;padding:2px 3px;border:solid 1px #8cc63e\">注册账号</a> <a href=\"/login/newlogin/ForgetPassword\" style=\"margin: 0px 5px 0px 5px;color: #8cc63e;text-decoration: none\">忘记密码</a></div>" +
    "        </div>" +
    "        <div id=\"errorText\" style=\"height: 40px;padding: 0px 30px 0px 30px;text-align: center;color: #bb0005\">" +
    "        </div>" +
    "        <div style=\"height: 20px;box-sizing: border-box;border-top: 1px solid #efefef;margin-top: 10px;position: relative\"><div style=\"position: absolute;top: -15px;left: 120px;text-align: center;color: #8cc63e;width: 160px;background-color: #fff;height: 30px;line-height: 30px;\">使用第三方登录</div></div>" +
    "        <div style=\"height: 30px;line-height:30px; text-align: center;padding-bottom: 15px;\">" +
    "        <a id=\"QQBundle\" href=\"javascript:;\" style=\"text-decoration: none;color: #afafaf;\" title=\"QQ\"><i class=\"Hui-iconfont Hui-iconfont-share-qq\" style=\"font-size: 28px;vertical-align: middle\"></i></a>" +
    "        <a id=\"WXBundle\" href=\"javascript:;\" style=\"text-decoration: none;margin: auto 15px;color: #afafaf;\" title=\"微信\"><i class=\"Hui-iconfont Hui-iconfont-share-weixin\" style=\"font-size: 28px;vertical-align: middle\"></i></a>" +
    "        <a id=\"WBBundle\" href=\"javascript:;\" style=\"text-decoration: none;color: #afafaf;\" title=\"微博\"><i class=\"Hui-iconfont Hui-iconfont-share-weibo\" style=\"font-size: 28px;vertical-align: middle\"></i></a>" +
    "    </div>" +
    "    </form>" +
    "</div>";


            var index = layer.open({
                type: 1,
                title: false,
                content: content,
                closeBtn: 0,
                area: '400px',
                shade: 0.6,
                success: function () {

                    $("#quickLogin input").placeholder({ isUseSpan: true, onInput: false });//开启提示

                    $("#closeQuickLogin").on("click", function () {
                        layer.close(index);
                    });

                    $("#quickLogin button").on("click", function () {

                        if ($.trim($("#quickLogin #uid").val()) == "" || $.trim($("#quickLogin #psw").val()) == "") {
                            $("#quickLogin #errorText").css("color", "#bb0005").html("请输入账号密码");
                            return;
                        }

                        var Ajax = $.ajax({
                            url: "/login/NewLogin/AjaxForQuicklogin",
                            type: "POST",
                            data: { loginStr: $("#quickLogin #uid").val(), pwd: $("#quickLogin #psw").val(), isRememberlogin: $("#rememberLogin").prop("checked") ? true : false },
                            dataType: "json",
                            beforeSend: function () {
                                $("#quickLogin #errorText").css("color", "#6c53e2").html("正在登录...");
                            },
                            error: function () {
                                $("#quickLogin #errorText").css("color", "#bb0005").html("网络错误,请稍后再试");
                            },
                            success: function (data) {
                                if (!data.success) {
                                    $("#quickLogin #errorText").css("color", "#bb0005").html(data.msg);
                                    if (data.isChangeCaptcha)
                                        window.location = "/login/NewLogin/Index?redirect_url=" + encodeURIComponent(url);
                                }
                                else {
                                    $("#quickLogin #errorText").css("color", "#8cc63e").html(data.msg);

                                    setTimeout(function () {
                                        window.location = url;
                                    }, 1500);
                                }
                            }
                        });

                    });

                    //QQ授权
                    $("#quickLogin #QQBundle").on("click", function () {
                        //绑定
                        $.ajax({
                            url: "/login/NewLogin/AjaxForGoToQQAuthorize",
                            type: "POST",
                            data: { redirectUrl: encodeURIComponent(url) },
                            success: function (response) {
                                if (response.success) {
                                    window.location = response.url;
                                }
                            }
                        });
                    }).on("mouseenter", function () {
                        $(this).css("color", "#1796ef");
                    }).on("mouseout", function () {
                        $(this).css("color", "#afafaf");
                    });


                    //微信授权
                    $("#quickLogin #WXBundle").on("click", function () {

                        $.ajax({
                            url: "/login/NewLogin/AjaxForGoToWXAuthorize",
                            type: "POST",
                            data: { redirectUrl: encodeURIComponent(url) },
                            success: function (response) {

                                var index = layer.open({
                                    type: 1,
                                    title: false,
                                    content: "<div style=\"width: 300px;height: 400px;margin: auto\" id=\"WxLoginContainer\"></div>",
                                    closeBtn: 1,
                                    shade: 0.8,
                                    area: ['400px', '410px'],
                                    success: function () {
                                        var obj = new WxLogin({
                                            self_redirect: true,
                                            id: "WxLoginContainer",
                                            appid: "wx90fd052cac8099a6",
                                            scope: "snsapi_login",
                                            redirect_uri: window.location.protocol == "https:" ? "https://www.51zxw.net/login/NewLogin/wxcallback" : "http://www.51zxw.net/login/NewLogin/wxcallback",
                                            state: response.state
                                        });

                                        function test() {
                                            $.ajax({
                                                url: "/login/NewLogin/AjaxForCheckWXlogin",
                                                type: "POST",
                                                data: {},
                                                isShowLoading: false,
                                                error: function () { },
                                                success: function (response) {
                                                    if (response.success == 0) {
                                                        setTimeout(test, 1500);
                                                    }
                                                    else if (response.success == 1) {
                                                        if (response.isNewUser) {
                                                            window.location = "/login/NewLogin/WXBundleAsk";
                                                        }
                                                        else {
                                                            window.location = response.url;
                                                        }
                                                    }
                                                    else if (response.success == 2) {
                                                        $("#quickLogin #errorText").css("color", "#bb0005").html("微信授权失败,请重试");
                                                    }
                                                }
                                            });
                                        }
                                        test();

                                    }
                                });

                            }
                        });

                    }).on("mouseenter", function () {
                        $(this).css("color", "#09bb07");
                    }).on("mouseout", function () {
                        $(this).css("color", "#afafaf");
                    });


                    //微博授权
                    $("#quickLogin #WBBundle").on("click", function () {
                        //绑定
                        $.ajax({
                            url: "/login/NewLogin/AjaxForGoToWBAuthorize",
                            type: "POST",
                            data: { redirectUrl: encodeURIComponent(url) },
                            success: function (response) {

                                if (response.success) {
                                    window.location = response.url;
                                }

                            }
                        });

                    }).on("mouseenter", function () {
                        $(this).css("color", "#e6162d");
                    }).on("mouseout", function () {
                        $(this).css("color", "#afafaf");
                    });

                }
            });


        }
    });
}