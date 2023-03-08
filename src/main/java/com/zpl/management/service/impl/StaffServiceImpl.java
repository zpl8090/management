package com.zpl.management.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpl.management.common.utils.PageUtils;
import com.zpl.management.common.utils.Query;

import com.zpl.management.dao.StaffDao;
import com.zpl.management.entity.StaffEntity;
import com.zpl.management.service.StaffService;


@Service("staffService")
public class StaffServiceImpl extends ServiceImpl<StaffDao, StaffEntity> implements StaffService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StaffEntity> page = this.page(
                new Query<StaffEntity>().getPage(params),
                new QueryWrapper<StaffEntity>()
        );

        return new PageUtils(page);
    }

}