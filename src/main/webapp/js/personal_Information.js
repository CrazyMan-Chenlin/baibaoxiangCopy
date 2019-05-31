$(function () {
    //

    $("#change").click(function () {
        var name = $.trim($("#name").val());
        var path = $.trim($("#path").val());
        $("form").submit();
    });
});