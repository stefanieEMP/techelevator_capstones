import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import NewBand from '../views/NewBand.vue'
import Band from '../views/Band.vue'
import AllBands from '../views/AllBands.vue'
import Profile from '../views/Profile.vue'
import Venue from '../views/Venue.vue'
import NotFound from '../views/NotFound.vue'
import AllVenues from '../views/AllVenues.vue'
import AllShows from '../views/AllShows.vue'
import Show from '../views/Show.vue'
import Genres from '../views/Genres.vue'
import UpdateBand from '../views/UpdateBand.vue'
import NewMessage from '../views/NewMessage.vue'
//import { getOwnPropertySymbols } from 'core-js/core/object'
Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/bands",
      name: "bands",
      component: AllBands,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/bands/:bandId",
      name: "band",
      component: Band,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/bands/:bandId/edit",
      name: "update-band",
      component: UpdateBand,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/genres",
      name: "genres",
      component: Genres,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/new-band",
      name: "new-band",
      component: NewBand,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/bands/:bandId/new-message",  //how do we get the bandId?
      name: "new-message",
      component: NewMessage,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/profile",
      name: "profile",
      component: Profile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/profile/:userId",
      name: "profile",
      component: Profile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/venues",
      name: "venues",
      component: AllVenues,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/shows",
      name: "shows",
      component: AllShows,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/venues/:venueId",
      name: "venue",
      component: Venue,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/shows/:showId",
      name: "show",
      component: Show,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/notfound",
      name: "not-found",
      component: NotFound,
      requiresAuth: false
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
