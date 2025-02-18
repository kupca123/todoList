<template>
  <div class="w-100 d-flex align-items-center gap-3">
    <div style="min-width: 20px;">
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
          :class="['form-control', 'input-textbox-common', {'input-textbox-custom': todoEntry.id != null}, {'todo-finished-custom': todoEntry.finished}]"
          v-model="todoEntry.title"
          @change="updateTodoEntry(this.todoEntry)"
          placeholder="Nový úkol">
    </div>
    <div
        class="ms-auto"
        v-if="todoEntry.id != null"
        @click="deleteTodoEntry(this.todoEntry.id)">
      <span class="text-light btn"><Trash2 /></span>

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

.input-textbox-common {
  width: 400px;
}

.input-textbox-custom {
  background-color: var(--bs-secondary);
  color: var(--bs-light);
  border: none;
}


.input-checkbox-custom {
  border: var(--bs-dark) solid 1px;
  height: 20px;
  width: 20px;
}

.input-checkbox-custom:checked {
  border: var(--bs-dark) solid 1px;
  background-color: var(--bs-dark);
}

.btn:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

.todo-finished-custom {
  text-decoration: line-through;
  pointer-events: none;
}

</style>