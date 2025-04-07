import { createRouter, createWebHistory } from "vue-router";
import HomeView from "./pages/HomeView.vue";
import AdminPanel from "./pages/admin/AdminPanel.vue";
import CrewPanel from "./pages/crew/CrewPanel.vue";


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
        path:"/crew-panel",
        name:"Crew Main Panel",
        component: CrewPanel
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;