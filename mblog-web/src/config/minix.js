import { mapGetters } from 'vuex';
import request from '../ustils/request';


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
