package io.khasang.eshop.service;

import io.khasang.eshop.entity.History;

import java.util.List;

public interface HistoryService {
    /**
     * method for receiving all history from DB
     *
     * @return List of all history
     */
    List<History> getAllHistory();

    /**
     * method for add history
     *
     * @param history = new history
     * @return created history
     */
    History addHistory(History history);

    /**
     * method for receiving specify history by id
     *
     * @param id = history's id
     * @return history by id
     */
    History getHistoryById(long id);

    /**
     * method for update history
     *
     * @param history = history order
     * @return history order
     */
    History updateHistory(History history);

    /**
     * method for delete specify order by id
     *
     * @param id = history's id
     * @return history by id
     */
    History deleteHistory(long id);
}
