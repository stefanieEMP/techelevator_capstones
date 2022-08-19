<template>
  <band-details v-bind:bandId="parseInt($route.params.bandId)" />
</template>

<script>
import BandDetails from "../components/BandDetails.vue";
export default {
  components: { BandDetails },
};
</script>

<style>
</style>
