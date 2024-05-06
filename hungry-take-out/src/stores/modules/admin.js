import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useAdminStore = defineStore(
  'adminStore',
  () => {
    const token = ref('')
    const setToken = (newToken) => {
      token.value = newToken
    }
    const removeToken = () => {
      token.value = ''
    }

    //计数器
    const count = ref(0)
    return {
      token,
      setToken,
      removeToken,
      count
    }
  },
  {
    persist: true
  }
)
