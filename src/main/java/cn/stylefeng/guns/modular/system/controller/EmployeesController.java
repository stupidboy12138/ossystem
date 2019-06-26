package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.modular.system.dao.EmployeesMapper;
import cn.stylefeng.guns.modular.system.dto.EmployeesDTO;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Employees;
import cn.stylefeng.guns.modular.system.service.IEmployeesService;

import java.util.Arrays;
import java.util.List;

/**
 * 员工管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-21 09:35:26
 */
@Controller
@RequestMapping("/employees")
public class EmployeesController extends BaseController {

    private String PREFIX = "/system/employees/";

    @Autowired
    private IEmployeesService employeesService;

    /**
     * 跳转到员工管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "employees.html";
    }

    /**
     * 跳转到添加员工管理
     */
    @RequestMapping("/employees_add")
    public String employeesAdd() {
        return PREFIX + "employees_add.html";
    }

    /**
     * 跳转到修改员工管理
     */
    @RequestMapping("/employees_update/{employeesId}")
    public String employeesUpdate(@PathVariable Integer employeesId, Model model) {
        Employees employees = employeesService.selectById(employeesId);
        model.addAttribute("item",employees);
        LogObjectHolder.me().set(employees);
        return PREFIX + "employees_edit.html";
    }

    /**
     * 获取员工管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,@RequestParam(required = false)String empCode) {
//        return employeesService.selectList(null);
//        return employeesService.findAllEmployeesInfo();
        return employeesService.findEmployeeByNameAndEmpCode(name,empCode);
    }

    /**
     * 新增员工管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Employees employees) {
        employeesService.insert(employees);
        return SUCCESS_TIP;
    }

    /**
     * 删除员工管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer empId) {
        employeesService.deleteById(empId);
        return SUCCESS_TIP;
    }

    /**
     * 修改员工管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Employees employees) {
        employeesService.updateById(employees);
        return SUCCESS_TIP;
    }

    /**
     * 员工管理详情
     */
    @RequestMapping(value = "/detail/{empId}")
    @ResponseBody
    public Object detail(@PathVariable("empId") Integer empId) {
        return employeesService.selectById(empId);
    }


    @GetMapping("/findByName")
    @ResponseBody
    public List<EmployeesDTO> findByName(@RequestParam(required = false)String name,@RequestParam(required = false)String empCode){
        return employeesService.findEmployeeByNameAndEmpCode(name,empCode);
    }

    @GetMapping("deleteByIds")
    @ResponseBody
    public boolean deleteByIds(@RequestParam(value = "ids[]") Integer[] ids){
        return employeesService.deleteBatchIds(Arrays.asList(ids));
    }
}
