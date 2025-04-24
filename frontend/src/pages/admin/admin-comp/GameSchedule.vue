<template>
    <div class="schedule-container">
        <!-- ********************************** -->
        <!-- Sub headd -->
        <!-- ********************************** -->
        <div class="subhead-schedule">
            <h1>Schedule Tab</h1>
            <ButtonC @custom-event="HideForm = !HideForm">{{ HideForm ? "Add" : "Close" }}</ButtonC>
        </div>



        <!-- ********************************** -->
        <!-- Adding a new schedule -->
        <!-- ********************************** -->

        <div class="add-schedule" :class="{ 'isActive': HideForm }">
            <h1>New Schedule</h1>

            <form @submit.prevent="submitNewSchedule">
                <input required v-model="sportName" type="text" placeholder="Sport">
                <input required v-model="seasonName" type="text" placeholder="Season">
                <div class="btns">
                    <ButtonC>Save</ButtonC>
                </div>
            </form>
        </div>

        <!-- ********************************** -->
        <!-- Showing error message -->
        <!-- ********************************** -->

        <!-- <div v-if="postResponse != ''">
            <h1>{{ postResponse }}</h1>
        </div> -->


        <!-- ********************************** -->
        <!-- Showing available schedules -->
        <!-- ********************************** -->

        <div class="schedules-list" v-if="schedules.length > 0">
            <table class="schedule-table">
                <tr class="schedule-header">
                    <th>Schedule ID</th>
                    <th>Sport</th>
                    <th>Season</th>
                    <th>Games</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <tr class="game-schedule" v-for="(item) in schedules" :key="item.id" @click="goToSchedule(item.id)">
                    <td>{{ item.id }}</td>
                    <td>{{ item.sport }}</td>
                    <td>{{ item.season }}</td>
                    <td>{{ item.games.length }}</td>
                    <td>
                        <ButtonC>Edit</ButtonC>
                    </td>
                    <td>
                        <ButtonC>Delete</ButtonC>
                    </td>
                </tr>
            </table>
        </div>

        <div v-else class="no-schedules">
            <h1>No schedules yet</h1>
        </div>

    </div>
</template>

<script setup>
import adminapi from '@/api/adminapi';
import ButtonC from '@/components/ButtonC.vue';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

// ****************************************
// GOING TO SCHEDULE
// ****************************************
const router = useRouter();

const goToSchedule = (id) => {
    router.push({ name: "Schedule-admin", params: { id: id } });
}



// true or false? show or hide
let HideForm = ref(true);

// ****************************************
// submitting new schedule
// ****************************************

const sportName = ref("");
const seasonName = ref("");
let postResponse = ref("");

let schedules = ref([]);
const loading = ref(true);

const submitNewSchedule = async () => {
    postResponse.value = "";
    // sports and seasons body
    const newSchedule = {
        sport: sportName.value,
        season: seasonName.value,
    }

    try {
        postResponse.value = adminapi.addGameSchedule(newSchedule) || "Schedule added successfully"

        schedules.value.push({
            sport: newSchedule.sport,
            season: newSchedule.season,
            games: [],
        });

        // clear the form
        sportName.value = "";
        seasonName.value = "";
    } catch (err) {
        postResponse.value = err;
    } finally{
        loading.value = false;
        HideForm.value = !HideForm.value;
    }
}




// ****************************************
// Fetching all schedules and showing them
// ****************************************
const loadAllSchedules = async () => {
    try {

        const schedulesRes = await adminapi.gameSchedule();
        schedules.value = schedulesRes.data;
    } catch (err) {
        console.log(err);
    } finally {
        loading.value = false;
    }
}

loadAllSchedules();
</script>


<style scoped>
.no-schedules {
    margin-top: 3rem;
}

.schedules-list {
    width: 70%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}


.game-schedule,
.schedule-header {
    text-align: center;
}

.game-schedule>*,
.schedule-header>* {
    padding: 1rem;
}

.game-schedule:hover {
    background: rgb(230, 230, 230);
    cursor: pointer;
}

/* ADDING A NEW SCHEDULE */

.isActive {
    display: none;
}

.add-schedule {
    flex-direction: column;
    border: 1px solid lightgray;
    width: fit-content;
    text-align: center;
    padding: 10px;
}

.add-schedule>form {
    display: flex;
    flex-direction: column;
    text-align: center;
}

.add-schedule>form>* {
    padding: 5px;
    margin: 5px;
    border-radius: 4px;
}

.schedule-container {
    /* background: lightgray */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.subhead-schedule {
    display: flex;
    width: 100%;
    justify-content: space-around;
    background: rgb(240, 240, 240);
    align-items: center;
}
</style>