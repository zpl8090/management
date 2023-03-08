<template>
  <el-dialog
    :title="!dataForm.companyId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="父公司ID，总公司为0" prop="parentId">
      <el-input v-model="dataForm.parentId" placeholder="父公司ID，总公司为0"></el-input>
    </el-form-item>
    <el-form-item label="公司名" prop="companyName">
      <el-input v-model="dataForm.companyName" placeholder="公司名"></el-input>
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
    <el-form-item label="成立时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="成立时间"></el-input>
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
          companyId: 0,
          parentId: '',
          companyName: '',
          email: '',
          mobile: '',
          status: '',
          createTime: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          parentId: [
            { required: true, message: '父公司ID，总公司为0不能为空', trigger: 'blur' }
          ],
          companyName: [
            { required: true, message: '公司名不能为空', trigger: 'blur' }
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
          createTime: [
            { required: true, message: '成立时间不能为空', trigger: 'blur' }
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
        this.dataForm.companyId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.companyId) {
            this.$http({
              url: this.$http.adornUrl(`/management/company/info/${this.dataForm.companyId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.parentId = data.company.parentId
                this.dataForm.companyName = data.company.companyName
                this.dataForm.email = data.company.email
                this.dataForm.mobile = data.company.mobile
                this.dataForm.status = data.company.status
                this.dataForm.createTime = data.company.createTime
                this.dataForm.gmtCreate = data.company.gmtCreate
                this.dataForm.gmtModified = data.company.gmtModified
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
              url: this.$http.adornUrl(`/management/company/${!this.dataForm.companyId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'companyId': this.dataForm.companyId || undefined,
                'parentId': this.dataForm.parentId,
                'companyName': this.dataForm.companyName,
                'email': this.dataForm.email,
                'mobile': this.dataForm.mobile,
                'status': this.dataForm.status,
                'createTime': this.dataForm.createTime,
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
