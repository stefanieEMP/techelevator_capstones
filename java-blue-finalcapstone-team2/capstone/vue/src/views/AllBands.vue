<template>
  <div>
    <band-list />
  </div>
</template>
<!--  
*need to be tied to venue list 
*as well as create new band option at top of band search 
*needs to have (search) user input on both band-venue 
*clicking on band-venue takes you to its page 
   -->
<script>
import BandList from "../components/BandList.vue";
export default {
  components: { BandList },
};
</script>

<style>
</style>
