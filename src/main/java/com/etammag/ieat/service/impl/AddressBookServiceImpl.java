package com.etammag.ieat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.ieat.entity.AddressBook;
import com.etammag.ieat.mapper.AddressBookMapper;
import com.etammag.ieat.service.AddressBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 地址管理 服务实现类
 * </p>
 *
 * @author etammag
 * @since 2023-10-03
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Resource
    private AddressBookMapper addressBookMapper;

    @Override
    public AddressBook queryById(Long id) {
        return addressBookMapper.selectById(id);
    }

    @Override
    public AddressBook queryDefault(Long userId) {
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(AddressBook::getUserId, userId)
                .eq(AddressBook::getIsDefault, true);
        return addressBookMapper.selectOne(queryWrapper);
    }

    @Override
    public List<AddressBook> queryAllA(AddressBook addressBook) {
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(addressBook.getUserId() != null, AddressBook::getUserId, addressBook.getUserId());
        queryWrapper
                .orderByDesc(AddressBook::getUpdateTime);
        return addressBookMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void save(AddressBook addressBook) {
        addressBookMapper.insert(addressBook);
    }

    @Override
    @Transactional
    public void setDefault(Long id) {
        AddressBook addressBook = new AddressBook();
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(AddressBook::getIsDefault, true);
        addressBook.setIsDefault(false);
        addressBookMapper.update(addressBook, queryWrapper);
        addressBook.setId(id);
        addressBook.setIsDefault(true);
        addressBookMapper.updateById(addressBook);
    }
}
