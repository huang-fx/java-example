<template>
  <button @click="requestTest">请求test</button>
  <div>
    <div>
      账号：<input v-model="username">
    </div>
    <div>
      密码：<input v-model="password">
    </div>
    <div>
      验证码：<input v-model="userCode">
    </div>
    <div>
      <button @click="getCode">获取验证码</button>
      <img :src="codeBase64">
    </div>
    <button @click="login">登录</button>
  </div>
</template>
<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'

const username = ref('')
const password = ref('')
const codeBase64 = ref('')
const userCode = ref('')
function login() {
  axios.post("http://localhost:3000/user/login", {
    username: username.value,
    password: password.value,
    code: userCode.value
  }).then(res => {
    localStorage.setItem("token", res.data.data.token)
  })
}

function getCode() {
  axios.get("http://localhost:3000/user/getCode").then((res) => {
    codeBase64.value = res.data
  })
}

function requestTest() {
  axios.defaults.headers.auth = localStorage.getItem("token")
  axios.get("http://localhost:3000/user/test")
}
</script>


<style scoped>

</style>
