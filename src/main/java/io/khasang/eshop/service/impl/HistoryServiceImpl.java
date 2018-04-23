package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.HistoryDao;
import io.khasang.eshop.entity.History;
import io.khasang.eshop.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryDao historyDao;

    @Override
    public List<History> getAllHistory() {
        return historyDao.getList();
    }

    @Override
    public History addHistory(History history) {
        return historyDao.add(history);
    }

    @Override
    public History getHistoryById(long id) {
        return historyDao.getById(id);
    }

    @Override
    public History updateHistory(History history) {
        return historyDao.update(history);
    }

    @Override
    public History deleteHistory(long id) {
        return historyDao.delete(getHistoryById(id));
    }
}
