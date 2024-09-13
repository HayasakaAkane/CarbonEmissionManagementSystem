<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">
        <Logo />
        <div style="display: flex;align-items: center;">
          <p>退出登录</p>
          <!-- 放用户头像的地方 -->
          <p ><img style="width: 30px;height:30px;margin: 0 20px;" src="../assets/logo.svg" alt=""></p>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <div
            class="menu"
            element-loading-text="Loading..."
            element-loading-svg-view-box="-10, -10, 50, 50"
            element-loading-background="rgba(122, 122, 122, 0.01)"
          >
            
            <el-scrollbar>
              <el-menu
                :default-active="activeMenu"
                :router="true"
                :collapse-transition="false"
                :unique-opened="true"
                background-color="#191a20"
                text-color="#bdbdc0"
                active-text-color="#fff"
              >
              <template v-for="subItem in menuList" :key="subItem.path">
                <el-sub-menu v-if="subItem.children && subItem.children.length > 0" :index="subItem.path">
                  <template #title>
                    <!-- <el-icon>
                      <component :is="subItem.icon"></component>
                    </el-icon> -->
                    <span v-show="!subItem.meta.hidden">{{ subItem.meta.title }}</span>
                  </template>
                  <SubItem :menuList="subItem.children" />
                </el-sub-menu>
                <el-menu-item v-else :index="subItem.path">
                  <!-- <el-icon>
                    <component :is="subItem.icon"></component>
                  </el-icon> -->
                  <template v-if="!subItem.isLink" #title>
                    <span v-show="!subItem.meta.hidden">{{ subItem.meta.title }}</span>
                  </template>
                  <template v-else #title>
                    <a class="menu-href" :href="subItem.isLink" target="_blank">{{ subItem.title }}</a>
                  </template>
                </el-menu-item>
              </template>
              </el-menu>
            </el-scrollbar>
          </div>
        </el-aside>
        <el-main>
          <router-view v-slot="{ Component, route }">
						<transition appear name="fade-transform" mode="out-in">
								<component :is="Component" :key="route.path"></component>
						</transition>
					</router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import { ref, computed } from "vue";
import Logo from "./components/Logo.vue";
import SubItem from "./components/SubItem.vue";
import { useRouter, useRoute } from 'vue-router';
const route = useRoute();
const router = useRouter();
const activeMenu = computed((): string => route.path);
const menuList = ref([]) as any
menuList.value = router.options.routes

</script>
<style scoped lang="scss">
@import "./index.scss";
</style>