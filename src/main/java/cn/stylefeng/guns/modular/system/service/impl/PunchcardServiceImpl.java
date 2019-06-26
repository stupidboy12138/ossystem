package cn.stylefeng.guns.modular.system.service.impl;

import cn.stylefeng.guns.modular.system.model.Punchcard;
import cn.stylefeng.guns.modular.system.dao.PunchcardMapper;
import cn.stylefeng.guns.modular.system.service.IPunchcardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-20
 */
@Service
public class PunchcardServiceImpl extends ServiceImpl<PunchcardMapper, Punchcard> implements IPunchcardService {

    @Autowired private PunchcardMapper punchcardMapper;

    @Override
    public List<Punchcard> findPunchItem() {
        return punchcardMapper.selectList(null);
    }
}
