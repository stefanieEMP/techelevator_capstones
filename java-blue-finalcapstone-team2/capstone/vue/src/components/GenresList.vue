<template>
  <div class="genres-container">
    <ul>
      <li v-for="genre in this.$store.state.genres" :key="genre.genreId">
        <i
          class="fa-solid fa-circle-minus"
          @click="deleteGenre(genre.genreId)"
        ></i>
        {{ genre.genreName }}
      </li>
    </ul>
    <form @submit.prevent="addGenre">
      <div class="error" v-if="genreError">{{ genreError }}</div>
      <input type="text" v-model="genreName" /><i
        class="fa-solid fa-circle-plus"
        @click="addGenre"
      ></i>
    </form>
  </div>
</template>

<script>
import genreService from "@/services/GenresService.js";

export default {
  name: "genre-list",
  data() {
    return {
      genreName: "",
      genreError: undefined,
    };
  },
  methods: {
    getGenres() {
      genreService.list().then((response) => {
        this.$store.commit("SET_GENRES", response.data);
      });
    },
    addGenre() {
      if (
        this.$store.state.genres.find(
          (genre) =>
            genre.genreName.toLowerCase() == this.genreName.toLowerCase()
        ) == undefined
      ) {
        this.genreError = undefined;
        genreService
          .addGenre(this.genreName)
          .then(() => {
            this.genreName = "";
            this.getGenres();
          })
          .catch((error) => {
            if (error.response.status == 400) {
              this.genreError = error.response.data;
            }
          });
      } else {
        this.genreError = "Genre names must be unique";
      }
    },
    deleteGenre(genreId) {
      genreService.deleteGenre(genreId).then(() => {
        this.getGenres();
      });
    },
  },
  created() {
    this.getGenres();
  },
};
</script>

<style>
</style>