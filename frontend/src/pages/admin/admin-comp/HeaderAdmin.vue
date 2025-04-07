<template>
    <nav class="flex justify-around nav-custom">
        <div class="logo">
            <router-link to="/admins-panel" class="frogcrew">FrogCrew | Admin</router-link>
        </div>

        <div class="flex custom-list" >
            <div></div>
            <div class="innercontainer">
                <router-link to="/admin-profile">My Profile </router-link>
            </div>
        </div>
    </nav>
</template>

<script setup>
import "@/assets/base.css";
</script>

<style scoped>
    .logo{
        width: 30%;
        display: flex;
        justify-content: center;
        align-items: center;
    }


    .frogcrew{
        font-size: 1.5rem;
    }

    .nav-custom{
        background:var(--tcu-purple-color);
        font-weight: bold;
        padding: 1rem;
        color: var(--tcu-white-color);
        height: 10vh;
    }

    .custom-list{
        display: flex;
        width: 100%;
        /* background: red; */
        justify-content: space-between;
        align-items: center;
    }

    .innercontainer{
        display: flex;
        width: 30%;
        justify-content: space-around;
    }

    .custom-list > a{
        font-weight: bold;
        display: flex;
    }
</style>