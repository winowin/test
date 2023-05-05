$('ul').on('click', 'li', function () {
    payType = $(this).index();
    var jfType = "支付宝";
    switch (payType) {
        case 0 :
            $(this).addClass("list");
            $(this).siblings().removeClass('list');
            $('#method1').attr('name','paymethod');
            $('#method2').removeAttr('name');
            $('#method3').removeAttr('name');
            break;
        case 1 :
            var jfType = "微信";
            $(this).addClass("list");
            $(this).siblings().removeClass('list');  //清除同级其他标签样式
            $('#method1').removeAttr('name');
            $('#method2').attr('name','paymethod');
            $('#method3').removeAttr('name');
            break;
        case 2 :
            var jfType = "银行卡";
            $(this).addClass("list");
            $(this).siblings().removeClass('list');
            $('#method1').removeAttr('name');
            $('#method2').removeAttr('name');
            $('#method3').attr('name','paymethod');
            break;
    }

});
