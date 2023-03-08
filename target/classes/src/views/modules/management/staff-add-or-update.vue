<template>
  <el-dialog
    :title="!dataForm.staffId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="员工姓名" prop="staffName">
      <el-input v-model="dataForm.staffName" placeholder="员工姓名"></el-input>
    </el-form-item>
    <el-form-item label="员工工号" prop="staffNo">
      <el-input v-model="dataForm.staffNo" placeholder="员工工号"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="联系电话" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="联系电话"></el-input>
    </el-form-item>
    <el-form-item label="状态  0：禁用   1：正常" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态  0：禁用   1：正常"></el-input>
    </el-form-item>
    <el-form-item label="入职时间" prop="joinTime">
      <el-input v-model="dataForm.joinTime" placeholder="入职时间"></el-input>
    </el-form-item>
    <el-form-item label="" prop="departmentId">
      <el-input v-model="dataForm.departmentId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="gmtCreate">
      <el-input v-model="dataForm.gmtCreate" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="gmtModified">
      <el-input v-model="dataForm.gmtModified" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          staffId: 0,
          staffName: '',
          staffNo: '',
          email: '',
          mobile: '',
          status: '',
          joinTime: '',
          departmentId: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          staffName: [
            { required: true, message: '员工姓名不能为空', trigger: 'blur' }
          ],
          staffNo: [
            { required: true, message: '员工工号不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '联系电话不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态  0：禁用   1：正常不能为空', trigger: 'blur' }
          ],
          joinTime: [
            { required: true, message: '入职时间不能为空', trigger: 'blur' }
          ],
          departmentId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          gmtCreate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          gmtModified: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.staffId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.staffId) {
            this.$http({
              url: this.$http.adornUrl(`/management/staff/info/${this.dataForm.staffId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.staffName = data.staff.staffName
                this.dataForm.staffNo = data.staff.staffNo
                this.dataForm.email = data.staff.email
                this.dataForm.mobile = data.staff.mobile
                this.dataForm.status = data.staff.status
                this.dataForm.joinTime = data.staff.joinTime
                this.dataForm.departmentId = data.staff.departmentId
                this.dataForm.gmtCreate = data.staff.gmtCreate
                this.dataForm.gmtModified = data.staff.gmtModified
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/management/staff/${!this.dataForm.staffId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'staffId': this.dataForm.staffId || undefined,
                'staffName': this.dataForm.staffName,
                'staffNo': this.dataForm.staffNo,
                'email': this.dataForm.email,
                'mobile': this.dataForm.mobile,
                'status': this.dataForm.status,
                'joinTime': this.dataForm.joinTime,
                'departmentId': this.dataForm.departmentId,
                'gmtCreate': this.dataForm.gmtCreate,
                'gmtModified': this.dataForm.gmtModified
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
