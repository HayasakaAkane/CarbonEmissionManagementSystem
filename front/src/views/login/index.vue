<template>
    <div class="login-container">
        <el-card class="box-card">
            <h2 class="login-title">登录</h2>
            <el-form :model="form" :rules="rules" ref="form" label-width="100px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username" placeholder="输入你的用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="form.password" placeholder="输入你的密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleLogin">登录</el-button>
                    <el-button @click="goToRegister">注册</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>

import axios from 'axios'
export default {
    data() {
        const validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
            }
        };
        return {
            form: {
                username: '',
                password: ''
            },
            rules: {
                username: [
                    { required: true, message: '请确认你的用户名', trigger: 'blur' },
                    // { pattern: /^1[3-9]\d{9}$/, message: '用户名格式不对', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入你的密码', trigger: 'blur' },
                    { min: 6, max: 16, message: '密码长度在6-16之间' },
                ]
            }
        };
    },
    methods: {
        handleLogin() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    let api = "http://localhost:5173/login"
                    //2.使用axios 进行get请求
                    // let url = '';
                    axios.post(api, this.form)
                        .then((res) => {
                            // console.log(res)
                            if (res.code == 0) {
                                // console.log(this.form);
                                const token = '123';
                                localStorage.setItem('token', token);
                                this.$message.success('登录成功');
                            }
                            else {
                                this.$message.error(res.msg)
                            }
                        }).catch((err) => {
                            //请求失败的回调函数
                            console.log(err)
                        })

                } else {
                    this.$message.error('请检查你的输入');
                }
            });
        },
        goToRegister() {
            this.$router.push('/register');
        },
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