<template>
  <show-details v-bind:showId="parseInt($route.params.showId)" />
</template>

<script>
import ShowDetails from "../components/ShowDetails.vue";
export default {
  components: { ShowDetails },
};
</script>

<style></style>