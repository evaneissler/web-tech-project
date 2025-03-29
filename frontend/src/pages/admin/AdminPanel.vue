<script>
import CustomButton from '@/components/CustomButton.vue';
import HeaderAdmin from './admin-comp/HeaderAdmin.vue';


    export default{
        name:"AdminPanel",
        components:{
            HeaderAdmin,
            CustomButton
        }
    }
</script>

<template>
<HeaderAdmin />

<div class="admin-functions">
    <CustomButton>Create New Game Schedule</CustomButton>
    <CustomButton> Schedule Crew</CustomButton>
    <CustomButton>View Profile</CustomButton>
</div>

<hr>


</template>



<style scoped>
.admin-functions{
    display: flex;
    align-items: center;
    justify-content: space-evenly;
    margin: 0.5rem;
}


</style>