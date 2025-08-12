import { ref, onMounted } from 'vue'

// 深色/浅色模式切换
let darkModeState = false;
export const darkMode = () => darkModeState;
export const value5 = ref(false)
export const toggleDark = () => {
  
  darkModeState = !darkModeState;
  if (darkModeState) {
    document.documentElement.classList.add('dark-mode');
    value5.value = true
  } else {
    document.documentElement.classList.remove('dark-mode');
    value5.value = false
  }
};
