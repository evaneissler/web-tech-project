<template>
    <div class="available-form">
        <form >
            <div>
                <h1>Submit Availability</h1>
            </div>
            <div class="data-container">
                <label for="gameId">Game ID</label>
                <select name="" id="">

                </select>
            </div>
            <ButtonC>Submit</ButtonC>
        </form>
    </div>
</template>

<script setup>
import ButtonC from '@/components/ButtonC.vue';
import { ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const id = route.params.id;
const availability = ref(false);
const comment = ref("");
const gameId = id;


</script>

<style scoped>

</style>