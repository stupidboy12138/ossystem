/**
 * 初始化员工管理详情对话框
 */
var EmployeesInfoDlg = {
    employeesInfoData : {},
    zTreeInstance : null,
    validateFields: {
        empCode: {
            validators: {
                notEmpty: {
                    message: '员工不能为空'
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        age: {
            validators: {
                notEmpty: {
                    message: '年龄不能为空'
                }
            }
        },
        nation: {
            validators: {
                notEmpty: {
                    message: '民族不能为空'
                }
            }
        },
        idCard: {
            validators: {
                notEmpty: {
                    message: '身份证不能为空'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '联系电话不能为空'
                }
            }
        },
        emergencyContact: {
            validators: {
                notEmpty: {
                    message: '紧急联系人不能为空'
                }
            }
        },
        emergencyContactPhone: {
            validators: {
                notEmpty: {
                    message: '紧急联系电话不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
EmployeesInfoDlg.clearData = function() {
    this.employeesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EmployeesInfoDlg.set = function(key, val) {
    this.employeesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EmployeesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EmployeesInfoDlg.close = function() {
    parent.layer.close(window.parent.Employees.layerIndex);
}

/**
 * 收集数据
 */
EmployeesInfoDlg.collectData = function() {
    this
    .set('empId')
    .set('empCode')
    .set('name')
    .set('sex')
    .set('age')
    .set('nation')
    .set('idCard')
    .set('salary')
    .set('phone')
    .set('emergencyContact')
    .set('emergencyContactPhone')
    .set('jobId')
    .set('description')
    .set('joinTime');
}


/**
 * 验证数据是否为空
 */
EmployeesInfoDlg.validate = function () {
    $('#employees-form').data("bootstrapValidator").resetForm();
    $('#employees-form').bootstrapValidator('validate');
    return $("#employees-form").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
EmployeesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/employees/add", function(data){
        Feng.success("添加成功!");
        window.parent.Employees.table.refresh();
        EmployeesInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.employeesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EmployeesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/employees/update", function(data){
        Feng.success("修改成功!");
        window.parent.Employees.table.refresh();
        EmployeesInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.employeesInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("employees-form", EmployeesInfoDlg.validateFields);
});
