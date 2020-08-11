package suanzhang;


import java.sql.Connection;
 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductItemDAO implements DAO<ProductItem>{
  
    public ProductItemDAO() {
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
  
            String sql = "select count(*) from productItem";
  
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
  
    public void add(ProductItem productItem) {
  
        String sql = "insert into productItem values(null,?,?,?,?,?,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, productItem.orderID);
            ps.setInt(2, productItem.productID);
            ps.setFloat(3, productItem.price);
            ps.setInt(4, productItem.number);
            ps.setString(5, productItem.orderState);
            ps.setString(6, productItem.productAttr);
            ps.setString(7, productItem.title);
            ps.setString(8, productItem.orderDate);
  
            ps.execute();
  
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                productItem.id = id;
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
    }
  
    public void update(ProductItem productItem) {
  
        String sql = "update productItem set orderID= ?, productID = ? , price=? number = ? orderState = ? productAttr= ? title= ? orderDate=? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
        	ps.setString(1, productItem.orderID);
            ps.setInt(2, productItem.productID);
            ps.setFloat(3, productItem.price);
            ps.setInt(4, productItem.number);
            ps.setString(5, productItem.orderState);
            ps.setString(6, productItem.productAttr);
            ps.setString(7, productItem.title);
            ps.setString(8, productItem.orderDate);
            
            ps.setInt(9, productItem.id);
            ps.execute();
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
  
    }
  
    public void delete(int id) {
  
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
  
            String sql = "delete from productItem where id = " + id;

            s.execute(sql);
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    public ProductItem get(int id) {
        ProductItem productItem = null;
  
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
  
            String sql = "select * from productItem where id = " + id;
  
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
            	productItem=new ProductItem();
            	
                productItem.id=rs.getInt(1);
                productItem.orderID=rs.getString(2);
                productItem.productID=rs.getInt(3);
                productItem.price=rs.getFloat(4);
                productItem.number=rs.getInt(5);
                productItem.orderState=rs.getString(6);
                productItem.productAttr=rs.getString(7);
                productItem.title=rs.getString(8);
                productItem.orderDate=rs.getString("orderDate");
            }
  
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return productItem;
    }
    
    //以List形式返回数据库所有数据
    public List<ProductItem> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<ProductItem> list(int start, int count) {
        List<ProductItem> productItems = new ArrayList<ProductItem>();
  
        String sql = "select * from productItem order by id limit ?,? ";//加一个desc就是倒序"select * from productItem order by id desc limit ?,? "
  
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                ProductItem productItem = new ProductItem();
                
                productItem.id=rs.getInt(1);
                productItem.orderID=rs.getString(2);
                productItem.productID=rs.getInt(3);
                productItem.price=rs.getFloat(4);
                productItem.number=rs.getInt(5);
                productItem.orderState=rs.getString(6);
                productItem.productAttr=rs.getString(7);
                productItem.title=rs.getString(8);
                productItem.orderDate=rs.getString("orderDate");
                
                productItems.add(productItem);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return productItems;
    }
    
    //以List形式返回数据库中特定productID的所有数据
    public List<ProductItem> list(int productID,String date) {
    	List<ProductItem> productItems = new ArrayList<ProductItem>();
    	  
        String sql = "select * from productItem  where productID = ? AND orderDate=? order by id";//加一个desc就是倒序"select * from productItem order by id desc limit ?,? "
  
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, productID);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                ProductItem productItem = new ProductItem();
                
                productItem.id=rs.getInt(1);
                productItem.orderID=rs.getString(2);
                productItem.productID=rs.getInt(3);
                productItem.price=rs.getFloat(4);
                productItem.number=rs.getInt(5);
                productItem.orderState=rs.getString(6);
                productItem.productAttr=rs.getString(7);
                productItem.title=rs.getString(8);
                productItem.orderDate=rs.getString("orderDate");
                
                productItems.add(productItem);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return productItems;
    }
    
    //test
    public static void main(String[]args) {
    	ProductItemDAO pI=new ProductItemDAO();
    	for(ProductItem tmp:pI.list(101,"2019-03-01")) {
    		tmp.show();
    	}
    }
  
}
