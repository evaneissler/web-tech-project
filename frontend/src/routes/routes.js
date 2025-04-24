import { createRouter, createWebHistory } from "vue-router";

import HomeView from "@/pages/HomeView.vue";

import AdminPanel from "@/pages/admin/AdminPanel.vue";
import AdminWelcome from "@/pages/admin/admin-comp/AdminWelcome.vue";
import Profile from "@/pages/admin/admin-comp/Profile.vue";
import CrewAvailable from "@/pages/crew/crew-comp/CrewAvailable.vue";
import CrewGameSchedule from "@/pages/crew/crew-comp/CrewGameSchedule.vue";
import CrewProfile from "@/pages/crew/crew-comp/CrewProfile.vue";
import WelcomeCrew from "@/pages/crew/crew-comp/WelcomeCrew.vue";
import CrewProfiles from "../pages/admin/admin-comp/CrewProfiles.vue";
import GameSchedule from "../pages/admin/admin-comp/GameSchedule.vue";
import Games from "../pages/admin/admin-comp/Games.vue";
import Invite from "../pages/admin/admin-comp/Invite.vue";
import CrewPanel from "../pages/crew/CrewPanel.vue";


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
            {path: "", name: "Admin Welcome Page", component: AdminWelcome},
            {path: "schedules", name:"Schedules", component: GameSchedule, props: true},
            {path:"schedules/:id/games", name:"Schedule-admin", component: Games, props: true},
            {path:"crew", name:"crews", component: CrewProfiles},
            {path:"crew/:id", name:"Profile-admins", component: Profile, props: true},
            {path:"invite", name:"invite", component: Invite},
            {path:"profile", name:"profile"}
        ]
    },


    // CREW LINKS
    {
        path:"/crew",
        name:"Crew Main Panel",
        component: CrewPanel,
        children:[
            {path:"", name:"WelcomeCrew", component: WelcomeCrew},
            {path: "schedule" ,name:"Schedule", component: CrewGameSchedule, props: true},
            {path:"games", name:"Games", component: CrewGameSchedule, props: true},
            {path:"availability", name:"Availability", component: CrewAvailable, props: true},
            {path:"profile", name:"Profile", component: CrewProfile, props: true}
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;