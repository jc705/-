$(function () {
    // page2页面获取传来的id值
    function getQueryString(name) {
        var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }
    var id = getQueryString('id');
    $.ajax({
        type: "get",
        url: 'http://localhost/shops/'+id,
        contentType: "application/json; charset=UTF-8",
        success: function (res) {
            if (res.code !== 20010) {
                return alert(res.msg)
            }
            
            $('#view-div').append(template('temp-view', res.data))
        }
    })
})