/**
 * 岗位管理管理初始化
 */
var Station = {
    id: "StationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Station.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '编码', field: 'jobCode', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'jobName', visible: true, align: 'center', valign: 'middle'},
            {title: '所属部门', field: 'jobDepart', visible: true, align: 'center', valign: 'middle'},
            {title: '上级部门', field: 'immediateSuperior', visible: true, align: 'center', valign: 'middle'},
            {title: '部门类别', field: 'jobCategory', visible: true, align: 'center', valign: 'middle'},
            {title: '岗位描述', field: 'jobDescription', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Station.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        // Station.seItem = selected[0];
        for (k in selected){
            Station.seItem = selected[k];
            console.log(selected[k])
        }
        return true;
    }
};

/**
 * 点击添加岗位管理
 */
Station.openAddStation = function () {
    var index = layer.open({
        type: 2,
        title: '添加岗位管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/station/station_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看岗位管理详情
 */
Station.openStationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '岗位管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/station/station_update/' + Station.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除岗位管理
 */
Station.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/station/delete", function (data) {
            Feng.success("删除成功!");
            Station.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("stationId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询岗位管理列表
 */
Station.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Station.table.refresh({query: queryData});
};


/**
 * 获取checkbox数组
 * @returns {*}
 */
Station.getIdSelections =  function() {
    return $.map($("#StationTable").bootstrapTable('getSelections'), function (row) {
        return row.id//返回数据行中的id值
    });
};

Station.deleteAll = function() {
    var ids = Station.getIdSelections();
    $.ajax({
        async: true,
        type: "get",
        dataType: "text",//返回数据格式
        contentType: "application/json;charset=UTF-8",
        data: {
            ids:ids
        },
        url: "/station/deleteByIds",
        success: function(d){
            Feng.success("批量删除成功!");
            $("#StationTable").bootstrapTable('refresh');
            StationInfoDlg.close();
        },
        error: function(e){
            Feng.error("批量删除失败!" + data.responseJSON.message + "!");
        }
    });
};

$(function () {
    var defaultColunms = Station.initColumn();
    var table = new BSTable(Station.id, "/station/list", defaultColunms);
    table.setPaginationType("client");
    Station.table = table.init();
});
