<template>
    <section>
    <el-table :data="user" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
        <el-table-column type="selection" >
        </el-table-column>
        <el-table-column type="index" label="编号"  width="80px">
        </el-table-column>
        <el-table-column prop="username" label="名称" align="center" sortable>
        </el-table-column>
        <el-table-column label="操作" width="150">
            <template scope="scope">
                <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            </template>
        </el-table-column>
    </el-table>


        <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px"  ref="editForm">
                <el-form-item label="旧密码" prop="password">
                    <el-input v-model="editForm.oldpassword" type="password" auto-complete="off"
                              v-on:blur="changeNumFun()"
                    ></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="password">
                    <el-input v-model="editForm.nowpassword" type="password" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="password">
                    <el-input v-model="editForm.checkpassword" type="password" auto-complete="off"
                              v-on:blur="changepassFun()"
                    ></el-input>
                </el-form-item>
                <el-form-item label="更改头像" prop="logo">
                    <el-upload
                            v-model="editForm.headimg" auto-complete="off"
                            placeholder="公司logo"
                            class="upload-demo"
                            action="http://localhost/file/upload"
                            :on-success="handleSuccess"
                            list-type="picture">
                        <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
              <el-button type="primary" @click.native="editSubmit" :loading="editLoading" :disabled="isdisabledFn" >提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    export default {
        data(){
            return {
                isdisabledFn:false,
                name: "account",
                listLoading: false,
                user:[],
                sels:[],
                editForm:{},
                editFormVisible:false,
                editLoading: false,
            }
        },
        methods:{
            handleSuccess(response,file,fileList){
                console.log(response);
                this.editForm.headimg = response;
            },
            changeNumFun(){
                this.formCheckToFront();
            },
            changepassFun(){
                this.formCheck();
            },
            formCheck(){
                let para = Object.assign({}, this.editForm);
                if(para.nowpassword!=para.checkpassword){
                    this.$message({
                        message: '两次密码不一致',
                        type: 'error'
                    });
                    this.isdisabledFn=true;
                }else {
                    this.isdisabledFn=false;
                }
            },
            formCheckToFront() {
                let para = Object.assign({}, this.editForm);
                para.password = para.oldpassword;
                this.$http.put("/employee/check",para).then((res)=>{
                    let success =res.data.success;
                    if(success!=true){
                        this.$message({
                            message: res.data.message,
                            type: 'error'
                        });
                        this.isdisabledFn=true;
                    }else {
                        this.isdisabledFn=false;
                    }
                })
            },
            getUser(){
                this.listLoading = true;
                let user = sessionStorage.getItem('user');
                user = JSON.parse(user);
                this.$http.get("/employee/"+user.id).then((res)=>{
                    this.user.push(res.data);
                    this.listLoading = false;
                })
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);
            },
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            let para = Object.assign({}, this.editForm);
                            para.password = para.nowpassword;
                            console.log(para);
                            this.$http.post("/employee", para).then(res => {
                                this.editLoading = false;
                                this.$message({
                                    message: '修改成功',
                                    type: 'success'
                                });
                                this.editFormVisible =false;
                            });

                        });
                    }
                });
            },
        },
        mounted() {
            this.getUser();
        }
    }

</script>

<style scoped>

</style>