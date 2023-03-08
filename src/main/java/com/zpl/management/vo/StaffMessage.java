package com.zpl.management.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author: [zhangpeiliang]
 */
@Data
public class StaffMessage {

  private Long staffId;
  /**
   * 员工姓名
   */
  private String staffName;
  /**
   * 员工工号
   */
  private String staffNo;
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
   * 入职时间
   */
  private Date joinTime;

  /**
   *
   */
  private Date gmtCreate;
  /**
   *
   */
  private Date gmtModified;
}
