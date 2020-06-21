package com.j2ee.dao;

import java.sql.SQLException;
import java.util.List;

import com.j2ee.vo.ProductLineVO;

public interface ProductlinesDao {

	List<ProductLineVO> selectAll() throws SQLException;

	int insert(ProductLineVO productLineVO) throws SQLException;

	int update(ProductLineVO productLineVO) throws SQLException;

	int delete(ProductLineVO productLineVO) throws SQLException;
}
