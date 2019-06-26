/**
 * 初始化考勤打卡详情对话框
 */
var PunchcardInfoDlg = {
    punchcardInfoData : {}
};

/**
 * 清除数据
 */
PunchcardInfoDlg.clearData = function() {
    this.punchcardInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PunchcardInfoDlg.set = function(key, val) {
    this.punchcardInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PunchcardInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PunchcardInfoDlg.close = function() {
    parent.layer.close(window.parent.Punchcard.layerIndex);
}

/**
 * 收集数据
 */
PunchcardInfoDlg.collectData = function() {
    this
    .set('id')
    .set('punchMan')
    .set('punchDate')
    .set('note')
    .set('isRepair')
    .set('isLeave');
}

/**
 * 提交添加
 */
PunchcardInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/punchcard/add", function(data){
        Feng.success("添加成功!");
        window.parent.Punchcard.table.refresh();
        PunchcardInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.punchcardInfoData);
    ajax.start();
}

/**
 * 提交添加打卡
 */
PunchcardInfoDlg.addSubmits = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/punchcard/clocksAdd", function(data){
        console.log("clockoutAdd");
        Feng.success(data);
        window.parent.Punchcard.table.refresh();
        PunchcardInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.punchcardInfoData);
    ajax.start();
}
/**
 * 提交修改
 */
PunchcardInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/punchcard/update", function(data){
        Feng.success("修改成功!");
        window.parent.Punchcard.table.refresh();
        PunchcardInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.punchcardInfoData);
    ajax.start();
}

$(function() {

});
