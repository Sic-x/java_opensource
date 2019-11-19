<template>

    <section>

        <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px"
                 class="demo-ruleForm login-container">
            <h3 class="title">账户绑定</h3>
            <el-form-item prop="username">
                <el-input type="text" v-model="ruleForm2.username" auto-complete="off" placeholder="绑定账号"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password" v-model="ruleForm2.password" auto-complete="off" placeholder="绑定密码"></el-input>
            </el-form-item>
            <el-form-item style="width:100%;" align="right">
                <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit" :loading="logining">绑定</el-button>
            </el-form-item>
        </el-form>

    </section>


</template>

<script>
    export default {
        data(){
            return{
                logining: false,
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

            }

        },
        methods:{

            handleSubmit(){
                var open_id = window.location.href.split("=")[1]
                this.logining = true;
                var loginParams = {username: this.ruleForm2.username, password: this.ruleForm2.password,open_id:open_id};
                this.$http.post("/login", loginParams).then(data => {
                    console.log(data)
                    let {message, success, resultObj} = data.data;
                    if (!success) {
                        this.$message({
                            message: message,
                            type: 'error'
                        });
                    } else {
                        //把传递回来的信息存入前台 自带的 session
                        sessionStorage.setItem('user', JSON.stringify(resultObj.user));
                        sessionStorage.setItem("token", resultObj.token);
                        this.$router.push({path: '/echarts'});
                    }
                });
            }

        },


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