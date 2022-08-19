package com.techelevator.dao;

import com.techelevator.model.Genre;
import com.techelevator.model.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMessageDao implements MessageDao {

    JdbcTemplate jdbcTemplate;

    public JdbcMessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Message> getMessagesOfCurrentUser(int userId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * from MESSAGES JOIN user_messages USING (message_id) JOIN band USING (band_id) WHERE user_id ="+ userId+
                "ORDER BY message_timestamp DESC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Message message = mapRowToMessage(results);
            messages.add(message);
        }
        return messages;
    }

    @Override
    public Message getMessageById(int messageId) {
        Message message = new Message();
        String sql = "SELECT * FROM messages WHERE message_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, messageId);
        if (results.next()) {
            message = mapRowToMessage(results);
        }
        return message;
    }

    @Override
    public List<Message> sortMessagesByDate() {
        List<Message> messages = new ArrayList<>();
        String sql = "select message_id, message_body, message_timestamp, band_name  \n" +
                "from user_messages\n" +
                "join messages using(message_id)\n" +
                "join band using(band_id)\n" +
                "where user_id = 1\n" +
                "sort by message_timestamp;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Message message = mapRowToMessage(results);
            messages.add(message);
        }
        return messages;
    }

    public boolean deleteMessage(int userId, int msgId){
        String sql = "DELETE FROM user_messages WHERE user_id = ? AND message_id = ?;";
        try {
            jdbcTemplate.update(sql, userId, msgId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Message> sortMessagesByBandName() {
        List<Message> messages = new ArrayList<>();
        String sql = "select message_id, message_body, message_timestamp, band_name  \n" +
                "from user_messages\n" +
                "join messages using(message_id)\n" +
                "join band using(band_id)\n" +
                "where user_id = 1\n" +
                "sort by band_name;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Message message = mapRowToMessage(results);
            messages.add(message);
        }
        return messages;
    }

    @Override
    public boolean sendMessageToFollowers(Message newMessage, int MgrId, int bandId) {
        String sql = "begin transaction;\n" +
                "\n" +
                "INSERT INTO messages(message_body, message_timestamp, band_id)\n" +
                "VALUES (?, CURRENT_TIMESTAMP, " + bandId + ");\n" +
                "\n" +
                "INSERT INTO user_messages (user_id, message_id)\n" +
                "SELECT\n" +
                "\tuser_band.user_id,\n" +
                "\t(select message_id from messages where message_timestamp = CURRENT_TIMESTAMP)\n" +
                "FROM user_band\n" +
                "WHERE user_band.band_id = (select band_id from band where manager_id = " + MgrId + ");\n" +
                "\n" +
                "commit transaction;";
        try{
            jdbcTemplate.update(sql, newMessage.getMessageBody());
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Message mapRowToMessage(SqlRowSet rs) {
        Message message = new Message();

        message.setMessageId(rs.getInt("message_id"));
        message.setMessageBody(rs.getString("message_body"));
        message.setMessageTimestamp(rs.getDate("message_timestamp"));
        message.setBandId(rs.getInt("band_id"));
        try{
            message.setBandName(rs.getString("band_name"));
        } catch (Exception e){}
        return message;
    }
}
