<template>
    <div class="login-container">
      <el-card class="box-card">
        <h2 class="login-title">Login</h2>
        <el-form :model="form" :rules="rules" ref="form" label-width="100px">
          <el-form-item label="Username" prop="username">
            <el-input v-model="form.username" placeholder="Enter your username"></el-input>
          </el-form-item>
          <el-form-item label="Password" prop="password">
            <el-input type="password" v-model="form.password" placeholder="Enter your password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleLogin">Login</el-button>
            <el-button @click="goToRegister">Register</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        form: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            { required: true, message: 'Please enter your username', trigger: 'blur' }
          ],
          password: [
            { required: true, message: 'Please enter your password', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      handleLogin() {
        this.$refs.form.validate(valid => {
          if (valid) {
            // 模拟登录成功
            const token = 'dummy-token';
            localStorage.setItem('token', token);
            this.$router.push('/dashboard');
            this.$message.success('Login successful');
          } else {
            this.$message.error('Please check the form');
          }
        });
      },
      goToRegister() {
        this.$router.push('/register');
      }
    }
  };
  </script>
  
  <style scoped>
  .login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f5f5f5;
  }
  
  .box-card {
    width: 400px;
    padding: 20px;
  }
  
  .login-title {
    text-align: center;
    margin-bottom: 20px;
    color: #409eff;
  }
  </style>
  