<template>
  <div>
    <h1>Venues</h1>
    <venue-list />
  </div>
</template>
<!--  
//need to be tied to venue list 
//clicking on venue takes you to its page 

-->
<script>
import VenueList from "../components/VenueList.vue";
export default {
  components: { VenueList },
};
</script>

<style>
section {
  height: 500px;
  margin: 50px;
}
h1 {
  font-weight: bold;
  margin: 10px;
}
</style>
