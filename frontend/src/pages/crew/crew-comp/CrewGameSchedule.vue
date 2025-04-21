<template>
    <div class="game-schedule-container">
        
        <div class="game-list">
            <div class="game-item" v-for="gameSchedule in gameSchedules" :key="gameSchedule.id">
                <router-link class="schedule-detail" :to="{ name: 'Schedule', params: { id: gameSchedule.id } }">
                    <span>{{ gameSchedule.id }}</span>
                    <span>{{ gameSchedule.name }}</span>
                    <span> {{ gameSchedule.games.length }} Game(s) </span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script setup>
import crewapi from '@/api/crewapi';
import { ref } from 'vue';

const gameSchedules = ref([]);
const loading = ref(true);

const loadAllGameSchedule = async () => {
    try {
        gameSchedules.value = await crewapi.viewGeneralSchedule();
        console.log(gameSchedule.value);
    } catch (err) {
        console.log(err);
    } finally {
        loading.value = false;
    }
}

loadAllGameSchedule();
</script>

<style scoped></style>