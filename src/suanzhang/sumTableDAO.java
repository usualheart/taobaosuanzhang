package suanzhang;


import java.sql.Connection;
 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;





public class sumTableDAO implements DAO<SumTableItem>{
  
    public sumTableDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eshop?characterEncoding=UTF-8", "root",
                "password");
    }
  
    public int getTotal() {
        int total = 0;
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select count(*) from sumtable";
  
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
  
           // System.out.println("total:" + total);
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return total;
    }
    
    //用于在eshop数据库中执行指定sql语句 除查询
    public  void execute(String sql) {
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
  
  
            
            s.execute(sql);
            System.out.println("sql语句执行成功！");
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //在eshop数据库的anstable表中查询数据，然后进行运算 计算出profit并更新到对应的表中

	//算账部分 所有相关信息（邮费等）已经完善 此部分用于更新anstable中对应的项 此部完成后算账完成 返回profit
    public float updateProfit(String date) {
	    	String sql="select * from anstable where orderDate='"+date+"';";
	    	
			int id=0;
			float totalSale,totalProductCost,expressFee,zhitongchgeFee,
			softwareFee,staffWage,profit = 0;
	        try (Connection c = getConnection(); Statement s = c.createStatement();) {
        	ResultSet rs = s.executeQuery(sql);
        	if(rs.next()) {
				id=rs.getInt(1);
			    totalSale = rs.getFloat(2);
			    totalProductCost=rs.getFloat(3);
			    expressFee=rs.getFloat(4);
			    zhitongchgeFee=rs.getFloat(5);
				softwareFee=rs.getFloat(6);
				staffWage=rs.getFloat(7);
				profit=totalSale-totalProductCost-expressFee-zhitongchgeFee-
						softwareFee-staffWage;
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
	    //更新结果到ansTable
		sql="update anstable set profit="+profit+"where id="+id+";";
		execute(sql);
		return profit;
    }
    public void add(SumTableItem sumTableItem) {
  
        String sql = "insert into sumtable values(null,?,?,?,?,?,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            
            ps.setInt(1, sumTableItem.productID);
            ps.setFloat(2, sumTableItem.cost);
            ps.setInt(3, sumTableItem.number);
            ps.setInt(4, sumTableItem.numberPresent);
            ps.setInt(5, sumTableItem.numberSum);
            ps.setFloat(6, sumTableItem.sumCost);
            ps.setString(7, sumTableItem.title);
            ps.setString(8, sumTableItem.orderDate);
  
            ps.execute();
  
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                sumTableItem.id = id;
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public void update(SumTableItem sumTableItem) {
  
        String sql = "update sumtable set productID= ?, cost = ? ,  number = ? ,numberPresent=?,numberSum = ? ,sumCost= ? ,title= ? ,orderDate=? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
        	ps.setInt(1, sumTableItem.productID);
            ps.setFloat(2, sumTableItem.cost);
            ps.setInt(3, sumTableItem.number);
            ps.setInt(4, sumTableItem.numberPresent);
            ps.setInt(5, sumTableItem.numberSum);
            ps.setFloat(6, sumTableItem.sumCost);
            ps.setString(7, sumTableItem.title);
            ps.setString(8, sumTableItem.orderDate);
            

            ps.setInt(9, sumTableItem.id);
  
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
    }
  
    public void delete(int id) {
  
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from sumtable where id = " + id;

            s.execute(sql);
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    public SumTableItem get(int id) {
        SumTableItem sumTableItem = null;
  
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from sumtable where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
            	sumTableItem=new SumTableItem();
            	
                sumTableItem.id=rs.getInt(1);
                sumTableItem.productID=rs.getInt(2);
                sumTableItem.cost=rs.getFloat(3);
                sumTableItem.number=rs.getInt(4);
                sumTableItem.numberPresent=rs.getInt(5);
                sumTableItem.numberSum=rs.getInt(6);
                sumTableItem.sumCost=rs.getFloat(7);
                sumTableItem.title=rs.getString(8);
                sumTableItem.orderDate=rs.getString("orderDate");
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return sumTableItem;
    }
    
    //以List形式返回数据库所有数据
    public List<SumTableItem> list() {
        return list(0, Short.MAX_VALUE);
    }
  //以List形式返回数据库中特定日期的所有数据
    public List<SumTableItem> list(String date) {
    	List<SumTableItem> sumTableItems = new ArrayList<SumTableItem>();
    	  
        String sql = "select * from sumtable  where orderDate=? order by id";//加一个desc就是倒序"select * from sumTableItem order by id desc limit ?,? "
  
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
           
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                SumTableItem sumTableItem = new SumTableItem();
                
                sumTableItem.id=rs.getInt(1);
                sumTableItem.productID=rs.getInt(2);
                sumTableItem.cost=rs.getFloat(3);
                sumTableItem.number=rs.getInt(4);
                sumTableItem.numberPresent=rs.getInt(5);
                sumTableItem.numberSum=rs.getInt(6);
                sumTableItem.sumCost=rs.getFloat(7);
                sumTableItem.title=rs.getString(8);
                sumTableItem.orderDate=rs.getString("orderDate");
                
                sumTableItems.add(sumTableItem);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return sumTableItems;
    }
    public List<SumTableItem> list(int start, int count) {
        List<SumTableItem> sumTableItems = new ArrayList<SumTableItem>();
  
        String sql = "select * from sumtable order by id limit ?,? ";//加一个desc就是倒序"select * from sumTableItem order by id desc limit ?,? "
  
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                SumTableItem sumTableItem = new SumTableItem();
                
                sumTableItem.id=rs.getInt(1);
                sumTableItem.productID=rs.getInt(2);
                sumTableItem.cost=rs.getFloat(3);
                sumTableItem.number=rs.getInt(4);
                sumTableItem.numberPresent=rs.getInt(5);
                sumTableItem.numberSum=rs.getInt(6);
                sumTableItem.sumCost=rs.getFloat(7);
                sumTableItem.title=rs.getString(8);
                sumTableItem.orderDate=rs.getString("orderDate");
                
                sumTableItems.add(sumTableItem);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return sumTableItems;
    }
    
    //test
    public static void main(String[]args) {
    	sumTableDAO pI=new sumTableDAO();
    	System.out.println(pI.list(10,20));
    }
  
}
