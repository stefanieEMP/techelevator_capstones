<template>
  <div>
    <div class="searchbar">
      <input
        class="input is-rounded"
        v-model="filter.showTitle"
        name="byShow"
        type="text"
        placeholder="Search by show"
      />
    </div>
    <div class="show">
      <div v-for="show in filteredList" :key="show.showId">
        <div class="card">
          <router-link
            class="card-header"
            v-bind:to="{ name: 'show', params: { showId: show.showId } }"
            ><h1>{{ show.showTitle }}</h1></router-link
          >
          <h2 class="card-header">Show Time: {{ show.showTime }}</h2>
          <h2 class="card-content">{{ show.showDesc }}</h2>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showService from "@/services/ShowService.js";

export default {
  name: "show-list",
  data() {
    return {
      filter: {
        showId: 0,
        showTime: 0,
        showTitle: "",
        showDesc: "",
        venueId: 0,
      },
    };
  },
  computed: {
    filteredList() {
      let showList = this.$store.state.shows;
      if (this.filter.showTitle != "") {
        showList = showList.filter((show) =>
          show.showTitle
            .toLowerCase()
            .includes(this.filter.showTitle.toLowerCase())
        );
      }
      return showList;
    },
  },
  methods: {
    getShows() {
      showService.list().then((response) => {
        this.$store.commit("SET_SHOWS", response.data);
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
.show {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
}
.card-header {
  font-size: 30px;
}
.card-content {
  font-size: 30px;
}
</style>
