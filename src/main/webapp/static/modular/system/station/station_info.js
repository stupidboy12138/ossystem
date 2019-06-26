/**
 * 初始化岗位管理详情对话框
 */
var StationInfoDlg = {
    stationInfoData : {}
};

/**
 * 清除数据
 */
StationInfoDlg.clearData = function() {
    this.stationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StationInfoDlg.set = function(key, val) {
    this.stationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StationInfoDlg.close = function() {
    parent.layer.close(window.parent.Station.layerIndex);
}

/**
 * 收集数据
 */
StationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('jobCode')
    .set('jobName')
    .set('jobDepart')
    .set('immediateSuperior')
    .set('jobCategory')
    .set('jobDescription');
}

/**
 * 提交添加
 */
StationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/station/add", function(data){
        Feng.success("添加成功!");
        window.parent.Station.table.refresh();
        StationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.stationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
StationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/station/update", function(data){
        Feng.success("修改成功!");
        window.parent.Station.table.refresh();
        StationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.stationInfoData);
    ajax.start();
}

$(function() {

});
