package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.Station;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaooo
 * @since 2019-06-21
 */
public interface IStationService extends IService<Station> {
    void deleteByIds(List<Integer> list);
}
