<style scoped>
    .crew-profile-container{
        /* background: red; */
    }

    .personal-details{
        /* background: red; */
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .img{
        border-radius: 100%;
        height: 200px;
        width: 200px;
        object-fit: cover;
    }

    .user-name{
        font-size: 1.5rem;
        font-weight: bold;
    }

    .roles-container{
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
    }

    .roles{
        height: 5vh;
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: space-around;
    }

</style>
<template>
    <div class="crew-profile-container">
        <div class="personal-details">
            <div class="img-holder">
                <img class="img" src="https://placehold.co/400" alt="">
            </div>
            <div class="name-bio">
                <h1 class="user-name">First & Last Name</h1>
                <a href="mailto:l.first@tcu.edu">l.first@tcu.edu</a>
            </div>

            <div class="roles-container">
                <div class="roles-title">
                    <h1 class="roles-title">Roles</h1>
                </div>
                <div class="roles">
                    <span>Video graher </span>
                    <span>Interviewer </span>
                    <span>Cheerleader</span>
                </div>
            </div>
        </div>
        <hr>
    </div>
</template>

<script setup>

</script>