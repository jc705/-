$(function () {
    function ref(res) {
        $(res).each(function () {
            var $this = $(this),
                $nav = $this.attr('data-nav');

            $this.slick({
                slidesToShow: 4,
                slidesToScroll: 1,
                autoplay: true,
                infinite: true,
                speed: 300,
                dots: false,
                arrows: true,
                appendArrows: $nav ? $nav : false,
                responsive: [{
                    breakpoint: 991,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 1,
                    }
                },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1,
                    }
                },
                ]
            });
        });
    }
    $.ajax({
        type: "get",
        url: 'http://localhost/shops/electronic',
        contentType: "application/json; charset=UTF-8",
        success: function (res) {
            if (res.code !== 20010) {
                return alert(res.msg)
            }
            var data = ''
            $.each(res.data, function (index, item) {
                    data += template('temp-card', item)
            })
            $('#tab1-temp').append(data)
            ref($('#tab1-temp'))
        }
    })
    $.ajax({
        type: "get",
        url: 'http://localhost/shops/clothing',
        contentType: "application/json; charset=UTF-8",
        success: function (res) {
            if (res.code !== 20010) {
                return alert(res.msg)
            }
            var data = ''
            $.each(res.data, function (index, item) {
                    data += template('temp-card', item)
            })
            $('#tab2-temp').append(data)
            ref($('#tab2-temp'))
        }
    })
    $.ajax({
        type: "get",
        url: 'http://localhost/shops/others',
        contentType: "application/json; charset=UTF-8",
        success: function (res) {
            if (res.code !== 20010) {
                return alert(res.msg)
            }
            var data = ''
            $.each(res.data, function (index, item) {
                    data += template('temp-card', item)
            })
            $('#tab3-temp').append(data)
            ref($('#tab3-temp'))
        }
    })
})