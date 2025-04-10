<template>
    <div>
        <!-- View Game schedule
        [view all crew that are working on that] -->

        <!-- {{ returnData }} -->
        <div class="item-div" v-for="(item, index) in returnData" :key="index" @click="getGameID(1)"><!-- change back to item.gameId -->
            <!-- {{ item }} -->
            <!-- <span>{{ item['id'] }}</span>
            <span>{{ item['sport'] }}</span>
            <span>{{ item['season'] }}</span>
        -->

            <span> 100 </span>
            <span> FOOTBALL </span>
            <span> 2024-25 </span>
        </div>

    </div>
</template>

<script setup>
import { computed, ref } from 'vue';

let returnData = ref("");
let gameId_from_div = ref(0);


const gameURL = "http://localhost:8080/api/v1/gameSchedule";
fetch(gameURL)
    .then(resp => resp.json())
    .then(data => {
        returnData.value = data.data;
    })
    .catch(error => console.error("error: " + error));



// viewing a game

function getGameID(selectedID){
    gameId_from_div.value = selectedID;
    const item_url = computed(()=>`${gameURL}/${gameId_from_div.value}`);
    // console.log(item_url.value);
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
</style>