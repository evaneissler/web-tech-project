<script>
import CustomButton from '@/components/CustomButton.vue';
import HeaderAdmin from './admin-comp/HeaderAdmin.vue';

    export default {
        name:"Crew Management",
        components: {
            HeaderAdmin,
            CustomButton
        }
    }
</script>

<template>
    <HeaderAdmin/>

    <div class="crew-container">
        <div class="btn-functions">
            <CustomButton>Invite</CustomButton>
            <CustomButton></CustomButton>
        </div>
    
    </div>
</template>



<style scoped>
    
</style>