<script>
import "@/assets/base.css";
import CustomButton from '@/components/CustomButton.vue';
import CrewAvailable from "./crew-comp/CrewAvailable.vue";
import CrewProfileView from "./crew-comp/CrewProfileView.vue";
import GameSchedule from "./crew-comp/GameSchedule.vue";
import HeaderCrew from "./crew-comp/HeaderCrew.vue";
import ViewGameSchedule from "./crew-comp/ViewGameSchedule.vue";


    export default{
        name:"AdminPanel",
        components:{
            HeaderCrew,
            CustomButton,
            ViewGameSchedule,
            CrewProfileView,
            GameSchedule,
            CrewAvailable
        },
        data(){
            return {
                currentOnPanel: ViewGameSchedule
            }
        }
    }
</script>

<template>
<HeaderCrew />

<div class="container-admin">
    <div class="admin-functions">
        <CustomButton @custom-click="currentOnPanel = 'ViewGameSchedule'" >Schedule</CustomButton>
        <CustomButton @custom-click="currentOnPanel = 'CrewAvailable'"> Availability</CustomButton>
        <CustomButton @custom-click="currentOnPanel = 'GameSchedule'"> Game</CustomButton>
        <CustomButton @custom-click="currentOnPanel = 'CrewProfileView'" > Crew</CustomButton>
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