<template>
    <div class="invite-container">
        <div class="sub-header">
            <h1>Invite</h1>
        </div>

        <!-- ********************************** -->
        <!-- ******** Invite form ******** -->
        <!-- ********************************** -->
        <div class="invite-form">
            <textarea v-model="emails" class="emails-container" placeholder="enter a list of emails separated by commas"></textarea>
            <br>
            <br>
            <ButtonC @custom-event="sendEmailsRequest">Invite</ButtonC>
        </div>
    </div>
</template>

<script setup>
import ButtonC from '@/components/ButtonC.vue';
import { ref } from 'vue';

// ****************************************
// getting all the emails and then proccessing them
// ****************************************

const emails = ref("");
const emailsUrl = `http://localhost:8080/api/v1/invite`;
const confirms = ref("");

const sendEmailsRequest = async () =>{
    const emailsString = {
        emails: emails.value
    }

    console.log(emails.value);

    try {
            
        const res = await fetch(emailsUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(emailsString.value),
        })

        if(!res.ok){
            throw new Error(`Error: ${res.status}`)
        }
    
        const data = await res.json();
        confirms.value = data.message || "Emails sent successfully";

        emails.value = "";
    } catch (error) {
        confirms.value = error;
    }

}

</script>

<style scoped>
    .sub-header{
        display: flex;
        width: 100%;
        justify-content: space-around;
        background: rgb(240, 240, 240);
        align-items: center;
    }

    .invite-container{
        display: flex;
        flex-direction: column;
        align-items: center;
        width: fit-content;
        border: 1px solid lightgray;
        width: 100%;
        justify-content: center;
    }

    .invite-form{
        width: 90%;
        margin:2rem;

    }


    .emails-container{
        width: 100%;
        height:20rem;
        padding: 10px;
        border: 1px solid lightgray;
    }
</style>