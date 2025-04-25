import './assets/main.css';

import { createApp } from 'vue';
import adminapi from './api/adminapi';
import App from './App.vue';
import router from './routes/routes';


const app = createApp(App);

// login globally
async function login() {
    const res = await adminapi.login("jane@smith.com", "password");
    localStorage.setItem("token", res.data.token);
    localStorage.setItem("userID", res.data.userInfo.id);
    localStorage.setItem("crewlastName", res.data.userInfo.lastName);
    // console.log(res.data.userInfo);
}

login();

app.use(router)
app.mount("#app");

