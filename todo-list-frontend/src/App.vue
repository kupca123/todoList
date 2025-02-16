<template>
<div>
  <TheHeaderApp />
  <TheMainTodoList />
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
