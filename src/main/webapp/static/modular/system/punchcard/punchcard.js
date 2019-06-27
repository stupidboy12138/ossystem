/**
 * 考勤打卡管理初始化
 */
var Punchcard = {
    id: "PunchcardTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Punchcard.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'punchMan', visible: true, align: 'center', valign: 'middle'},
            {title: '上班打卡时间', field: 'punchDate', visible: true, align: 'center', valign: 'middle'},
            {title: '下班签退时间', field: 'clockOut', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'note', visible: true, align: 'center', valign: 'middle'},
            {title: '上班状态',field:'dutyStatus', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
                    if (row.dutyStatus=="正常上班") {
                        return '<button type="button" class="btn btn-info">正  常</button>';
                    }
                    else if (row.dutyStatus=="上班迟到"){
                        return '<button type="button" class="btn btn-danger">迟  到</button>';
                    }else {
                        return '<button type="button" class="btn btn-info">'+row.note+'</button>';
                    }
                }},
            {title: '下班状态', field: 'offDutyStatus', visible: true, align: 'center', valign: 'middle',             formatter: function (value, row, index) {

                    if (row.offDutyStatus=="正常下班") {
                        return '<button type="button" class="btn btn-info btn-primary">正  常</button>';
                    }
                    else if (row.offDutyStatus=="下班早退") {
                        return '<button type="button" class="btn btn-danger btn-large">早  退</button>';
                    }else {
                        return '<button type="button" class="btn btn-info btn-small">'+row.note+'</button>';
                    }
                }},
            {title: '是否补卡', field: 'repair', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index){
                    if (row.repair==0){
                        return '<button type="button" class="btn btn-default">未补卡</button>';
                    }
                    if (row.repair==1){
                        return '<button type="button" class="btn btn-success">已补卡</button>';
                    }
                }

                },
            {title: '是否请假', field: 'leave', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index){
                    if (row.leave==0){
                        return '<button type="button" class="btn btn-default">未请假</button>';
                    }
                    if (row.leave==1){
                        return '<button type="button" class="btn btn-success">已请假</button>';
                    }
                }},
    ];
};

/**
 * 检查是否选中
 */
Punchcard.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Punchcard.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加考勤打卡
 */
Punchcard.openAddPunchcard = function () {
    var index = layer.open({
        type: 2,
        title: '添加考勤打卡',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/punchcard/punchcard_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看考勤打卡详情
 */
Punchcard.openPunchcardDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '考勤打卡详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/punchcard/punchcard_update/' + Punchcard.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除考勤打卡
 */
Punchcard.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/punchcard/delete", function (data) {
            Feng.success("删除成功!");
            Punchcard.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询考勤打卡列表
 */
Punchcard.search = function () {
    var queryData = {};
    queryData['punchMan'] = $("#punchMan").val();
    queryData['startTime'] = $("#startTime").val();
    queryData['endTime'] = $("#endTime").val();
    Punchcard.table.refresh({query: queryData});
};

Punchcard.exportExcel = function(){
    location.href="http://localhost:8080/punchcard/exportExcel";
};

Punchcard.clocks = function(){
    var index = layer.open({
        type: 2,
        title: '考勤打卡',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/punchcard/clocks'
    });
    this.layerIndex = index;
    // $.ajax({
    //     async: true,
    //     type: "get",
    //     dataType: "text",//返回数据格式
    //     contentType: "application/json;charset=UTF-8",
    //     data: {
    //     },
    //     url: "/punchcard/clocks",
    //     success: function(d){
    //         alert(d);
    //     },
    //     error: function(e){
    //         alert(e);
    //     }
    // });
};


/**
 * 获取checkbox数组
 * @returns {*}
 */
Punchcard.getIdSelections =  function() {
    return $.map($("#PunchcardTable").bootstrapTable('getSelections'), function (row) {
        return row.id//返回数据行中的id值
    });
};

Punchcard.deleteAll = function() {
    var ids = Punchcard.getIdSelections();
    $.ajax({
        async: true,
        type: "get",
        dataType: "text",//返回数据格式
        contentType: "application/json;charset=UTF-8",
        data: {
            ids:ids
        },
        url: "/punchcard/deleteByIds",
        success: function(d){
            Feng.success("批量删除成功!");
            $("#PunchcardTable").bootstrapTable('refresh');
            PunchcardInfoDlg.close();
        },
        error: function(e){
            Feng.error("批量删除失败!" + data.responseJSON.message + "!");
        }
    });
};



$(function () {
    var defaultColunms = Punchcard.initColumn();
    var table = new BSTable(Punchcard.id, "/punchcard/list", defaultColunms);
    table.setPaginationType("client");
    Punchcard.table = table.init();
});
