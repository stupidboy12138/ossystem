var pcounts;
getEcharsData = function(){

    $.ajax({
        async: false,
        url: "punchcardCount",
        contentType: "application/json;charset=UTF-8",
        data:{

        },
        success: function (data) {
            pcounts=data;
        },
        error: function (data) {
            console.log(data);
        }
    })
    return pcounts;
}
