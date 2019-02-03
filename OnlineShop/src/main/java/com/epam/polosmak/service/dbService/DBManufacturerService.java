package com.epam.polosmak.service.dbService;

import com.epam.polosmak.dao.tranaction.TransactionManager;
import com.epam.polosmak.entity.Manufacturer;
import com.epam.polosmak.exception.DBException;
import com.epam.polosmak.repository.Repository;
import com.epam.polosmak.service.ManufacturerService;
import com.epam.polosmak.specification.Specification;
import com.epam.polosmak.specification.mysql.GetAllManufacturersMySqlSpecification;

import java.util.List;

public class DBManufacturerService implements ManufacturerService {
    private TransactionManager transactionManager;
    private Repository<Manufacturer> repository;


    public DBManufacturerService(TransactionManager transactionManager, Repository<Manufacturer> repository) {
        this.transactionManager = transactionManager;
        this.repository = repository;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() throws DBException {
        Specification specification = new GetAllManufacturersMySqlSpecification();
        return transactionManager.execute(connection -> repository.query(connection, specification)
        );
    }
}
