package io.khasang.eshop.service.impl;

import io.khasang.eshop.dao.AnonymousDao;
import io.khasang.eshop.entity.Anonymous;
import io.khasang.eshop.service.AnonymousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("anonymousService")
public class AnonymousServiceImpl implements AnonymousService {
    @Autowired
    private AnonymousDao anonymousDao;

    @Override
    public List<Anonymous> getAllAnonymous() {
        return anonymousDao.getList();
    }

    @Override
    public Anonymous addAnonymous(Anonymous anonymous) {
        return anonymousDao.add(anonymous);
    }

    @Override
    public Anonymous updateAnonymous(Anonymous anonymous) {
        return anonymousDao.update(anonymous);
    }

    @Override
    public Anonymous patchAnonymous(Anonymous anonymous) {
        return anonymousDao.update(anonymous);
    }

    @Override
    public Anonymous delete(long id) {
        return anonymousDao.delete(getById(id));
    }

    @Override
    public Anonymous getById(long id) {
        return anonymousDao.getById(id);
    }
}
