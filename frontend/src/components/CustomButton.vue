<template>
    <button type="submit" class="custom-button" @click="$emit('custom-click')"> 
        <slot></slot>
    </button>
</template>

<script>
    import "@/assets/base.css";
    export default {
        name: 'CustomButton'
    };
</script>

<style scoped>
    .custom-button{
        background:var(--tcu-purple-color);
        color:white;
        font-size: 1.2rem;
        padding: 10px;
        border:none;
        font-weight: bold;
        border-radius: 4px;
        /* margin-top: 1.5rem; */
    }

    .custom-button:hover{
        background: white;
        color:black;
    }

</style>