<script>
import "@/assets/base.css";
import CustomButton from '@/components/CustomButton.vue';
import CrewProfile from './admin-comp/CrewProfiles.vue';
import GameSchedule from "./admin-comp/GameSchedule.vue";
import Games from './admin-comp/Games.vue';
import HeaderAdmin from './admin-comp/HeaderAdmin.vue';
import Invite from "./admin-comp/Invite.vue";


    export default{
        name:"AdminPanel",
        components:{
            HeaderAdmin,
            CustomButton,
            Games,
            CrewProfile,
            GameSchedule,
            Invite
        },
        data(){
            return {
                currentOnPanel: GameSchedule
            }
        }
    }
</script>

<template>
<HeaderAdmin />

<div class="container-admin">
    <div class="admin-functions">
        <CustomButton @custom-click="currentOnPanel = 'Games'" >Game</CustomButton>
        <CustomButton @custom-click="currentOnPanel = 'GameSchedule'"> Schedule</CustomButton>
        <CustomButton @custom-click="currentOnPanel = 'CrewProfile'" >Crew</CustomButton>
        <CustomButton @custom-click="currentOnPanel = 'Invite'"> Invite</CustomButton>
    </div>

    <div class="currentTab">
        <component :is="currentOnPanel" />
    </div>
</div>

</template>



<style scoped>
.container-admin{
    display: flex;
}

.admin-functions{
    width: 20vw;
    background: var(--main-background);
    display: flex;
    flex-direction: column;
    padding: 5px;
    border-right: solid 1px lightgray;
    height: calc(100vh - 10vh);
}

.admin-functions > *{
    background: var(--sub-button-color);
    color:black;
    border-bottom: 1px solid lightgray;
    margin-top:1rem;
}

.currentTab{
    background: white;
    height: calc(100vh - 10vh);
    width: calc(100vw - 20vw);
}


</style>