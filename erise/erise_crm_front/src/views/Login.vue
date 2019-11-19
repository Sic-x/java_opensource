<template>
    <section>
        <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px"
                 class="demo-ruleForm login-container">
            <h3 class="title">系统登录</h3>
            <el-form-item prop="username">
                <el-input type="text" v-model="ruleForm2.username" auto-complete="off" placeholder="账号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="ruleForm2.password" auto-complete="off" placeholder="密码"></el-input>
            </el-form-item>
            <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
            <el-form-item style="width:100%;" align="right">
                <el-button type="success" @click.native.prevent="weixinSubmit" :loading="wxlogining">微信登录</el-button>
                <el-button type="primary" style="width:30%;" @click.native.prevent="handleSubmit2" :loading="logining">登录</el-button>
                <el-button style="width:30%;" @click.native.prevent="toRegister">注册</el-button>
            </el-form-item>
        </el-form>

        <el-dialog title="注册" :visible.sync="registerFormVisible" :close-on-click-modal="false">
            <el-form :model="registerForm" :rules="rules" ref="registerForm" label-width="80px">
                <el-form-item label="公司名称" prop="ename">
                    <el-input type="text" v-model="registerForm.realName" auto-complete="off"
                              placeholder="公司名称"></el-input>
                </el-form-item>
                <el-form-item label="公司地址" prop="address">
                    <el-input type="text" v-model="registerForm.address" auto-complete="off"
                              placeholder="公司地址"></el-input>
                </el-form-item>
                <el-form-item label="公司logo" prop="logo">
                    <el-upload
                            v-model="registerForm.headimg" auto-complete="off"
                            placeholder="公司logo"
                            class="upload-demo"
                            action="http://localhost/file/upload"
                            :on-success="handleSuccess"
                            list-type="picture">
                        <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>
                </el-form-item>
                <el-form-item label="公司账号" prop="name">
                    <el-input type="text" v-model="registerForm.username" auto-complete="off" placeholder="公司账号"
                              v-on:blur="checkUserName()"
                    ></el-input>
                </el-form-item>
                <el-form-item label="公司电话" prop="phonenum">
                    <el-input type="text" v-model="registerForm.tel" auto-complete="off"
                              placeholder="公司电话"
                              v-on:blur="checkPhone()"
                    ></el-input>
                </el-form-item>
                <el-form-item label="电子邮件" prop="email">
                    <el-input type="text" v-model="registerForm.email" auto-complete="off"
                              placeholder="电子邮件"
                              v-on:blur="checkEmail()"
                    ></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="registerForm.password" auto-complete="off"
                              placeholder="密码"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPassword">
                    <el-input type="password" v-model="registerForm.checkPassword" auto-complete="off"
                              placeholder="确认密码"></el-input>
                </el-form-item>
                <el-form-item style="width:100%;" align="right">
                    <el-button @click.native="registerFormVisible = false">去登录</el-button>
                    <el-button type="primary" @click.native.prevent="handleSubmit" :loading="registering"
                               :disabled="isdisabledFn"
                    >入驻
                    </el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </section>

</template>

<script>
    import {requestLogin} from '../api/api';
    //import NProgress from 'nprogress'
    export default {
        data() {

            var validatePass3 = (rule, value, callback) => {
                if (value.trim() === '') {
                    callback(new Error('密码不能为空'));
                } else if (value !== this.registerForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                isdisabledFn: false,
                employee:'',
                logining: false,
                wxlogining: false,
                registerFormVisible: false,
                registering: false,
                registerForm: {},
                rules: {
                    username: [
                        {required: true, message: '请输入公司账号', trigger: 'blur'},
                    ],
                    tel: [
                        {required: true, message: '请输入公司号码', trigger: 'blur'},

                    ],
                    checkPassword:[
                        {required: true, message: '请再次输入密码', trigger: 'blur'},
                        {validator: validatePass3,required: true,trigger: 'blur'}
                    ],
                    password:[
                        {required: true, message: '请输入密码', trigger: 'blur'},

                    ]
                },
                ruleForm2: {

                },
                rules2: {
                    username: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        //{ validator: validaePass }
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        //{ validator: validaePass2 }
                    ]
                },
                checked: true
            };

        },
        methods: {
            checkUserName() {
                this.formCheckName();
            },
            checkPhone() {
                this.formCheckPhone();
            },
            checkEmail() {
                this.formCheckEmail();
            },


            formCheckName() {
                let para = this.registerForm.username;
                this.$http.put("/employee/checkName",para).then((res)=>{
                    let success =res.data.success;
                    if(success!=true){
                        this.$message({
                            message: res.data.message,
                            type: 'error'
                        });
                        this.isdisabledFn=true;
                    }else {
                        this.isdisabledFn=false;
                        var regName =/^(?![0-9]*$)(?![a-zA-Z]*$)[a-zA-Z0-9]{6,20}$/;
                        if (this.registerForm.username != '' && !regName.test(this.registerForm.username)) {
                            this.$message({
                                message: '账号必须为6-20位字母和数字组合',
                                type: 'error'
                            })
                            this.isdisabledFn = true;
                        } else {
                            this.isdisabledFn = false;
                        }
                    }
                })
            },
            formCheckPhone() {
                var TEL_REGEXP = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
                if (this.registerForm.tel != '' && !TEL_REGEXP.test(this.registerForm.tel)) {
                    this.$message({
                        message: '请输入正确的电话号码',
                        type: 'error'
                    })
                    this.isdisabledFn = true;
                } else {
                    this.isdisabledFn = false;
                }
            },
            formCheckEmail() {
                var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                if (this.registerForm.email != '' && !regEmail.test(this.registerForm.email)) {
                    this.$message({
                        message: '邮箱格式不正确',
                        type: 'error'
                    })
                    this.isdisabledFn = true;
                } else {
                    this.isdisabledFn = false;
                }

            },




            handleSuccess(response,file,fileList){
                this.registerForm.headimg = response;
            },
            handleSubmit() {
                this.$refs.registerForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认入驻吗？', '提示', {}).then(() => {
                            this.registering = true;
                            //NProgress.start();
                            //assign拷贝 把form表单数据拷贝到新增对象里面
                            let para = Object.assign({}, this.registerForm);
                            this.$http.post("/register", para).then(res => {
                                this.$message({
                                    message: '注册成功',
                                    type: 'success'
                                });
                                this.registerFormVisible = false;
                            });
                        });
                    }
                });
            },
            toRegister() {
                this.registerFormVisible = true;
                this.registerForm={};
            },
            handleSubmit2(ev) {
                var _this = this;
                this.$refs.ruleForm2.validate((valid) => {
                    if (valid) {
                        this.logining = true;
                        var loginParams = {username: this.ruleForm2.username, password: this.ruleForm2.password};
                        //requestLogin(loginParams).then(data => {
                        this.$http.post("/login", loginParams).then(data => {
                            this.logining = false;
                            //解析表达式  resultObj[user:...,token:xxx]
                            let {message, success, resultObj} = data.data;
                            if (!success) {
                                this.$message({
                                    message: message,
                                    type: 'error'
                                });
                            } else {
                                //把传递回来的信息存入前台 自带的 session
                                console.log(resultObj.user)
                                sessionStorage.setItem('user', JSON.stringify(resultObj.user));
                                sessionStorage.setItem("token", resultObj.token);
                                this.$router.push({path: '/echarts'});
                            }
                        });
                    } else {
                        return false;
                    }
                });
            },
            weixinSubmit(ev) {
                        this.$http.get("/login").then(data => {
                           window.location.href=data.data.resultObj.url;
                        });

            }
        }
    }

</script>

<style lang="scss" scoped>
    .login-container {
        /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
        -webkit-border-radius: 5px;
        border-radius: 5px;
        -moz-border-radius: 5px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        .title {
            margin: 0px auto 40px auto;
            text-align: center;
            color: #505458;
        }
        .remember {
            margin: 0px 0px 35px 0px;
        }
    }
</style>