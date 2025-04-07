<template>
    <div>
        <!-- View Crew Profile

        [create crew profile]
        [see all members]
        [search and view crew profile] -->
        <div class="crew-management">

            <div class="search">
                <input class="searchbar" type="search" placeholder="Search">
                <CustomButton class="btn">Search</CustomButton>
                <CustomButton class="fa fa-plus plus-button btn"> </CustomButton>
            </div>
        </div>
        <hr>

        <div class="crew-profiles">
            
        </div>


    </div>
</template>

<script setup>
import "@/assets/base.css";
import CustomButton from '@/components/CustomButton.vue';


</script>

<style scoped>

    .btn{
        background: white;
        color:black;
    }
    .crew-management{
        display: flex;
        flex-direction: column;
        align-items: center;
        background: var(--main-background);
        width: 100%;
        padding:0.5rem;
    }

    .search{
        display: flex;
        justify-content: center;
        width: 100%;
    }

    .searchbar{
        width: 50%;
        border-radius: 10px;
        font-weight: bolder;
        padding-left: 15px;
        margin-right: 1rem;
        border: 1px solid lightgray;
    }

    .plus-button{
        font-size: 1.5rem;
        margin-left: 1.5rem;
    }
    .plus-button > *{
        font-size: 1.5rem;
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        /* margin-left:10px; */
    }
</style>