$(function () {
    //

    $("#change").click(function () {
        var name = $.trim($("#name").val());
        var path = $.trim($("#path").val());
        if(confirm("是否决定修改?")){
            $("form").submit();
        }
    });
});