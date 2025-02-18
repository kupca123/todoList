<template>
  <div v-if="this.sent && !this.success">
    <div class="alert alert-danger alert-dismissible fade show mt-2" role="alert">
      {{ this.errorMsg }}
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
  </div>
  <div class="todo-list-custom" ref="listRef">
    <div class="todo-entry-custom">
      <TodoEntry
          :todo-entry-prop="newTodoEntry"
          @update-todo-entry="createTodoEntry(newTodoEntry)"/>
    </div>
    <div
        class="todo-entry-custom"
        v-for="todoEntry in todoEntries"
        :key="todoEntry.id">
      <TodoEntry
          :todo-entry-prop="todoEntry"
          @update-todo-entry="updateTodoEntry(todoEntry)"
          @delete-todo-entry="deleteTodoEntry(todoEntry.id)"/>
    </div>
  </div>
</template>

<script>
import {ref} from "vue";
import {useInfiniteScroll} from '@vueuse/core'
import TodoEntry from "@/components/todoEntry/TodoEntry.vue";
import {ApiGet, ApiPost, ApiPut, ApiDelete} from "../commons/Api.js";

export default {
  name: 'TheMainTodoList',

  components: {
    TodoEntry
  },

  setup() {

    const listRef = ref(null);
    let todoEntries = ref([]);
    let page = ref(1);
    let pageSize = ref(11);
    let totalCount = ref(1);

    let sent = ref(false);
    let success = ref(false);
    let errorMsg = ref('');


    const getMoreData = async () => {
      try {
        if(totalCount.value <= todoEntries.value.length) {
          return;
        }
        const newData
            = await ApiGet('/api/v1/todoEntry?page=' + page.value + '&pageSize=' + pageSize.value)
        totalCount.value = newData.pagination.totalCount;

        todoEntries.value.push(...newData.data);
        page.value += 1;

      } catch(error) {
        console.log(error);
        sent.value = true;
        success.value = false;
        errorMsg.value = 'Chyba při načítání dat.';
      }

    }

    useInfiniteScroll(
        listRef,
        async () => {
          await getMoreData()
        },
        {threshold: 10});

    return {
      listRef,
      todoEntries,
      page,
      pageSize,
      sent,
      success,
      errorMsg
    }

  },

  data() {
    return {
      newTodoEntry: {},
    }
  },

  methods: {
    createTodoEntry(todoEntry) {
      ApiPost('/api/v1/todoEntry/create', todoEntry)
          .then(async () => {
            await this.getTodoEntries()
          })
          .catch((error) => {
            this.setError(error, 'Chyba při vytváření úkolu.');
          })
    },

    updateTodoEntry(todoEntry) {
      ApiPut('/api/v1/todoEntry/update', todoEntry)
          .then(async () => {
            await this.getTodoEntries()
          })
          .catch((error) => {
            this.setError(error, 'Chyba při ukládání úkolu.');
          })
    },


    deleteTodoEntry(todoEntryId) {
      ApiDelete('/api/v1/todoEntry/delete/' + todoEntryId)
          .then(async () => {
            await this.getTodoEntries()
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
    },

    async getTodoEntries() {
      try {
        const responseData
            = await ApiGet('/api/v1/todoEntry?page=1&pageSize=' + this.pageSize);
        this.todoEntries = responseData.data;
        this.page = 2;
        this.totalCount = responseData.pagination.totalCount;
      } catch (error) {
        this.setError(error, 'Chyba při načítání úkolů.');
      }
    }

  }
}

</script>


<style scoped>

.todo-list-custom {
  background-color: var(--bs-secondary);
  width: 600px;
  height: 750px;
  padding: 12px 20px;
  overflow-y: scroll;
  margin-top: 40px;
  margin-bottom: 1rem;
  border-radius: 0.5rem;
}

.todo-entry-custom {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-top: 0.2rem;
  padding: 0.5rem 0.7rem;
  border: var(--bs-light) 3px solid;
  border-radius: 0.5rem;
}
</style>