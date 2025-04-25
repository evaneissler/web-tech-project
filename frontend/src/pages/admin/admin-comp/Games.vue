<template>
    <!-- ********************************** -->
    <!-- Sub headd -->
    <!-- ********************************** -->
    <div class="games-container">
        <div class="sub-head">
            <h1> Games in schedule {{ scheduleId }}</h1>
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
            <select  required name="" id="" v-model="gamefinalized">
                <option value="true" selected>True</option>
                <option value="false">False</option>
            </select>

            <ButtonC >Save</ButtonC>
        </form>
    </div>

    <!-- ********************************** -->
    <!-- Showing available games -->
    <!-- ********************************** -->
    <div class="game-list" v-if="availableGames.length > 0">
        <table class="game-table">
                <thead>
                    <tr>
                        <th>Game ID</th>
                        <th>Game Date</th>
                        <th>Venue</th>
                        <th>Opponent</th>
                        <th>Is Finalized</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody class="game-item" v-for="game in availableGames" :key="game.gameId">
                    <tr>
                        <td>{{ game.gameId }}</td>
                        <td>{{ game.gameDate }}</td>
                        <td>{{ game.venue }}</td>
                        <td>{{ game.opponent }}</td>
                        <td>{{ game.isFinalized }}</td>
                        <td>
                            <ButtonC>Edit</ButtonC>
                        </td>
                        <td>
                            <ButtonC>Delete</ButtonC>
                        </td>
                    </tr>
                </tbody>
            </table>
    </div>
    <div v-else class="no-games">
        <h1>No games yet</h1>
    </div>

</div>
</template>

<script setup>
import adminapi from '@/api/adminapi';
import ButtonC from '@/components/ButtonC.vue';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
let HideForm = ref(true);
const loading = ref(true);

// ****************************************
// After submitting a new game, we need to hide the form
// ****************************************


// ****************************************
// Fetching Games under schedules and showing them
// ****************************************


const route = useRoute();
const scheduleId = route.params.id;
const availableGames = ref([]);
const scheduleTitle = ref("");

// ****************************************
// fetching the schedule and getting the games
// ****************************************
const loadAllGames = async () =>{
    try{
        const availableGamesRes = await adminapi.findAllGames(scheduleId);
        // const scheduleTitleRes = await adminapi.findAllGames(scheduleId);
        // scheduleTitle.value = scheduleTitleRes;
        availableGames.value = availableGamesRes.data;
        console.log(availableGames.value);
    } catch(err){
        console.log(err);
    } finally {
        loading.value = false;
    }
}

loadAllGames();

// ****************************************
// Submitting new game/ Adding a new game
// ****************************************
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
        postResponse.value = adminapi.addNewGame(scheduleId, newGame) || "Game added successfully"

        availableGames.value.push({
            gameId:null,
            gameDate: newGame.gameDate,
            venue: newGame.venue,
            opponent: newGame.opponent,
            isFinalized: newGame.isFinalized
        });

        // clear the form
        gamevenue.value = "";
        gameopponent.value = "";
        gamefinalized.value = "";
        gamedate.value = "";
    }catch(err){
        postResponse.value = err;
    } finally {
        HideForm.value = !HideForm.value;
    }
}

// ****************************************
// Deleting a game || NOT implemented
// ****************************************
// const deleteGame = async (id) =>{

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

    .no-games{
        margin-top:3rem;
    }

    a{
        text-decoration: none;
    }

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
        /* background: red; */
        margin-bottom: 2rem;
        transition: all 0.3s ease-in-out;
    transform: scaleY(1);
    opacity: 1;
    }

    .add-game > form{
        display: flex;
        flex-direction: column;
        /* align-items: center; */
        justify-content: center;
        border-radius: 20px;
    }

    .add-game.active {
    transform: scaleY(0);
    opacity: 0;
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
        /* background: red; */
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top:2rem;
    }

    .game-item{
        text-align: center;
    }

    .game-item  *{
        padding: 1rem;
    }

    .game-item:hover{
        background: rgb(230, 230, 230);
        cursor: pointer;
    }


</style>