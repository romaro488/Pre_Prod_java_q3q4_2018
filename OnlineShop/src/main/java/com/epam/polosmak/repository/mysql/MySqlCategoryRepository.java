package com.epam.polosmak.repository.mysql;

import com.epam.polosmak.JdbcTemplate;
import com.epam.polosmak.entity.Category;
import com.epam.polosmak.mapper.CategoryMapper;
import com.epam.polosmak.mapper.Mapper;
import com.epam.polosmak.repository.Repository;
import com.epam.polosmak.specification.Specification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySqlCategoryRepository implements Repository<Category> {
    private static final Logger LOG = LoggerFactory.getLogger(MySqlCategoryRepository.class);

    private static final String INSERT_NEW_CATEGORY = "INSERT INTO `onlineshop`.`category` (`name`) VALUES (?)";
    private static final String DELETE_CATEGORY_BY_ID = "DELETE FROM `onlineshop`.`category` WHERE `id`= ?";
    private static final String UPDATE_CATEGORY_BY_ID = "UPDATE `onlineshop`.`category` SET `name`= ? WHERE `id`= ?";

    private Mapper<Category> mapper;
    private JdbcTemplate<Category> jdbcTemplate;

    public MySqlCategoryRepository() {
        this.mapper = new CategoryMapper();
        jdbcTemplate = new JdbcTemplate<>(mapper);
    }

    @Override
    public void save(Connection connection, Category category) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_NEW_CATEGORY)) {
            int k = 1;
            pstmt.setString(k++, category.getName());
            pstmt.executeUpdate();
            LOG.info("Saved category in DB : category --> {}", category);
        }
    }

    @Override
    public void delete(Connection connection, Category category) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(DELETE_CATEGORY_BY_ID)) {
            int k = 1;
            pstmt.setInt(k++, category.getId());
            pstmt.executeUpdate();
            LOG.info("Deleted category in DB : category --> {}", category);
        }
    }

    @Override
    public void update(Connection connection, Category category) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_CATEGORY_BY_ID)) {
            int k = 1;
            pstmt.setString(k++, category.getName());
            pstmt.setInt(k++, category.getId());
            pstmt.executeUpdate();
            LOG.info("Updated category in DB : category --> {}", category);
        }
    }

    @Override
    public List<Category> query(Connection connection, Specification specification) throws SQLException {
        return jdbcTemplate.getAllBySpecification(connection, specification);
    }

    @Override
    public int getCount(Connection connection, Specification specification) throws SQLException {
        return jdbcTemplate.getCountBySpecification(connection, specification);
    }
}
