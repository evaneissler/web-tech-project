import './assets/main.css';

import { createApp } from 'vue';
import adminapi from './api/adminapi';
import App from './App.vue';
import router from './routes';


const app = createApp(App);

// login globally
async function login() {
    const res = await adminapi.login("jane@smith.com", "password");
    console.log(res);
}

login();

app.use(router)
app.mount("#app");

