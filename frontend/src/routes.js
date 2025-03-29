import { createRouter, createWebHistory } from "vue-router";
import HomeView from "./pages/HomeView.vue";
import AdminPanel from "./pages/admin/AdminPanel.vue";
import CrewManagement from "./pages/admin/CrewManagement.vue";


const routes = [
    {
        path: "/",
        name: "Home",
        component: HomeView
    },
    {
        path: "/admins-panel",
        name: "Admin Main Panel",
        component: AdminPanel
    },
    {
        path:"/admin-crew",
        name:"Admin Managing Crew",
        component: CrewManagement
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;