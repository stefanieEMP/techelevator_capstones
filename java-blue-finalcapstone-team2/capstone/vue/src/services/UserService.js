import axios from 'axios';

export default {
    get(userId) {
        return axios.get(`/user/${userId}`);
    },
    updateUser(user) {
        return axios.put(`/user/${user.id}`, user);
    },
    followBand(bandId) {
        return axios.post(`/bands/${bandId}/follow`);
    },
    getFollowedBands() {
        return axios.get('/following');
    },
    unfollowBand(bandId) {
        return axios.delete(`/bands/${bandId}/unfollow`);
    }

}
