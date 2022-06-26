<template>
  <div class="login_container">
    <div class="login_box">
      <h2>用户登录</h2>

      <el-form
        label-width="0px"
        class="login_in"
        ref="loginForm"
        :model="loginForm"
        :rules="rules"
      >
        <el-form-item prop="userName">
          <el-input
            type="text"
            prefix-icon="el-icon-user"
            v-model="loginForm.userName"
          ></el-input>
        </el-form-item>
        <el-form-item prop="passWord">
          <el-input
            type="password"
            prefix-icon="el-icon-lock"
            v-model="loginForm.passWord"
          ></el-input>
        </el-form-item>
        <el-form-item class="btns">
          <el-button type="primary" @click="submitForm()">登录</el-button>
          <el-button>注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        userName: "",
        passWord: "",
      },
      rules: {
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 3, max: 8, message: "长度在 3 到 8 个字符", trigger: "blur" },
        ],
        passWord: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 6,
            max: 16,
            message: "长度在 6 到 16 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    submitForm() {
      axios({
        url: "http://localhost:8181/user/login",
        method: "post",
        BASE_URL: "/api",
        headers: {
          "Content-Type": "application/json",
        },
        params: {
          userName: this.loginForm.userName,
          password: this.loginForm.passWord,
        },
      }).then((res) => {
        console.log(res.data);
      });
    },
  },
};
</script>
<style scoped lang="less">
.login_container {
  background-color: #42b983;
  height: 100%;
}
.login_box {
  width: 450px;
  height: 280px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.btns {
  display: flex;
  justify-content: flex-end;
}

.login_in {
  position: absolute;
  bottom: 10px;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}
</style>