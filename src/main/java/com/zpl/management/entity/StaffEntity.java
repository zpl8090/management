package com.zpl.management.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 员工
 * 
 * @author zhangpeiliang
 * @email 1720619112@qq.com
 * @date 2023-03-08 15:55:17
 */
@Data
@TableName("staff")
public class StaffEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
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
	@TableField(fill = FieldFill.INSERT)
	private Date joinTime;
	/**
	 * 部门ID
	 */
	private Long departmentId;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;

}
