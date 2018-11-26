/*global Vue*/

/* weex initialized here, please do not move this line */
import mixins from '@/mixins/index';
//import * as filters from '@/filter/index';

const App = require('@/index.vue');
const router = require('./router');

//Object.keys(filters).forEach(key => {
//  Vue.filter(key, filters[key])
//})

// register global mixins.
Vue.mixin(mixins);

/* eslint-disable no-new */
new Vue(Vue.util.extend({el: '#root', router}, App));
router.push('/');

