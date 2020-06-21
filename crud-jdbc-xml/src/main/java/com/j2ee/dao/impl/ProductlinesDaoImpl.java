package com.j2ee.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j2ee.dao.ProductlinesDao;
import com.j2ee.vo.ProductLineVO;

public class ProductlinesDaoImpl implements ProductlinesDao {
	
	private String SELECT_ALL = "SELECT * FROM productlines";
	private String INSERT_ALL = "INSERT INTO productlines (productLine, textDescription) VALUES (?, ?)";
	private String UPDATE_BY_ID = "UPDATE productlines SET productLine = ?, textDescription = ? WHERE productLine = ?;";
	private String DELETE_BY_ID = "DELETE FROM productlines WHERE productLine = ?";
	
	private static Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product?"+ "autoReconnect=true&useSSL=false", "root", "root");
		} catch (Exception e) {
			throw new SQLException(e + "  DB Connection Problem");
		}
		return con;
	}
	
	@Override
	public List<ProductLineVO> selectAll() throws SQLException {
		List<ProductLineVO> productLineVOs = null;
		Connection con = getConnection();
		if (con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL);
				ResultSet set = preparedStatement.executeQuery();
				productLineVOs = new ArrayList<ProductLineVO>();
				while (set.next()) {
					ProductLineVO productLineVO = new ProductLineVO();
					productLineVO.setProductLine(set.getString("productLine"));
					productLineVO.setTextDescription(set.getString("textDescription"));
					productLineVOs.add(productLineVO);
				}
			}catch (Exception e) {
				throw new SQLException(e + "  CAN NOT FETCH ProductLine informations");
			}
		}
		return productLineVOs;
	}

	@Override
	public int insert(ProductLineVO productLineVO) throws SQLException {
		int result = -1;
		Connection con = getConnection();
		if (con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(INSERT_ALL);
				preparedStatement.setString(1, productLineVO.getProductLine());
				preparedStatement.setString(2, productLineVO.getTextDescription());
				result = preparedStatement.executeUpdate();
			}catch (Exception e) {
				throw new SQLException(e + "  Not able to Insert");
			}
		}
		return result;
	}

	@Override
	public int update(ProductLineVO productLineVO) throws SQLException {
		int result = -1;
		Connection con = getConnection();
		if (con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(UPDATE_BY_ID);
				preparedStatement.setString(1, productLineVO.getProductLine());
				preparedStatement.setString(2, productLineVO.getTextDescription());
				preparedStatement.setString(3, productLineVO.getProductLine());

				result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				throw new SQLException(e + "  Not able to Update");
			}
		}
		return result;
	}

	@Override
	public int delete(ProductLineVO productLineVO) throws SQLException {
		int result = -1;
		Connection con = getConnection();
		if (con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_BY_ID);
				preparedStatement.setString(1, productLineVO.getProductLine());
				result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				throw new SQLException(e + "  Not able to Delete");
			}
		}
		return result;
	}

}
