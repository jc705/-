$(function () {
    var uid = parent.$('#loginTest').attr('data-id')
    getlikelist(uid)

    //获取分页数据
    function getlikelist(id) {
        $.ajax({
            type: "get",
            url: "http://localhost/likes/"+id,
            success: function (res) {
                if (res.code !== 20010)
                    return $('#productlikes').html(`<h4 class="pro-h1">您的收藏为空<h4>`)
                var data = ''
                $.each(res.data, function (index, item) {
                    data += template('temp-likes', item)
                })
                $('#productlikes').html(data)
            }
        })
    }

    $('#productlikes').on('click','#remove-likes',function(){
        var sid = $(this).parent().parent().parent().attr('data-id')
        console.log(uid + " " + sid);
        $.ajax({
            type: "delete",
            url: "http://localhost/likes",
            data: JSON.stringify({
                uid: uid,
                sid: sid
            }),
            contentType: "application/json; charset=UTF-8",
            success: function (res) {
                if (res.code !== 40010)
                    return alert(res.msg)
                getlikelist(uid)
            }
        })
    })

})