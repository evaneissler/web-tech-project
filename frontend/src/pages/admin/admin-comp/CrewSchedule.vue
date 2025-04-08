<template>
    <div>
        list of all game Schedule

            game
                assign user to a game
    </div>
</template>

<script setup>
// make request to the backend
const url=  "http://localhost:8080/api/v1/gameSchedule/games"


fetch(url)
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok ' + response.statusText);
    }
    return response.json();
  })
  .then(data => {
    console.log(data);
  })
  .catch(error => {
    console.error('There was a problem with the fetch operation:', error);
  });



</script>

<style scoped>

</style>