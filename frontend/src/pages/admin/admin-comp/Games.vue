<template>
    <!-- ********************************** -->
    <!-- Sub headd -->
    <!-- ********************************** -->
    <div class="games-container">
        <div class="sub-head">
            <h1>{{ scheduleTitle ? scheduleTitle : "Could not find schedule" }}</h1>
            <ButtonC @custom-event="HideForm = !HideForm"> {{ HideForm ? "Add" : "Close" }}</ButtonC>
        </div>



    <!-- ********************************** -->
    <!-- Add new game form-->

    <div class="add-game" :class="{'active':HideForm}">
        <h1>Add new game</h1>
        <form @submit.prevent="submitNewGame">
            <input required v-model="gamedate" type="date">
            <input required v-model="gamevenue" type="text" placeholder="Game Venue">
            <input required v-model="gameopponent" type="text" placeholder="Opponent">

            <label for="isFinalized">Is the game finalized?</label>
            <select required name="" id="">
                <option value="true">True</option>
                <option value="false">False</option>
            </select>

            <ButtonC>Save</ButtonC>
        </form>
    </div>

    <!-- ********************************** -->
    <!-- Showing available games -->
    <!-- ********************************** -->
    <div class="game-list">
        <div class="game-item" v-for="game in availableGames" v-bind:key="game.id">


            <!-- ********************************** -->
            <!-- ******** SHOW GAME DETAILS ******** -->
             <!-- ******* Uncomment when the backend is ready ******** -->
            <!-- ********************************** -->

            <!-- <router-link class="game-detail" :to="{ name: 'Game', params: { id: game.gameId }}">
                <span>{{game.gameId}}</span>
                <span>{{game.gameDate}}</span>
                <span>{{game.venue}}</span>
                <span>{{game.opponent}}</span>
                <span>{{game.isFinalized}}</span>
                <span> <ButtonC>Delete</ButtonC></span>

            </router-link> -->


            <!-- ********************************** -->
            <!-- ******** Delete after backend is ready ******** -->
             <!-- ********************************** -->
            <!-- <router-link class="game-detail" :to="{ name: 'Game', params: { id: game.id}}"> -->
                <span>{{game.id}}</span>
                <span>{{game.name}}</span>
                <span>{{game.venue}}</span>
                <span> <ButtonC @custom-event="deleteGame(game.id)">Delete</ButtonC></span>
            <!-- </router-link> -->


        </div>
    </div>

</div>
</template>

<script setup>
import ButtonC from '@/components/ButtonC.vue';
import { ref } from 'vue';
import { useRoute } from 'vue-router';

// ****************************************
// Fetching Games under schedules and showing them
// ****************************************
let HideForm = ref(true);

const route = useRoute();
const scheduleId = route.params.id;
const gamesUrl = `http://localhost:8080/api/v1/gameSchedule/${scheduleId}`;
const availableGames = ref([]);
const scheduleTitle = ref("");

// ****************************************
// fetching the schedule and getting the games
// ****************************************
fetch(gamesUrl)
    .then(res =>{
        if(!res.ok){
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    })
    .then(data =>{
        availableGames.value = data.data.games;
        scheduleTitle.value = data.data.name;
    })
    .catch(err =>{
        console.log(err);
    })

// ****************************************
// Submitting new game/ Adding a new game
// ****************************************

const gameSubmitUrl = `http://localhost:8080/api/v1/gameSchedule/${scheduleId}/games`;
const gamevenue = ref("");
const gameopponent = ref("");
const gamefinalized = ref("");
const gamedate = ref("");

const postResponse = ref("");

const submitNewGame = async () =>{
    postResponse.value = "";

    const newGame = {
        gameDate: gamedate.value,
        venue: gamevenue.value,
        opponent: gameopponent.value,
        isFinalized: gamefinalized.value,
    }

    try{
        // submitting the new game
        const res = await fetch(gameSubmitUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newGame),
        })

        if(!res.ok){
            throw new Error(`Error: ${res.status}`)
        }
        
        const data = await res.json();
        postResponse.value = data.message || "Game added successfully"

        availableGames.value.push({
            name: newGame.gameDate,
            gameId: data.data.id,
        });

        // clear the form
        gamevenue.value = "";
        gameopponent.value = "";
        gamefinalized.value = "";
        gamedate.value = "";
        HideForm.value = true;
    
    }catch(err){
        postResponse.value = err;
    }
}

// ****************************************
// Deleting a game || NOT implemented
// ****************************************
// const deleteGame = async (id) =>{
//     const deleteUrl = `http://localhost:8080/api/v1/gameSchedule/${scheduleId}/games/${id}`;

//     try{
//         // submitting the new game
//         const res = await fetch(deleteUrl, {
//             method: "DELETE",
//         })

//         if(!res.ok){
//             throw new Error(`Error: ${res.status}`)
//         }
        
//         const data = await res.json();
//         postResponse.value = data.message || "Game deleted successfully"

//         availableGames.value = availableGames.value.filter(game => game.gameId !== id);
    
//     } catch(err){
//         postResponse.value = err;
//     }
// }

</script>

<style  scoped>
    .games-container{
        display: flex;
        flex-direction: column;
        width: 100%;
        align-items: center;
        align-content: center;
    }

    .add-game{
        width: fit-content;
        padding: 10px;
        border: 1px solid lightgray;
        margin-top:2rem;
    }

    .add-game > form{
        display: flex;
        flex-direction: column;
        /* align-items: center; */
        justify-content: center;
        border-radius: 20px;
    }

    .add-game > form > *{
        padding: 5px;
        margin: 5px;
        border-radius: 4px;
    }

    .active{
        display: none;
    }

    .sub-head{
        display: flex;
        width: 100%;
        justify-content: space-around;
        background: rgb(240, 240, 240);
        align-items: center;
    }

    .game-list{
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .game-item{
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
        width: 50%;
        flex-direction: row;
    }

    .game-item:hover{
        background: rgb(230, 230, 230);
    }
</style>