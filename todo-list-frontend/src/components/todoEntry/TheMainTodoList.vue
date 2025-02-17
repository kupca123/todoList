<template>
  <div v-if="this.sent && !this.success">
    <div class="alert alert-danger alert-dismissible fade show mt-2" role="alert">
      {{ this.errorMsg }}
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
  </div>
  <div class="controls-custom">
    Komponenta pro řádek ovládání
  </div>
  <div class="todo-list-custom" v-for="todoEntry in todoEntries" :key="todoEntry.id">
    <div class="todo-entry-custom">
      <TodoEntry
          :todo-entry-prop="todoEntry"
          @update-todo-entry="updateTodoEntry(todoEntry)"
          @delete-todo-entry="deleteTodoEntry(todoEntry.id)"/>
    </div>
<!--    <div class="todo-entry-custom">-->
<!--      <TodoEntry-->
<!--          :todo-entry-prop="newTodoEntry"-->
<!--          @update-todo-entry="createTodoEntry(newTodoEntry)"/>-->
<!--    </div>-->
  </div>

</template>

<script>
import TodoEntry from "@/components/todoEntry/TodoEntry.vue";
import {ApiGet, ApiPost, ApiPut, ApiDelete} from "../commons/Api.js";

export default {
  name: 'TheMainTodoList',

  components: {
    TodoEntry
  },

  data() {
    return {
      todoEntries: [],
      sent: false,
      success: false,
      errorMsg: ''
    }
  },

  mounted() {
    ApiGet('/api/v1/todoEntry')
        .then(data => {
          this.todoEntries = data;
        })
        .catch((error) => {
          console.log(error);
          this.sent = true;
          this.success = false;
          this.errorMsg = 'Chyba při načítání dat.';
        })
  },

  methods: {
    createTodoEntry(todoEntry) {
      ApiPost('/api/v1/todoEntry/create', todoEntry)
          .then(async () => {
            try {
              return await ApiGet('/api/v1/todoEntry');
            } catch (error) {
              this.setError(error, 'Chyba při načítání úkolů.');
            }
          })
          .then(data => {
            this.todoEntries = data;
          })
          .catch((error) => {
            this.setError(error, 'Chyba při vytváření úkolu.');
          })
    },

    updateTodoEntry(todoEntry) {
      ApiPut('/api/v1/todoEntry/update', todoEntry)
          .then(data => {
            console.log('data: ', data)
            this.todoEntries = data;
          })
          .catch((error) => {
            this.setError(error, 'Chyba při ukládání úkolu.');
          })
    },

    deleteTodoEntry(todoEntryId) {
      ApiDelete('/api/v1/todoEntry/delete/' + todoEntryId)
          .then(async () => {
            try {
              return await ApiGet('/api/v1/todoEntry');
            } catch (error) {
              this.setError(error, 'Chyba při načítání úkolů.');
            }
          })
          .then(data => {
            this.todoEntries = data;
          })
          .catch((error) => {
            this.setError(error, 'Chyba při mazání úkolů.');
          })
    },

    setError(error, msgError) {
      console.log(error);
      this.sent = true;
      this.success = false;
      this.errorMsg = msgError;
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
</style>