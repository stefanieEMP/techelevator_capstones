<template>
  <venue-details v-bind:venueId="parseInt($route.params.venueId)" />
</template>

<!--
//uses venueDetails
<iframe width="600" height="450" style="border:0" loading="lazy" allowfullscreen src="https://www.google.com/maps/embed/v1/place?q=place_id:ChIJ9cCHcw2mIIYRV7dOHIoKO4Q&key=AIzaSyBRq0tBrXJcI2Tff8JxVYGmR58CimOPI7o"></iframe>
-->
<script>
import VenueDetails from "../components/VenueDetails.vue";
export default {
  components: { VenueDetails },
};
</script>

<style></style>
