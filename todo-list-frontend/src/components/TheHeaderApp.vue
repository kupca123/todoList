<template>
  <div class="header-custom ">
    <div class="ms-2 me-2">
      <ListChecks size="36"/>
    </div>
    <div>
      <h1 class="m-0">ToDo List</h1>
    </div>
  </div>
</template>

<script>
import { ListChecks } from 'lucide-vue-next';

export default {
  name: 'TheHeaderApp',
  components: {
    ListChecks
  }
}

</script>

<style scoped>

.header-custom {
  height: 60px;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid var(--bs-dark);
}

</style>