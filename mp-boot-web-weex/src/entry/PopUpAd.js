
import mixins from '../mixins/index'
//import * as filters from '../filter/index'
import PopUpAd from '../components/PopUpAd.vue'

//sync(store, router)

//Object.keys(filters).forEach(key => {
//  Vue.filter(key, filters[key])
//})

// register global mixins.
Vue.mixin(mixins)

/* eslint-disable no-new */
new Vue(Vue.util.extend({el: '#root'}, PopUpAd))
