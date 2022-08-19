import axios from 'axios';

export default {

    list() {
        return axios.get('/venues');
    },

    get(venueId) {
        return axios.get(`/venues/${venueId}`);
    },
    addVenue(venue) {
        return axios.post('/venues', venue);
    },
    updateVenue(venue) {
        return axios.put(`/venues/${venue.id}`, venue);
    },
    deleteVenue(venueId) {
        return axios.delete(`/venues/${venueId}`);
    },
}
