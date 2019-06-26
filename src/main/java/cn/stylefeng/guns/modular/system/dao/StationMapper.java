package cn.stylefeng.guns.modular.system.dao;

import cn.stylefeng.guns.modular.system.model.Station;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaooo
 * @since 2019-06-21
 */
public interface StationMapper extends BaseMapper<Station> {

    void deleteByIds(List<Integer> list);

}
