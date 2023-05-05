window.onload  = function () { 
    var totalQuantity = 0; //商品总数
    
    var checkAlls = document.getElementsByClassName('checkAll'); //获取所有全选框
    var checkItems = document.getElementsByClassName('checkItem');
    var items = document.getElementsByClassName('row');
    var amounts = document.getElementsByClassName('amount');
    var totalAmount = document.querySelector('.totalAmount');
    var total = document.querySelector('.total');
    var totalPrice = 0;
    total.innerText = totalPrice.toFixed(2);

    //全选功能
    for (var i = 0; i < checkAlls.length; i++) {
        checkAlls[i].index = i;
        checkAlls[i].addEventListener('click', function () {
            totalQuantity = 0;
            changeStatus(this.checked);
            if(checkAlls[this.index == 0]){
                this.checked = true;
            }
            if(checkAlls[this.index == 1]){
                this.checked = false;
            }

            getTotal();
        })  
    }
    function changeStatus(status) {
        for (var i = 0; i < checkItems.length; i++) {
            checkItems[i].checked = status;
            if (status) {
                items[i].classList.add('bg');
                totalQuantity += parseInt(amounts[i].value);
            } else {
                items[i].classList.remove('bg');
            }
        }
        totalAmount.innerText = totalQuantity;
    }


    //减少商品数量功能
    var leftbtns = document.getElementsByClassName('count_d');
    for (var i = 0; i < leftbtns.length; i++) {
        leftbtns[i].index = i;
        leftbtns[i].addEventListener('click', function () {
            var amount = amounts[this.index].value;
            if (amount == 1) {
                return;
            } else {
                amount--;
            }
            amounts[this.index].value = amount;
            // if (!checkItems[this.index].checked) {
            //     checkItems[this.index].checked = true;
            //     items[this.index].classList.add('bg');
            // }
            getSubtotal(this.index);
            getGoodsNum();
            getTotal();
        })
    }
    //增加商品数量功能
    var rightbtns = document.getElementsByClassName('count_i');
    for (var i = 0; i < rightbtns.length; i++) {
        rightbtns[i].index = i;
        rightbtns[i].addEventListener('click', function () {
            var amount = amounts[this.index].value;
            amount++;
            amounts[this.index].value = amount;
            // if (!checkItems[this.index].checked) {
            //     checkItems[this.index].checked = true;
            //     items[this.index].classList.add('bg');
            // }
            getSubtotal(this.index);
            getGoodsNum();
            getTotal();
        })
    }
     //计算小计
     var perPrice = document.getElementsByClassName('perPrice');
     var smallPrice = document.getElementsByClassName('smallPrice');
     function getSubtotal(index) {
         var Price = parseInt(amounts[index].value) * perPrice[index].innerText;
         smallPrice[index].innerText = Price.toFixed(2);
     }
     //计算商品总数
     function getGoodsNum() {
         var flag = false;
         totalQuantity = 0;
         for (var i = 0; i < checkItems.length; i++) {
             if (checkItems[i].checked) {
                 totalQuantity += parseInt(amounts[i].value);
                 flag = true;
             }
         }
         if (!flag) {
             for (var i = 0; i < checkAlls.length; i++) {
                 checkAlls[i].checked = false;
             }
         }
         totalAmount.innerText = totalQuantity;
     }
     //选择或取消单个商品
     for (var i = 0; i < checkItems.length; i++) {
         checkItems[i].index = i;
         checkItems[i].addEventListener('click', function () {
             if (this.checked) {
                 items[this.index].classList.add('bg');
             } else {
                 items[this.index].classList.remove('bg');
             }
             getGoodsNum();
             getTotal();
         })
     }
     //计算总价
     function getTotal() {
         totalPrice = 0;
         for (var i = 0; i < checkItems.length; i++) {
             if (checkItems[i].checked) {
                 totalPrice += parseFloat(smallPrice[i].innerText);
             }
         }
         total.innerText = totalPrice.toFixed(2);
     }
    //  //删除单件商品（模拟）
    // var deletebtns = document.getElementsByClassName('delete');
    // for (var i = 0; i < deletebtns.length; i++) {
    //     deletebtns[i].index = i;
    //     deletebtns[i].addEventListener('click', function () {
    //         items[this.index].remove();
    //         //更新元素的索引
    //         for (var i = 0; i < items.length; i++) {
    //             deletebtns[i].index = i;
    //             leftbtns[i].index = i;
    //             rightbtns[i].index = i;
    //             checkItems[i].index = i;
    //         }
    //         getGoodsNum();
    //         getTotal();
    //     })
    // }
 }

