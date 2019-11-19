<template >
	<div>
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
				<!--<el-form-item>-->
					<!--<el-button type="primary" @click="handleAdd">新增</el-button>-->
				<!--</el-form-item>-->
			</el-form>
		</el-col>
		<!--列表-->
		<el-table :data="departments" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" >
			</el-table-column>
			<el-table-column type="index" >
			</el-table-column>
			<el-table-column prop="custormer" label="客户姓名"  sortable>
			</el-table-column>
			<el-table-column prop="repairOrder.address" label="客户地址"  sortable>
			</el-table-column>
			<el-table-column prop="carStatus" label="还车状态"  sortable>
				<template slot-scope="scope">
					<div v-if="scope.row.carStatus==true" style="color: green">已还</div>
					<div v-else style="color: red">未还</div>
				</template>
				<!--="scope.row.carStatus==false"-->
			</el-table-column>

			<el-table-column label="操作" width="200">
				<template scope="scope">
					<el-button size="small" @click="selectAdrressConfirm (scope.$index, scope.row)" style="background-color: #55b916">地图线路</el-button>
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<!--<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>-->
				</template>
			</el-table-column>

		</el-table>

    <!--工具条-->
		<el-col :span="24" class="toolbar">
			<!--<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>-->
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
			</el-pagination>
		</el-col>
		<el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false" style="width:auto" >
			<el-form :model="editForm" label-width="70px" :rules="editFormRules" ref="editForm">
				<el-form-item label="还车状态" prop="carStatus">
					<template>
						<el-select v-model="editForm.carStatus"  placeholder="请选择" style="width: 100%">
							<el-option
									v-for="item in carStatus"
									:key="item.value"
									:label="item.label"
									:value="item.value">
							</el-option>
						</el-select>
					</template>
				</el-form-item>
			</el-form>

			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

		<!--新增界面-->
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="部门名称" prop="name">
					<el-input v-model="addForm.name" auto-complete="off"></el-input>
				</el-form-item>


			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>
	</section>
		<el-dialog
				:visible.sync="mapDialogVisibale"
				width="60%" @click="selectAdrressConfirm">
			<baidu-map  :center=address1 :zoom="20" >
				<bm-view class="bmap"/>
				<bm-driving start="成都中宝汽车销售服务有限公司" :auto-viewport="true" :end="address1"
							:select-first-result="true"
							policy="BMAP_DRIVING_POLICY_LEAST_DISTANCE"
							:panel="false"
				>
				</bm-driving>
				<bm-map-type  :map-types="['BMAP_NORMAL_MAP', 'BMAP_HYBRID_MAP']" anchor="BMAP_ANCHOR_TOP_LEFT" >
				</bm-map-type>
				<bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true">
				</bm-geolocation>
			</baidu-map>
		</el-dialog>
	</div>
</template>

<script>
	import util from '../../common/js/util'
	//import NProgress from 'nprogress'
	import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser } from '../../api/api';

	export default {
		data() {
			return {
                mapDialogVisibale:false,
				filters: {
                    carStatus:'',
                    keywords: '',
				},
                address1:"",
                carStatus: [{
                    value: 'true',
                    label:'已还'

                }, {
                    value: 'false',
                    label: '未还'
                }],
                value: '',
				departments: [],
				total: 0,
				page: 1,
				listLoading: false,
				sels: [],//列表选中列

				editFormVisible: false,//编辑界面是否显示
				editLoading: false,
				editFormRules: {
					name: [
						{ required: true, message: '请输入姓名', trigger: 'blur' }
					]
				},
                dituForm: {
                    id: 0,
                    address: '',
                },
                dituFormVisible:false,
				//编辑界面数据
				editForm: {
					id: 0,
					name: '',
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
					name: '',
				}

			}
		},
		methods: {

			handleCurrentChange(val) {
				this.page = val;
				this.getDepartments();
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
                    this.total = res.data.total;
                    this.departments = res.data.rows;
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
            //显示地图
            selectAdrressConfirm: function (index, row) {
                this.dituFormVisible=true;
                //把对话框关闭
                this.mapDialogVisibale = true;
                this.editForm = Object.assign({}, row);
                this.address1=row.repairOrder.address;
            },




			//显示编辑界面
			handleEdit: function (index, row) {
				this.editFormVisible = true;
				this.editForm = Object.assign({}, row);
				this.editForm.carStatus?this.editForm.carStatus ="已还":this.editForm.carStatus ="未还";
			},
			//显示新增界面
			handleAdd: function () {
				this.addFormVisible = true;
				//清空表单数据
				this.addForm = {
					name: '',
				};
			},
			//修改
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							//NProgress.start();
							let para = Object.assign({}, this.editForm);
							//editUser(para).then((res) => {
                            this.$http.put('/setted',para).then((res) => {
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
				var ids = this.sels.map(item => item.id).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					//NProgress.start();
					let para = { ids: idsdelete };
					batchRemoveUser(para).then((res) => {
                    //this.$http.delete('/department',para).then((res) => {
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
			}
		},
		mounted() {
			this.getDepartments();
		}
	}

</script>

<style scoped>
	.bmap{
		width: 100%;
		height: 600px;
	}

</style>