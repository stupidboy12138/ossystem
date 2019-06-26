/**
 * 初始化派薪单详情对话框
 */
var PaysalaryInfoDlg = {
    paysalaryInfoData : {}
};

/**
 * 清除数据
 */
PaysalaryInfoDlg.clearData = function() {
    this.paysalaryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaysalaryInfoDlg.set = function(key, val) {
    this.paysalaryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PaysalaryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PaysalaryInfoDlg.close = function() {
    parent.layer.close(window.parent.Paysalary.layerIndex);
}

/**
 * 收集数据
 */
PaysalaryInfoDlg.collectData = function() {
    this
    .set('Id')
    .set('salariedPeople')
    .set('fixedSalary')
    .set('selectionSalary')
    .set('startTime')
    .set('endTime');
}

/**
 * 提交添加
 */
PaysalaryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paysalary/add", function(data){
        Feng.success("添加成功!");
        window.parent.Paysalary.table.refresh();
        PaysalaryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paysalaryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PaysalaryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/paysalary/update", function(data){
        Feng.success("修改成功!");
        window.parent.Paysalary.table.refresh();
        PaysalaryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.paysalaryInfoData);
    ajax.start();
}

$(function() {

});
