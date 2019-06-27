package cn.stylefeng.guns.modular.system.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.modular.system.model.Punchcard;
import cn.stylefeng.guns.modular.system.service.IPunchcardService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Leave;
import cn.stylefeng.guns.modular.system.service.ILeaveService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 请假单控制器
 *
 * @author fengshuonan
 * @Date 2019-06-21 13:00:06
 */
@Controller
@RequestMapping("/leave")
public class LeaveController extends BaseController {

    private String PREFIX = "/system/leave/";

    @Autowired
    private ILeaveService leaveService;

    @Autowired
    private IPunchcardService punchcardService;

    /**
     * 跳转到请假单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "leave.html";
    }

    /**
     * 跳转到添加请假单
     */
    @RequestMapping("/leave_add")
    public String leaveAdd() {
        return PREFIX + "leave_add.html";
    }

    /**
     * 跳转到修改请假单
     */
    @RequestMapping("/leave_update/{leaveId}")
    public String leaveUpdate(@PathVariable Integer leaveId, Model model) {
        Leave leave = leaveService.selectById(leaveId);
        model.addAttribute("item",leave);
        LogObjectHolder.me().set(leave);
        return PREFIX + "leave_edit.html";
    }

    /**
     * 获取请假单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return leaveService.selectList(null);
    }

    /**
     * 新增请假单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Leave leave) {
        EntityWrapper<Punchcard> wrapper = new EntityWrapper<>();
        wrapper.eq("punch_man",leave.getLeaveMan()).and().between("punch_date", DateUtil.parse(leave.getStartTime()),DateUtil.parse(leave.getEndTime()));
        List<Punchcard> punchcards = punchcardService.selectList(wrapper);
        long betweenDay = DateUtil.between(DateUtil.parse(leave.getStartTime()), DateUtil.parse(leave.getEndTime()), DateUnit.DAY);
        //如果请假区间不存在打卡记录
        if (punchcards.size()==0){
            //循环依次插入请假记录
            for(long i=0 ;i<betweenDay; i++ ){
                Date date = DateUtil.parse(leave.getStartTime());
                DateTime nextDay = DateUtil.offsetDay(date, Math.toIntExact(i));
                Punchcard punchcard = new Punchcard();
                punchcard.setPunchMan(leave.getLeaveMan());
                punchcard.setNote("请假");
                punchcard.setDutyStatus("请假");
                punchcard.setOffDutyStatus("请假");
                punchcard.setLeave(true);
                punchcard.setPunchDate(DateUtil.offsetDay(nextDay,9));
                punchcard.setClockOut(DateUtil.offsetHour(nextDay,17));
                punchcardService.insert(punchcard);
            }
        }else {
            for (Punchcard punchcard:punchcards) {
                if (punchcard.getPunchDate()==null){

                }else {

                }
            }
        }
        leaveService.insert(leave);
        return SUCCESS_TIP;
    }

    /**
     * 删除请假单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer id) {
        leaveService.deleteById(id);
        return SUCCESS_TIP;
    }

    @GetMapping("/deleteByIds")
    @ResponseBody
    public Object deleteByIds(@RequestParam(value = "ids[]") Integer[] ids){
        return leaveService.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 修改请假单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Leave leave) {
        leaveService.updateById(leave);
        return SUCCESS_TIP;
    }

    /**
     * 请假单详情
     */
    @RequestMapping(value = "/detail/{leaveId}")
    @ResponseBody
    public Object detail(@PathVariable("leaveId") Integer leaveId) {
        return leaveService.selectById(leaveId);
    }
}
