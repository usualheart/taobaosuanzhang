package suanzhang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

public class Suanzhang {
	//需要使用的DAO 用于对宝贝销售记录进行数据库操作
	public static ProductItemDAO pID=new ProductItemDAO();
	//用于对sumTable进行操作
	public static sumTableDAO sTD=new sumTableDAO();
	
	//定义商品编码和商品名字之间的对应关系
	public static HashMap<Integer,String> productID2Title=new HashMap<Integer,String>();
	public static HashMap<Integer,Float> productID2Cost=new HashMap<Integer,Float>();
	
	//构造函数
	public static void makeHashMap() {
			productID2Title.put(101, "西豆罐装小米粉");
			productID2Title.put(102, "西豆盒装小米粉");
			productID2Title.put(103, "西豆清清宝");
			productID2Title.put(104, "西豆葡萄糖");
			productID2Title.put(105, "西豆磨牙棒");
			productID2Title.put(106, "西豆奶泡泡");
			productID2Title.put(201, "金盾爱婴蒸米粉");
			productID2Title.put(202, "金盾爱婴米乳");
			productID2Title.put(301, "智慧熊大桶米粉");
			productID2Title.put(302, "智慧熊清清宝");
			productID2Title.put(303, "智慧熊水果条");
			productID2Title.put(401, "维多莱蛋白质粉");
			productID2Title.put(501, "宝健丽洗衣片");
			productID2Title.put(502, "宝健丽痱子粉");
			
			productID2Cost.put(101, 11.5f);
			productID2Cost.put(102, 7.5f);
			productID2Cost.put(103, 10.5f);
			productID2Cost.put(104, 15f);
			productID2Cost.put(105, 4f);
			productID2Cost.put(106, 6f);
			productID2Cost.put(201, 17f);
			productID2Cost.put(202, 17f);
			productID2Cost.put(301, 12f);
			productID2Cost.put(302, 6f);
			productID2Cost.put(303, 0.8f);
			productID2Cost.put(401, 28f);
			productID2Cost.put(501, 11.5f);
			productID2Cost.put(502, 12f);
			
			
	}
	
	//存储解析出来的数据到数据库  date指定的是存储的记录的对应的日期 "2019-03-01" 指的是这些订单是哪个月的
	public static void save(List<List> allList,String date) {
		if(productID2Title.isEmpty())makeHashMap();//初始化HashMap
		
		for(List tmp:allList) {
			ProductItem productItem = new ProductItem();
			
                productItem.orderID=(String)tmp.get(0);
                productItem.price=Float.parseFloat((String)tmp.get(2));
                productItem.number=Integer.parseInt((String)tmp.get(3));
                
                //有的商品没有填写编码 需要特别处理一下
                if(((String) tmp.get(4)).equals("null")) {
                	productItem.productID=999;//没有商品编码的id：999
                	productItem.show();
                }
                else
                	//有编码则进行正常处理
                	productItem.productID=Integer.parseInt((String) tmp.get(4));
                
                productItem.orderState=(String)tmp.get(8);
                productItem.productAttr=(String)tmp.get(5);
                productItem.title=productID2Title.get(productItem.productID);
                productItem.orderDate=date;
                
                
                /*
                //对订单号进行处理 还原为非科学记数法形式 目前这个方法存在问题
                BigDecimal bd = new BigDecimal(productItem.orderID);  
                System.out.println(bd.toPlainString()+productItem.title);
                */
               // productItem.show();
                pID.add(productItem);
		}
		System.out.println("存储成功！");
	}
	
	//统计某商品 对应的销售数量之和 在某月
	public static int getTotalByPID(int productID,String date ) {
		List<ProductItem> PIList=pID.list(productID,date);//返回特定商品在特定时间的销售记录
		int sum=0;
		for(ProductItem tmp:PIList) {
			if(!tmp.orderState.equals("交易关闭")) {
				sum+=tmp.number;
			}
		}
		return sum;
		
	}
	
	//算账部分 订单按商品进行初步统计 并存储到sumtable
	public static void makeSumTable(String date ) {
		
		if(productID2Title.isEmpty())makeHashMap();//初始化HashMap
		
		for(int tmp:productID2Title.keySet()) {
           SumTableItem sTI=new SumTableItem();
           sTI.productID=tmp;
           sTI.cost=productID2Cost.get(tmp);
           sTI.number=getTotalByPID(tmp,date);
           sTI.numberPresent=0;//默认设置为0 还需要手动修改
           sTI.numberSum=0;//先设置为0 还需要更新
           sTI.sumCost=0;//总成本默认设置为0 到时候还需要更新
           sTI.title=productID2Title.get(tmp);
           sTI.orderDate=date;
           
           sTD.add(sTI);
        }
		System.out.println("makeSumTable成功！");
	}
	
	//算账部分 赠送部分已经完善 此部分用于更新sumtable中的numberSum和sumCost，以及初始化anstable中对应的项
	public static void updateSumtable(String date) {
		List<SumTableItem> list=sTD.list(date);
		float totalProductCost=0;
		for(SumTableItem tmp:list) {
			tmp.numberSum=tmp.number+tmp.numberPresent;
			tmp.sumCost=tmp.cost*tmp.numberSum;
			sTD.update(tmp);//更新sumtable中对应的项
			
			totalProductCost+=tmp.sumCost;
		}
		String sql="insert into anstable values(null,0,"+totalProductCost+",0,0,0,0,0,'"+date+"');";
		sTD.execute(sql);
	}
	public static void main(String[] args) throws Exception {
		//List <List> allList=CSVInput.readWithCsvListReader("D:\\my document\\网店\\csv\\ExportOrderDetailList201908041104.csv");//allList存储的是csv解析的结果，allList里边的元素还是List  //CSVInput.show(allList);//输出结果
		
		String date="2019-07-01";//指定存储到数据库的日期
		//save(allList,date);//把csv解析后存储到数据库中
		//makeSumTable(date);//根据指定日期 从数据库中获取数据 统计后存储到数据库的sumTable表
		//updateSumtable(date);//完善赠品统计后 更新数据库的sumTable表
		sTD.updateProfit(date);//算账部分 所有相关信息（邮费等）已经完善 此部分用于更新anstable中对应的项 此部完成后算账完成
		
	}
}
