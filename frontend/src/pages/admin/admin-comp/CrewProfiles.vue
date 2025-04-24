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
        <div class="list-crew" v-if="!loading">
            <table class="crew-table">
                <thead>
                    <tr class="crew-title">
                        <td>ID</td>
                        <td>Full Name</td>
                        <td>Email</td>
                        <td>Phone Number</td>
                    </tr>
                </thead>
                <tbody class="crew" v-for="crew in allCrew" :key="crew.id">
                    <tr>
                        <td>{{ crew.id }}</td>
                        <td>{{ crew.fullName }}</td>
                        <td>{{ crew.email }}</td>
                        <td>{{ crew.phoneNumber }}</td>
                        <!-- <td><ButtonC>Edit</ButtonC></td> -->
                        <!-- <td><ButtonC>Delete</ButtonC></td> -->
                    </tr>
                </tbody>
            </table>
        </div>

        <div v-else class="loading-crew">
            <h1>Loading... or network error</h1>
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

.loading-crew {
    margin-top:3rem;
    text-align: center;
}

.crew-container {
    display: flex;
    flex-direction: column;
    align-items: center;
}


.list-crew {
    width: 80%;
    margin-top: 2rem;
    display: flex;
    /* align-items: center; */
    justify-content: center;
}

.crew-title {
    font-weight: bold;
    text-align: center;
}

.crew tr {
    padding: 2rem;
}

.crew * {
    padding: 1rem;
    cursor: pointer;
}

.crew:hover {
    background: #e1e1e1;
}

a {
    text-decoration: none;
}

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
    align-items: center;
    /* background: green; */
    justify-content: space-around;
}

.search-by-id>form {
    display: flex;
    /* flex-direction: column; */
    align-items: center;
    width: fit-content;
    gap: 2rem;
}

.search-by-id>form>input {
    width: 100%;
    height: 2rem;
    padding: 0.2rem;
}
</style>