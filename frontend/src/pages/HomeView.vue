<template>
    <div class="maincontainer">
        <div class="form-container">
            <h2 class="mb-10 font-extrabold text-4xl">Login</h2>
            <form class="loginform"  @submit.prevent="handleLogin">
                <div class="form-group">
                    <label for="username">Username:</label>

                    <input v-model="user_login_data.username"
                    type="text" id="username"
                    name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                
                    <input v-model="user_login_data.password"
                    type="password"
                    id="password"
                    name="password" required>
                </div>
                <CustomButton>Login</CustomButton>
            </form>
        </div>
    </div>
</template>

<script setup>
import "@/assets/base.css";
import CustomButton from '@/components/CustomButton.vue';
import { ref } from "vue";
import { useRouter } from "vue-router";

const routerRedirect = useRouter();

// getting user input
const user_login_data = ref({
    username:"",
    password:""
})

const login_status = ref("");

const handleLogin = async()=>{
    login_status.value = 'pending';

    try {
        const serverRespoonse = await axios.post("/our server",{
            username:user_login_data.value.username,
            password: user_login_data.value.password
        });

        if(serverRespoonse.status === 200){
            // when logged in
    
        }
        
    } catch (error) {
        login_status.value = "failed to signin";

    }

    // for now just going to the panel as needed
    routerRedirect.push("/admins-panel")
}



</script>

<style scoped>

        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }
        .form-group label {
            flex: 1;
            margin-right: 10px;
        }
        .form-group input {
            flex: 2;
            padding: 2px;
            font-size: 1.2rem;

        }
        .form-container {
            display: flex;
            flex-direction: column;
            align-content: center;
            justify-content: center;
            align-items: center;
            margin: auto;
            padding: 10px;
        }

        .maincontainer{
            display: flex;
            height: 100vh;
            width: 100vw;
            background:var(--main-background)
        }

        .loginform{
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
</style>