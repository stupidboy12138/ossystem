package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.dto.EmployeesDTO;
import cn.stylefeng.guns.modular.system.model.Employees;
import cn.stylefeng.guns.modular.system.dao.EmployeesMapper;
import cn.stylefeng.guns.modular.system.service.IEmployeesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaooo
 * @since 2019-06-21
 */
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements IEmployeesService {

    @Autowired
    private EmployeesMapper employeesMapper;

    @Override
    public List<EmployeesDTO> findAllEmployeesInfo() {
        return employeesMapper.selectEmployeesAllInfo();
    }

    @Override
    public List<EmployeesDTO> findEmployeeByNameAndEmpCode(@RequestParam(required = false) String name, @RequestParam(required = false) String empCode) {
        return employeesMapper.selectByNameAndEmpCode(name,empCode);
    }
}
