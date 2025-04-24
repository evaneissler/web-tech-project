<template>
    <div class="welcome-container">
        <h1>Welcome to the Admin Panel</h1>
        <br>
        <p>Here you can manage your game schedules, invite your crew, and more!</p>
        <div class="functions">
            <router-link class="function" :to="{ name: 'Schedules' }">Manage Game Schedules</router-link>
            <router-link class="function" :to="{ name: 'crews' }">Manage Crew</router-link>
            <router-link class="function" :to="{ name: 'invite' }">Invite Crew</router-link>
        </div>
    </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>
    .welcome-container {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 100%;
        margin-top:5rem;
    }


    .functions {
        display: flex;
        flex-direction: row;
        justify-content: space-evenly;
        align-items: center;
        width: 100%;
        margin-top: 1rem;
    }
</style>