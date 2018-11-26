import router from '@/router/index'

router.beforeEach((to, from, next) => {
  // 登录标识
  let sessionKey = localStorage.getItem('sessionKey');
  if (sessionKey == null && to.path !== '/login') {
    next('/login')
  } else {
    if (to.path === '/login') {
      next();
    } else {
      next('/index');
    }
  }
});
