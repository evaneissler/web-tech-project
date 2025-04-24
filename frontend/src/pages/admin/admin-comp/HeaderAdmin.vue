<template>
    <div>
        <nav class="nav">
            <div class="logo">
                <h1 class="home-admin" >
                    <router-link to="/admins">FrogCrew | Admin</router-link>
                </h1>
            </div>

            <div class="sub-links">
                <router-link to="/admins/schedules">Schedule</router-link>
                <router-link to="/admins/invite">Invite</router-link>
                <router-link to="/admins/crew">Crew</router-link>
            </div>
            <div class="profile">
                <router-link to="/admins/profile">Profile</router-link>
            </div>
        </nav>
        <hr>
    </div>
</template>

<script setup>

</script>

<style  scoped>
    .logo{
        display: flex;
        align-items: center;
        text-align: center;
        justify-content: center;
        padding: 1rem;
    }

    .logo *{
        text-decoration: none;
    }

    .nav{
        /* background: green;s */
        display: flex;
        justify-content: space-evenly;
        width: calc(100vw - 2vw);
        align-items: center;
    }

    .sub-links{
        width: 60%;
        display: flex;
        gap: 10%;
        justify-content: center;
        align-items: center;
    }

    .sub-links > *, .profile > *{
        text-decoration: none;
        /* background-color: red; */
        height: 100%;
        display: flex;
        align-items: center;
        padding: 1rem;
    }

    .home-admin{
        cursor: pointer;
    }
</style>