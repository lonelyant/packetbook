$(document).ready(function () {
    toastr.options = {
        closeButton: true,
        debug: false,
        progressBar: true,//显示进度条
        positionClass: "toast-top-full-width",
        onclick: null,
        showDuration: "300",
        hideDuration: "1000",
        timeOut: "3000",
        extendedTimeOut: "1000",
        showEasing: "swing",
        hideEasing: "linear",
        showMethod: "fadeIn",
        hideMethod: "fadeOut"
    };
    // 列表更改为 购物 时显示 商品名 输入框
    $("#category").change(function () {
        if ($("#category  option:selected").text() == "购物"){
            $("#category_info_div").removeClass("hide");
        }else{
            $("#category_info_div").addClass("hide");
            $("#category_info").val("");
        }
    });

    // 提交验证
    $("#bookkeeping_submit").click(function () {
        var money = $("#money").val();
        var category = $("#category  option:selected").text();
        var fromWho = $("#fromWho  option:selected").text();
        var category_info = $("#category_info").val();
        if(!$("#category_info_div").hasClass("hide") && (category_info=="" || category_info==null )){
            toastr.error("商品名不能为空");
            $("#category_info_div").addClass("has-error");
            return;
        }
        if(category_info.length>8){
            $("#category_info_div").addClass("has-error");
            toastr.error("商品名长度不得超过8个字");
            return;
        }

        if (money == null || money == "" || money<=0){
            toastr.error("金额不能为空或负数，或者包含非数字");
            $("#money_div").addClass("has-error");
            return;
        }
        $("#bookkeeping_category").text(category + (category_info==""?"":" - "+category_info));
        $("#bookkeeping_money").text(money);
        $("#bookkeeping_fromWho").text(fromWho);
        $("#bookkeeping_confirm").modal("show");

        $("#bookkeeping_confirm_submit").click(function () {
            $("#bookkeeping_form").submit();
        });

    });

    // 输入框聚焦移除错误显示
    $("#money").focus(function () {
        $("#money_div").removeClass("has-error");
    });
    $("#category_info").focus(function () {
        $("#category_info_div").removeClass("has-error");
    });




    var isSuccess = $("#isSuccess").text();
    //alert("..."+isSuccess);
    if(isSuccess == "success"){
        toastr.success("成功！完美的又花了一笔钱");
        // 异步请求服务器删除session，避免刷新后再次显示提示信息
        $.ajax({url:"/removeIsSuccess",async:false});
    }else if(isSuccess == "fail"){
        toastr.error("小傻瓜，添加失败啦，服务器可能被曦曦炸啦！");
        $.ajax({url:"/removeIsSuccess",async:false});
    }


    // rich值大于0，设为绿色背景
    $(".btn.btn-default.btn-info.btn-group-lg").each(function () {
        var rich = this.children[1].innerText;
        if(rich > 0){
            $(this).css({background:"#66CD00",border: 0});
        }
    });

});