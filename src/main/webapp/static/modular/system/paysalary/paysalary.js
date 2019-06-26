/**
 * 派薪单管理初始化
 */
var Paysalary = {
    id: "PaysalaryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Paysalary.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '领薪人', field: 'salariedPeople', visible: true, align: 'center', valign: 'middle'},
            {title: '个人时薪', field: 'fixedSalary', visible: true, align: 'center', valign: 'middle'},
            {title: '区间薪水', field: 'selectionSalary', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'startTime', visible: true, align: 'center', valign: 'middle'},
            {title: '截至时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Paysalary.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Paysalary.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加派薪单
 */
Paysalary.openAddPaysalary = function () {
    var index = layer.open({
        type: 2,
        title: '添加派薪单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/paysalary/paysalary_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看派薪单详情
 */
Paysalary.openPaysalaryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '派薪单详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/paysalary/paysalary_update/' + Paysalary.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除派薪单
 */
Paysalary.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/paysalary/delete", function (data) {
            Feng.success("删除成功!");
            Paysalary.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询派薪单列表
 */
Paysalary.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Paysalary.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Paysalary.initColumn();
    var table = new BSTable(Paysalary.id, "/paysalary/list", defaultColunms);
    table.setPaginationType("client");
    Paysalary.table = table.init();
});
