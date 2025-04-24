<template>
    <div class="crew-container">
        <div class="crew-operations">
            <!-- search by id form -->
            <div class="search-by-id">
                <h1>Search by ID</h1>
                <form @submit.prevent="handleCrewSearch">
                    <input v-model="crewID" type="text" placeholder="Enter ID">
                    <ButtonC>Search</ButtonC>
                </form>
            </div>
        </div>
        <hr>
        <div class="list-crew">

        </div>
    </div>
</template>

<script setup>
import adminapi from '@/api/adminapi';
import ButtonC from '@/components/ButtonC.vue';
import { ref } from 'vue';

// ****************************************
// Fetching all crew
// ****************************************
const allCrew = ref([]);
const loading = ref(true);

const loadAllCrew = async () => {
    try {
        const allCrewRes = await adminapi.findAllCrew();
        allCrew.value = allCrewRes.data;
        console.log(allCrew.value);
    } catch (err) {
        console.log(err);
    } finally {
        loading.value = false;
    }
}

loadAllCrew();

// ****************************************
// Searching by ID
// ****************************************
const crewID = ref("");
const handleCrewSearch = async () => {
    try {
        const crew = await adminapi.findCrewById(crewID);
        console.log(crew);
    } catch (err) {
        console.log(err);
    } finally {
        loading.value = false;
    }
}
</script>

<style scoped>
.crew-operations {
    display: flex;
    /* flex-direction: column; */
    width: 100%;
    align-items: center;
    /* background: red; */
}

.search-by-id {
    width: 100%;
    display: flex;
    /* flex-direction: column; */
    align-items: center;
    /* background: green; */
    justify-content: space-around;
}

.search-by-id > form {
    display: flex;
    /* flex-direction: column; */
    align-items: center;
    width: fit-content;
    gap: 2rem;
}

.search-by-id > form > input {
    width: 100%;
    height: 2rem;
    padding: 0.2rem;
}
</style>