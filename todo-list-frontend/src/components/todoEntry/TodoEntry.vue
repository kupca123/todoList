<template>
  <div class="d-flex align-items-center gap-3">
    <div>
      <input
          type="checkbox"
          class="form-check-input input-checkbox-custom"
          v-model="todoEntry.finished"
          @click="updateFinished">
    </div>
    <div>
      <input
          type="text"
          class="form-control input-custom"
          v-model="todoEntry.title"
          @change="updateTodoEntry(this.todoEntry)">
    </div>
    <div @click="deleteTodoEntry(this.todoEntry.id)">
      <Trash2 />
    </div>
  </div>
</template>

<script>
import { Trash2 } from 'lucide-vue-next';

export default {
  name: 'TodoEntry',

  components: {
    Trash2
  },

  data() {
    return {
      todoEntry: this.todoEntryProp
    }
  },

  props: {
    todoEntryProp: {
      type: Object,
      required: true
    }
  },

  methods: {
    updateFinished() {
      this.todoEntry.finished = !this.todoEntry.finished;
      this.updateTodoEntry(this.todoEntry);
    },
    updateTodoEntry(todoEntry) {
      this.$emit('update-todo-entry', todoEntry);
    },
    deleteTodoEntry(todoEntryId) {
      this.$emit('delete-todo-entry', todoEntryId);
    }
  }

}

</script>

<style scoped>
.input-custom {
  background-color: var(--bs-primary);
  color: var(--bs-light);
  border: none;
}

.input-checkbox-custom:checked {
  background-color: var(--bs-success);
}

</style>