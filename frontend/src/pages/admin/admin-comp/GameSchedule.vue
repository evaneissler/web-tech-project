<template>
<div class="schedule-container">
    <!-- ********************************** -->
    <!-- Sub headd -->
    <!-- ********************************** -->
    <div class="subhead-schedule">
        <h1>Schedule Tab</h1>
        <ButtonC @custom-event="HideForm = !HideForm">{{ HideForm ? "Add" : "Close" }}</ButtonC>
    </div>



    <!-- ********************************** -->
    <!-- Adding a new schedule -->
    <!-- ********************************** -->
    
    <div class="add-schedule" :class="{'isActive':HideForm}">
        <h1>New Schedule</h1>
        
        <form @submit.prevent="submitNewSchedule">
            <input required v-model="sportName" type="text" placeholder="Sport">
            <input required v-model="seasonName" type="text" placeholder="Season">
            <div class="btns">
                <ButtonC>Save</ButtonC>
            </div>
        </form>
    </div>

    <!-- ********************************** -->
    <!-- Showing error message -->
    <!-- ********************************** -->

    <div v-if="postResponse != ''">
        <h1>{{ postResponse }}</h1>
    </div>


    <!-- ********************************** -->
    <!-- Showing available schedules -->
    <!-- ********************************** -->
    
    <div class="schedules-list">
        <div class="game-schedule" v-for="(item) in schedules" v-bind:key="item.id">

            <!-- <span>{{item.id}}</span>
            <span>{{item.sport}}</span>
            <span>{{item.season}}</span> -->

            <router-link class="schedule-detail" :to="{ name: 'Schedule', params: { id: item.id }}">
                <span>{{item.id}}</span>
                <span>{{item.name }}</span>
                <span> {{ item.games.length }} Game(s) </span>
            </router-link>
        </div>

    </div>
</div>
</template>

<script setup>
import adminapi from '@/api/adminapi';
import ButtonC from '@/components/ButtonC.vue';
import { ref } from 'vue';
// true or false? show or hide
let HideForm = ref(true);

// ****************************************
// submitting new schedule
// ****************************************

const sportName = ref("");
const seasonName = ref("");
let postResponse = ref("");

const submitNewSchedule = async () =>{
    postResponse.value = "";
    // sports and seasons body
    const newSchedule = {
        sport: sportName.value,
        season: seasonName.value,
    }

    try{
        postResponse.value = adminapi.addGameSchedule(newSchedule) || "Schedule added successfully"

        schedules.value.push({
            name: newSchedule.sport,
            games: [],
        });

        // clear the form
        sportName.value = "";
        seasonName.value = "";
        HideForm.value = true;
    }catch(err){
            postResponse.value = err;
    }
}




// ****************************************
// Fetching all schedules and showing them
// ****************************************
let schedules = ref([]);
const loading = ref(true);

const loadAllSchedules = async () =>{
    try{
        schedules.value = await adminapi.gameSchedule();
        console.log(schedules.value);
    }catch(err){
        console.log(err);
    } finally {
        loading.value = false;
    }
}

loadAllSchedules();
</script>








<style  scoped>

    .schedules-list{
        background: rgb(245, 245, 245);
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top:2rem;
    }

    .game-schedule{
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
        width: 50%;
    }

    .game-schedule:hover{
        background: rgb(230, 230, 230);
    }

    .isActive{
        display: none;
    }

    .add-schedule{
        flex-direction: column;
        border: 1px solid lightgray;
        width: fit-content;
        text-align: center;
        padding: 10px;
    }

    .add-schedule > form {
        display: flex;
        flex-direction: column;
        text-align: center;
    }

    .add-schedule > form > *{
        padding: 5px;
        margin: 5px;
        border-radius: 4px;
    }

    .schedule-container{
        /* background: lightgray */
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .subhead-schedule{
        display: flex;
        width: 100%;
        justify-content: space-around;
        background: rgb(240, 240, 240);
        align-items: center;
    }

    .schedule-detail{
        text-decoration: none;
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
</style>