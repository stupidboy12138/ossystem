/**
 * 员工管理管理初始化
 */
var Employees = {
    empId: "EmployeesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Employees.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '员工ID', field: 'empId', visible: true, align: 'center', valign: 'middle'},
            {title: '编码', field: 'empCode', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'sex', visible: true, align: 'center', valign: 'middle'},
            {title: '年龄', field: 'age', visible: true, align: 'center', valign: 'middle'},
            {title: '民族', field: 'nation', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证', field: 'idCard', visible: true, align: 'center', valign: 'middle'},
            {title: '工资', field: 'salary', visible: true, align: 'center', valign: 'middle'},
            {title: '手机', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '紧急联系人', field: 'emergencyContact', visible: true, align: 'center', valign: 'middle'},
            {title: '紧急联系电话', field: 'emergencyContactPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '工作ID', field: 'jobId', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '加入时间', field: 'joinTime', visible: true, align: 'center', valign: 'middle'},
            {title: '所在部门', field: 'simplename', visible: true, align: 'center', valign: 'middle'},
            {title: '上级部门', field: 'fullname', visible: true, align: 'center', valign: 'middle'},
            {title: '部门负责人', field: 'principal', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Employees.check = function () {
    // var selected = $('#' + this.empId).bootstrapTable('getSelections');
    // if(selected.length == 0){
    //     Feng.info("请先选中表格中的某一记录！");
    //     return false;
    // }else{
    //     var k in selected
    //     Employees.seItem = selected[0];
    //     console.log(selected[0]);
    //     return true;
    // }
    var selected = $('#' + this.empId).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        console.log(selected.length);
        Employees.seItem = selected[0];
        console.log(selected[0]);
        return true;
    }
};

/**
 * 点击添加员工管理
 */
Employees.openAddEmployees = function () {
    var index = layer.open({
        type: 2,
        title: '添加员工管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/employees/employees_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看员工管理详情
 */
Employees.openEmployeesDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '员工管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/employees/employees_update/' + Employees.seItem.empId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除员工管理
 */
Employees.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/employees/delete", function (data) {
            Feng.success("删除成功!");
            Employees.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("empId",this.seItem.empId);
        ajax.start();
    }
};

/**
 * 查询员工管理列表
 */
Employees.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    queryData['empCode'] = $("#empCode").val();
    Employees.table.refresh({query: queryData});
};

/**
 * 获取checkbox数组
 * @returns {*}
 */
Employees.getIdSelections =  function() {
    return $.map($("#EmployeesTable").bootstrapTable('getSelections'), function (row) {
        return row.empId//返回数据行中的id值
    });
};

Employees.deleteAll = function() {
    console.log(Employees.getIdSelections());
    var ids = Employees.getIdSelections();
    $.ajax({
        async: true,
        type: "get",
        dataType: "text",//返回数据格式
        contentType: "application/json;charset=UTF-8",
        data: {
            ids:ids
        },
        url: "/employees/deleteByIds",
        success: function(d){
            Feng.success("批量删除成功!");
            $("#EmployeesTable").bootstrapTable('refresh');
            EmployeesInfoDlg.close();
        },
        error: function(e){
            Feng.error("批量删除失败!" + data.responseJSON.message + "!");
        }
    });
}



$(function () {
    var defaultColunms = Employees.initColumn();
    var table = new BSTable(Employees.empId, "/employees/list", defaultColunms);
    table.setPaginationType("client");
    Employees.table = table.init();
});
