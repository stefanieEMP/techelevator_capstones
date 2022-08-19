<template>
  <div class="home">
    <img src="musicnotes.png" class="headerimg" />
    <h1>Welcome to Louisiana</h1>
    <img src="actualsaxmirror (1).png" class="titleimg1" /><img
      src="actualsax.png"
      class="titleimg2"
    />
    <h1 class="wave">Beat-Wave!</h1>

    <p class="mymessage">{{ message }}</p>
    <br />
    <img src="jazzband.png" class="footerimg" /><br />
    <router-link v-bind:to="{ name: 'bands' }"
      ><button class="big-button">Explore Bands</button></router-link
    >
    <router-link v-bind:to="{ name: 'venues' }"
      ><button class="big-button">Explore Venues</button></router-link
    >
    <router-link v-bind:to="{ name: 'shows' }"
      ><button class="big-button">Explore Shows</button></router-link
    ><br />
    <router-link v-bind:to="{ name: 'login' }"
      ><button class="big-button" v-if="loggedOut">Login</button></router-link
    >
    <router-link v-bind:to="{ name: 'register' }"
      ><button class="big-button" v-if="loggedOut">
        Create an Account
      </button></router-link
    >
  </div>
</template>

<!--
home page 
-->
<script>
export default {
  name: "home",
  data() {
    return {
      message:
        "Louisiana Beat Wave celebrates the birthplace of Jazz - New Orleans, Louisiana - and it's diverse music scene. Users can connect with their favorite bands and discover new ones to fall in love with. Bands can connect with their followers by sending message updates.",
    };
  },
  computed: {
    loggedOut() {
      return this.$store.state.token == "";
    },
  },
};
</script>
<style>
.home {
  text-align: center;
}
h1 {
  text-align: center;
  font-size: xx-large;
}
.mymessage {
  text-align: center;
  font-family: "Indie Flower", cursive;
  font-size: x-large;
}
.headerimg {
  display: block;
  margin: auto;
  margin-top: -100px;
  transform: scaleX(1.5);
}
.wave {
  margin-top: -20px;
  font-size: 70px;
  font-family: "Inspiration", cursive;
}
.titleimg2 {
  display: block;
  margin-left: 800px;
  margin-top: -152px;
  max-width: 8%;
  margin-bottom: -90px;
}
.titleimg1 {
  display: block;
  margin-left: 464px;
  margin-top: -70px;
  max-width: 8%;
}
.footerimg {
  display: block;
  margin: auto;
  margin-bottom: -45px;
}
</style>
