<template>
    <nav>
        <div class="logo">
            <router-link to="/crew">
                FrogCrew | Crew
            </router-link>
        </div>

        <div class="sub-links">
            <router-link to="/crew/schedule">Schedule</router-link>
            <router-link to="/crew/games">Games</router-link>
            <router-link to="/crew/availability">Availability</router-link>
            <!-- <router-link to="/crew/profile">Profile</router-link> -->
        </div>

        <div class="profile">
            <router-link to="/crew/profile">Profile</router-link>
        </div>

    </nav>
</template>

<script setup>

</script>

<style  scoped>
    nav{
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .logo{
        display: flex;
        background: green;
        height: 100%;
    }

    .sub-links{
        display: flex;
        gap: 2rem;
        height: 100%;
        align-items: center;
        justify-content: center;
    }

    .sub-links > *, .profile > *, .logo > *{
        height: 100%;
        display: flex;
        padding: 1rem;
        text-decoration: none;
    }
</style>