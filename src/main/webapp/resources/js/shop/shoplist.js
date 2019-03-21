$(function () {
    getlist();
    function getlist(e) {
        $.ajax({
            url: "/o2o_war/shopadmin/getshoplist",
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    //渲染shopList，显示用户名
                    handleList(data.shopList);
                    handleUser(data.user);
                }
            }
        });
    }

    function handleUser(data) {
        $('#user-name').text(data.name);
    }

    function handleList(data) {
        var html = '';
        data.map(function (item, index) {
            html += '<div class="row row-shop"><div class="col-40">' + item.shopName + '</div><div class="col-40">' + shopStatus(item.enableStatus) + '</div><div class="col-20">' + goShop(item.enableStatus, item.shopId) + '</div></div>';

        });
        $('.shop-wrap').html(html);
    }
//生成一个连接，点击进入，可以进入到店铺的详细信息中去
    function goShop(status, id) {
        if (status != 0 && status != -1) {
            return '<a href="/o2o_war/shopadmin/shopmanagement?shopId=' + id + '">进入</a>';
        } else {
            return '';
        }
    }
    //根据后端返回来的状态值用文字输出
    function shopStatus(status) {
        if (status == 0) {
            return '审核中';
        } else if (status == -1) {
            return '店铺非法';
        } else {
            return '审核通过';
        }
    }


    $('#log-out').click(function () {
        $.ajax({
            url: "/myo2o_war/shop/logout",
            type: "post",
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    window.location.href = '/myo2o_war/shop/ownerlogin';
                }
            },
            error: function (data, error) {
                alert(error);
            }
        });
    });


    getlist();
});
