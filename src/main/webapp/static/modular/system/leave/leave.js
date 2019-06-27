/**
 * 请假单管理初始化
 */
var Leave = {
    id: "LeaveTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Leave.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '请假人', field: 'leaveMan', visible: true, align: 'center', valign: 'middle'},
            {title: '外出日期', field: 'startTime', visible: true, align: 'center', valign: 'middle'},
            {title: '归来日期', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '请假原因', field: 'reason', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Leave.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Leave.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加请假单
 */
Leave.openAddLeave = function () {
    var index = layer.open({
        type: 2,
        title: '添加请假单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/leave/leave_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看请假单详情
 */
Leave.openLeaveDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '请假单详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/leave/leave_update/' + Leave.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除请假单
 */
Leave.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/leave/delete", function (data) {
            Feng.success("删除成功!");
            Leave.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};


/**
 * 获取checkbox数组
 * @returns {*}
 */
Leave.getIdSelections =  function() {
    return $.map($("#LeaveTable").bootstrapTable('getSelections'), function (row) {
        return row.id//返回数据行中的id值
    });
};

Leave.deleteAll = function() {
    var ids = Leave.getIdSelections();
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
            $("#LeaveTable").bootstrapTable('refresh');
            $("#PunchcardTable").bootstrapTable('refresh');
            PunchcardInfoDlg.close();
        },
        error: function(e){
            Feng.error("批量删除失败!" + data.responseJSON.message + "!");
        }
    });
};

/**
 * 查询请假单列表
 */
Leave.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Leave.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Leave.initColumn();
    var table = new BSTable(Leave.id, "/leave/list", defaultColunms);
    table.setPaginationType("client");
    Leave.table = table.init();
});
