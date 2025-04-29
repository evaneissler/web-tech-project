<template>
    <div class="user-list-container">
        <div class="game-details" v-if="!loading">
            <div class="game-details-title">
                <h1>Game Details: {{ gameId }}</h1>
                <hr>
            </div>
            <div class="game-content">
                <table>
                    <thead>
                        <tr>
                            <th>Start Date</th>
                            <th>Start Time</th>
                            <th>Opponent</th>
                            <th>Venue</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td>{{ gameDate }}</td>
                            <td>{{ gameStart }}</td>
                            <td>{{ opponent }}</td>
                            <td>{{ venue }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="game-crew">
                <div class="game-crew-title">
                    <h1>Crew</h1>
                    <hr>
                </div>
                <div class="crew-list">
                    <div class="crew-list-item" v-for="user in crewList" :key="user.id">
                        <!-- <span>{{ user }}</span> -->
                        <span>{{ user.userId }}</span>
                        <span>{{ user.fullName }}</span>
                        <span>{{ user.reportLocation }}</span>
                        <span v-for="pos in user.positions"">
                            {{ pos }}
                        </span>
                    </div>
                </div>
            </div>
        </div>

        <div v-else>
            <div class="loading-container">
                <div class="loading-text">
                    <h1>Loading... or down :/</h1>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import adminapi from '@/api/adminapi';
import { ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const gameId = route.params.id;

// ****************************************
// Fetching all crew
// ****************************************
const allCrew = ref([]);
const loading = ref(true);
let gameStart, gameDate, opponent, venue;
gameDate = ref("");
gameStart = ref("");
opponent = ref("");
venue = ref("");

const crewList = ref([]);

const loadAllCrew = async () => {
    try {
        const allCrewRes = await adminapi.usersUnderGame(gameId);
        allCrew.value = allCrewRes.data;

        gameDate.value = allCrew.value.gameDate;
        gameStart.value = allCrew.value.gameStart;
        opponent.value = allCrew.value.opponent;
        venue.value = allCrew.value.venue;
        
        crewList.value = allCrew.value.crewedMembers;
    } catch (err) {
        console.error(err);
    } finally {
        loading.value = false;
    }
}

loadAllCrew();


</script>

<style lang="scss" scoped>
    .game-details{
        width: 90%;
        text-align: center;
    }
    .game-content > table{
        width: 100%;
        // background: green;
        text-align: center;
    }

    .user-list-container{
        display: flex;
        flex-direction: column;
        align-items: center;
        // background: red;
    }

    .game-crew{
        margin-top: 2rem;
    }

    .crew-list{
        // background: yellow;
        width: 100%;
    }

    .crew-list-item{
        display: flex;
        justify-content: space-evenly;
        padding: 1rem;
    }

    .crew-list-item:hover{
        cursor: pointer;
        background: #f2f2f2;
    }
</style>