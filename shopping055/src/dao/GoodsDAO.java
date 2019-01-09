package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.Goods;
import util.JDBCutils;
public class GoodsDAO {
	//��ȡ��Ʒ��Ϣ
	public ArrayList<Goods> getAllGoods(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Goods> list = new ArrayList<>(); 
		try {
			conn = JDBCutils.getConnection();
			String sql = "select * from goods";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()) {
				Goods item =new Goods();
				item.setId(rs.getInt("��Ʒid"));
				item.setName(rs.getString("��Ʒ��"));
				item.setPrice(rs.getInt("�۸�"));
				item.setText(rs.getString("��Ʒ����"));
				item.setPhoto(rs.getString("ͼƬ"));
				list.add(item);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutils.release(rs,stmt, conn);
		}
		return list; 
	}
	//������Ʒid��ȡ��Ʒ��Ϣ
	public Goods getGoodId(Object id) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Goods item=new Goods();
		try {
			conn = JDBCutils.getConnection();
			String sql = "select * from goods where ��Ʒid=? ";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, (String) id);
			rs=stmt.executeQuery();
			if(rs.next()) {
				item.setId(rs.getInt("��Ʒid"));
				item.setName(rs.getString("��Ʒ��"));
				item.setPrice(rs.getInt("�۸�"));
				item.setText(rs.getString("��Ʒ����"));
				item.setPhoto(rs.getString("ͼƬ"));
				return item;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutils.release(rs,stmt, conn);
		}
		
		return item;
	}
}
