$(function () {
    var str = parent.$('iframe').attr('data-like').trim()
    getlikes(str, 1)

    //获取分页数据
    function getlikes(str, page) {
        $.ajax({
            type: "get",
            url: "http://localhost/shops/likes",
            data: {
                str: str,
                page: page,
            },
            success: function (res) {
                if (res.code !== 20010){
                    $('#productlikes').html(`<h4 class="pro-h1">已经没有商品啦<h4>`)
                    return $('#productlikes').attr('data-datas','no')
                }
                var data = ''
                $.each(res.data, function (index, item) {
                    data += template('temp-likes', item)
                })
                $('#productlikes').html(data)
                $('#productlikes').attr('data-datas','yes')
            }
        })
    }

    $('#a-prv').on('click',function(){
        var page = $('#center-span').html()
        if(page === '1')
            return
        page--
        $('#center-span').html(page)
        getlikes(str, page)
    })
    $('#a-nex').on('click',function(){
        var page = $('#center-span').html()
        if($('#productlikes').attr('data-datas') === 'no')
            return
        page++
        $('#center-span').html(page)
        getlikes(str, page)
    })

})