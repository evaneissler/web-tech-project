<template>
    <nav class="flex justify-around nav-custom">
        <div class="logo">
            <router-link to="/admins-panel" class="frogcrew">FrogCrew</router-link>
        </div>

        <div class="flex custom-list" >
            <router-link to="/admin-crew">Crew</router-link>
            <router-link to="/invite">Invite </router-link>
            <router-link to="/game-schedule">Game Schedule </router-link>
        </div>
    </nav>
</template>

<script setup>
import "@/assets/base.css";
</script>

<style scoped>
    .frogcrew{
        font-size: xx-large;
    }

    .nav-custom{
        background:var(--tcu-purple-color);
        font-weight: bold;
        padding: 1rem;
        color: var(--tcu-white-color);
    }

    .custom-list{
        display: flex;
        width: 100%;
        justify-content: space-evenly;
        align-items: center;
    }

    .custom-list > a{
        font-weight: bold;
        display: flex;
    }
</style>