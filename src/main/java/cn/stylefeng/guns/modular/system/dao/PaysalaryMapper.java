package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Paysalary;
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
public interface PaysalaryMapper extends BaseMapper<Paysalary> {
    List<Paysalary> selectByNameAndTime(@Param("salariedPeople")String salariedPeople,@Param("startTime")String startTime,
                                        @Param("endTime")String endTime);
}
