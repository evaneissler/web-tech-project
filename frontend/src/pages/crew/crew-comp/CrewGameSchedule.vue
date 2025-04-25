<template>
    <div class="game-schedule-container">


        <table class="schedule-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Sport</th>
                    <th>Season</th>
                    <th>Games</th>
                </tr>
            </thead>

            <tbody class="schedule-item" v-for="schedule in gameSchedules" :key="schedule.id" @click="goToTheGame(schedule.id)">
                <tr >
                    <td>{{ schedule.id }}</td>
                    <td>{{ schedule.sport }}</td>
                    <td>{{ schedule.season }}</td>
                    <td>{{ schedule.games.length }}</td>
                </tr>
            </tbody>
        </table>

    </div>
</template>

<script setup>
import crewapi from '@/api/crewapi';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const gameSchedules = ref([]);
const router = useRouter();
const loading = ref(true);

// ****************************************
// Getting all schedules.
// ****************************************
const loadAllGameSchedule = async () => {
    try {
        const generalSchedules = await crewapi.viewGeneralSchedule();
        gameSchedules.value = generalSchedules.data;
    } catch (err) {
        console.log(err);
    } finally {
        loading.value = false;
    }
}

// ****************************************
// Going to the schedules to see the games.
// ****************************************
const goToTheGame = async (id) => {
    router.push({ name: "Schedule-crew", params: { id: id } });
}

loadAllGameSchedule();
</script>

<style scoped>
.game-schedule-container {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.schedule-table{
    width: 80%;
    margin-top: 2rem;
    text-align: center;
    background: #f5f5f5;
}

.schedule-table *{
    padding: 1rem;
}


.schedule-item :hover{
    cursor: pointer;
    background: #e1e1e1
}
</style>