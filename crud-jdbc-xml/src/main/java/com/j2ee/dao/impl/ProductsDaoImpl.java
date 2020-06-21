package com.j2ee.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j2ee.dao.ProductsDao;
import com.j2ee.vo.ProductVO;

public class ProductsDaoImpl implements ProductsDao {
	private String SELECT_ALL = "SELECT * FROM products";
	private String INSERT_ALL = "INSERT INTO products(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP)VALUES(?,?,?,?,?,?,?,?,?)";
	private String UPDATE_BY_ID = "UPDATE products SET productName = ?, productLine = ?, productScale = ?, productVendor = ?,\n"
			+ "productDescription = ?,quantityInStock = ?, buyPrice = ?,MSRP = ? WHERE productCode = ?";
	private String DELETE_BY_ID = "DELETE FROM products WHERE productCode = ?";

	private static Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/product?" + "autoReconnect=true&useSSL=false", "root", "root");
		} catch (Exception e) {
			throw new SQLException(e + "  DB Connection Problem");
		}
		return con;
	}

	@Override
	public List<ProductVO> selectAll() throws SQLException {
		List<ProductVO> productVOs = null;
		Connection con = getConnection();
		if (con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL);
				ResultSet set = preparedStatement.executeQuery();
				productVOs = new ArrayList<ProductVO>();
				while (set.next()) {
					ProductVO productVO = new ProductVO();
					productVO.setProductCode(set.getString("productCode"));
					productVO.setProductName(set.getString("productName"));
					productVO.setProductLine(set.getString("productLine"));
					productVO.setProductScale(set.getString("productScale"));
					productVO.setProductVendor(set.getString("productVendor"));
					productVO.setProductDescription(set.getString("productDescription"));
					productVO.setQuantityInStock(set.getInt("quantityInStock"));
					productVO.setBuyPrice(set.getDouble("buyPrice"));
					productVO.setMSRP(set.getDouble("MSRP"));
					productVOs.add(productVO);
				}
			} catch (Exception e) {
				throw new SQLException(e + "  CAN NOT FETCH ProductLine informations");
			}
		}
		return productVOs;
	}

	@Override
	public int insert(ProductVO productVO) throws SQLException {
		int result = -1;
		Connection con = getConnection();
		if (con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(INSERT_ALL);
				preparedStatement.setString(1, productVO.getProductCode());
				preparedStatement.setString(2, productVO.getProductName());
				preparedStatement.setString(3, productVO.getProductLine());
				preparedStatement.setString(4, productVO.getProductScale());
				preparedStatement.setString(5, productVO.getProductVendor());
				preparedStatement.setString(6, productVO.getProductDescription());
				preparedStatement.setInt(7, productVO.getQuantityInStock());
				preparedStatement.setDouble(8, productVO.getBuyPrice());
				preparedStatement.setDouble(9, productVO.getMSRP());
				result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				throw new SQLException(e + "  Not able to Insert");
			}
		}
		return result;
	}

	@Override
	public int update(ProductVO productVO) throws SQLException {
		int result = -1;
		Connection con = getConnection();
		if (con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(UPDATE_BY_ID);
				preparedStatement.setString(1, productVO.getProductName());
				preparedStatement.setString(2, productVO.getProductLine());
				preparedStatement.setString(3, productVO.getProductScale());
				preparedStatement.setString(4, productVO.getProductVendor());
				preparedStatement.setString(5, productVO.getProductDescription());
				preparedStatement.setInt(6, productVO.getQuantityInStock());
				preparedStatement.setDouble(7, productVO.getBuyPrice());
				preparedStatement.setDouble(8, productVO.getMSRP());
				preparedStatement.setString(9, productVO.getProductCode());
				result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				throw new SQLException(e + "  Not able to Insert");
			}
		}
		return result;
	}

	@Override
	public int delete(ProductVO productVO) throws SQLException {
		int result = -1;
		Connection con = getConnection();
		if (con != null) {
			try {
				PreparedStatement preparedStatement = con.prepareStatement(DELETE_BY_ID);
				preparedStatement.setString(1, productVO.getProductCode());
				result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				throw new SQLException(e + "  Not able to Delete");
			}
		}
		return result;
	}

}
