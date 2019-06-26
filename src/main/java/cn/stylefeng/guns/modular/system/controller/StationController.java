package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Station;
import cn.stylefeng.guns.modular.system.service.IStationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 岗位管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-21 11:34:38
 */
@Controller
@RequestMapping("/station")
public class StationController extends BaseController {

    private String PREFIX = "/system/station/";

    @Autowired
    private IStationService stationService;

    /**
     * 跳转到岗位管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "station.html";
    }

    /**
     * 跳转到添加岗位管理
     */
    @RequestMapping("/station_add")
    public String stationAdd() {
        return PREFIX + "station_add.html";
    }

    /**
     * 跳转到修改岗位管理
     */
    @RequestMapping("/station_update/{stationId}")
    public String stationUpdate(@PathVariable Integer stationId, Model model) {
        Station station = stationService.selectById(stationId);
        model.addAttribute("item",station);
        LogObjectHolder.me().set(station);
        return PREFIX + "station_edit.html";
    }

    /**
     * 获取岗位管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return stationService.selectList(null);
    }

    /**
     * 新增岗位管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Station station) {
        stationService.insert(station);
        return SUCCESS_TIP;
    }

    /**
     * 删除岗位管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer stationId) {
        stationService.deleteById(stationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改岗位管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Station station) {
        stationService.updateById(station);
        return SUCCESS_TIP;
    }

    /**
     * 岗位管理详情
     */
    @RequestMapping(value = "/detail/{stationId}")
    @ResponseBody
    public Object detail(@PathVariable("stationId") Integer stationId) {
        return stationService.selectById(stationId);
    }

    @GetMapping("/deleteByIds")
    @ResponseBody
    public boolean deleteByIds(@RequestParam("ids[]") Integer[] ids){
        return stationService.deleteBatchIds(Arrays.asList(ids));
    }
}
