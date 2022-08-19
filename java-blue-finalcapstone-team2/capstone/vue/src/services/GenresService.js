import axios from 'axios';

export default {

    getByBand(bandName) {
        return axios.get(`/genres/band/${bandName}`);
    },

    get(genreId) {
        return axios.get(`/genres/${genreId}`);
    },
    list() {
        return axios.get('/genres');
    },
    addGenre(genre) {
        return axios.post('/genres', { genreName: genre });
    },
    deleteGenre(genreId) {
        return axios.delete(`/genres/${genreId}`);
    }
}