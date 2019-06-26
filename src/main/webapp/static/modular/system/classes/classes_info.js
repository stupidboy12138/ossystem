/**
 * 初始化排班表详情对话框
 */
var ClassesInfoDlg = {
    classesInfoData : {}
};

/**
 * 清除数据
 */
ClassesInfoDlg.clearData = function() {
    this.classesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClassesInfoDlg.set = function(key, val) {
    this.classesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClassesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClassesInfoDlg.close = function() {
    parent.layer.close(window.parent.Classes.layerIndex);
}

/**
 * 收集数据
 */
ClassesInfoDlg.collectData = function() {
    this
    .set('Id')
    .set('code')
    .set('endtime')
    .set('note')
    .set('name')
    .set('begintime');
}

/**
 * 提交添加
 */
ClassesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/classes/add", function(data){
        Feng.success("添加成功!");
        window.parent.Classes.table.refresh();
        ClassesInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.classesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ClassesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/classes/update", function(data){
        Feng.success("修改成功!");
        window.parent.Classes.table.refresh();
        ClassesInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.classesInfoData);
    ajax.start();
}

$(function() {

});
