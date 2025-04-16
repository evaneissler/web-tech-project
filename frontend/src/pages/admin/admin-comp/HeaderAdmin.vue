<template>
    <div>
        <nav class="nav">
            <div class="logo">
                <h1 class="home-admin" >
                    <router-link to="/admins">FrogCrew | Admin</router-link>
                </h1>
            </div>

            <div class="sub-links">
                <router-link to="/admins/schedule">Schedule</router-link>
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
        /* background: red; */
    }

    .logo *{
        text-decoration: none;
    }

    .nav{
        /* background: green; */
        display: flex;
        justify-content: space-evenly;
        width: calc(100vw - 2vw);
        padding: 10px;
        align-items: center;
    }

    .sub-links{
        width: 60%;
        display: flex;
        gap: 10%;
        justify-content: center;
    }

    .sub-links > *, .profile > *{
        text-decoration: none;
    }

    .home-admin{
        cursor: pointer;
    }
</style>