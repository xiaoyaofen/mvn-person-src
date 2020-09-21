//类级别
//异步请求封装
$.extend({
    AjaxMethod: function (options) {

        //默认设置
        var defaults = {
            type: "POST",
            url: "",
            data: {},
            dataType: "json",
            error: null,
            errorText: "网络异常,稍后再试",
            beforeSend: null,
            success: null,
            complete: null,
            isShowLoading: true,
            loadingText: "加载中"
        };
        options = $.extend(defaults, options);

        if ($.trim(defaults.url) != "") {

            var layerIndex;//加载层

            var Ajax = $.ajax({
                url: options.url,                                           //请求地址
                type: options.type ? options.type : "POST",                 //请求方式
                data: options.data,                                         //请求数据
                dataType: options.dataType ? options.dataType : "json",     //返回数据类型
                beforeSend: function () {                                   //请求前执行

                    if (options.isShowLoading) {
                        layerIndex = layer.msg(options.loadingText, { icon: 16, shade: 0.01, time: 0 });
                    }

                    if (options.beforeSend) {
                        options.beforeSend();
                    }
                },
                error: function () {                                        //错误回调

                    if (options.isShowLoading) {
                        layer.close(layerIndex);
                    }

                    if (options.error)
                        options.error();
                    else {
                        layer.msg(options.errorText, { shade: 0.01, time: 2000 });
                    }
                },
                success: function (data) {                                  //成功回调

                    if (options.isShowLoading) {
                        layer.close(layerIndex);
                    }

                    if (options.success)
                        options.success(data);
                },
                complete: function () {                                     //结束回调

                    if (options.complete)
                        options.complete();

                }
            });

            return Ajax;
        }
        else {
            console.log("异步请求地址为空");
        }
    }
});

//主站搜索验证
if ($("#webSearchForm").length > 0) {
    $("#webSearchForm").validate({
        submitHandler: function (form) {
            $.AjaxMethod({
                url: "/MainExtend/AjaxForCheckSearch.ashx",
                data: { keyWords: $("#webSearchForm #keyWordsT").val() },
                isShowLoading: false,
                success: function (response) {

                    if (!response.success) {
                        layer.msg(response.msg, {
                            shade: 0.01, time: 2000, end: function () {
                                layer.closeAll();
                            }
                        });
                    }
                    else {

                        $("#webSearchForm #keyWordsH").val(encodeURIComponent($('#webSearchForm #keyWordsT').val()));
                        $("#webSearchForm button").prop("disabled", true).find("i").remove();
                        $("#webSearchForm button").prepend("<i class=\"iconfont icon-sandglass2 searching\" style=\"font-size: 20px;\"></i>");
                        form.submit();

                    }

                }
            });
        }
    });
}