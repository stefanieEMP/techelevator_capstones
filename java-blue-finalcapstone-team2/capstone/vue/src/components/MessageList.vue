<template>
  <div>
    <button
      v-on:click="sortByDate()"
      class="button is-info is-rounded"
      id="sortDate"
    >
      Sort By Date
    </button>
    <button
      v-on:click="sortByBand()"
      class="button is-success is-rounded"
      id="sortBand"
    >
      Sort by Band
    </button>
    <div
      class="message is-warning is-large"
      v-for="message in this.$store.state.messages"
      v-bind:key="message.messageId"
    >
      <h5 class="message-header">
        {{ message.bandName }}<i class="fa-solid fa-envelope"></i>
      </h5>

      <h3 class="message-body">"{{ message.messageBody }}"</h3>

      <h4>{{ message.messageTimestamp }}</h4>

      <button
        v-on:click="deleteMessage(message.messageId)"
        class="button is-danger"
      >
        Delete
      </button>
    </div>
  </div>
</template>

<script>
import MessageService from "../services/MessageService";
import messageService from "../services/MessageService";
export default {
  name: "message-list",
  data() {
    return { sb: true, sd: true };
  },
  methods: {
    getMessages() {
      messageService.messageList().then((response) => {
        this.$store.commit("SET_MESSAGES", response.data);
      });
    },
    sortByBand() {
      if (!this.sb) {
        this.$store.state.messages.sort(this.compareBandDesc);
        this.sb = !this.sb;
      } else {
        this.$store.state.messages.sort(this.compareBand);
        this.sb = !this.sb;
      }
    },
    compareBand(a, b) {
      //Ascending
      if (a.bandName.toLowerCase() < b.bandName.toLowerCase()) {
        return -1;
      }
      if (a.bandName.toLowerCase() > b.bandName.toLowerCase()) {
        return 1;
      }
      return 0;
    },
    compareBandDesc(a, b) {
      if (a.bandName.toLowerCase() > b.bandName.toLowerCase()) {
        return -1;
      }
      if (a.bandName.toLowerCase() < b.bandName.toLowerCase()) {
        return 1;
      }
      return 0;
    },

    sortByDate() {
      if (!this.sd) {
        this.$store.state.messages.sort(this.compareDateDesc);
        this.sd = !this.sd;
      } else {
        this.$store.state.messages.sort(this.compareDate);
        this.sd = !this.sd;
      }
    },

    compareDate(a, b) {
      //Ascending
      if (a.messageTimestamp < b.messageTimestamp) {
        return -1;
      }
      if (a.messageTimestamp > b.messageTimestamp) {
        return 1;
      }
      return 0;
    },
    compareDateDesc(a, b) {
      //Descending
      if (a.messageTimestamp > b.messageTimestamp) {
        return -1;
      }
      if (a.messageTimestamp < b.messageTimestamp) {
        return 1;
      }
      return 0;
    },
    deleteMessage(messageId) {
      MessageService.deleteMessage(
        this.$store.state.user.userId,
        messageId
      ).then((response) => {
        if (response.status === 200) {
          this.getMessages();
        }
      });
    },
  },
  created() {
    this.getMessages();
  },
  computed: {},
};
</script>

<style></style>
