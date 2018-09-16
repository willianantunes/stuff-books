import Vue from 'vue'
import App from './App'
import router from './router'

// Because we are using Webpack we’ll need to use the require keyword with the relative path to the asset.
require('./assets/app.css');

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: {App}
});
