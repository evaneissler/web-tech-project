<template>
    <div>
        <!-- View Crew Profile

        [create crew profile]
        [see all members]
        [search and view crew profile] -->
        <div class="crew-management">
            <div class="search">
                <input type="search" placeholder="Search">
                <CustomButton>Search</CustomButton>
                <CustomButton class="fa fa-plus plus-button"> </CustomButton>
            </div>
        </div>



    </div>
</template>

<script setup>
import CustomButton from '@/components/CustomButton.vue';


</script>

<style scoped>

    .search{
        display: flex;
        /* flex-direction: column; */
        background: red;
    }

    .plus-button{
        font-size: 1.5rem;
    }
    .plus-button > *{
        font-size: 1.5rem;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
    }
</style>