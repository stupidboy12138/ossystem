package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.dto.EmployeesDTO;
import cn.stylefeng.guns.modular.system.model.Employees;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaooo
 * @since 2019-06-21
 */
public interface EmployeesMapper extends BaseMapper<Employees> {

    List<EmployeesDTO> selectEmployeesAllInfo();

    List<Employees> selectEmployeesAll();

    List<EmployeesDTO> selectByNameAndEmpCode(@Param("name")String name,@Param("empCode")String empCode);


}
