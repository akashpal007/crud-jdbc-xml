package com.j2ee.dao;

import java.sql.SQLException;
import java.util.List;

import com.j2ee.vo.ProductVO;

public interface ProductsDao {
	List<ProductVO> selectAll() throws SQLException;

	int insert(ProductVO productVO) throws SQLException;

	int update(ProductVO productVO) throws SQLException;

	int delete(ProductVO productVO) throws SQLException;

}
