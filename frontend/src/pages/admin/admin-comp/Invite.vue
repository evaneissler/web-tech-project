<template>
    <div class="invite-container">
        <div class="instructions">
            <p>Separate each email by space</p>
        </div>
        <div class="input-container">
            <textarea
                name="" id=""
                v-model="emails.emailString"
                ></textarea>
        </div>
        <div class="inputbutton">
            <CustomButton @custom-click="handleInputedEmails">Invite</CustomButton>
        </div>
    </div>
</template>

<script setup>
import CustomButton from '@/components/CustomButton.vue';
import { ref } from 'vue';

const emails = ref(
    {emailString: ""}
)

function handleInputedEmails(){
    if(emails.value.emailString != ""){
        console.log(emails.value.emailString);
    } else {
        return;
    }
}





</script>

<style scoped>
    .invite-container{
        display: flex;
        flex-direction: column;
        width: 100%;
    }

    .invite-container > div{
        width: 100%;
        /* background: green; */
        margin-top:1rem;
        margin-bottom: 1rem;
        display: flex;
        justify-content: center;
    }

    .input-container{
        border-bottom: solid 1px lightgray;
        width: fit-content;
    }


    textarea{
        background: rgb(236, 236, 236);
        width: 80%;
        border:solid rgb(202, 202, 202) 1px;
        height: 30vh;
        border-radius: 10px;
        padding: 10px;
        
    }
</style>