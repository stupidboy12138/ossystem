/**
 * 初始化补卡单详情对话框
 */
var RepaircardInfoDlg = {
    repaircardInfoData : {}
};

/**
 * 清除数据
 */
RepaircardInfoDlg.clearData = function() {
    this.repaircardInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RepaircardInfoDlg.set = function(key, val) {
    this.repaircardInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RepaircardInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RepaircardInfoDlg.close = function() {
    parent.layer.close(window.parent.Repaircard.layerIndex);
}

/**
 * 收集数据
 */
RepaircardInfoDlg.collectData = function() {
    this
    .set('Id')
    .set('repairMan')
    .set('repairDate')
    .set('repairReason');
}

/**
 * 提交添加
 */
RepaircardInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/repaircard/add", function(data){
        Feng.success("添加成功!");
        window.parent.Repaircard.table.refresh();
        RepaircardInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.repaircardInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RepaircardInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/repaircard/update", function(data){
        Feng.success("修改成功!");
        window.parent.Repaircard.table.refresh();
        RepaircardInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.repaircardInfoData);
    ajax.start();
}

$(function() {

});
