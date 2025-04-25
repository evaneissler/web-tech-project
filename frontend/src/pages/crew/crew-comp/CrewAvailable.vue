<template>
    <div class="available-form">
        <form @submit.prevent="submitAvailability">
            <div class="form-header">
                <h1>Submit Availability</h1>
                <hr>
            </div>
            <div class="data-container">
                <label for="scheduleId">Schedule ID</label>
                <select name="scheduleId" id="scheduleId" v-model="scheduleId" required>
                    <option v-for="schedule in schedules" :key="schedule.id" :value="schedule.id">{{ schedule.id }} - {{ schedule.sport }} - {{ schedule.season }}</option>
                </select>

                <!-- critical -->
                <label for="gameId" >Game ID</label>
                <select name="gameId" id="gameId" v-model="gameId" required>
                    <option v-if="loadingGames || gamesInSchedule.length == 0" disabled value="">Select a schedule or there are no games in the schedule</option>
                    <option  v-else v-for="game in gamesInSchedule" :key="game.gameId" :value="game.gameId">{{ game.gameId }} - {{ game.gameDate }} - {{ game.venue }}</option>
                </select>

                <label for="userId"> User ID</label>
                <p>{{ userID }}</p>

                <label for="availability">Are you aveilable to work?</label>
                <select name="availability" id="availability" v-model="availability" required>
                    <option value="true" selected>Yes</option>
                    <option value="false">No</option>
                </select>

                <label for="comment">Comment</label>
                <textarea name="comment" id="comment" v-model="comment">

                </textarea>
            </div>


            <ButtonC>Submit</ButtonC>
        </form>
    </div>
</template>

<script setup>
import adminapi from '@/api/adminapi';
import crewapi from '@/api/crewapi';
import ButtonC from '@/components/ButtonC.vue';
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const id = route.params.id;
const availability = ref(true);
const comment = ref("");
let gameId = ref(null);
let userID = ref(null);
const scheduleId = ref(null);

// ****************************************
// get all schedules
// ****************************************
const schedules = ref([]);
const loadAllSchedules = async () => {
    try {
        const schedulesRes = await crewapi.viewGeneralSchedule();
        schedules.value = schedulesRes.data;
    } catch (err) {
        console.error(err);
    }
}
loadAllSchedules();



// ****************************************
// get all games in a schedule
// ****************************************
const gamesInSchedule = ref([]);
const loadGamesInSchedule = async (id) => {
    try {
        const gamesInScheduleRes = await adminapi.findAllGames(id);
        gamesInSchedule.value = gamesInScheduleRes.data;
    } catch (err) {
        console.error(err);
    }
}

const loadingGames = ref(false);
// Cache the games for faster loading
const gamesCache = new Map();

watch(scheduleId, async (newScheduleId) => {
    gamesInSchedule.value = [];
    if (newScheduleId) {
        if (gamesCache.has(newScheduleId)) {
            gamesInSchedule.value = gamesCache.get(newScheduleId);
        } else {
            loadingGames.value = true;
            try {
                await loadGamesInSchedule(newScheduleId);
                gamesCache.set(newScheduleId, [...gamesInSchedule.value]);
            } catch (err) {
                console.error(err);
            } finally {
                loadingGames.value = false;
            }
        }
    }
});


// ****************************************
// Submitting availability
// ****************************************
gameId.value = id;
userID.value = crewapi.getUserID();

function numerify(t){
    return t === true?  1: 0;
}

const  submitAvailability = async() => {
    const availabilityBody = {
        gameId: gameId.value,
        userId: userID.value,
        availability: numerify(availability.value),
        comment: comment.value? comment.value : ""
    }

    try {
        const res = await crewapi.submitAvailability(availabilityBody);
        if(res.code === 200){
            alert("Availability submitted successfully");
        }
    } catch (err) {
        console.error(err);
    }

    gameId.value = "";
    scheduleId.value = "";
    availability.value = null;
    comment.value = "";
}

</script>

<style scoped>
.form-header{
    text-align: center;
}

.available-form {
    position: relative;
    top: 10vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

select{
    padding: 1rem;
}

.available-form>form {
    display: flex;
    flex-direction: column;
    padding: 1rem;
    border-radius: 10px;
    background: inherit;
    border:none;
    width: 50vw;
}

textarea {
    height: 5rem;
    padding: 5px;
}

input, select, textarea{
    /* border:none; */
    border-radius: 5px;
}

.data-container {
    display: flex;
    flex-direction: column;
    margin-top: 1rem;
    margin-bottom: 1rem;
    width: 100%;
    background:inherit;
}

.data-container>* {
    margin-top: 0.5rem;
}
</style>