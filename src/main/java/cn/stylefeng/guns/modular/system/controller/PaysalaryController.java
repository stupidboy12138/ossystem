package cn.stylefeng.guns.modular.system.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.dao.PunchcardMapper;
import cn.stylefeng.guns.modular.system.model.Punchcard;
import cn.stylefeng.guns.modular.system.service.IPunchcardService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Paysalary;
import cn.stylefeng.guns.modular.system.service.IPaysalaryService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 派薪单控制器
 *
 * @author fengshuonan
 * @Date 2019-06-21 12:57:03
 */
@Controller
@RequestMapping("/paysalary")
public class PaysalaryController extends BaseController {

    private String PREFIX = "/system/paysalary/";

    @Autowired
    private IPaysalaryService paysalaryService;

    @Autowired
    private IPunchcardService punchcardService;

    @Autowired
    private PunchcardMapper punchcardMapper;

    /**
     * 跳转到派薪单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "paysalary.html";
    }

    /**
     * 跳转到添加派薪单
     */
    @RequestMapping("/paysalary_add")
    public String paysalaryAdd() {
        return PREFIX + "paysalary_add.html";
    }

    /**
     * 跳转到修改派薪单
     */
    @RequestMapping("/paysalary_update/{paysalaryId}")
    public String paysalaryUpdate(@PathVariable Integer paysalaryId, Model model) {
        Paysalary paysalary = paysalaryService.selectById(paysalaryId);
        model.addAttribute("item",paysalary);
        LogObjectHolder.me().set(paysalary);
        return PREFIX + "paysalary_edit.html";
    }

    /**
     * 获取派薪单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return paysalaryService.selectList(null);
    }

    /**
     * 新增派薪单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Paysalary paysalary) {
        paysalaryService.insert(paysalary);
        return SUCCESS_TIP;
    }

    /**
     * 删除派薪单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer id) {
        paysalaryService.deleteById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改派薪单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Paysalary paysalary) {
        Long totalHours = Long.valueOf(0);
        Double reduceMoney= Double.valueOf(0);

        //计算两个日期区间的天数
        String dateStr1 = paysalary.getStartTime();
        Date date1 = DateUtil.parse(dateStr1);
        String dateStr2 = paysalary.getEndTime();
        Date date2 = DateUtil.parse(dateStr2);
        Long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
        //计算区间薪水
        List<Punchcard> punchcards = punchcardMapper.selectByNameAndTime(paysalary.getSalariedPeople(),paysalary.getStartTime(),paysalary.getEndTime(),false);
        List<Punchcard> leaves = punchcardMapper.selectByNameAndTime(paysalary.getSalariedPeople(),paysalary.getStartTime(),paysalary.getEndTime(),true);
        for (Punchcard punchcard:punchcards){
            Long selectionHours = DateUtil.between(punchcard.getPunchDate(),punchcard.getClockOut(),DateUnit.HOUR);
            totalHours = totalHours+selectionHours;
        }

        if (leaves.size()>0){
            reduceMoney = leaves.size()*4*paysalary.getFixedSalary();
        }
        Double fixSalary = paysalary.getFixedSalary();
        Double selectionMoney = totalHours*fixSalary+reduceMoney;
        paysalary.setSelectionSalary(selectionMoney);
        paysalaryService.updateById(paysalary);
        return SUCCESS_TIP;
    }

    /**
     * 派薪单详情
     */
    @RequestMapping(value = "/detail/{paysalaryId}")
    @ResponseBody
    public Object detail(@PathVariable("paysalaryId") Integer paysalaryId) {
        return paysalaryService.selectById(paysalaryId);
    }
}
