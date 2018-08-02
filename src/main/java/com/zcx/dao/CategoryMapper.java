package com.zcx.dao;

import com.zcx.model.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/08/01 10:17
 * @Description:
 */
@Repository("CategoryMapper")
public interface CategoryMapper {
    List<Category> findCategoryList();
    Category findCateById(@Param("id") String id);
}
