<template>
  <div class="band-container">
    <div class="buttonnav">
      <router-link v-bind:to="{ name: 'new-band' }"
        ><button class="big-button">Create a New Band</button></router-link
      >
      <div class="adminhub" v-if="isAdmin">
        <router-link v-bind:to="{ name: 'genres' }"
          ><button class="big-button">Manage genres</button></router-link
        ><button class="big-button">Manage bands</button>
      </div>
    </div>
    <br />
    <div class="band">
      <div class="searchbar">
        <br />
        <input
          id="band-filter"
          class="input is-rounded"
          v-model="filter.bandName"
          name="byBand"
          type="text"
          placeholder="Search by band"
        /><br />
        <input
          class="input is-rounded"
          name="byGenre"
          type="text"
          placeholder="Search by genre"
        />
      </div>
      <div v-for="band in filteredList" :key="band.bandId">
        <div class="card">
          <router-link
            v-bind:to="{
              name: 'band',
              params: { bandId: band.bandId },
            }"
          >
            <h1 class="card-header-title is-size-2">
              {{ band.bandName }}
            </h1></router-link
          >
          <router-link
            v-bind:to="{
              name: 'band',
              params: { bandId: band.bandId },
            }"
            ><figure class="image">
              <img :src="band.bandImage" /></figure
          ></router-link>
          <div class="card-content">{{ band.bandDesc }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import bandService from "@/services/BandService.js";

export default {
  name: "band-list",
  setup() {},
  data() {
    return {
      filter: {
        bandId: 0,
        bandName: "",
        bandImage: "",
        bandDesc: "",
        Members: "",
        mgrId: "",
      },
    };
  },
  computed: {
    filteredList() {
      let bandList = this.$store.state.bands;
      if (this.filter.bandName != "") {
        bandList = bandList.filter((band) =>
          band.bandName
            .toLowerCase()
            .includes(this.filter.bandName.toLowerCase())
        );
      }
      return bandList;
    },
    isAdmin() {
      return (
        this.$store.state.user.authorities != undefined &&
        this.$store.state.user.authorities.find(
          (authority) => authority.name == "ROLE_ADMIN"
        ) != undefined
      );
    },
  },
  methods: {
    getBands() {
      bandService.list().then((response) => {
        this.$store.commit("SET_BANDS", response.data);
      });
    },
    getBandGenres(bandId) {
      bandService.getBandGenres(bandId).then((response) => {
        this.$store.commit("SET_BAND_GENRES", response.data);
      });
    },
  },
  created() {
    this.getBands();
  },
};
</script>

<style>
.band {
  margin: 0vw 7vw 0vw 7vw;
  border-radius: 12px;
  background-image: url(https://images.pexels.com/photos/3353055/pexels-photo-3353055.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2);
}
.card {
  background-color: #8b008b;
  border-radius: 12px;
  background-image: url("https://www.transparenttextures.com/patterns/asfalt-dark.png");
}
.card-header-title {
  color: white;
}
.card-content {
  color: white;
}
.image {
  display: block;
  margin: auto;
  max-width: 90%;
}
.searchbar {
  margin-left: 10vw;
  margin-right: 10vw;
}
.adminhub {
  display: block;
  margin-left: 700px;
  margin-top: -85px;
}
.buttonnav {
  margin-bottom: 20px;
}
</style>
