/**
 * 补卡单管理初始化
 */
var Repaircard = {
    id: "RepaircardTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Repaircard.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '补卡人', field: 'repairMan', visible: true, align: 'center', valign: 'middle'},
            {title: '补卡日期', field: 'repairDate', visible: true, align: 'center', valign: 'middle'},
            {title: '补卡原因', field: 'repairReason', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Repaircard.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Repaircard.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加补卡单
 */
Repaircard.openAddRepaircard = function () {
    var index = layer.open({
        type: 2,
        title: '添加补卡单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/repaircard/repaircard_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看补卡单详情
 */
Repaircard.openRepaircardDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '补卡单详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/repaircard/repaircard_update/' + Repaircard.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除补卡单
 */
Repaircard.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/repaircard/delete", function (data) {
            Feng.success("删除成功!");
            Repaircard.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询补卡单列表
 */
Repaircard.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Repaircard.table.refresh({query: queryData});
};



/**
 * 获取checkbox数组
 * @returns {*}
 */
Repaircard.getIdSelections =  function() {
    return $.map($("#RepaircardTable").bootstrapTable('getSelections'), function (row) {
        return row.id//返回数据行中的id值
    });
};

Repaircard.deleteAll = function() {
    var ids = Repaircard.getIdSelections();
    $.ajax({
        async: true,
        type: "get",
        dataType: "text",//返回数据格式
        contentType: "application/json;charset=UTF-8",
        data: {
            ids:ids
        },
        url: "/repaircard/deleteByIds",
        success: function(d){
            Feng.success("批量删除成功!");
            $("#RepaircardTable").bootstrapTable('refresh');
            PunchcardInfoDlg.close();
        },
        error: function(e){
            Feng.error("批量删除失败!" + data.responseJSON.message + "!");
        }
    });
}

$(function () {
    var defaultColunms = Repaircard.initColumn();
    var table = new BSTable(Repaircard.id, "/repaircard/list", defaultColunms);
    table.setPaginationType("client");
    Repaircard.table = table.init();
});
