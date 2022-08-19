<template>
  <form class="new-band-form" @submit.prevent="addNewBand()">
    <div>
      <label for="name-input"> Band Name: </label>
      <input
        type="text"
        id="bandName"
        class="name-input"
        placeholder="Name"
        v-model="newBand.bandName"
        required
        autofocus
      />
    </div>
    <div>
      <label for="bandMember-input"> Members: </label>
      <input
        type="text"
        id="bandMember"
        class="bandMember-input"
        placeholder="Name"
        required
        v-model="newBand.members"
      />
    </div>

    <!-- <div>
      <label for="managerId-input"> Manager Id: </label>
      <input 
        type="number" 
        id="bandManager"
        class="managerId-input"
        placeholder="Your user ID"
        required
        v-model="newBand.mgrId"
      />
    </div> -->

    <div>
      <label for="bandImage-input"> Cover Photo: </label>
      <input
        type="img"
        id="bandImage"
        class="bandImage-input"
        placeholder="Cover Image"
        required
        v-model="newBand.bandImage"
      />
    </div>

    <div class="field">
      <label for="bandDesc-input">Who Dat:</label>
      <input
        type="text"
        id="bandDesc"
        class="bandDesc-input"
        placeholder="Description"
        required
        v-model="newBand.bandDesc"
      />
    </div>

    <div class="control">
      <button type="submit" class="button is-primary">SAVE</button>
    </div>
  </form>
</template>

<script>
import bandService from "../services/BandService";

export default {
  name: "add-band",
  data() {
    return {
      newBand: {
        bandName: "",
        bandImage: "",
        bandDesc: "",
        members: "",
        mgrId: 1,
      },
    };
  },
  methods: {
    addNewBand() {
      bandService.addBand(this.newBand).then((addedBand) => {
        this.$store.commit("ADD_BAND", addedBand.data);
      }),
        this.$router.push({ name: "home" });
    },
  },
};
</script>

<style></style>
