<style scoped>
    .crew-profile-container{
        /* display: flex; */
    }

    .personal-details{
        /* background: red; */
        height: 30vh;
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
        margin: 10px;
    }

    .name-bio{
        background: rgb(236, 236, 236);
        height: calc(89vh - 30vh);
        display: flex;
        padding-top: 10px;
        justify-content: space-around;
    }

    .roles-container{
        display: flex;
        flex-direction: column;
    }

</style>
<template>
    <div class="crew-profile-container">
        <div class="personal-details">
            <div class="img-holder">
                <img class="img" src="https://placehold.co/400" alt="">
            </div>
        </div>
        <hr>

        <div class="name-bio">
            <div class="name-email">
                <h1 class="user-name"> First & Last Name</h1>
                <a href="mailto:l.first@tcu.edu">l.first@tcu.edu</a>
            </div>

            <div class="roles-container">
                <ul>
                    <li>Video graher</li>
                    <li>Interviewer</li>
                    <li>Cheerleader</li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>