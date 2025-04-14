<template>
    <div class="crewlist">
        <div class="title">
            <h1>Crew List</h1>
            <!-- <CustomButton></CustomButton> -->
        </div>
        <div class="crew-member" v-for="(member) in crewList" :key="1"> <!-- member.userId -->
            
            <!-- <span>{{ member.userId }} </span>
            <span> {{ member.fullName }} </span>
            <span> {{ member.email }} </span>
            <span> {{ member.phoneNumber }} </span> -->


            <span>902 </span>
            <span> James john </span>
            <span> j.james@frogcrew.com </span>
            <span> (293)-182-2894 </span>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';


const crewList = ref();

const gameURL = "http://localhost:8080/api/v1/crewMember";
fetch(gameURL)
    .then(resp => resp.json())
    .then(data => {
        crewList.value = data.data;
        console.log(crewList.value)
    })
    .catch(error => console.error("error: " + error));


</script>

<style scoped>
    .crewlist{
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .crew-member{
        display: flex;
        border-bottom:1px solid lightgray;
        justify-content: center;
        width: 90%;
        gap: 5%;
        height: 6vh;
        align-items: center;
    }

    .title{
        font-weight: bold;
        font-size: xx-large;
    }
    
</style>