$('#order_type').on('click', 'li', function () {
    payType = $(this).index();
    var jfType = "全部订单";
    switch (payType) {
        case 0 :

            $(this).addClass("regTabs");
            $(this).siblings().removeClass('regTabs');
            break;

        case 1 :

            var jfType = "待支付";
            $(this).addClass("regTabs");
            $(this).siblings().removeClass('regTabs');  //清除同级其他标签样式
            break;

        case 2 :

            var jfType = "待收货";
            $(this).addClass("regTabs");
            $(this).siblings().removeClass('regTabs');
            break;

        case 3 :


            var jfType = "已收货";
            $(this).addClass("regTabs");
            $(this).siblings().removeClass('regTabs');
            break;

    }

});
