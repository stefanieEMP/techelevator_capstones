import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    bands: [],
    genres: [],
    activeBand: {
      bandId: 0,
      bandName: "",
      bandImage: "",
      bandDesc: "",
      Members: "",
      mgrId: "",

    },
    activeBandPhotos: [],
    activeBandGenres: [],
    venues: [],
    activeVenue: {
      venueId: 0,
      venueName: "",
      venueAddress: "",
      venueDesc: "",
      venueMap: ""
    },
    messages: [],
    activeMessage: {
      messageId: 0,
      messageBody: "",
      messageTimestamp: "",
      bandId: "",
      bandName: ""
    },
    shows: [],
    activeShow: {
      showId: 0,
      showTime: 0,
      showTitle: "",
      showDesc: "",
      venueId: 0
    },
    followed: [],
  },

  mutations: {
    SET_BANDS(state, data) {
      state.bands = data;
    },
    SET_ACTIVE_BAND(state, data) {
      state.activeBand = data;
    },
    SET_BAND_SHOW(state, data) {
      state.shows = data;
    },
    SET_GENRES(state, data) {
      state.genres = data;
    },
    SET_VENUES(state, data) {
      state.venues = data;
    },
    SET_ACTIVE_VENUE(state, data) {
      state.activeVenue = data;
    },
    SET_SHOWS(state, data) {
      state.shows = data;
    },
    SET_ACTIVE_SHOW(state, data) {
      state.activeShow = data;
    },
    SET_VENUE_SHOW(state, data) {
      state.shows = data;
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    ADD_BAND(state, band) {
      state.bands.push(band)
    },
    UPDATE_BAND(state, band) {
      state.bands.push(band)
    },
    SET_MESSAGES(state, data) {
      state.messages = data;
    },
    SET_FOLLOWED(state, followed) {
      state.followed = followed;
    },
    SET_BAND_GENRES(state, genres) {
      state.activeBandGenres = genres;
    },
    ADD_MESSAGE(state, message) {
      state.messages.push(message)
    },
    SET_BAND_PHOTOS(state, photos) {
      state.activeBandPhotos = photos;
    }
  }
})
