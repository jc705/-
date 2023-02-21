$(function () {
    $('#ipt-division').on('blur', function () {
        console.log($(this).find('option:selected').attr('data-id'));
    })

    //字数监听
    $('#ipt-pic').on('input', function () {
        var len = $(this).val().length
        $('#useCount1').html(len)
    })
    $('#ipt-introduction').on('input', function () {
        var len = $(this).val().length
        $('#useCount2').html(len)
    })

    myajax()

    function myajax() {
        $.ajax({
            type: "get",
            url: 'http://localhost/shops',
            success: function (res) {
                if (res.code !== 20010) {
                    return alert(res.msg)
                }
                $('#add-tbody').html('')
                var uid = parent.$('#loginTest').attr('data-id')
                $.each(res.data, function (index, item) {
                    
                    if (item.uname === parseInt(uid))
                        $('#add-tbody').append(template('tbody-temp', item))
                })
            }
        })
    }


    $('#btnAdd').on('click', function () {
        var uid = parent.$('#loginTest').attr('data-id')
        if (uid === '')
            return alert('请先登录')
        var product = $('#ipt-product').val().trim()
        var division = $('#ipt-division').find("option:selected").attr("data-id")
        var money = $('#ipt-money').val().trim()
        var sclass = $('#ipt-sclass').val().trim()
        var pic = $('#ipt-pic').val().trim()
        var introduction = $('#ipt-introduction').val().trim()
        if (product === '' || money === '' || sclass === '' || pic === '' || introduction === '') {
            return alert('请先完善商品')
        }
        var datas = {
            "product": product,
            "division": parseInt(division),
            "money": parseInt(money),
            "sclass": sclass,
            "pic": pic,
            "uname": parseInt(uid),
            "introduction": introduction
        }
        $.ajax({
            type: "post",
            url: 'http://localhost/shops',
            data: JSON.stringify(datas),
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                if (res.code !== 30010) {
                    return alert(res.msg)
                }
                alert('发布成功')
                $('#ipt-product').val('')
                $('#ipt-money').val('')
                $('#ipt-sclass').val('')
                $('#ipt-pic').val('')
                $('#ipt-introduction').val('')
                myajax()
            }
        })
    })
    $('#add-tbody').on('click','#btn-del', function () {
        var id = $(this).attr('data-id')
        $.ajax({
            type: "delete",
            url: 'http://localhost/shops/' + id,
            success: function (res) {
                if (res.code !== 40010) {
                    return alert(res.msg)
                }
                alert('删除成功')
                myajax()
            }
        })
    })

})