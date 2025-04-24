<template>
    <div class="welcome-container">
        <div class="welcome-crew">
            <h1>Welcome {{ crewname }},</h1>
            <p>Click on on desired link to see the content of that page.</p>
            <br>
            <p>To submit availability, click on genera schedules or schedule</p>
        </div>
        <div class="operations">
            <router-link to="/crew/schedule">Genera Schedules</router-link>
            <router-link to="/crew/profile">Crew Profile</router-link>
        </div>
    </div>
</template>

<script setup>
const crewname = "crew name";
</script>

<style lang="scss" scoped>

    .welcome-container{
        width: 70%;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin: auto;
        margin-top:5rem;
        text-align: center;
    }

    .welcome-crew{
        padding: 2rem;
    }

    .operations{
        display: flex;
        justify-content: space-around;
        gap: 2rem;
    }
</style>