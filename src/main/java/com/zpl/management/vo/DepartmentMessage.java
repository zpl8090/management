package com.zpl.management.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: [zhangpeiliang]
 */
@Data
public class DepartmentMessage {

  private Long departmentId;

  private List<DepartmentMessage> childDepartments = new ArrayList<>();

  private List<StaffMessage> staffs = new ArrayList<>();

  /**
   * 公司ID
   */
  private Long companyId;
  /**
   * 公司名
   */
  private String departmentName;
  /**
   * 邮箱
   */
  private String email;
  /**
   * 联系电话
   */
  private String mobile;
  /**
   * 状态  0：禁用   1：正常
   */
  private Integer status;
  /**
   * 成立时间
   */
  private Date createTime;
  /**
   *
   */
  private Date gmtCreate;
  /**
   *
   */
  private Date gmtModified;
}
