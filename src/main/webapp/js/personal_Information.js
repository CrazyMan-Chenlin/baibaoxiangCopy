$(function () {
    //页面加载时加载头像
    $("img").attr("src",$("#path").text());


    $("#imgfile").change(function (e) {
        var file, img;
        if ((file = this.files[0])) {
            img = new Image();
            img.onload = function () {
                if (this.width!=42&&this.height!=42){
                    alert("上传尺寸不对！必须为42*42像素");
                    $("#imgfile").val("");
                }
            };
        }
    });


    $("#change").click(function () {
        var name = $.trim($("#name").val());
        var path = $.trim($("#path").val());
        if ($("#imgfile").val()!=''||name!=""){
            if(confirm("是否决定修改?")){
                $("form").submit();
            }
        }else {
            return false;
        }
    });
});