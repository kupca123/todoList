<template>
  <div class="controls-custom">
    Komponenta pro řádek ovládání
  </div>
  <div class="todo-list-custom" v-for="todoEntry in todoEntries" :key="todoEntry.id">
    <div class="todo-entry-custom">
      <TodoEntry :todo-entry-prop="todoEntry" @update-todo-entry="updateTodoEntry"/>
    </div>
  </div>
  <div class="error-custom" ref="chyby">
    Komponenta pro výpis chyb
  </div>
</template>

<script>
import TodoEntry from "@/components/todoEntry/TodoEntry.vue";
import { ApiGet } from "../commons/Api.js";
import { ApiPut } from "../commons/Api.js";

export default {
  name: 'TheMainTodoList',

  components: {
    TodoEntry
  },

  data() {
    return {
      todoEntries: []
    }
  },

  mounted() {
    ApiGet('/api/v1/todoEntry')
        .then(data => {
          this.todoEntries = data;
        })
  },

  methods: {
    updateTodoEntry(todoEntry) {
      ApiPut('/api/v1/todoEntry/update', todoEntry)
          .then(data => {
            console.log(data);
          })
    }
  }
}

</script>


<style scoped>

.controls-custom {
  position: fixed;
  height: 60px;
  width: 100%;
  display: flex;
  flex-direction: column-reverse;
  align-items: flex-start;
  padding: 0 0.7rem;
}

.controls-custom div {
  padding-right: 2rem;
  display: flex;
  align-items: center;
}

.todo-list-custom {
  margin-top: 80px;
  margin-bottom: 1rem;
}

.todo-entry-custom {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  background-color: var(--bs-primary);
  color: var(--bs-light);
  margin-top: 0.2rem;
  padding: 0.5rem 0.7rem;
  border-radius: 0.5rem;
}


.error-custom {
  position: absolute;
  top: 10px;
  left: 230px;
  width: calc(100vw - 240px);
  background-color: var(--bs-danger);
  z-index: 50;
  padding: 0.5rem;
  border-radius: 0.5rem;
  opacity: 1;
}

</style>