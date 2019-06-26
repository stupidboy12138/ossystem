package cn.stylefeng.guns.modular.system.controller;

import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.modular.system.model.Punchcard;
import cn.stylefeng.guns.modular.system.service.IPunchcardService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Repaircard;
import cn.stylefeng.guns.modular.system.service.IRepaircardService;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 补卡单控制器
 *
 * @author fengshuonan
 * @Date 2019-06-21 12:44:21
 */
@Controller
@RequestMapping("/repaircard")
public class RepaircardController extends BaseController {

    private String PREFIX = "/system/repaircard/";

    @Autowired
    private IRepaircardService repaircardService;

    @Autowired
    private IPunchcardService punchcardService;

    /**
     * 跳转到补卡单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "repaircard.html";
    }

    /**
     * 跳转到添加补卡单
     */
    @RequestMapping("/repaircard_add")
    public String repaircardAdd() {
        return PREFIX + "repaircard_add.html";
    }

    /**
     * 跳转到修改补卡单
     */
    @RequestMapping("/repaircard_update/{repaircardId}")
    public String repaircardUpdate(@PathVariable Integer repaircardId, Model model) {
        Repaircard repaircard = repaircardService.selectById(repaircardId);
        model.addAttribute("item",repaircard);
        LogObjectHolder.me().set(repaircard);
        return PREFIX + "repaircard_edit.html";
    }

    /**
     * 获取补卡单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return repaircardService.selectList(null);
    }

    /**
     * 新增补卡单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @ApiOperation(value = "新增补卡单")
    @Transactional
    public Object add(Repaircard repaircard) {
        EntityWrapper<Punchcard> wrapper = new EntityWrapper<>();
        wrapper.eq("punch_man",repaircard.getRepairMan()).and().like("punch_date",repaircard.getRepairDate());
        Punchcard punchcard = punchcardService.selectOne(wrapper);
        if (punchcard != null) {
            punchcard.setRepair(true);
            punchcardService.updateById(punchcard);
            repaircardService.insert(repaircard);
            return "补卡成功";
        }else {
            Punchcard newPunchcard = new Punchcard();
            newPunchcard.setPunchMan(repaircard.getRepairMan());
            newPunchcard.setPunchDate(DateUtil.parse(repaircard.getRepairDate()));
            newPunchcard.setRepair(true);
            newPunchcard.setNote("补卡");
            punchcardService.insert(newPunchcard);
            repaircardService.insert(repaircard);
            return "补签成功";
        }
    }

    /**
     * 删除补卡单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer id) {
        repaircardService.deleteById(id);
        return SUCCESS_TIP;
    }

    @GetMapping("/deleteByIds")
    @ResponseBody
    @ApiOperation(value = "根据ID数组批量删除")
    public Object deleteByIds(@RequestParam(value = "ids[]") Integer[] ids){
        return repaircardService.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 修改补卡单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Repaircard repaircard) {
        repaircardService.updateById(repaircard);
        return SUCCESS_TIP;
    }

    /**
     * 补卡单详情
     */
    @RequestMapping(value = "/detail/{repaircardId}")
    @ResponseBody
    public Object detail(@PathVariable("repaircardId") Integer repaircardId) {
        return repaircardService.selectById(repaircardId);
    }
}
