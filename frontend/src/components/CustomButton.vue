<template>
    <button class="custom-button" @click="$emit('custom-click')"> 
        <slot></slot>
    </button>
</template>

<script>
    export default {
        name: 'CustomButton'
    };
</script>

<style scoped>
    .custom-button{
        background:rgb(27, 150, 221);
        color:white;
        font-size: 1.2rem;
        padding: 10px;
        border:none;
        font-weight: bold;
        border-radius: 4px;
        /* margin-top: 1.5rem; */
    }

    .custom-button:hover{
        background: rgb(27, 111, 221)
    }

</style>