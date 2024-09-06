<script setup lang="ts">
import { RouterLink } from 'vue-router';
</script>

<template>
    <div class="common-layout">
        <!-- 路由链接 -->
        <!-- 字符串链接 -->
        <router-link to="/home">Home</router-link>
        <!-- 对象跳转 -->
        <router-link :to="{ path: '/home' }">Home</router-link>
        <!-- 名称跳转 -->
        <router-link :to="{ name: 'home' }">Home</router-link>
        <router-link to="/notfound">NotFound</router-link>
        <router-link to="/emissionSourceSketch">排放源概览</router-link>
    </div>

</template>
