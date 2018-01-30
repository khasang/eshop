package io.khasang.eshop.servise.impl;

import io.khasang.eshop.dao.PhoneDao;
import io.khasang.eshop.entity.Phone;
import io.khasang.eshop.servise.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service("phoneService")
public class PhoneServiceImpl implements PhoneService{

    @Autowired
    private PhoneDao phoneDao;

    @Override
    public List<Phone> allPhone() {
        return phoneDao.getList();
    }

    @Override
    public Phone getById(long id) {
        return phoneDao.getById(id);
    }
}
