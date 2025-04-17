import { createRouter, createWebHistory } from "vue-router";
import HomeView from "./pages/HomeView.vue";
import AdminPanel from "./pages/admin/AdminPanel.vue";
import CrewProfiles from "./pages/admin/admin-comp/CrewProfiles.vue";
import GameSchedule from "./pages/admin/admin-comp/GameSchedule.vue";
import Games from "./pages/admin/admin-comp/Games.vue";
import Invite from "./pages/admin/admin-comp/Invite.vue";
import CrewPanel from "./pages/crew/CrewPanel.vue";


const routes = [

    {
        path: "/",
        name: "Home",
        component: HomeView
    },
    
    // ADMIN LINKS
    {
        path: "/admins",
        name: "Admin Main Panel",
        component: AdminPanel,
        children:[
            {path: "schedules", name:"Schedules", component: GameSchedule, props: true},
            {path:"schedules/:id/games", name:"Schedule", component: Games, props: true},
            {path:"crew", name:"crews", component: CrewProfiles},
            {path:"invite", name:"invite", component: Invite},
            {path:"profile", name:"profile"}
        ]
    },


    // CREW LINKS
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