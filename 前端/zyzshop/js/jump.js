$(function () {
    //初始化
    iframeHeight()
    //获取高度
    function iframeHeight() {
        var h = $('#sub_frame').height(window.innerHeight - 210)
    }

    //导航栏选中状态
    $('#Navigation-ul li').on('click', function () {
        $('#Navigation-ul li').removeClass()
        $(this).addClass('active')
    })

    //index触发点击事件
    $('#shop-0').on('click', function () {
        parent.$('#ul-li2').trigger('click')
    })
    $('#shop-1').on('click', function () {
        parent.$('#ul-li3').trigger('click')
    })
    $('#shop-2').on('click', function () {
        parent.$('#ul-li4').trigger('click')
    })
    $('#shop-3').on('click', function () {
        parent.$('#ul-li5').trigger('click')
    })

    //homepage跳转
    $('#gohome').on('click', function () {
        //window.open会打开新页面
        // window.open('../main/home.html')
        window.location.href = '../main/home.html'
    })

    //login登录按钮
    $('#a-login').on('click', function () {
        $('#sub_frame').attr('src', '../pages/login.html')
    })

    //控制iframe跳转
    $('#ul-li1').on('click', function () {
        $('#sub_frame').attr('src', '../pages/index.html')
    })
    $('#ul-li7').on('click', function () {
        $('#sub_frame').attr('src', '../pages/chat.html')
    })
    $('#ul-li2').on('click', function () {
        $('#sub_frame').attr('src', '../pages/hot.html')
    })
    $('#ul-li3').on('click', function () {
        $('#sub_frame').attr('src', '../pages/electronic.html')
    })
    $('#ul-li4').on('click', function () {
        $('#sub_frame').attr('src', '../pages/clothing.html')
    })
    $('#ul-li5').on('click', function () {
        $('#sub_frame').attr('src', '../pages/others.html')
    })
    $('#ul-li6').on('click', function () {
        $('#sub_frame').attr('src', '../pages/myRelease.html')
    })
    $('#cart-a').on('click',function(){
        var id = $('#loginTest').attr('data-id')
        if(id === '')
            return alert('请先登录')
        $('#sub_frame').attr('src', '../pages/cart.html')
    })
    $('#likes-a').on('click',function(){
        var id = $('#loginTest').attr('data-id')
        if(id === '')
            return alert('请先登录')
        $('#sub_frame').attr('src', '../pages/likes.html')
    })
    $("#searching-btn").on('click',function(){
        var str = $(this).prev().val().trim()
        if(str === '')
            return $(this).prev().val('')
        $('#sub_frame').attr('data-like',str)
        $('#sub_frame').attr('src', '../pages/Searching.html')
        $(this).prev().val('')
    })
    $("#searching-ipt").on('keyup',function(e){
        if(e.keyCode === 13)
            $("#searching-btn").trigger('click')
    })

    //登录input检测
    $('.sign-in-htm #user').on('blur', function () {
        var span = $(this).siblings('span')
        if ($(this).val() === '') {
            span.removeClass().addClass('error')
            span.text('请输入账号')
            return
        }
        span.text('')
        span.removeClass()
    })
    $('.sign-in-htm #pass').on('blur', function () {
        var span = $(this).siblings('span')
        if ($(this).val() === '') {
            span.removeClass().addClass('error')
            span.text('请输入密码')
            return
        }
        span.text('')
        span.removeClass()
    })

    //登录事件
    $('#login-btn').on('click', function () {
        var name = $('.sign-in-htm #user').val()
        var pwd = $('.sign-in-htm #pass').val()

        var data = {
            uname: name,
            upassword: pwd
        }

        var user = $('.sign-in-htm #pass')
        var pass = $('.sign-in-htm #user')
        user.trigger('blur')
        pass.trigger('blur')
        if (name === '' || pwd === '')
            return

        $.ajax({
            type: 'post',
            url: 'http://localhost/login',
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify(data),
            success: function (res) {
                if (res.code !== 10010) {
                    $('#pass').val('')
                    return alert(res.msg)
                }
                var li = '<i class="fa fa-user-o"></i><a href="javascript:;">' + name + '</a><a href="../main/home.html"> 退出</a>'
                parent.$('#home-login').html(li)
                alert(res.msg)
                parent.$('#ul-li1').trigger('click')
                var id = res.data.uid
                parent.$('#loginTest').attr('data-id', id)
                parent.$('#like-counts').html(res.data.like)
                parent.$('#cart-counts').html(res.data.cart)
            }
        })
    })

    


    //邮件模块
    $('#email-btn').on('click', function () {
        if (parent.$('#loginTest').attr('data-id') === '') {
            return alert('请先登录！！！')
        }
        if ($('#email-ipt').val().trim() === '')
            return alert('请不要发送空白信息')
        alert('已收到反馈，客服将在第一时间联系您！！')
    })

    //注册格式检测
    var testUser = /^[a-zA-Z0-9]{4,16}$/
    var testPwd = /^[a-zA-Z0-9]{6,16}$/
    var testEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/
    $('.sign-up-htm #user').on('blur', function () {
        var span = $(this).siblings('span')
        if (testUser.test($(this).val())) {
            span.removeClass().addClass('right')
            span.text('当前账号可以使用')
        } else {
            span.removeClass().addClass('error')
            span.text('请输入4~16位数字字母组合')
        }
    })
    $('.sign-up-htm #pass').on('blur', function () {
        var span = $(this).siblings('span')
        if (testPwd.test($(this).val())) {
            span.removeClass().addClass('right')
            span.text('当前密码可以使用')
        } else {
            span.removeClass().addClass('error')
            span.text('请输入6~16位数字字母组合')
        }
    })
    $('.sign-up-htm #password').on('blur', function () {
        var span = $(this).siblings('span')
        let pass = $('.sign-up-htm #pass').val()
        if (pass === ($(this).val())) {
            if (pass === '') {
                span.removeClass().addClass('error')
                span.text('请先输入密码')
                return
            }

            span.removeClass().addClass('right')
            span.text('两次密码一致')
            return
        } else {
            span.removeClass().addClass('error')
            span.text('请输入相同的密码')
        }
    })
    $('.sign-up-htm #email').on('blur', function () {
        var span = $(this).siblings('span')
        if (testEmail.test($(this).val())) {
            span.removeClass().addClass('right')
            span.text('当前邮箱可以使用')
        } else {
            span.removeClass().addClass('error')
            span.text('请输入正确的邮箱格式')
        }
    })

    //注册提交
    $('#register-ipt').on('click', function () {
        var name = $('.sign-up-htm #user')
        var pwd = $('.sign-up-htm #pass')
        var pwds = $('.sign-up-htm #password')
        var email = $('.sign-up-htm #email')
        name.trigger('blur')
        pwd.trigger('blur')
        pwds.trigger('blur')
        email.trigger('blur')

        bool = name.siblings('span').hasClass('right') && pwd.siblings('span').hasClass('right') && pwds.siblings('span').hasClass('right') && email.siblings('span').hasClass('right')
        if (!bool)
            return

        var uname = name.val()
        var upassword = pwd.val()
        var uemail = email.val()
        var data = {
            "uname": uname,
            "upassword": upassword,
            "uemail": uemail
        }

        $.ajax({
            type: 'post',
            url: 'http://localhost/register',
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify(data),
            success: function (res) {
                if (res.code !== 11010) {
                    $('#pass').val('')
                    return alert(res.msg)
                }
                var li = '<i class="fa fa-user-o"></i>' + uname + '<a href="../main/home.html"> 退出</a>'
                parent.$('#home-login').html(li)
                alert(res.msg)
                parent.$('#ul-li1').trigger('click')
                parent.$('#loginTest').attr('data-id', res.data.uid)
            }
        })
    })

    //添加购物车
    function addcart(){
        var uid = parent.$('#loginTest').attr('data-id')
        if(uid === '')
            return alert('请先登录')
        var sid = $(this).parent().parent().attr('data-id')
        $.ajax({
            type: 'post',
            url:'http://localhost/trolleys',
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                uid: uid,
                sid: sid
            }),
            success: function(res){
                if(res.code !== 30010)
                    return alert(res.msg)
                var count = parent.$('#cart-counts').html()
                count++
                parent.$('#cart-counts').html(count)
                alert('添加成功')
            }
        })
    }
    $('.products-slick').on('click','#add-tocart',addcart)
    $('#view-div').on('click','#add-tocart',addcart)
    $('#productlikes').on('click','#add-tocart',addcart)

    //添加收藏
    function addlike(){
        var uid = parent.$('#loginTest').attr('data-id')
        if(uid === '')
            return alert('请先登录')
        var sid = $(this).parent().parent().parent().attr('data-id')
        $.ajax({
            type: 'post',
            url:'http://localhost/likes',
            contentType: "application/json; charset=UTF-8",
            data: JSON.stringify({
                uid: uid,
                sid: sid
            }),
            success: function(res){
                if(res.code !== 30010)
                    return alert(res.msg)
                var count = parent.$('#like-counts').html()
                count++
                parent.$('#like-counts').html(count)
                alert('添加成功')
            }
        })
    }
    
    $('.products-slick').on('click','#add-like',addlike)
    $('#view-div').on('click','#add-like',addlike)
    $('#productlikes').on('click','#add-like',addlike)
})