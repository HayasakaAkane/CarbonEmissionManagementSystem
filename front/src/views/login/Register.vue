<template>
    <div class="register-container">
        <el-card class="box-card">
            <h2 class="register-title">注册</h2>
            <el-form :model="form" :rules="rules" ref="form" label-width="100px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username" placeholder="输入你的用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="form.password" placeholder="输入你的密码"></el-input>
                </el-form-item>
                <el-form-item label="确认你的密码" prop="confirmPassword">
                    <el-input type="password" v-model="form.confirmPassword" placeholder="确认你的密码"></el-input>

                </el-form-item>

                <el-form-item label="选择你的公司" prop="company">
                    <el-select v-model="companyId" :rules="rules.companyId" placeholder="请选择">
                        <el-option v-for="item in companies" :key="item.companyId" :label="item.companyName"
                            :formCompanyId="item.companyId">
                        </el-option>
                    </el-select>

                </el-form-item>

                <el-form-item>
                    <el-row>
                        <el-col :span="12">
                            <div class="grid-content bg-purple">
                                <el-button type="primary" @click="handleRegister">注册</el-button>
                            </div>
                        </el-col>
                        <el-col :span="12">
                            <div class="grid-content bg-purple-light">
                                <el-button @click="goToLogin">返回登录</el-button>
                            </div>
                        </el-col>
                    </el-row>
                </el-form-item>


            </el-form>

        </el-card>

    </div>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        const validatePassword = (rule, confirmPassword, callback) => {
            if (confirmPassword === '') {
                callback(new Error('请输入密码'));
            } else {
                if (confirmPassword != this.form.password) {
                    callback(new Error('两次输入的密码不一致'))
                }
                callback();
            }
        }
        return {
            form: {
                username: '',
                password: '',
                confirmPassword: ''
            },
            rules: {
                username: [
                    { required: true, message: '请确认你的用户名', trigger: 'blur' },
                    { pattern: /^1[3-9]\d{9}$/, message: '用户名格式不对', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入你的密码', trigger: 'blur' },
                    { min: 6, max: 16, message: '密码长度在6-16之间' },
                ],
                confirmPassword: [
                    { validator: validatePassword, trigger: blur }
                ],
                companyId: [
                    { required: true, message: '请选择你的公司', trigger: 'blur' }
                ]
            },
            // companies: [],
            companies: [
                {
                    companyId: 'company1',
                    companyName: '百度'
                },
                {
                    companyId: 'company1',
                    companyName: '百度'
                },
                {
                    companyId: 'company1',
                    companyName: '百度'
                },
                {
                    companyId: 'company1',
                    companyName: '百度'
                },
                {
                    companyId: 'company1',
                    companyName: '百度'
                },

            ],
            selectCompanyId: ''
        };
    },
    mounted() {
        this.getCompanis()
    },
    methods: {
        getCompanis() {

            let api = "http://localhost:8080/"
            api += 'user/register'
            // 向给定ID的用户发起请求
            axios.get(api)
                .then(function (response) {
                    this.companies = response.data
                })
                .catch(function (error) {
                    // 处理错误情况
                    console.log(error);
                })
                .finally(function () {
                    // 总是会执行
                });

        },
        handleRegister() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    // 模拟注册成功

                    let api = "http://localhost:8080/"
                    api += 'user/register'

                    console.log(this.companyId);
                    axios.post(api, { username: this.form.username, password: this.form.password, company: this.companyId })
                        .then((res) => {
                            // console.log(res)
                            if (res.code == "200") {
                                this.$router.push('/login');
                                this.$message.success('注册成功');
                            }
                            else {
                                this.$message.error(res.msg)
                            }
                        }).catch((err) => {
                            console.log(err)
                        })
                } else {
                    this.$message.error('请检查你的输入');
                }
            });
        },
        goToLogin() {
            this.$router.push('/login');
        },
        getCompany() {
            let api = "http://localhost:5173/login"
            //2.使用axios 进行get请求
            // let url = '';
            axios.get(api, this.form)
                .then((res) => {
                    if (res.code == 0) {
                        this
                    }
                    else {
                        this.$message.error(res.msg)
                    }
                }).catch((err) => {
                    //请求失败的回调函数
                    console.log(err)
                })

        }
    }
}


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