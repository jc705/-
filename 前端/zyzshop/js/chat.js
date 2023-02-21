$(function () {
    // resetui()

    //发送按钮
    $('#btnSend').on('click', function () {
        var text = $('#ipt').val().trim()
        $('#ipt').val('')
        if (text.length <= 0) {
            return
        }
        $('#talk_list').append('<li class="right_word"><img src="../img/person/person02.png" /> <span>' + text + '</span></li>')
        resetui()
        getMsg(text)
    })

    $('#ipt').on('keyup', function (e) {
        if (e.keyCode === 13) {
            $('#btnSend').click()
        }
    })

    //聊天机器人回复生成
    function getMsg(text) {
        $.ajax({
            method: 'GET',
            url: 'http://www.liulongbin.top:3006/api/robot',
            data: {
                spoken: text
            },
            success: function (res) {
                if (res.message === 'success') {
                    var msg = res.data.info.text
                    $('#talk_list').append('<li class="left_word"><img src="../img/person/person01.png" /> <span>' + msg + '</span></li>')
                    resetui()
                    getVoice(msg)
                }
            }
        })
    }

    //把文字转为语音
    function getVoice(text) {
        $.ajax({
            method: 'GET',
            url: 'http://www.liulongbin.top:3006/api/synthesize',
            data:{
                text: text
            },
            success: function(res){
                $('#voice').attr('src',res.voiceUrl)
            }
        })
    }
    var $main = $('.main');
    var $list = $('.talk_list');
    var $drager = $('.drager');
    var $mainh = $main.outerHeight(false);
    var $listh = $list.outerHeight(false);

    var $rate = $mainh / $listh;
    var $dragh = $mainh * $rate;
    var $top = 0;
    $drager.css({ 'height': $dragh });

    $drager.draggable({
        containment: "parent",
        drag: function (ev, ui) {
            $top = ui.position.top;
            $list.css({ 'top': -$top / $rate });
        }
    });

    $(window).resize(function () {
        resetui();
    });

    //var timer = null;
    var flag = false;

    $main.mousewheel(function(ev,delta){
        //console.log(delta);
        //clearTimeout(timer);
        //timer = setTimeout(function(){
            // 向上滚动正值，向下滚动负值
        if(flag){
            return;
        }

        flag = true;
        
        setTimeout(function(){
            flag = false;
        },300);

        if($listh <= $mainh){
            return;
        }else{
            if(delta>0){
                $top = $top-60;
                if($top<0){
                    $top=0;
                }
                $drager.animate({ 'top': $top },200);
                $list.animate({ 'top': -$top / $rate },200);
            }else{
                $top = $top+60;
                if($top>($mainh-$dragh)){
                    $top=parseInt($mainh-$dragh);
                }
                $drager.animate({ 'top': $top },200);
                $list.animate({ 'top': -parseInt($top / $rate) },200); 
            }
        }       
    });
    if ($listh <= $mainh) {
        $('.drag_bar').hide();
        $('.drager').hide();
    }

    function resetui(){
        $mainh = $main.outerHeight(false);
        $listh = $list.outerHeight(false);
        $rate = $mainh / $listh;
        $dragh = $mainh * $rate;
        $drager.css({ 'height': $dragh });
        
        if ($listh <= $mainh) {
            $('.drag_bar').hide();
            $drager.hide();
            $list.css({ 'top':0 });
        } else {
            $('.drag_bar').show();
            $drager.show();
            $drager.css({ 'top': $mainh-$dragh });
            $list.css({ 'top': -($listh-$mainh) });
        }
    }

    window.resetui = resetui;
})