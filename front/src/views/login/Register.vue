<template>
    <div class="register-container">
        <el-card class="box-card">
            <h2 class="register-title">注册</h2>
            <el-form :model="form" ref="form" label-width="100px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username" placeholder="输入你的用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="form.password" placeholder="输入你的密码"></el-input>
                </el-form-item>
                <el-form-item label="确认你的密码" prop="confirmPassword">
                    <el-input type="password" v-model="form.confirmPassword" placeholder="确认你的密码"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleRegister">注册</el-button>
                    <el-button @click="goToLogin">返回登录</el-button>
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
                password: '',
                confirmPassword: ''
            }
        };
    },
    methods: {
        handleRegister() {
            if (this.form.username && this.form.password && this.form.password === this.form.confirmPassword) {
                // 注册逻辑
                this.$message.success('Registration successful');
                this.$router.push('/login');
            } else {
                this.$message.error('Please check your inputs');
            }
        },
        goToLogin() {
            this.$router.push('/login');
        }
    }
};
</script>

<style scoped>
.register-container {
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

.register-title {
    text-align: center;
    margin-bottom: 20px;
    color: #67c23a;
}
</style>