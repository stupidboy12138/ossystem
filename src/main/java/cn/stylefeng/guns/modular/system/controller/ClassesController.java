package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Classes;
import cn.stylefeng.guns.modular.system.service.IClassesService;

import java.util.List;

/**
 * 排班表控制器
 *
 * @author fengshuonan
 * @Date 2019-06-21 13:49:59
 */
@Controller
@RequestMapping("/classes")
public class ClassesController extends BaseController {

    private String PREFIX = "/system/classes/";

    @Autowired
    private IClassesService classesService;

    /**
     * 跳转到排班表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "classes.html";
    }

    /**
     * 跳转到添加排班表
     */
    @RequestMapping("/classes_add")
    public String classesAdd() {
        return PREFIX + "classes_add.html";
    }

    /**
     * 跳转到修改排班表
     */
    @RequestMapping("/classes_update/{classesId}")
    public String classesUpdate(@PathVariable Integer classesId, Model model) {
        Classes classes = classesService.selectById(classesId);
        model.addAttribute("item",classes);
        LogObjectHolder.me().set(classes);
        return PREFIX + "classes_edit.html";
    }

    /**
     * 获取排班表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return classesService.selectList(null);
    }

    /**
     * 新增排班表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Classes classes) {
        classesService.insert(classes);
        return SUCCESS_TIP;
    }

    /**
     * 删除排班表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer id) {
        classesService.deleteById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改排班表
     */
    @ApiOperation(value = "修改排班表")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Classes classes) {
        classesService.updateById(classes);
        return SUCCESS_TIP;
    }

    /**
     * 排班表详情
     */
    @RequestMapping(value = "/detail/{classesId}")
    @ResponseBody
    public Object detail(@PathVariable("classesId") Integer classesId) {
        return classesService.selectById(classesId);
    }
}
