package com.etammag.ieat.service;

import com.etammag.ieat.entity.AddressBook;

import java.util.List;

/**
 * <p>
 * 地址管理 服务类
 * </p>
 *
 * @author etammag
 * @since 2023-10-03
 */
public interface AddressBookService {

    AddressBook queryById(Long id);

    AddressBook queryDefault(Long userId);

    List<AddressBook> queryAllA(AddressBook addressBook);

    void save(AddressBook addressBook);

    void setDefault(Long id);

}
