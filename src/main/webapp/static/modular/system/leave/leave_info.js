/**
 * 初始化请假单详情对话框
 */
var LeaveInfoDlg = {
    leaveInfoData : {}
};

/**
 * 清除数据
 */
LeaveInfoDlg.clearData = function() {
    this.leaveInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LeaveInfoDlg.set = function(key, val) {
    this.leaveInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LeaveInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LeaveInfoDlg.close = function() {
    parent.layer.close(window.parent.Leave.layerIndex);
}

/**
 * 收集数据
 */
LeaveInfoDlg.collectData = function() {
    this
    .set('Id')
    .set('leaveMan')
    .set('startTime')
    .set('endTime')
    .set('reason');
}

/**
 * 提交添加
 */
LeaveInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/leave/add", function(data){
        Feng.success("添加成功!");
        window.parent.Leave.table.refresh();
        LeaveInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.leaveInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LeaveInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/leave/update", function(data){
        Feng.success("修改成功!");
        window.parent.Leave.table.refresh();
        LeaveInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.leaveInfoData);
    ajax.start();
}

$(function() {

});
