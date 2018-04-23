package io.khasang.eshop.controller;

import io.khasang.eshop.entity.History;
import io.khasang.eshop.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    public HistoryService historyService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public History addHistory(@RequestBody History history) {
        return historyService.addHistory(history);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public History getHistoryById(@PathVariable(value = "id") String id) {
        return historyService.getHistoryById(Long.parseLong(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public History updateHistory(@RequestBody History history) {
        return historyService.updateHistory(history);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public History deleteHistory(@PathVariable(value = "id") String id) {
        return historyService.deleteHistory(Long.parseLong(id));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<History> getAllHistory() {
        return historyService.getAllHistory();
    }
}
