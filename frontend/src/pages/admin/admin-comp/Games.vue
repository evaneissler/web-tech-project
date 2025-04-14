<template>
    <div>
        <div class="add-game">
            <div class="title">
                <h1 class="all-game-title">Games {2024-25} Season</h1>
                <CustomButton @custom-click="gameActive = !gameActive;
                ">Add Game</CustomButton>
            </div>
            <hr>
            <!-- <div class="add-tab">
                    <form class="add-form" @submit.prevent="handlerForm">
                        <input  v-model="sportsName" type="text" placeholder="Sport type">
                        <input  v-model="seasonRange" type="text" placeholder="Season">
                        <CustomButton>Add</CustomButton>
                    </form>
            </div>
            <hr> -->
        </div>


        <!-- {{ availableGames }} -->
        <div class="item-div" v-for="(item, index) in availableGames" :key="index" @click="getGameID(1)"><!-- change back to item.gameId -->
            <!-- {{ item }} -->
            <!-- <span>{{ item['gameId'] }}</span>
            <span>{{ item['gameDate'] }}</span>
            <span>{{ item['venue'] }}</span>
            <span>{{ item['opponent'] }}</span>
            <span>{{ item['isFinalized']? "Upcoming": "Game is done" }}</span> -->

            <span> 3001 </span>
            <span> today </span>
            <span> TCU </span>
            <span> Texas tech </span>
            <span> the game is going on </span>
            <span>
                <CustomButton>Delete</CustomButton>
            </span>
        </div>
    </div>
</template>

<script setup>
import CustomButton from '@/components/CustomButton.vue';
import { ref } from 'vue';


// all refs
let availableGames = ref("");


// first things to see is a list of games that are available
// with the ability to add another game

// fetching available games
// ************************
const available_game_url = "http://localhost:8080/api/v1/gameSchedule/games"

fetch(available_game_url)
    .then(resp => resp.json())
    .then(data => {
        availableGames.value = data.data;
        // console.log(availableGames.value)
    })
    .catch(error => console.error("error: " + error));


// finding a game by ID or viewing for details.
// *******************************************
let gameId_from_div = ref(0);

function getGameID(selectedID){
    gameId_from_div.value = selectedID;
    
    const item_url = computed(()=>`${available_game_url}/${gameId_from_div.value}`);
    console.log(item_url.value);
}


</script>

<style scoped>
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

    .title{
        display: flex;
        justify-content: space-around;
        align-items: center;
    }

    .title > *{
        height: fit-content;
    }

    .all-game-title{
        font-size: 2rem;
        font-weight: bolder;
        margin: 1rem;
    }

/* 
    .add-tab{
        padding: 10px;
        display: flex;
        justify-content: center;
    }

    .add-form{
        display: flex;
        margin: 10px;
        justify-content: space-evenly;
        width: 100%;
    } */
</style>