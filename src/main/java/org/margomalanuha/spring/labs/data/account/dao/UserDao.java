package org.margomalanuha.spring.labs.data.account.dao;

import org.margomalanuha.spring.labs.data.account.pojo.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {

    /*JdbcTemplate jdbcTemplate;

    public int create(User user) {
        String sql = "insert into users(id, name, surname, email, password, user_type_id, basket_id) values(" + user.getId() + "," + user.getName() + ","
                + user.getSurname() + ");";
        return jdbcTemplate.update(sql);
    }

    public int update(Rights right) {
        String sql = "update rights set title=" + right.getTitle() + " where id=" + right.getId() + ";";
        return jdbcTemplate.update(sql);
    }

    public int delete(int id) {
        String sql = "delete from rights where id=" + id + ";";
        return jdbcTemplate.update(sql);
    }

    public Rights getById(int id) {
        String sql = "select * from rights where id=" + id + ";";
        return jdbcTemplate.queryForObject(sql, Rights.class);
    }

    public List<Rights> getAll() {
        return jdbcTemplate.query("select * from rights", (rs, row) -> {
            Rights e = new Rights();
            e.setId(rs.getInt(1));
            e.setTitle(rs.getString(2));
            return e;
        });
    }*/
}
