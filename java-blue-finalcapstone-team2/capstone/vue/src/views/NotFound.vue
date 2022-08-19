<template>
  <div>
    <h1>Not Found</h1>
    <p>
      Return to Home
      <router-link v-bind:to="{ name: 'home' }"> click here </router-link>
    </p>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
