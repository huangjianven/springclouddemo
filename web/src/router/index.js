import Vue from 'vue'
import Router from 'vue-router'
import Bind from '@/components/Bind'
import Index from '@/components/Index'
import iView from 'iview';
import 'iview/dist/styles/iview.css';

Vue.use(Router)
Vue.use(iView);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/bind',
      name: 'bind',
      component: Bind
    }
  ]
})
