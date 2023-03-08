package com.zpl.management.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zpl.management.entity.StaffEntity;
import com.zpl.management.service.StaffService;
import com.zpl.management.common.utils.PageUtils;
import com.zpl.management.common.utils.R;



/**
 * 员工
 *
 * @author zhangpeiliang
 * @email 1720619112@qq.com
 * @date 2023-03-08 15:55:17
 */
@RestController
@RequestMapping("management/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = staffService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{staffId}")
    public R info(@PathVariable("staffId") Long staffId){
		StaffEntity staff = staffService.getById(staffId);

        return R.ok().put("staff", staff);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody StaffEntity staff){
		staffService.save(staff);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody StaffEntity staff){
		staffService.updateById(staff);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] staffIds){
		staffService.removeByIds(Arrays.asList(staffIds));

        return R.ok();
    }

}
