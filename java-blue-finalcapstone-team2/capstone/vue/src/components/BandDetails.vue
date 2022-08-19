<template>
  <div class="band-page">
    <h1 class="name">{{ this.$store.state.activeBand.bandName }}</h1>
    <div class="manager-hub" v-if="isManager">
      <router-link v-bind:to="{ name: 'update-band' }"
        ><button class="big-button">Edit</button></router-link
      >
      |
      <button class="big-button">Add Show</button>
      |
      <router-link v-bind:to="{ name: 'new-message' }"
        ><button class="big-button">Message</button></router-link
      >
    </div>
    <div v-else></div>
    <figure class="this-image"><img :src="band.bandImage" /></figure>
    <div class="text-content">
      <h1>Who Dat</h1>
      <h2 class="description">{{ band.bandDesc }}</h2>
      <h1>Genres</h1>
      <ul class="genre-list">
        <li v-for="genre in genres" :key="genre.genreId">
          {{ genre.genreName }}
        </li>
      </ul>
      <h1>Members</h1>
      <h2 class="band-members">{{ band.members }}</h2>

      <div class="user-hub">
        <button class="big-button" @click="followBand" v-if="!isFollowing">
          Follow
        </button>
        <button
          class="big-button"
          @click="unfollowBand"
          v-if="isFollowing"
          id="unfollow"
        >
          Unfollow
        </button>
      </div>
    </div>
    <div id="photo-gallery">
      <h1>Photo Gallery</h1>
      <ul class="photo-gallery">
        <li v-for="photo in photos" :key="photo.photoId">
          <img :src="photo.photoImage" class="gallery-img" />
        </li>
      </ul>
    </div>
    <!-- <div>
        <router-link :to="{ name: '', params: { id: band.id } }"
          >Edit</router-link
        >
      </div> -->
  </div>
</template>

<script>
import bandService from "@/services/BandService.js";
import userService from "@/services/UserService.js";

export default {
  name: "band-details",
  props: {
    bandId: Number,
  },
  computed: {
    band() {
      return this.$store.state.activeBand;
    },
    photos() {
      return this.$store.state.activeBandPhotos;
    },
    genres() {
      return this.$store.state.activeBandGenres;
    },
    isFollowing() {
      return (
        this.$store.state.followed.find((band) => band.bandId == this.bandId) !=
        undefined
      );
    },
    isManager() {
      return this.$store.state.user.id == this.$store.state.activeBand.mgrId;
    },
  },
  methods: {
    followBand() {
      userService.followBand(this.bandId).then(() => {
        this.updateFollowedList();
      });
    },
    updateFollowedList() {
      userService.getFollowedBands().then((followedResponse) => {
        this.$store.commit("SET_FOLLOWED", followedResponse.data);
      });
    },
    updateBandGenres() {
      bandService.getBandGenres(this.bandId).then((response) => {
        this.$store.commit("SET_BAND_GENRES", response.data);
      });
    },
    updateBandPhotos() {
      bandService.getBandPhotos(this.bandId).then((response) => {
        this.$store.commit("SET_BAND_PHOTOS", response.data);
      });
    },
    unfollowBand() {
      userService.unfollowBand(this.bandId).then(() => {
        this.updateFollowedList();
      });
    },
  },
  created() {
    bandService
      .get(this.bandId)
      .then((response) => {
        this.$store.commit("SET_ACTIVE_BAND", response.data);
      })
      .catch((error) => {
        if (error.response.status == 404) {
          this.$router.push({ name: "not-found" });
        }
      });
    this.updateFollowedList();
    this.updateBandGenres();
    this.updateBandPhotos();
  },
};
</script>

<style>
.band-page {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
}
.manager-hub {
  text-align: center;
  margin-top: -10px;
}
.user-hub {
  text-align: center;
}
.description {
  text-align: center;
}
.band-members {
  text-align: center;
}
.genre-list {
  text-align: center;
}
.this-image img {
  display: block;
  margin: auto;
  max-width: 100%;
  border-radius: 12px;
}
.photo-gallery {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
}
.gallery-img {
  max-width: 90%;
}
.gallery-img:hover {
  transform: scale(1.3);
  transition: all 0.3s;
}
:root #unfollow {
  --backgroundColor: rgb(246, 209, 209);
  --colorShadeA: rgb(163, 106, 106);
  --colorShadeB: rgb(186, 121, 121);
  --colorShadeC: rgb(232, 150, 150);
  --colorShadeD: rgb(232, 187, 187);
  --colorShadeE: rgb(255, 205, 205);
}
#photo-gallery {
  grid-column: span 2;
}
.genre-list {
  text-align: center;
}
</style>
