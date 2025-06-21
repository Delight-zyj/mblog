import { createStore } from 'vuex';
import sysconf from '../config/config';

export default createStore({
  state: {
    sysconf: { ...sysconf },
  },
  getters: {
    sysconf: (state) => state.sysconf,
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  },
})
