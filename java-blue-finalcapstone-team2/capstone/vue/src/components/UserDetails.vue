<template>
  <div>
    <h1 class="name">Hello, {{ this.$store.state.user.username }}!</h1>
    <div id="manager" v-if="isManager">
      <button>Manage Band</button>
      <!-- this still doesn't link to anything -->
    </div>
    <h1>Followed bands:</h1>
    <div class="followed-bands">
      <router-link
        v-for="followedBand in followedBands"
        :key="followedBand.bandId"
        :to="{ name: 'band', params: { bandId: followedBand.bandId } }"
        ><img
          :src="followedBand.bandImage"
          class="followed-band-image"
        /><br />{{ followedBand.bandName }}</router-link
      >
    </div>
  </div>
</template>

<script>
import userService from "@/services/UserService.js";

export default {
  name: "user-details",
  props: {
    userId: Number,
  },
  computed: {
    user() {
      return this.$store.state.activeUser;
    },
    followedBands() {
      return this.$store.state.followed;
    },
    isManager() {
      return (
        this.$store.state.user.authorities != undefined &&
        this.$store.state.user.authorities.find(
          (authority) => authority.name == "ROLE_MANAGER"
        ) != undefined
      );
    },
  },
  methods: {},
  created() {
    userService
      .get(this.userId)
      .then((response) => {
        this.$store.commit("SET_USER", response.data);
      })
      .catch((error) => {
        if (error.response.status == 404) {
          this.$router.push({ name: "not-found" });
        }
      });
    userService.getFollowedBands().then((response) => {
      this.$store.commit("SET_FOLLOWED", response.data);
    });
  },
};
</script>
<style>
.followed-band-image {
  width: 200px;
}
.followed-bands {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
}
</style>
