package com.zpl.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zpl.management.common.utils.PageUtils;
import com.zpl.management.entity.CompanyEntity;
import com.zpl.management.vo.CompanyMessage;

import java.util.List;
import java.util.Map;

/**
 * 公司
 *
 * @author zhangpeiliang
 * @email 1720619112@qq.com
 * @date 2023-03-08 15:55:17
 */
public interface CompanyService extends IService<CompanyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CompanyMessage> queryAll();

    List<CompanyMessage> queryById(Long companyId);
}

