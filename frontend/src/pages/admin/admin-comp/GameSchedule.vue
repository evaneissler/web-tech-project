<template>
    <div>
        <div class="add-game">
            <div class="title">
                <h1 class="all-game-title">Games Schedules</h1>
                <CustomButton @custom-click="scheduleActive = !scheduleActive;

">Add Schedule</CustomButton>
                <hr>
            </div>
            <!-- adding game form as a sub header will go here -->
            <!-- <div class="add-schedule">
                    <form class="add-form" @submit.prevent="handlerForm">
                        <input  v-model="sportsName" type="text" placeholder="Sport type">
                        <input  v-model="seasonRange" type="text" placeholder="Season">
                        <CustomButton>Add Schedule</CustomButton>
                    </form>
            </div> -->


            <div class="action-buttons">
            </div>
        </div>
        <hr>

        <!--                    adding                      -->
        <!-- ********************************************** -->
        <div class="pop-container">
            <div class="pop-schedule" :class="{'isInActive': scheduleActive}">
                <form class="pop-schedule-form" @submit.prevent="gameScheduleHandler">
                    <h1 class="form-title">New Schedule Form</h1>
                    <hr>
                    <input v-model="sportsName" type="text" placeholder="Sport Name">
                    <input v-model="seasonRange" type="text" placeholder="Season Range">

                    <CustomButton>Save</CustomButton>
                </form>
            </div>


            <div class="pop-game">
                <form class="pop-game-form" :class="{'isInActive': gameActive}" @submit.prevent="gameAddHandler">
                    <h1 class="form-title">New Game Form</h1>
                    <hr>
                    <input v-model="gameDate" type="date" id="game-date" placeholder="">
                    <input v-model="gameVenue" type="text" placeholder="Venue">
                    <input v-model="gameOpponent" type="opponent" placeholder="Opponent">

                    <label for="isFinalized">Set Up Complete?</label>
                    <select v-model="gameIsFinalized" name="isFinalized" id="">
                        <option value="true" selected>True</option>
                        <option value="false">False</option>
                    </select>

                    <label for="schedule-id">Schedule</label>
                    <select v-model="gameScheduleId" name="schedule-id" id="schedule-id">
                        <option value="3001" selected>3001</option>
                        <option value="3001">3001</option>
                    </select>
                    <CustomButton>Save</CustomButton>
                </form>
            </div>
        </div>
        <!--                    above                      -->
        <!-- ********************************************** -->


        <div class="item-div" v-for="(item, index) in availableGames" :key="index" @click="getGameID(1)"><!-- change back to item.gameId -->
            <!-- {{ item }} -->
        <!--
            <span>{{ item['id'] }}</span>
            <span>{{ item['sport'] }}</span>
            <span>{{ item['season'] }}</span>
        -->

            <span> 3001 </span>
            <span> FootBall </span>
            <span> 2024-25 </span>
            <span>
                <CustomButton>🔧</CustomButton>
            </span>
        </div>
    </div>
</template>

<script setup>
import CustomButton from '@/components/CustomButton.vue';
import { computed, ref } from 'vue';


// all refs
// ********
let scheduleActive = ref(true);
let gameActive = ref(true);

let availableGames = ref("");


// fetching available games
const available_gameSchedule_url = "http://localhost:8080/api/v1/gameSchedule"

fetch(available_gameSchedule_url)
    .then(resp => resp.json())
    .then(data => {
        availableGames.value = data.data;
    })
    .catch(error => console.error("error: " + error));

// selecting a schedule for details
// ********************************
let gameId_from_div = ref(0);

function getGameID(selectedID){
    gameId_from_div.value = selectedID;
    
    const item_url = computed(()=>`${available_gameSchedule_url}/${gameId_from_div.value}`);
    console.log(item_url.value);
}


//  getting admin input to add new schedule
// ****************************************
const sportsName = ref("");
const seasonRange = ref("");

const gameScheduleHandler = () =>{
    const newSchedule = {
        sports_name: sportsName.value,
        season_name: seasonRange.value
    }


    fetch(available_gameSchedule_url, {
        method: "POST",
        headers:{
            "Content-Type":"application/json",
        },
        body:JSON.stringify(newSchedule),
    })
    .then((res) => res.json())
    .then((data) =>{
        console.log(data.data);
    })
    .catch((error) =>{
        console.error("There was an error: ", error);
    })
}

// admin adding a new game
// ***********************
const gameDate = ref("");
const gameVenue = ref("");
const gameOpponent = ref("");
const gameIsFinalized = ref(null);
const gameScheduleId = ref(0);



const gameAddHandler = () =>{
    const newGame = {
        date : gameDate.value,
        venue : gameVenue.value,
        opponent : gameOpponent.value,
        isFinalized: gameIsFinalized.value,
        scheduleID:gameScheduleId.value
    }

    // *******************************
    // Remind back end to add this url.
    // *******************************
    const addNewGame_url = `http://localhost:8080/api/v1/${scheduleID}/games`
    fetch(addNewGame_url, {
        method: "POST",
        headers:{
            "Content-Type":"application/json",
        },
        body:JSON.stringify(newGame),
    })
    .then((res) => res.json())
    .then((data) =>{
        console.log(data.data);
    })
    .catch((error) =>{
        console.error("There was an error: ", error);
    })
}


</script>
<!-- ********************** style -->

<style scoped>

    .form-title{
        font-size: 1.2rem;
        text-align: center;
        font-weight: bold;
    }

    .title{
        display: flex;
        justify-content: space-around;
        align-items: center;
    }

    .title > *{
        height: fit-content;
    }

    .item-div{
        background: rgb(238, 238, 238);
        display: flex;
        justify-content: center;
    }

    .item-div:hover{
        background: white;
        cursor: pointer;
    }

    .item-div > span{
        margin: 1.5rem;

    }

    .all-game-title{
        font-size: 2rem;
        font-weight: bolder;
        margin: 1rem;
    }


    .action-buttons{
        display: flex;
        justify-content: space-around;
        /* background: red; */
        /* flex-direction: column; */
        padding: 10px;
    }

    /* ***************** GAME POP */
    .pop-container{
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .pop-game-form{
        display: flex;
        flex-direction: column;
        width: 35vw;
        border:solid 1px lightgray;
        justify-content: center;
        border-radius: 10px;
    }


    .pop-game-form > *{
        padding: 10px;
    }

    /* ************************** Schedule */
    .pop-schedule-form{
        display: flex;
        flex-direction: column;
        width: 35vw;
        border: solid 1px lightgray;
        margin: 10px;
        border-radius: 10px;
    }

    .pop-schedule-form > * {
        padding: 10px;
    }

    .isInActive{
        display: none;
    }

</style>