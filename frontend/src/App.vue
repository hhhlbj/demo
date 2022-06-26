<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>
<script>
import LoginView from "./views/LoginView.vue";
import RegisterView from "@/views/RegisterView";
export default {
  name: "app",
  components: {
    RegisterView,
    LoginView,
  },
};
</script>
<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
