package com.zcx.service.impl;

import com.zcx.dao.CategoryMapper;
import com.zcx.model.Category;
import com.zcx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/08/01 10:22
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findCategoryList() {
        return categoryMapper.findCategoryList();
    }

}
