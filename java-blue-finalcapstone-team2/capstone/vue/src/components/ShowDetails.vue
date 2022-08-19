<template>
  <div class="show-details">
    <h1 class="name">{{ this.$store.state.activeShow.showTitle }}</h1>
    <div class="time">{{ show.showTime }}</div>
    <h1>Details:</h1>
    <h2 class="description">{{ show.showDesc }}</h2>
  </div>
</template>

<script>
import showService from "@/services/ShowService.js";

export default {
  name: "show-details",
  props: {
    showId: Number,
  },
  computed: {
    show() {
      return this.$store.state.activeShow;
    },
  },
  methods: {},
  created() {
    showService
      .get(this.showId)
      .then((response) => {
        this.$store.commit("SET_ACTIVE_SHOW", response.data);
        console.log("here");
        console.log(this.$store.state.activeShow);
        console.log(this.$store);
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
.show-details {
  margin: 10px;
  text-align: center;
}
.time {
  font-size: x-large;
}
.description {
  font-size: larger;
}
</style>