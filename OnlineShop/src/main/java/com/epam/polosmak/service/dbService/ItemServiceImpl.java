package com.epam.polosmak.service.dbService;

import com.epam.polosmak.dao.ItemDAO;
import com.epam.polosmak.dao.tranaction.TransactionManager;
import com.epam.polosmak.entity.Product;
import com.epam.polosmak.service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private TransactionManager transactionManager;
    private ItemDAO itemDAO;

    public ItemServiceImpl(TransactionManager transactionManager, ItemDAO itemDAO) {
        this.transactionManager = transactionManager;
        this.itemDAO = itemDAO;
    }

    @Override
    public List<Product> getAllItems() {
        return transactionManager.execute(connection -> itemDAO.getAllItems());
    }

    @Override
    public Product getItemById(int id) {
        return transactionManager.execute(connection -> itemDAO.getItemById(connection, id));
    }
}