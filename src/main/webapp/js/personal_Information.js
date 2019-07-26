$(function () {
    //页面加载时加载头像
    $("img").attr("src",$("#path").text());

    var _URL = window.URL || window.webkitURL;
    $("#file").change(function (e) {
        var file, img;
        if ((file = this.files[0])) {
            img = new Image();
            img.onload = function () {
                console.log(this.width);
                if (this.width!=42&&this.height!=42){
                    alert("上传尺寸不对！必须为42*42像素");
                    $("#file").val("");
                }
            };
        }
        img.src = _URL.createObjectURL(file);
    });


    $("#change").click(function () {
        var name = $.trim($("#name").val());
        var path = $.trim($("#path").val());
        if ($("#file").val()!=''||name!=""){
            if(confirm("是否决定修改?")){
                $("form").submit();
            }
        }else {
            return false;
        }
    });
});