package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Cart;
import bean.Goods;
import bean.alreadybuy;
import util.JDBCutils;

public class alreadybuyDAO {
	//��������
	public static void insert(String id) {
		//ͨ��id��ȡcart���е�����,��cart���е�ֵ��ֵ��alreadybuy��
		ShoppingcartDAO cartDao = new ShoppingcartDAO();
		Cart cart = cartDao.getCartId(id);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCutils.getConnection();
			String sql = "insert into alreadybuy values(?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, cart.getUsername());
			stmt.setString(2, cart.getGoodname());
			stmt.setString(3, cart.getNum());
			stmt.setString(4, cart.getId());
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			JDBCutils.release(rs,stmt, conn);
		}
	}
		//ͨ���û�����ȡ������Ϣ
		public ArrayList<alreadybuy> getAllAlready(Object name){
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<alreadybuy> list = new ArrayList<>(); 
			try {
				conn = JDBCutils.getConnection();
				String sql = "select * from alreadybuy where �û���=?";
				stmt=conn.prepareStatement(sql);
				stmt.setString(1, (String)name);
				rs=stmt.executeQuery();
				while(rs.next()) {
					alreadybuy item =new alreadybuy();
					item.setGoodname(rs.getString("��Ʒ��"));
					item.setId(rs.getString("��Ʒid"));
					item.setNum(rs.getString("��������"));
					item.setUsername(rs.getString("�û���"));
					list.add(item);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCutils.release(rs,stmt, conn);
			}
			return list; 
		}
}
