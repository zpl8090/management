package com.zpl.management.service.impl;

import com.zpl.management.entity.DepartmentEntity;
import com.zpl.management.entity.StaffEntity;
import com.zpl.management.service.DepartmentService;
import com.zpl.management.service.StaffService;
import com.zpl.management.vo.CompanyMessage;
import com.zpl.management.vo.DepartmentMessage;
import com.zpl.management.vo.StaffMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpl.management.common.utils.PageUtils;
import com.zpl.management.common.utils.Query;

import com.zpl.management.dao.CompanyDao;
import com.zpl.management.entity.CompanyEntity;
import com.zpl.management.service.CompanyService;


@Service("companyService")
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, CompanyEntity> implements CompanyService {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private StaffService staffService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CompanyEntity> page = this.page(
                new Query<CompanyEntity>().getPage(params),
                new QueryWrapper<CompanyEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CompanyMessage> queryAll() {
        List<CompanyMessage> roots = queryById(0L);

        getCompanyTree(roots);

        return roots;
    }

    @Override
    public List<CompanyMessage> queryById(Long companyId) {
        List<CompanyMessage> res = new ArrayList<>();
        List<CompanyEntity> companyEntities = this.list(new QueryWrapper<CompanyEntity>().eq(companyId != null, "parent_id", companyId));
        for (CompanyEntity companyEntity : companyEntities) {
            CompanyMessage companyMessage = new CompanyMessage();
            BeanUtils.copyProperties(companyEntity, companyMessage);
            res.add(companyMessage);
        }
        return  res;
    }

    public List<DepartmentMessage> queryDepartmentById(Long departmentId) {
        List<DepartmentMessage> res = new ArrayList<>();
        List<DepartmentEntity> departmentEntities = departmentService.list(new QueryWrapper<DepartmentEntity>().eq(departmentId != null, "parent_id", departmentId));
        for (DepartmentEntity departmentEntity : departmentEntities) {
            DepartmentMessage departmentMessage = new DepartmentMessage();
            BeanUtils.copyProperties(departmentEntity, departmentMessage);
            res.add(departmentMessage);
        }
        return  res;
    }

    public List<DepartmentMessage> queryDepartmentByCompanyId(Long companyId) {
        List<DepartmentMessage> res = new ArrayList<>();
        List<DepartmentEntity> departmentEntities = departmentService.list(new QueryWrapper<DepartmentEntity>().eq(companyId != null, "company_id", companyId)
                .eq("parent_id", 0));
        for (DepartmentEntity departmentEntity : departmentEntities) {
            DepartmentMessage departmentMessage = new DepartmentMessage();
            BeanUtils.copyProperties(departmentEntity, departmentMessage);
            res.add(departmentMessage);
        }
        return  res;
    }

    public List<StaffMessage> queryStaffByDepartmentId(Long departmentId) {
        List<StaffMessage> res = new ArrayList<>();
        List<StaffEntity> staffEntities = staffService.list(new QueryWrapper<StaffEntity>().eq(departmentId != null, "department_id", departmentId));
        for (StaffEntity staffEntity : staffEntities) {
            StaffMessage staffMessage = new StaffMessage();
            BeanUtils.copyProperties(staffEntity, staffMessage);
            res.add(staffMessage);
        }
        return  res;
    }

    public List<CompanyMessage> getCompanyTree(List<CompanyMessage> roots) {
        if (roots != null) {
            List<CompanyMessage> children = new ArrayList<>();

            for (CompanyMessage root : roots) {
                root.setDepartments(getDepartmentTree(queryDepartmentByCompanyId(root.getCompanyId())));
                root.setChildCompanies(getCompanyTree(queryById(root.getCompanyId())));
                children.add(root);
            }

            return children;
        }

        return null;
        
    }

    public List<DepartmentMessage> getDepartmentTree(List<DepartmentMessage> roots) {
        if (roots != null) {
            List<DepartmentMessage> children = new ArrayList<>();

            for (DepartmentMessage root : roots) {
                root.setStaffs(queryStaffByDepartmentId(root.getDepartmentId()));
                root.setChildDepartments(getDepartmentTree(queryDepartmentById(root.getDepartmentId())));
                children.add(root);
            }

            return children;
        }

        return null;

    }

}