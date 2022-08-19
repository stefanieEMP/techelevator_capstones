<template>
  <form class="update-band-form" @submit.prevent="updateBand()">
    <div>
      <label for="name-input"> Band Name: </label>
      <input
        type="text"
        id="bandName"
        class="name-input"
        placeholder="Name"
        v-model="updatedBand.bandName"
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
        v-model="updatedBand.members"
      />
    </div>

    <div>
      <label for="managerId-input"> Manager Id: </label>
      <input
        type="number"
        id="bandManager"
        class="managerId-input"
        placeholder="New user ID"
        required
        v-model="updatedBand.mgrId"
      />
    </div>

    <div>
      <label for="bandImage-input"> Cover Photo: </label>
      <input
        type="img"
        id="bandImage"
        class="bandImage-input"
        placeholder="Cover Image"
        required
        v-model="updatedBand.bandImage"
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
        v-model="updatedBand.bandDesc"
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
  name: "update-band",
  data() {
    return {
      updatedBand: {
        bandName: "",
        bandImage: "",
        bandDesc: "",
        members: "",
        mgrId: "",
      },
    };
  },
  methods: {
    updateBand() {
      const updatedBand = {
        id: this.$route.params.bandId,
        bandName: this.updatedBand.bandName,
        bandImage: this.updatedBand.bandImage,
        bandDesc: this.updatedBand.bandDesc,
        members: this.updatedBand.members,
        mgrId: this.updatedBand.mgrId,
      };
      bandService.updateBand(updatedBand), this.$router.push({ name: "bands" });
    },
  },
};
</script>

<style></style>
