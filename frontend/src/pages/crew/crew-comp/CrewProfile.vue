<template>
    <div v-if="!loading">
    <div class="profile" >
        <div class="details">
            <div class="name">
                <p>name</p> <br>
                <h3>{{ myprofile.firstName }} {{ myprofile.lastName }}</h3>
            </div>
            <div class="id">
                <p>id</p> <br>
                <h3>{{ myprofile.id }}</h3>
            </div>
            <div class="email">
                <p>email</p> <br>
                <h3>{{ myprofile.email }}</h3>
            </div>
            <div class="phone">
                <p>phone</p> <br>
                <h3>{{ myprofile.phoneNumber }}</h3>
            </div>
            <div class="positions">
                <p>positions</p> <br>
                <h3 v-for="position in positions" :key="position.id">{{ position }}</h3>
            </div>
        </div>
    </div>


    <div class="upcoming-shifts">
        <div class="title">
            <h1>Upcoming Shifts</h1>
        </div>
    </div>
</div>

<div class="title" v-else>
    <h1>Loading... or network error</h1>
</div>
</template>

<script setup>
import crewapi from '@/api/crewapi';
import { ref } from 'vue';
const myprofile = ref(null);
const positions = ref([]);
const loading = ref(true);

const loadMyProfile = async () => {
    try {
        const profile = await crewapi.getMyProfile();
        myprofile.value = profile.data;
        positions.value = profile.data.positions;
    } catch (err) {
        console.log(err);
    } finally {
        loading.value = false;
    }
}
loadMyProfile();

// console.log(myprofile.value.positions);

</script>

<style lang="scss" scoped>
    .profile {
        display: flex;
        justify-content: center;
    }

    .details {
        display: flex;
        flex-direction: column;
        background: #f1f1f1;
        width: 80%;
        justify-content: center;

    }

    .details > *{
        display: flex;

        justify-content: space-around;
    }

    .title{
        text-align: center;
    }
</style>