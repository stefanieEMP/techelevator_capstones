<template>
  <div>
    <h1 class="name">{{ this.$store.state.activeVenue.venueName }}</h1>
    <div class="address">{{ venue.venueAddress }}</div>
    <h1>About:</h1>
    <h2 class="description">{{ venue.venueDesc }}</h2>
    <br />
    <iframe
      width="600"
      height="450"
      style="border: 0"
      loading="lazy"
      allowfullscreen
      :src="venue.venueMap"
    ></iframe>
  </div>
</template>

<script>
import venueService from "@/services/VenueService.js";

export default {
  name: "venue-details",
  props: {
    venueId: Number,
  },
  computed: {
    venue() {
      return this.$store.state.activeVenue;
    },
  },
  methods: {},
  created() {
    venueService
      .get(this.venueId)
      .then((response) => {
        this.$store.commit("SET_ACTIVE_VENUE", response.data);
      })
      .catch((error) => {
        if (error.response.status == 404) {
          this.$router.push({ name: "not-found" });
        }
      });
  },
};
</script>

<style>
div {
  margin: 10px;
}
.address {
  text-align: center;
  font-size: larger;
}
iframe {
  display: block;
  margin: auto;
}
</style>
