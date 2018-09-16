import Vue from 'vue'
import Router from 'vue-router'
import Form from '@/components/Form'
import Main from '@/components/Main'

Vue.use(Router);

export default new Router({
  // by default the vue-router uses hashes when routing. When navigating to form in the browser, Vue will construct
  // the URL as #/form instead of /form. We can turn this
  // off by adding mode: 'history’ to the router.
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'iMain',
      component: Main,
      props: true
    },
    {
      path: '/form',
      name: 'Form',
      component: Form,
      props: true
    }

  ]
})
