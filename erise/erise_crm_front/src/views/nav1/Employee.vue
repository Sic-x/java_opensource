<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.keywords" placeholder="员工名称"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getEmployees">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="employees" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
            <el-table-column type="selection" >
            </el-table-column>
            <el-table-column type="index" label="编号" width="80px">
            </el-table-column>
            <el-table-column prop="username" label="员工账户"  sortable>
            </el-table-column>
            <el-table-column prop="realName" label="真实姓名"  sortable>
            </el-table-column>
            <el-table-column prop="password" label="密码" v-if=false  sortable>
            </el-table-column>
            <el-table-column prop="tel" label="电话号码"  sortable>
            </el-table-column>
            <el-table-column prop="address" label="家庭住址"  sortable>
            </el-table-column>
            <el-table-column prop="dept.name" label="所属部门"  sortable>
            </el-table-column>
            <el-table-column :formatter="timeformat" prop="inputTime" label="录入时间" sortable>
            </el-table-column>


            <el-table-column label="操作" width="150">
                <template scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>

            <el-pagination layout="prev, pager, next"
                           @current-change="handleCurrentChange"  :total="total"
                           style="float:right;">
            </el-pagination>

        </el-col>

        <!--编辑界面-->
        <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
            <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
                <el-form-item label="员工账户" prop="username">
                    <el-input v-model="editForm.username" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名" prop="realName">
                    <el-input v-model="editForm.realName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="tel">
                    <el-input v-model="editForm.tel" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="家庭住址" prop="address">
                    <el-input v-model="editForm.address" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="所属部门" prop="dept.name">
                    <el-select v-model="editForm.dept" value-key="id"   placeholder="请选择">
                        <el-option  v-for="dept in deptList"
                                    :key="dept.id"
                                    :label="dept.name"
                                    :value="dept">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="修改密码" prop="passwordNew">
                    <el-input v-model="editForm.passwordNew" auto-complete="off" placeholder="不修改则不输入密码"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
                <el-form-item label="员工账户" prop="username">
                    <el-input v-model="addForm.username" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名" prop="realName">
                    <el-input v-model="addForm.realName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="员工电话" prop="tel">
                    <el-input v-model="addForm.tel" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="家庭住址" prop="address">
                    <el-input v-model="addForm.address" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="所属部门" prop="dept.name">
                    <el-select v-model="addForm.dept" value-key="id"   placeholder="请选择">
                        <el-option  v-for="dept in deptList"
                                    :key="dept.id"
                                    :label="dept.name"
                                    :value="dept">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="员工密码" prop="password">
                    <el-input v-model="addForm.password" auto-complete="off" placeholder="不输入则密码为1"></el-input>
                </el-form-item>


            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    export default {
        data() {
            return {
                filters: {
                    keywords: ''
                },
                deptList:[],
                employees: [],
                total: 0,
                page: 1,
                listLoading: false,
                sels: [],//列表选中列

                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                editFormRules: {
                    username: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ]
                },
                //编辑界面数据
                editForm: {
                    id: 0,
                    username: '',
                    dept:'',
                    password:'',
                    passwordNew:''
                },

                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ]
                },
                //新增界面数据
                addForm: {
                    username: '',
                    realName: '',
                    tel: '',
                    password: '',
                    dept:'',
                    address:'',
                }

            }
        },
        methods: {
            timeformat:function(row) {//将后台传过来的valueOf格式的时间改成yyyy/m/d的格式
                const
                    date = new Date(row.inputTime),
                    y = date.getFullYear(),
                    m = date.getMonth() + 1,
                    d = date.getDate(),
                    h= date.getHours(),
                    m1=date.getMinutes()
                return `${y}/${m}/${d}/${h}:${m1}`
            },
            doGetDicts() {
                this.$http.patch("/department/findAll").then((res) => {
                    this.deptList= res.data;//把请求返回的列表，赋值给之前定义的
                })
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getEmployees();
            },
            //获取用户列表
            getEmployees() {
                let para = {
                    page: this.page,
                    keywords: this.filters.keywords
                };
                //加载属性
                this.listLoading = true;
                //NProgress.start();
                //getUserListPage(para).then((res) => {
                this.$http.patch('/employee',para).then((res) => {
                    this.employees = res.data.rows;
                    this.total = res.data.total;
                    this.listLoading = false;
                    //NProgress.done();
                });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { id: row.id };
                    //removeUser(para).then((res) => {
                    this.$http.delete('/employee/'+row.id).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getEmployees();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {

                this.editFormVisible = true;
                this.editForm = Object.assign({}, row);
                this.editForm.dept={
                    id:row.dept.id,
                    name:row.dept.name
                };
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                //清空表单数据
                this.addForm = {
                    username: '',
                    realName:'',
                    tel:'',
                    address:'',
                    password: '',
                    dept:'',

                };
            },
            //修改
            editSubmit: function () {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            //NProgress.start();
                            if(this.editForm.passwordNew){
                                this.editForm.password = this.editForm.passwordNew;
                            }
                            let para = Object.assign({}, this.editForm);
                            //editUser(para).then((res) => {
                            this.$http.post('/employee',para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getEmployees();
                                this.doGetDicts();
                            });
                        });
                    }
                });
            },
            //新增
            addSubmit: function () {
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.addLoading = true;
                            //NProgress.start();
                            //assign拷贝 把form表单数据拷贝到新增对象里面
                            let para = Object.assign({}, this.addForm);
                            //addUser(para).then((res) => {
                            this.$http.put('/employee',para).then((res) => {
                                this.addLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getEmployees();
                                this.doGetDicts();
                            });
                        });
                    }
                });
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                //var ids = this.sels.map(item => item.id).toString();
                var ids = [];
                this.sels.forEach(item=>
                    ids.push(item.id)
                )
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;

                    //NProgress.start();
                    /*let para = {'ids':ids};
                    console.log(para);*/
                    //batchRemoveUser(para).then((res) => {
                    this.$http.delete('/employee/batchRemove?ids='+ids).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getEmployees();
                    });
                }).catch(() => {

                });
            }
        },
        mounted() {
            this.doGetDicts();
            this.getEmployees();
        }
    }
</script>

<style scoped>

</style>