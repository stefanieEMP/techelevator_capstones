import axios from 'axios';

export default {

    venueShowList() {
        return axios.get('/venue');
    },

    getShowVenue(venueId) {
        return axios.get(`/venue/${venueId}`);
    },
    addVenueShow(show) {
        return axios.post('/venue', show);
    },
    updateVenueShow(show) {
        return axios.put(`/venue/${show.id}`, show);
    },
    deleteVenueShow(showId) {
        return axios.delete(`/venue/${showId}`);
    },
    bandShowList() {
        return axios.get('/bands');
    },

    getBandVenue(bandId) {
        return axios.get(`/bands/${bandId}`);
    },
    addBandShow(show) {
        return axios.post('/bands', show);
    },
    updateBandShow(show) {
        return axios.put(`/bands/${show.id}`, show);
    },
    deleteBandShow(showId) {
        return axios.delete(`/bands/${showId}`);
    },
    list() {
        return axios.get('/shows');
    },
    get(showId) {
        return axios.get(`/shows/${showId}`);
    },
}
