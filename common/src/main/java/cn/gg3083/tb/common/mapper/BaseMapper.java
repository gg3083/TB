package cn.gg3083.tb.common.mapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义  通用 Mapper
 *
 */

public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> ,IdsMapper<T>{
}
