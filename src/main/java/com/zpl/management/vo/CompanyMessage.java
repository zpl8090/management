package com.zpl.management.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: [zhangpeiliang]
 */
@Data
public class CompanyMessage {
  /**
   *
   */
  private Long companyId;

  private List<CompanyMessage> childCompanies = new ArrayList<>();

  private List<DepartmentMessage> departments = new ArrayList<>();
  /**
   * 公司名
   */
  private String companyName;
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
