package com.zpl.management.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zpl.management.entity.DepartmentEntity;
import com.zpl.management.service.DepartmentService;
import com.zpl.management.common.utils.PageUtils;
import com.zpl.management.common.utils.R;



/**
 * 部门
 *
 * @author zhangpeiliang
 * @email 1720619112@qq.com
 * @date 2023-03-08 15:55:17
 */
@RestController
@RequestMapping("management/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = departmentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{departmentId}")
    public R info(@PathVariable("departmentId") Long departmentId){
		DepartmentEntity department = departmentService.getById(departmentId);

        return R.ok().put("department", department);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DepartmentEntity department){
		departmentService.save(department);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DepartmentEntity department){
		departmentService.updateById(department);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] departmentIds){
		departmentService.removeByIds(Arrays.asList(departmentIds));

        return R.ok();
    }

}
