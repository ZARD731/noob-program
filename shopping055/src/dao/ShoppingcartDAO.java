package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Cart;
import bean.Goods;
import bean.User;
import util.JDBCutils;

public class ShoppingcartDAO {
	//����id��ȡ��Ʒ��Ϣ
	public static void getGoodId(String username,String id,String num) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Cart cart=new Cart();
		try {
			conn = JDBCutils.getConnection();
			String sql = "select * from goods where ��Ʒid=? ";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,id);
			rs=stmt.executeQuery();
			if(rs.next()) {
				cart.setUsername(username);
				cart.setGoodname(rs.getString("��Ʒ��"));
				cart.setNum(num);
				cart.setId(id);
				cart.setPrice(rs.getString("�۸�"));
				insert(cart);
				
			}
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}	finally {
			JDBCutils.release(rs,stmt, conn);
		}
	}
	//��������ӵ�shoppingcart���ݱ���
	public static boolean insert(Cart cart) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCutils.getConnection();
			String sql = "insert into shoppingcart values(?,?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, cart.getUsername());
			stmt.setString(2, cart.getGoodname());
			stmt.setString(3, cart.getNum());
			stmt.setString(4, cart.getId());
			stmt.setString(5, cart.getPrice());
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	//��ȡ���ﳵ������Ϣ
	public ArrayList<Cart> getAllGoods(Object name){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Cart> list = new ArrayList<>(); 
			try {
				conn = JDBCutils.getConnection();
				String sql = "select * from shoppingcart where �û���=?";
				stmt=conn.prepareStatement(sql);
				stmt.setString(1, (String)name);
				rs=stmt.executeQuery();
				while(rs.next()) {
					Cart item =new Cart();
					item.setUsername(rs.getString("�û���"));
					item.setGoodname(rs.getString("��Ʒ��"));
					item.setNum(rs.getString("��������"));
					item.setId(rs.getString("��Ʒid"));
					item.setPrice(rs.getString("����"));
					list.add(item);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCutils.release(rs,stmt, conn);
			}
			return list; 
	}	
	//����idɾ����Ʒ��Ϣ
	public static void delGoodId(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCutils.getConnection();
			String sql = "delete from shoppingcart where ��Ʒid=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,id);
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//ͨ��id��ȡ���ﳵ��Ϣ
	public static Cart getCartId(String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Cart cart=new Cart();
		try {
			conn = JDBCutils.getConnection();
			String sql = "select * from shoppingcart where ��Ʒid=? ";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,id);
			rs=stmt.executeQuery();
			if(rs.next()) {
				cart.setGoodname(rs.getString("��Ʒ��"));
				cart.setUsername(rs.getString("�û���"));
				cart.setNum(rs.getString("��������"));
				cart.setId(rs.getString("��Ʒid"));
				cart.setPrice(rs.getString("����"));
			}
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}	finally {
			JDBCutils.release(rs,stmt, conn);
		}
		return cart;
	}
}