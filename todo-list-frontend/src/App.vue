<template>
<div class="d-flex flex-column justify-content-center align-items-center">
  <div class="w-100">
    <TheHeaderApp />
  </div>
  <div>
    <TheMainTodoList />
  </div>
</div>
</template>

<script>
import TheHeaderApp from "@/components/TheHeaderApp.vue";
import TheMainTodoList from "@/components/todoEntry/TheMainTodoList.vue";

export default {
  name: 'App',
  components: {
    TheHeaderApp,
    TheMainTodoList
  }
}
</script>

<style>
#app {
  position: absolute;
  top: 0;
  right: 0;
  left: 0;
  padding: 0 1.5rem;
}
</style>
