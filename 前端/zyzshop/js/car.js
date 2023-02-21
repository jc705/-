$(function () {
    
    //初始化选中函数
    function ischecked(){
        var count = 0
        var len = $('#cart-list').find('.j-checkbox').length
        $.each($('#cart-list').find('.j-checkbox'),function(){
            var bool = $(this).attr('data-check')
            if(bool === '1'){
                count++
                $(this).prop("checked",true)
                $(this).parent().parent().addClass("check-cart-item")
            }
        })
        if(len === count)
            $(".checkall").prop("checked", true)
        else
            $(".checkall").prop("checked", false)
    }

    //视图
    function cartview(id) {
        $.ajax({
            type: 'get',
            url: 'http://localhost/trolleys/' + id,
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                if (res.code !== 20010) {
                    return
                }
                var data = ''
                $.each(res.data, function (index, item) {
                    data += (template('trolley-temp', item))
                })
                parent.$('#cart-counts').html(res.data.length)
                $('#cart-list').html(data)
                ischecked()
                total()
            }
        })
    }
    var id = parent.$('#loginTest').attr('data-id')
    cartview(id)

    //初始化统计
    total()

    //全选函数
    function check_all(num){
        $.ajax({
            type: 'put',
            url: 'http://localhost/statics',
            data: JSON.stringify({
                uid: id,
                statics: num
            }),
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                if (res.code !== 50010) {
                    return console.log(res.msg)
                }
                cartview(id)
            }
        })
    }
    //全选模块
    $(".checkall").change(function () {
        $(".checkall").prop("checked", $(this).prop("checked"))
        $('#cart-list').find('.j-checkbox').prop("checked", $(this).prop("checked"))

        if ($(this).prop("checked"))
            check_all(1)
        else
            check_all(0)

        total()
    })
    //单选函数
    function check_one(uid,sid,statics){
        $.ajax({
            type: 'put',
            url: 'http://localhost/statics/one',
            data: JSON.stringify({
                uid: uid,
                sid: sid,
                statics: statics
            }),
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                if (res.code !== 50010) {
                    return console.log(res.msg)
                }
                cartview(id)
            }
        })
    }
    //单选模块
    $('#cart-list').on('change','.j-checkbox',function(){
        // var all = $('#cart-list').find('.j-checkbox').length
        // var ck = $('#cart-list').find('.j-checkbox:checked').length
        // if (all === ck)
        //     $(".checkall").prop("checked", true)
        // else
        //     $(".checkall").prop("checked", false)
        var sid = $(this).parent().parent().attr('data-id')
        if ($(this).prop("checked")){
            check_one(id,sid,1)
            $(this).parents(".cart-item").addClass("check-cart-item")
        }else{
            check_one(id,sid,0)
            $(this).parents(".cart-item").removeClass("check-cart-item")
        }
        total()
    })


    //总计函数
    function total(){
        var count = $('#cart-list').find('.j-checkbox:checked').length
        var moneys = 0
        $.each($('#cart-list').find('.j-checkbox:checked'),function(){
            var money = parseFloat($(this).parent().siblings('#cartsums').text().substr(1))
            moneys += money
        })
        $(".amount-sum em").text(count)
        $(".price-sum em").text("￥" + moneys.toFixed(2))
    }
    //增减模块
    $("#cart-list").on('click', '.decrement', function () {
        var n = $(this).siblings(".itxt").val()
        if (n == 1)
            return
        n--
        var sid = $(this).parent().parent().parent().attr('data-id')
        $.ajax({
            type: 'put',
            url: 'http://localhost/trolleys',
            data: JSON.stringify({
                uid: id,
                sid: sid,
                tcount: n
            }),
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                if (res.code !== 50010) {
                    return alert(res.msg)
                }
                cartview(id)
            }
        })
    })
    $("#cart-list").on('click', '.increment', function () {
        var n = $(this).siblings(".itxt").val()
        n++
        var sid = $(this).parent().parent().parent().attr('data-id')
        $.ajax({
            type: 'put',
            url: 'http://localhost/trolleys',
            data: JSON.stringify({
                uid: id,
                sid: sid,
                tcount: n
            }),
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                if (res.code !== 50010) {
                    return alert(res.msg)
                }
                cartview(id)
            }
        })
    })

    //删除商品模块
    function delById(uid,sid){
        $.ajax({
            type: 'delete',
            url: 'http://localhost/trolleys',
            data: JSON.stringify({
                uid: uid,
                sid: sid
            }),
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                if (res.code !== 40010) {
                    return alert(res.msg)
                }
                cartview(id)
            }
        })
    }
    $("#cart-list").on('click', '.p-action a', function () {
        var sid = $(this).parent().parent().attr('data-id')
        delById(id,sid)
        total()
    })
    //清除购物车
    function clearall(){
        $.ajax({
            type: 'delete',
            url: 'http://localhost/trolleys/'+id,
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                if (res.code !== 40010) {
                    return alert(res.msg)
                }
                $('#cart-list').html('')
                parent.$('#cart-counts').html(0)
                $('.checkall').prop("checked", false)
                total()
            }
        })
    }
    $(".clear-all").click(clearall)

    //删除选中商品
    $(".remove-batch").click(function(){
        var all = $('#cart-list').find('.j-checkbox').length
        var ck = $('#cart-list').find('.j-checkbox:checked').length
        if(all === ck)
            return clearall()
        var arr = []
        $.each($('#cart-list').find('.j-checkbox:checked'),function(){
            arr.push($(this).parent().parent().attr('data-id'))
        })
        $.each(arr,function(index,item){
            delById(id,item)
        })
        total()
    })

    //结算模块
    $('#toarea').on('click',function(){
        var all = $('#cart-list').find('.j-checkbox').length
        var ck = $('#cart-list').find('.j-checkbox:checked').length
        if(all === ck)
            return clearall()
        var arr = []
        $.each($('#cart-list').find('.j-checkbox:checked'),function(){
            arr.push($(this).parent().parent().attr('data-id'))
        })
        $.each(arr,function(index,item){
            delById(id,item)
        })
        alert('我们将第一时间通知卖家发货')
        total()
    })
}) 