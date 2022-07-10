<template>
    <div class="reg_container">
        <div class="reg_box">
            <h2>用户注册</h2>

            <el-form
                    label-width="0px"
                    class="reg_in"
                    ref="regForm"
                    :model="regForm"
                    :rules="rules"
            >
                <el-form-item prop="userName">
                    <el-input
                            type="text"
                            prefix-icon="el-icon-user"
                            v-model="regForm.userName"
                            @keyup.enter.native="submitRegForm()"
                    ></el-input>
                </el-form-item>
                <el-form-item prop="passWord">
                    <el-input
                            type="password"
                            prefix-icon="el-icon-lock"
                            v-model="regForm.passWord"
                            @keyup.enter.native="submitRegForm()"
                    ></el-input>
                </el-form-item>
                <el-form-item prop="confirmPassword">
                    <el-input
                            type="password"
                            prefix-icon="el-icon-lock"
                            v-model="regForm.confirmPassword"
                            @keyup.enter.native="submitRegForm()"
                    ></el-input>
                </el-form-item>
                <el-form-item class="btns">
                    <el-button type="primary" @click="submitRegForm()">注册</el-button>
                    <el-link href="/login" target="_blank" :underline="false">已有账号，快去登录</el-link>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "RegisterView",
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.regForm.passWord) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            regForm: {
                userName: "",
                passWord: "",
                confirmPassword: ""
            },
            rules: {
                userName: [
                    {required: true, message: "请输入用户名", trigger: "blur"},
                    {min: 3, max: 11, message: "长度在 3 到 11 个字符", trigger: "blur"},
                ],
                passWord: [
                    {required: true, message: "请输入密码", trigger: "blur"},
                    {
                        min: 6,
                        max: 16,
                        message: "长度在 6 到 16 个字符",
                        trigger: "blur",
                    },
                ],
                confirmPassword: [
                    {required: true, validator: validatePass, trigger: "blur",}
                ],
            },
        }
    },
    methods: {
        submitRegForm() {
            axios({
                url: "http://localhost:8181/user/register",
                method: "post",
                BASE_URL: "/api",
                headers: {
                    "Content-Type": "application/json",
                },
                params: {
                    userName: this.regForm.userName,
                    password: this.regForm.passWord,
                    conPassword: this.regForm.confirmPassword,
                },
            }).then((res) => {
                if (res.data.code === 200) {
                    this.$message.success(res.data.message);
                    setTimeout(() => {
                        //需要延迟的代码 :3秒后延迟跳转到首页，可以加提示什么的
                        this.$router.push({
                            path: "/login"
                        });
                        //延迟时间：3秒
                    }, 3000)

                } else {
                    this.$message.error(res.data.message);
                }
            });
        }
    }
}
</script>

<style scoped lang="less">
    .reg_container {
        background-color: #42b983;
        height: 100%;
    }

    .reg_box {
        width: 450px;
        height: 350px;
        background-color: #fff;
        border-radius: 3px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
    }

    .reg_in {
        position: absolute;
        bottom: 10px;
        width: 100%;
        padding: 0 20px;
        box-sizing: border-box;
    }
</style>