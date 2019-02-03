package com.epam.polosmak.service;

import com.epam.polosmak.entity.Manufacturer;
import com.epam.polosmak.exception.DBException;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> getAllManufacturers() throws DBException;
}
