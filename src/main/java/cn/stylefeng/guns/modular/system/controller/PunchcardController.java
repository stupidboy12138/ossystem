package cn.stylefeng.guns.modular.system.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.DeptDict;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.dao.PunchcardMapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Punchcard;
import cn.stylefeng.guns.modular.system.service.IPunchcardService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 考勤打卡控制器
 *
 * @author fengshuonan
 * @Date 2019-06-20 16:18:08
 */
@Controller
@RequestMapping("/punchcard")
public class PunchcardController extends BaseController {

    private String PREFIX = "/system/punchcard/";

    @Autowired
    private IPunchcardService punchcardService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PunchcardMapper punchcardMapper;

    /**
     * 跳转到考勤打卡首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "punchcard.html";
    }

    /**
     * 跳转到添加考勤打卡
     */
    @RequestMapping("/punchcard_add")
    public String punchcardAdd() {
        return PREFIX + "punchcard_add.html";
    }

    /**
     * 跳转到添加考勤打卡
     */
    @RequestMapping("/clocks")
    public String clocksAdd() {
        return PREFIX + "clocks_add.html";
    }

    /**
     * 跳转到修改考勤打卡
     */
    @RequestMapping("/punchcard_update/{punchcardId}")
    public String punchcardUpdate(@PathVariable Integer punchcardId, Model model) {
        Punchcard punchcard = punchcardService.selectById(punchcardId);
        model.addAttribute("item",punchcard);
        LogObjectHolder.me().set(punchcard);
        return PREFIX + "punchcard_edit.html";
    }

    /**
     * 获取考勤打卡列表
     */
    @ApiOperation(value = "获取考勤打卡列表")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false)String punchMan,@RequestParam(required = false)String startTime,
                       @RequestParam(required = false)String endTime,@RequestParam(required = false)boolean isLeave) {
        return punchcardMapper.selectByNameAndTime(punchMan,startTime,endTime,isLeave);
//        return punchcardService.selectList(null);
    }


    /**
     * 获取考勤打卡列表
     */
    @ApiOperation(value = "获取考勤打卡列表")
    @RequestMapping(value = "/myList")
    @ResponseBody
    public Object myList(String condition) {
        return punchcardService.selectList(null);
    }

    /**
     * 考勤打卡
     */
    @ApiOperation(value = "考勤打卡")
    @RequestMapping(value = "/clocksAdd")
    @ResponseBody
    public Object clocksAdd(Punchcard punchcard) throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        //当前时间
        String nowTimes=new String(simpleDateFormat.format(date));
        //打卡时间
        String clockTimes = new String (simpleDateFormat2.format(date)+" 09:00:00");
        String clockOutTimes = new String (simpleDateFormat2.format(date)+" 17:00:00");

        Date nowTime = simpleDateFormat.parse(nowTimes);
        Date clockTime = simpleDateFormat.parse(clockTimes);
        Date clockOutTime = simpleDateFormat.parse(clockOutTimes);

        if (punchcard.getNote().equals("上班打卡")){
            punchcard.setPunchDate(DateUtil.parse(nowTimes));
            if (nowTime.getTime()<clockTime.getTime()){
                punchcard.setDutyStatus("正常上班");
            }else {
                punchcard.setDutyStatus("上班迟到");
            }
            if (redisTemplate.opsForValue().get(ShiroKit.getUser().name+"_"+simpleDateFormat2.format(date))!=null && redisTemplate.opsForValue().get(ShiroKit.getUser().name+"_"+simpleDateFormat2.format(date)).equals(simpleDateFormat2.format(date))){
                return "您今日已经打过卡了哦";
            }else {
                redisTemplate.opsForValue().set(ShiroKit.getUser().name+"_"+simpleDateFormat2.format(date),simpleDateFormat2.format(date));
                punchcard.setPunchMan(ShiroKit.getUser().name);
                punchcardService.insert(punchcard);
                return "今日打卡成功";

            }

        }
        else if (punchcard.getNote().equals("下班打卡")){
            if (redisTemplate.opsForValue().get(ShiroKit.getUser().name+"_"+simpleDateFormat2.format(date))!=null && redisTemplate.opsForValue().get(ShiroKit.getUser().name+"_"+simpleDateFormat2.format(date)).equals(simpleDateFormat2.format(date))){
                punchcard.setClockOut(DateUtil.parse(nowTimes));
                if (nowTime.getTime()>clockOutTime.getTime()){
                    punchcard.setOffDutyStatus("正常下班");
                }else {
                    punchcard.setOffDutyStatus("下班早退");
                }
                punchcardMapper.updateClocktimeByNameAndPunchtime(ShiroKit.getUser().name,simpleDateFormat2.format(date),DateUtil.parse(nowTimes),punchcard.getOffDutyStatus());
                return "下班打卡成功";
            }else {
                return "今日尚未打卡上班,请先打卡上班";
            }

        } else {
            return "请选择打卡类型";
        }

//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//        //当前时间
//        String nowTimes=new String(simpleDateFormat.format(date));
//        //打卡时间
//        String clockTimes = new String (simpleDateFormat2.format(date)+" 09:00:00");
//        String clockOutTimes = new String (simpleDateFormat2.format(date)+" 17:00:00");
//
//        Date nowTime = simpleDateFormat.parse(nowTimes);
//        Date clockTime = simpleDateFormat.parse(clockTimes);
//        Date clockOutTime = simpleDateFormat.parse(clockOutTimes);
//
//        if (punchcard.getNote().equals("上班打卡")){
//            punchcard.setPunchDate(DateUtil.parse(nowTimes));
//            System.out.println("5"+DateUtil.parse(nowTimes));
//            if (nowTime.getTime()<clockTime.getTime()){
//                punchcard.setDutyStatus("正常上班");
//            }else {
//                punchcard.setDutyStatus("上班迟到");
//            }
//        }
//        if (punchcard.getNote().equals("下班打卡")){
//            punchcard.setClockOut(DateUtil.parse(nowTimes));
//            System.out.println("5"+DateUtil.parse(nowTimes));
//            if (nowTime.getTime()>clockOutTime.getTime()){
//                punchcard.setOffDutyStatus("正常下班");
//            }else {
//                punchcard.setOffDutyStatus("下班早退");
//            }
//        }
//        punchcard.setPunchMan(ShiroKit.getUser().name);
//        punchcardService.insert(punchcard);
//        return SUCCESS_TIP;
    }

    /**
     * 新增考勤打卡
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @ApiOperation(value = "增考勤打卡")
    public Object add(Punchcard punchcard) throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        //当前时间
        String nowTimes=new String(simpleDateFormat.format(date));
        //打卡时间
        String clockTimes = new String (simpleDateFormat2.format(date)+" 09:00:00");
        String clockOutTimes = new String (simpleDateFormat2.format(date)+" 17:00:00");

        Date nowTime = simpleDateFormat.parse(nowTimes);
        Date clockTime = simpleDateFormat.parse(clockTimes);
        Date clockOutTime = simpleDateFormat.parse(clockOutTimes);

        if (punchcard.getNote().equals("上班打卡")){
//            redisTemplate.opsForValue().set(ShiroKit.getUser().name+"_"+simpleDateFormat2.format(date),simpleDateFormat2.format(date));
            punchcard.setPunchDate(DateUtil.parse(nowTimes));
            if (nowTime.getTime()<clockTime.getTime()){
                punchcard.setDutyStatus("正常上班");
            }else {
                punchcard.setDutyStatus("上班迟到");
            }
            if (redisTemplate.opsForValue().get(punchcard.getPunchMan()+"_"+simpleDateFormat2.format(date))!=null && redisTemplate.opsForValue().get(punchcard.getPunchMan()+"_"+simpleDateFormat2.format(date)).equals(simpleDateFormat2.format(date))){
                return "您今日已经打过卡了哦";
            }else {
                redisTemplate.opsForValue().set(punchcard.getPunchMan()+"_"+simpleDateFormat2.format(date),simpleDateFormat2.format(date));
                punchcard.setPunchMan(punchcard.getPunchMan());
                punchcardService.insert(punchcard);
                return "上班打卡成功";

            }
        }
        else if (punchcard.getNote().equals("下班打卡")){
            if (redisTemplate.opsForValue().get(punchcard.getPunchMan()+"_"+simpleDateFormat2.format(date))!=null && redisTemplate.opsForValue().get(punchcard.getPunchMan()+"_"+simpleDateFormat2.format(date)).equals(simpleDateFormat2.format(date))){
                punchcard.setClockOut(DateUtil.parse(nowTimes));
                if (nowTime.getTime()>clockOutTime.getTime()){
                    punchcard.setOffDutyStatus("正常下班");
                }else {
                    punchcard.setOffDutyStatus("下班早退");
                }
                punchcardMapper.updateClocktimeByNameAndPunchtime(punchcard.getPunchMan(),simpleDateFormat2.format(date),DateUtil.parse(nowTimes),punchcard.getOffDutyStatus());
                return "下班打卡成功";
            }else {
                return "今日尚未打卡上班,请先打卡上班";
            }

        } else {
            return "请选择打卡类型";
        }

    }

    /**
     * 删除考勤打卡
     */
    @ApiOperation(value = "删除考勤打卡")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer id) {
        punchcardService.deleteById(id);
        return SUCCESS_TIP;
    }

    @ApiOperation(value = "批量删除")
    @GetMapping("/deleteByIds")
    @ResponseBody
    public Object deleteByIds(@RequestParam(value = "ids[]") Integer[] ids){
        return punchcardService.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 修改考勤打卡
     */
    @ApiOperation(value = "修改考勤打卡")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Punchcard punchcard) {
        punchcardService.updateById(punchcard);
        return SUCCESS_TIP;
    }

    /**
     * 考勤打卡详情
     */
    @ApiOperation(value = "考勤打卡详情")
    @RequestMapping(value = "/detail/{punchcardId}")
    @ResponseBody
    public Object detail(@PathVariable("punchcardId") Integer id) {
        return punchcardService.selectById(id);
    }

    @ApiOperation(value = "导出Excel")
    @RequestMapping(value = "/exportExcel")
    @BussinessLog(value = "111111", key = "simplename")
    @Permission
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Punchcard> punchcards = punchcardService.findPunchItem();

        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("Id","ID");
        writer.addHeaderAlias("punchMan","用户姓名");
        writer.addHeaderAlias("punchDate","上班打卡时间");
        writer.addHeaderAlias("clockOut","下班打卡时间");
        writer.addHeaderAlias("dutyStatus","上班打卡状态");
        writer.addHeaderAlias("offDutyStatus","下班打卡状态");
        writer.addHeaderAlias("note","类型");
        writer.addHeaderAlias("isRepair","是否补卡");
        writer.addHeaderAlias("isLeave","是否请假");
        writer.setColumnWidth(2,30);
        writer.setColumnWidth(3,30);

        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(punchcards, true);
        //out为OutputStream，需要写出到的目标流

        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition","attachment;filename=attendance.xls");
        ServletOutputStream out=response.getOutputStream();

        writer.flush(out, true);
        // 关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    @GetMapping("/echarts")
    public String test(){
        return PREFIX + "pecharts.html";
    }

    @GetMapping("/punchcardCount")
    @ResponseBody
    public Integer[] punchcardCount(){
        return punchcardMapper.selectPcounts();
    }
}
