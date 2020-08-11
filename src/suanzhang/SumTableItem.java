package suanzhang;
//定义产品条目的类
public class SumTableItem {
	  int id;//数据库中的id
	  int productID;//商品编号
	  float cost;//商品成本
	  int  number;//购买数量
	  int  numberPresent;//赠送数量
	  int  numberSum;//总数量
	  float sumCost;//商品总成本
	  String title;//订单状态、商品属性、商品标题
	  String orderDate;//订单日期 某年某月
	  
	  public void show() {
		  System.out.println(id+"\t"+productID+"\t"+cost+"\t"+
	  number+"\t"+numberPresent+"\t"+numberSum+"\t"+title+"\t"+orderDate);
	  }
}
