package com.zpl.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zpl.management.common.utils.PageUtils;
import com.zpl.management.entity.DepartmentEntity;

import java.util.Map;

/**
 * 部门
 *
 * @author zhangpeiliang
 * @email 1720619112@qq.com
 * @date 2023-03-08 15:55:17
 */
public interface DepartmentService extends IService<DepartmentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

