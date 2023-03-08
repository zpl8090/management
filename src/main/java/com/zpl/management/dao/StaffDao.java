package com.zpl.management.dao;

import com.zpl.management.entity.StaffEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工
 * 
 * @author zhangpeiliang
 * @email 1720619112@qq.com
 * @date 2023-03-08 15:55:17
 */
@Mapper
public interface StaffDao extends BaseMapper<StaffEntity> {
	
}
