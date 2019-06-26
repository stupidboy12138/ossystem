package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.Station;
import cn.stylefeng.guns.modular.system.dao.StationMapper;
import cn.stylefeng.guns.modular.system.service.IStationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements IStationService {

    @Autowired
    private StationMapper stationMapper;

    @Override
    public void deleteByIds(List<Integer> list) {
        stationMapper.deleteByIds(list);
    }
}
