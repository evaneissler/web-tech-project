<template>
    <div>
        <button class="button" @click="$emit('custom-event')">
            <slot></slot>
        </button>
    </div>
</template>

<script setup>

</script>

<style scoped>
    .button{
        padding: 5px;
        background:lightgray;
        border: none;
        font-weight: bold;
    }
</style>