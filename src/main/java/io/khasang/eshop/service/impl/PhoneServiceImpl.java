package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.PhoneDao;
import io.khasang.eshop.entity.Phone;
import io.khasang.eshop.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Phone addPhone(Phone phone) {
        return phoneDao.add(phone);
    }

    @Override
    public Phone deletePhone(long id) {
        return phoneDao.delete(getById(id));
    }

    @Override
    public Phone updatePhone(Phone phone) {
        return phoneDao.update(phone);
    }
}
