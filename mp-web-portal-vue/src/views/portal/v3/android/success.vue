<template>
  <div>
    <!-- {{ $route.fullPath }} success -->
    sucess page
    <button @click="incrementTest">加{{count}}_{{count1}}</button>

    <h5>{{doneTodosCount}}</h5>

    <h5>1111</h5>
    <h5>{{getTodos}}</h5>

    <h5>{{fruit}}</h5>

    <h5>11{{fruitTodos}}</h5>

    <h5>add{{getTodos}}</h5>


    <h5>deviceMac:{{configPortalInfo}}</h5>
    <h5>deviceMac11:{{baseDeviceMac}}</h5>


  </div>

</template>

<script>

  import store from '@/store/index.js'

  import {mapState,mapGetters} from 'vuex'

  export default {
    name: 'androidSuccess',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },
    mounted(){
      this.incrementTest();
    },

    methods:{
      incrementTest(){
        //let count =this.$store.state.count;
        store.commit('SET_TEST_COUNT');
      }
    },

    computed: {
      ...mapState({
          count: 'numb',
          count1: 'count'
      }),
      ...mapGetters([
          'fruit',
          'fruitTodos',
          'baseDeviceMac',
          'configPortalInfo'
      ]),

      doneTodosCount1 () {
        return this.$store.state.todos.filter(todo => todo.done).length
      },

      doneTodosCount2 () {
        return store.getters.countAnother
      },

      //通过方法访问
      getTodos:function(){
        return this.$store.state.todos;
      }
    }

  }
</script>

<style type="text/css">
</style>

