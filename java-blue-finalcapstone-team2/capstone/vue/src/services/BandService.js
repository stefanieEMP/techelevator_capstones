import axios from 'axios';

export default {

    list() {
        return axios.get('/bands');
    },

    get(bandId) {
        return axios.get(`/bands/${bandId}`);
    },
    addBand(band) {
        return axios.post('/bands/newband', band);
    },
    updateBand(band) {
        return axios.put(`/bands/${band.id}`, band);
    },
    deleteBand(bandId) {
        return axios.delete(`/bands/${bandId}`);
    },
    getBandGenres(bandId) {
        return axios.get(`/genres/bands/${bandId}`);
    },
    getBandPhotos(bandId) {
        return axios.get(`/bands/${bandId}/photos`);
    }
}
