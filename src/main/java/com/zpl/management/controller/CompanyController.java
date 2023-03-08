package com.zpl.management.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.zpl.management.vo.CompanyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zpl.management.entity.CompanyEntity;
import com.zpl.management.service.CompanyService;
import com.zpl.management.common.utils.PageUtils;
import com.zpl.management.common.utils.R;



/**
 * 公司
 *
 * @author zhangpeiliang
 * @email 1720619112@qq.com
 * @date 2023-03-08 15:55:17
 */
@RestController
@RequestMapping("management/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 公司详情列表
     */
    @RequestMapping("/details")
    public R list(){
        List<CompanyMessage> messages = companyService.queryAll();
        return R.ok().put("details", messages);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = companyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{companyId}")
    public R info(@PathVariable("companyId") Long companyId){
		CompanyEntity company = companyService.getById(companyId);

        return R.ok().put("company", company);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CompanyEntity company){
		companyService.save(company);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CompanyEntity company){
		companyService.updateById(company);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] companyIds){
		companyService.removeByIds(Arrays.asList(companyIds));

        return R.ok();
    }

}
