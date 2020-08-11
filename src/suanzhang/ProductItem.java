package suanzhang;
//定义产品条目的类
public class ProductItem {
	  int id;//数据库中的id
	  String orderID;//订单编号
	  int productID;//商品编号
	  float price;//商品价格 当月销售价格
	  int  number;//购买数量
	  String orderState,productAttr,title;//订单状态、商品属性、商品标题
	  String orderDate;//订单日期 某年某月
	  
	  public void show() {
		  System.out.println(id+"\t"+orderID+"\t"+productID+"\t"+price+"\t"+
	  number+"\t"+orderState+"\t"+productAttr+"\t"+title+"\t"+orderDate);
	  }
}
