$(document).ready(function () {
    var total = $('.image > div').length;
    var num = 1;
    $('.info > span:first').text(num);
    $('.info > span:last').text(total);
    $('.prev').click(function () {
        num--;
        if (num < 1) {
            num = total;
        }
        $('.info > span:first').text(num);
        $('.image div:last').insertBefore('.image div:first');
    });
    $('.next').click(function () {
        num++;
        if (num > total) {
            num = 1;
        }
        $('.info > span:first').text(num);
        $('.image div:first').insertAfter('.image div:last');
    });
});

/*


*/
