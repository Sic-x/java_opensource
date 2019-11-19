<template >
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.keywords" placeholder="客户姓名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getDepartments">查询</el-button>
                </el-form-item>
<!--                <el-form-item>-->
<!--                    <el-button type="primary" @click="handleAdd">新增</el-button>-->
<!--                </el-form-item>-->
            </el-form>
        </el-col>

        <!--列表-->
        <el-table :data="setteds" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
            <el-table-column type="selection" >
            </el-table-column>
            <el-table-column type="index" >
            </el-table-column>
            <el-table-column prop="custormer" label="客户姓名"  sortable>
            </el-table-column>
            <el-table-column prop="repairOrder.carnum" label="车牌号"  sortable>
            </el-table-column>
            <el-table-column :formatter="timeformat" prop="settedtime" label="结算时间"  sortable>
            </el-table-column>
            <el-table-column prop="repairOrder.status" label="状态" sortable >
                <template slot-scope="scope">
<!--                    {{scope.row.repairOrder.status}}-->
                    <span  v-if="!scope.row.repairOrder.status" style="color: green">
                        已结算
                    </span>
                    <span  v-if="scope.row.repairOrder.status" style="color: red">
                        未结算
                    </span>
                </template>
            </el-table-column>
            <el-table-column prop="partsname" label="配件名称"  sortable>
            </el-table-column>
            <el-table-column prop="num" label="配件数量"  sortable>
            </el-table-column>
            <el-table-column prop="optPrivce" label="配件价格"  sortable>
            </el-table-column>
            <el-table-column prop="re_amount" label="应付金额"  sortable>
            </el-table-column>
            <el-table-column prop="pay_amount" label="实付金额"  sortable>
            </el-table-column>
            <el-table-column prop="repairOrder.address" label="地址"  sortable>
            </el-table-column>
            <el-table-column prop="pay.paytypename" label="支付方式"  sortable>
            </el-table-column>
            <el-table-column label="操作" width="220">
                <template scope="scope">
                    <el-button type="success" size="small" @click="jiesuan(scope.$index,scope.row)">结算</el-button>
                    <el-button type="primary" size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
            <el-button type="success" @click="batchjiesuan" :disabled="this.sels.length===0">批量结算</el-button>
            <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :total="total" style="float:right;">
            </el-pagination>
        </el-col>

        <!--编辑界面//:rules="editFormRules""-->
      <el-dialog  title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
            <el-form :rules="companyRules" :model="editForm" label-width="80px"  ref="editForm">
                <el-form-item label="客户姓名" prop="custormer">
                    <el-input v-model="editForm.custormer" auto-complete="off" ></el-input>
                </el-form-item>
                 <el-form-item label="客户地址" prop="address" >
                     <el-input v-model="editForm.address" auto-complete="off"></el-input>
                 </el-form-item>
                 <el-form-item label="实付金额" prop="pay_amount" >
                     <el-input v-model="editForm.pay_amount" auto-complete="off"></el-input>
                 </el-form-item>
                 <el-form-item label="支付方式" prop="pay.paytypename">
                     <el-select v-model="editForm.pay" value-key="id" placeholder="请选择">
                         <el-option  v-for="pay in payList"
                                     :key="pay.id"
                                     :label="pay.paytypename"
                                     :value="pay">
                         </el-option>
                     </el-select>
                 </el-form-item>
<!--                 <el-form-item label="配件名称" prop="parts">-->
<!--                     <el-select v-model="editForm.partsname" value-key="id" placeholder="请选择" >-->
<!--                         <el-option  v-for="parts in partsList"-->
<!--                                     :key="parts.id"-->
<!--                                     :label="parts.partsname"-->
<!--                                     :value="parts.partsname">-->
<!--                         </el-option>-->
<!--                     </el-select>-->
<!--                 </el-form-item>-->
            </el-form>
          <div slot="footer" class="dialog-footer">
              <el-button type="info" @click.native="editFormVisible = false">取消</el-button>
              <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
          </div>
        </el-dialog>
      <el-dialog  title="结算单:" style="font-family: 方正粗黑宋简体" :visible.sync="jiesuanFormVisible" :close-on-click-modal="false" customClass="customWidth">
            <el-form :model="jiesuanForm" label-width="80px"  ref="jiesuanForm">
                <el-form-item style="font-family:方正粗黑宋简体;font-size: 150px" label="客户姓名:" prop="custormer">
                    {{jiesuanForm.custormer}}
                </el-form-item>
                <el-form-item label="维修配件:" style="font-family: 方正粗黑宋简体" prop="partsname">
                    {{jiesuanForm.partsname}}：{{jiesuanForm.num}} * {{jiesuanForm.optPrivce}} = {{jiesuanForm.re_amount}}
                </el-form-item>
                <el-form-item style="font-family: 方正粗黑宋简体" label="总计:">
                    <label slot="label" style="font-size: 15px">总&emsp;&emsp;计: </label>
                    {{jiesuanForm.re_amount}}
                </el-form-item>
                <el-form-item  style="font-family: 方正粗黑宋简体" label="折扣金额:">
                    0
                </el-form-item>
                <el-form-item label="实付金额:" style="font-family: 方正粗黑宋简体">
                    {{jiesuanForm.re_amount}}
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="info" @click.native="jiesuanFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="jiesuanSubmit" :loading="jiesuanLoading">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>
<style>
    .customWidth {
        width: 20%;
    }
    .bianjiClass{
        width: 20%;
    }
</style>
<script>
    import util from '../../common/js/util'
    //import NProgress from 'nprogress'
    import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser } from '../../api/api';

    export default {
        data() {
            var validateName = (rule, value, callback) => {
                var re = /[，\s_'’‘\"”“|\\~#$@%^&*;\/<>\?？]/;   //正则表达式
                if (value == "") {
                    callback(new Error("名称不能为空!"));
                } else if (re.test(value)) {
                    callback(new Error("名称不能含有特殊字符，请重新输入!"));
                } else {
                    callback();
                }
            };
            var jine = (rule,value,callback) =>{
                var re = /[，\s_'’‘\"”“|\\~#$@%^&*;\/<>\?？]/;
                var re1 = /(?!^0*(\.0{1,2})?$)^\d{1,8}(\.\d{1,2})?$/;
                if (value == "") {
                    callback(new Error("金额不能为空!"));
                }else if(value<0){
                    callback(new Error("金额不能为负数"));
                }else if (/[\u4E00-\u9FA5]/g.test(value)) {
                    callback(new Error('金额不能输入汉字!'));
                }else if (!re1.test(value)) {
                    callback(new Error("请输入正确金额！"));
                } else if (re.test(value)) {
                    callback(new Error("金额不能含有特殊字符，请重新输入!"));
                } else {
                    callback();
                }
            }
            return {
                companyRules:{
                    custormer: [
                        { required: true, message: "请输入客户名称", trigger: "blur" },
                        { max: 10, message: "允许输入最大字符数为10个", trigger: "blur" },
                        { validator: validateName, trigger: "blur" }
                    ],
                    address:[
                        { required: true, message: "请输入客户地址", trigger: "blur" },
                            { max: 32, message: "允许输入最大字符数为32个", trigger: "blur" },
                            { validator: validateName, trigger: "blur" }
                        ],
                    pay_amount:[
                        { required: true, message: "请输入金额", trigger: "blur" },
                        // { max: 8, message: "允许输入最大金额为99999999", trigger: "blur" },
                        { validator: jine, trigger: "blur" }
                    ],
                },

                input:{
                    partsname:''
                },
                filters: {
                    name: '',
                },
                setteds: [],
                total: 0,
                page: 1,
                listLoading: false,
                sels: [],//列表选中列
                payList:[],
                partsList:[],
                jiesuanForm:{
                },
                jiesuanFormVisible:false,
                jiesuanFormVisible2:false,
                editFormVisible: false,//编辑界面是否显示
                editLoading: false,
                //编辑界面数据
                editForm: {
                    id: 0,
                    name: '',
                    pay:'',
                },
                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                //新增界面数据
                addForm: {
                    name: '',
                }
            }
        },
        methods: {
            timeformat:function(row) {//将后台传过来的valueOf格式的时间改成yyyy/m/d的格式
                const
                    date = new Date(row.settedtime),
                    y = date.getFullYear(),
                    m = date.getMonth() + 1,
                    d = date.getDate(),
                    h= date.getHours(),
                    m1=date.getMinutes()
                return `${y}/${m}/${d}/${h}:${m1}`
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getDepartments();
            },
            //支付列表
            doGetPay(){
                this.$http.patch("/pay/findAll").then((res) => {
                    this.payList= res.data;//把请求返回的列表，赋值给之前定义的
                })
            },
            doGetparts(){
                this.$http.patch("/parts/findAll").then((res) => {
                    this.partsList= res.data;//把请求返回的列表，赋值给之前定义的
                })
            },
            //获取用户列表
            getDepartments() {
                let para = {
                    page: this.page,
                    keywords: this.filters.keywords
                };
                //加载属性
                this.listLoading = true;
                //NProgress.start();
                //getUserListPage(para).then((res) => {
                this.$http.patch('/setted',para).then((res) => {
                    this.setteds = res.data.rows;
                    this.total = res.data.total;
                    this.listLoading = false;
                    //NProgress.done();
                });
            },
            /*删除单表的*/
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = { id: row.id };
                    //removeUser(para).then((res) => {
                    this.$http.delete('/setted/'+row.id).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getDepartments();
                    });
                }).catch(() => {
                });
            },
            //显示jiesuan界面
            jiesuan: function(index, row){
                if (!row.repairOrder.status){
                    this.$confirm("请不要重复提交！","提示",{
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"});
                    this.jiesuanFormVisible=false;
                }else if (row.pay.paytypename=="未支付"){
                    this.$confirm("您还没有选择支付方式","提示",{
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"});
                    this.jiesuanFormVisible=false;
                }else if (row.pay.paytypename!="未支付"){
                    this.jiesuanFormVisible=true;
                    this.jiesuanForm = Object.assign({},row);
                }
            },
            //显示bianji界面
            handleEdit: function (index, row) {
                this.editFormVisible = true;
                row.address = row.repairOrder.address;
                this.editForm = Object.assign({},row);
                console.log(row);
                // this.editForm.repairOrder.address = row.repairOrder.address;
                // var a = row.repairOrder.address;
                // this.editForm.address = "asd";
                this.editForm.pay_amount = row.re_amount;
                this.editForm.pay = {
                    id:row.pay.id,
                    paytypename: row.pay.paytypename
                };
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                //清空表单数据
                this.addForm = {
                    name: '',
                };
            },
            jiesuanSubmit: function(){
                this.$refs.jiesuanForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.jiesuanLoading = true;
                            let para = Object.assign({}, this.jiesuanForm);
                            console.log(this.jiesuanForm);
                            //editUser(para).then((res) => {
                            this.$http.post('/setted/updatestatus',para).then((res) => {
                                this.jiesuanLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['jiesuanForm'].resetFields();
                                this.jiesuanFormVisible = false;
                                this.getDepartments();
                            });
                        });
                    }
                })
            },
            //修改
            editSubmit: function (row) {
                this.$refs.editForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.editLoading = true;
                            this.editForm.repairOrder.address = this.editForm.address;
                            let para = Object.assign({}, this.editForm);
                            console.log(this.editForm);
                            //editUser(para).then((res) => {
                            this.$http.post('/setted/update',para).then((res) => {
                                this.editLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['editForm'].resetFields();
                                this.editFormVisible = false;
                                this.getDepartments();
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
                            this.$http.put('/setted',para).then((res) => {
                                this.addLoading = false;
                                //NProgress.done();
                                this.$message({
                                    message: '提交成功',
                                    type: 'success'
                                });
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getDepartments();
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
                this.sels.forEach(item => ids.push(item.id))
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    // let para = { ids: idsdelete };
                    // batchRemoveUser(para).then((res) => {
                    this.$http.delete('/setted/batchRemove?ids='+ids).then((res) => {
                        //this.$http.delete('/setted',para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getDepartments();
                    });
                }).catch(() => {
                });
            },
            //批量结算
            batchjiesuan: function (index, row) {
                //var ids = this.sels.map(item => item.id).toString();
                var ids = [];
                this.sels.forEach(item => ids.push(item.repairOrder.id))
                console.log(ids);
                this.$confirm('确认提交选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //this.jiesuanFormVisible = true;
                    //NProgress.start();
                    // let para = { ids: idsdelete };
                    // batchRemoveUser(para).then((res) => {
                    this.$http.post('/setted/plupdatestatus?ids='+ids).then((res) => {
                        //this.$http.delete('/setted',para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '批量结算成功',
                            type: 'success'
                        });
                        this.getDepartments();
                    });
                }).catch(() => {
                });
            }
        },
        mounted() {
            this.getDepartments();
            this.doGetPay();
            this.doGetparts();
        }
    }
</script>
<style scoped>
</style>