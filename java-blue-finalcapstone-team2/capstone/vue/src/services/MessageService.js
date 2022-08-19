import axios from 'axios';

export default {

    messageList() {
        return axios.get('/inbox');
    },
    get(messageId) {
        return axios.get(`/messages/${messageId}`);
    },
    sendMessage(bandId, message) {
        return axios.post(`/bands/${bandId}/newmessage`, message); 
    },
    deleteMessage(user, messageId){
        return axios.delete(`/inbox/${messageId}`, user);
    }
}
