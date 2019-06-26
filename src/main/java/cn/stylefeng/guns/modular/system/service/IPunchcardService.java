package cn.stylefeng.guns.modular.system.service;

import cn.stylefeng.guns.modular.system.model.Punchcard;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-20
 */
public interface IPunchcardService extends IService<Punchcard> {
    List<Punchcard> findPunchItem();
}
