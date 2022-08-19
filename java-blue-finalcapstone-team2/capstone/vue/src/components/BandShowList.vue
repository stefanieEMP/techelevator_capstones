<template>
  <div class="show-container">
    <div class="show">
      <tbody>
        <tr v-for="show in this.$store.state.shows" v-bind:key="show.bandName">
          <div class="container">
            <h2 class="title">{{ show.showTitle }}</h2>
            <h2 class="Time">{{ show.showTime }}</h2>
            <h2 class="description">{{ show.showDesc }}</h2>
            <h2 class="address">{{ venue.venueAddress }}</h2>
          </div>
        </tr>
      </tbody>
    </div>
  </div>
</template>

<script>
import showService from "@/services/ShowService.js";

export default {
  name: "show-list",
  methods: {
    getShows() {
      showService.bandShowList().then((response) => {
        this.$store.commit("SET_BAND_SHOW", response.data);
      });
    },
    deleteShow() {},
  },
  created() {
    this.getShows();
  },
};
</script>

<style>
.container {
  padding: 10px;
}
</style>
