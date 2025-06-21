import { mapGetters } from 'vuex';

export default {
    data() {
    return {

    }
  },
  computed: {
    ...mapGetters([
      'sysconf'
    ])
  },
  mounted() {
    // console.log(this.sysconf.language);
  },
}
