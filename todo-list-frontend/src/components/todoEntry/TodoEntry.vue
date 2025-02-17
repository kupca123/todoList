<template>
  <div class="d-flex align-items-center gap-3">
    <div>
      <input
          type="checkbox"
          class="form-check-input input-checkbox-custom"
          v-if="todoEntry.id != null"
          v-model="todoEntry.finished"
          @click="updateFinished">
    </div>
    <div>
      <input
          type="text"
          class="form-control input-textbox-custom"
          v-model="todoEntry.title"
          @change="updateTodoEntry(this.todoEntry)">
    </div>
    <div
        v-if="todoEntry.id != null"
        @click="deleteTodoEntry(this.todoEntry.id)">
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
      if (this.todoEntry.id == null) {
        this.todoEntry.title = '';
      }
    },

    deleteTodoEntry(todoEntryId) {
      this.$emit('delete-todo-entry', todoEntryId);
    }
  }

}

</script>

<style scoped>
.input-textbox-custom {
  background-color: var(--bs-light);
  border: none;
}

.input-checkbox-custom {
  border: var(--bs-dark) solid 1px;
  height: 20px;
  width: 20px;
}
/*
.input-checkbox-custom:checked {
  background-color: var(--bs-primary);
}
*/
</style>