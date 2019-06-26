package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.dto.EmployeesDTO;
import cn.stylefeng.guns.modular.system.model.Employees;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaooo
 * @since 2019-06-21
 */
public interface IEmployeesService extends IService<Employees> {
    List<EmployeesDTO> findAllEmployeesInfo();

    List<EmployeesDTO> findEmployeeByNameAndEmpCode(@RequestParam(required = false) String name, @RequestParam(required = false) String empCode);
}
