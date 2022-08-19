<template>
  <div>
    <user-details v-bind:userId="parseInt($route.params.userId)" />
    <h1 class="user-inbox">Inbox</h1>
    <div class="inbox-content">
      <message-list />
    </div>
    <br />
  </div>
</template>

<script>
import MessageList from "../components/MessageList.vue";
import UserDetails from "../components/UserDetails.vue";
export default {
  components: { UserDetails, MessageList },
};
</script>

<style>
.inbox-content {
  text-align: left;
}
#manager {
  text-align: center;
}
</style>
