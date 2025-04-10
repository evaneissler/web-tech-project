import './assets/main.css';

import { createApp } from 'vue';
import App from './App.vue';
import router from './routes';


const app = createApp(App);

// global variables
app.config.globalProperties.$base_url = "http://localhost:8080/api/v1"

app.use(router)
app.mount("#app");

