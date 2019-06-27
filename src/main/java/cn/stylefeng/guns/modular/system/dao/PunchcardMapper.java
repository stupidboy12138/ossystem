package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Paysalary;
import cn.stylefeng.guns.modular.system.model.Punchcard;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-20
 */
public interface PunchcardMapper extends BaseMapper<Punchcard> {

    List<Punchcard> selectPunchcardAttendance(@Param("punchMan") String punchMan, @Param("punchDate")Date punchDate,
                                              @Param("clockOut") Date clockOut);

    void updateClocktimeByNameAndPunchtime(@Param("punchMan")String punchMan,@Param("punchDate")String punchDate,
                                           @Param("clockOut")Date clockOut,@Param("offDutyStatus") String offDutyStatus);

    List<Punchcard> selectByNameAndTime(@Param("punchMan")String punchMan, @Param("startTime")String startTime,
                                        @Param("endTime")String endTime,@Param("isLeave") boolean isLeave);

    Integer[] selectPcounts();
}
