import Vue from 'vue'
import App from './App'
import router from './router'
import {store} from './store/store';
// Because we are using Webpack we’ll need to use the require keyword with the relative path to the asset.
require('./assets/app.css');

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
});
