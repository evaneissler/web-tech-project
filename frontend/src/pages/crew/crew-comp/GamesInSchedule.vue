<template>
    <div class="games">
        <hr>
        <h1>Games in schedule {{ scheduleId }}</h1>
        <hr>
    </div>

    <div class="game-list-container">
        <div class="game-list" v-if="availableGames.length > 0">
            <table class="game-table">
                <thead>
                    <tr>
                        <th>Game ID</th>
                        <th>Game Date</th>
                        <th>Venue</th>
                        <th>Opponent</th>
                        <th>Is Finalized</th>
                        <th>Add Availability</th>
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
                            <ButtonC @custom-event="goToAddAvailability(game.gameId)">AA</ButtonC>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div v-else class="no-games">
            <h1>No games yet</h1>
        </div>

        <div class="instr">
            <p>AA stands for Add Availability</p>
        </div>
    </div>
</template>

<script setup>
import adminapi from '@/api/adminapi';
import ButtonC from '@/components/ButtonC.vue';
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const scheduleId = route.params.id;
const loading = ref(true);
const availableGames = ref([]);
const router = useRouter();

// ****************************************
// fetching the schedule and getting the games
// ****************************************
const loadAllGames = async () => {
    try {
        const availableGamesRes = await adminapi.findAllGames(scheduleId);
        availableGames.value = availableGamesRes.data;
        console.log(availableGames.value);
    } catch (err) {
        console.log(err);
    } finally {
        loading.value = false;
    }
}

loadAllGames();

// ****************************************
// Going to add availability to the game
// ****************************************
const goToAddAvailability = async (id) => {
    router.push({ name: "Availability", params: { id: id } });
}
</script>

<style lang="scss" scoped>
.instr{
    text-align: center;
    margin-top: 3rem;
}
.games {
    text-align: center;
}

.game-list {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 2rem;
}

.game-item {
    text-align: center;
}

.game-item * {
    padding: 0.5rem;
}

.game-item:hover {
    background: rgb(230, 230, 230);
    color: purple;
    cursor: pointer;
}
</style>