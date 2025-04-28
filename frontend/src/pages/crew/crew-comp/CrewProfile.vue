<template>
    <!-- <div>
        <h1>{{ myprofile.firstName }} {{ myprofile.lastName }}</h1>
        <p>{{ myprofile.id }}</p>
        <p>{{ myprofile.email }}</p>
        <p>{{ myprofile.phoneNumber }}</p>
        <p v-for="position in positions" :key="position.id">{{ position.position }}</p>
    </div> -->
</template>

<script setup>
import crewapi from '@/api/crewapi';
import { ref } from 'vue';
const myprofile = ref(null);
const positions = ref(null);

const loadMyProfile = async () => {
    try {
        const profile = await crewapi.getMyProfile();
        myprofile.value = profile.data;
        positions.value = profile.data.positions;
    } catch (err) {
        console.log(err);
    }
}
loadMyProfile();

// console.log(myprofile.value.positions);

</script>

<style lang="scss" scoped></style>