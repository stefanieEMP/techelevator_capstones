<template>
  <div>
    <form @submit.prevent="sendNewMessage()">
      <label for="textarea">Send New Message</label>
      <textarea
        class="textarea"
        id="message"
        placeholder="Enter Message"
        v-model="newMessage.messageBody"
      />
      <button v-on:click="cancel()">Cancel</button> |
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import messageService from "../services/MessageService";
export default {
  data() {
    return {
      newMessage: {
        messageBody: "",
      },
    };
  },

  methods: {
    sendNewMessage() {
      messageService
        .sendMessage(this.$store.state.activeBand.bandId, this.newMessage)
        .then((addedMessage) => {
          this.$store.commit("ADD_MESSAGE", addedMessage.data);
        }),
        this.$router.push({ name: "band" }); //will this go back to the correct spot??
    },
    cancel() {
      this.$router.push({ name: "band" });
    },
  },
};
</script>

<style>
</style>