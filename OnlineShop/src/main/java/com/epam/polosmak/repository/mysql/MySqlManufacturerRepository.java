package com.epam.polosmak.repository.mysql;

import com.epam.polosmak.JdbcTemplate;
import com.epam.polosmak.entity.Manufacturer;
import com.epam.polosmak.mapper.ManufacturerMapper;
import com.epam.polosmak.mapper.Mapper;
import com.epam.polosmak.repository.Repository;
import com.epam.polosmak.specification.Specification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySqlManufacturerRepository implements Repository<Manufacturer> {
    private static final Logger LOG = LoggerFactory.getLogger(MySqlManufacturerRepository.class);

    private static final String INSERT_NEW_MANUFACTURER = "INSERT INTO `onlineshop`.`manufacturer` (`name`) VALUES (?)";
    private static final String DELETE_MANUFACTURER_BY_ID = "DELETE FROM `onlineshop`.`manufacturer` WHERE `id`= ?";
    private static final String UPDATE_MANUFACTURER_BY_ID = "UPDATE `onlineshop`.`manufacturer` SET `name`= ? WHERE `id`= ?";

    private Mapper<Manufacturer> mapper;
    private JdbcTemplate<Manufacturer> jdbcTemplate;

    public MySqlManufacturerRepository() {
        this.mapper = new ManufacturerMapper();
        jdbcTemplate = new JdbcTemplate<>(mapper);
    }

    @Override
    public void save(Connection connection, Manufacturer manufacturer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_MANUFACTURER)) {
            int k = 1;
            preparedStatement.setString(k++, manufacturer.getName());
            preparedStatement.executeUpdate();
            LOG.info("Saved manufacturer in DB : manufacturer --> {}", manufacturer);
        }
    }

    @Override
    public void delete(Connection connection, Manufacturer manufacturer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MANUFACTURER_BY_ID)) {
            int k = 1;
            preparedStatement.setInt(k++, manufacturer.getId());
            preparedStatement.executeUpdate();
            LOG.info("Deleted manufacturer in DB : manufacturer --> {}", manufacturer);
        }
    }

    @Override
    public void update(Connection connection, Manufacturer manufacturer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MANUFACTURER_BY_ID)) {
            int k = 1;
            preparedStatement.setString(k++, manufacturer.getName());
            preparedStatement.setInt(k++, manufacturer.getId());
            preparedStatement.executeUpdate();
            LOG.info("Updated manufacturer in DB : manufacturer --> {}", manufacturer);
        }
    }

    @Override
    public List<Manufacturer> query(Connection connection, Specification specification) throws SQLException {
        return jdbcTemplate.getAllBySpecification(connection, specification);
    }

    @Override
    public int getCount(Connection connection, Specification specification) throws SQLException {
        return jdbcTemplate.getCountBySpecification(connection, specification);
    }
}
