<template>
    <div>
        <!-- View Crew Profile

        [create crew profile]
        [see all members]
        [search and view crew profile] -->
        <div class="crew-management">
            <div class="photo-name">
                <img src="/img/image.png" alt="">
                <h1>John Jour</h1>
                <p>Roles: </p>
            </div>
        </div>
        <hr>
    </div>
</template>

<script setup>
import "@/assets/base.css";


</script>

<style scoped>
    .crew-management{
        display: flex;
        flex-direction: column;
        align-items: center;
        background: var(--main-background);
        width: 100%;
        padding:0.5rem;
    }

    img{
        height: 200px;
        width: 200px;
        border-radius: 100%;
    }

</style>