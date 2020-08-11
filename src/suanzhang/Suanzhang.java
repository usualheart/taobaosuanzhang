package suanzhang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

public class Suanzhang {
	//��Ҫʹ�õ�DAO ���ڶԱ������ۼ�¼�������ݿ����
	public static ProductItemDAO pID=new ProductItemDAO();
	//���ڶ�sumTable���в���
	public static sumTableDAO sTD=new sumTableDAO();
	
	//������Ʒ�������Ʒ����֮��Ķ�Ӧ��ϵ
	public static HashMap<Integer,String> productID2Title=new HashMap<Integer,String>();
	public static HashMap<Integer,Float> productID2Cost=new HashMap<Integer,Float>();
	
	//���캯��
	public static void makeHashMap() {
			productID2Title.put(101, "������װС�׷�");
			productID2Title.put(102, "������װС�׷�");
			productID2Title.put(103, "�������屦");
			productID2Title.put(104, "����������");
			productID2Title.put(105, "����ĥ����");
			productID2Title.put(106, "����������");
			productID2Title.put(201, "��ܰ�Ӥ���׷�");
			productID2Title.put(202, "��ܰ�Ӥ����");
			productID2Title.put(301, "�ǻ��ܴ�Ͱ�׷�");
			productID2Title.put(302, "�ǻ������屦");
			productID2Title.put(303, "�ǻ���ˮ����");
			productID2Title.put(401, "ά���������ʷ�");
			productID2Title.put(501, "������ϴ��Ƭ");
			productID2Title.put(502, "���������ӷ�");
			
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
	
	//�洢�������������ݵ����ݿ�  dateָ�����Ǵ洢�ļ�¼�Ķ�Ӧ������ "2019-03-01" ָ������Щ�������ĸ��µ�
	public static void save(List<List> allList,String date) {
		if(productID2Title.isEmpty())makeHashMap();//��ʼ��HashMap
		
		for(List tmp:allList) {
			ProductItem productItem = new ProductItem();
			
                productItem.orderID=(String)tmp.get(0);
                productItem.price=Float.parseFloat((String)tmp.get(2));
                productItem.number=Integer.parseInt((String)tmp.get(3));
                
                //�е���Ʒû����д���� ��Ҫ�ر���һ��
                if(((String) tmp.get(4)).equals("null")) {
                	productItem.productID=999;//û����Ʒ�����id��999
                	productItem.show();
                }
                else
                	//�б����������������
                	productItem.productID=Integer.parseInt((String) tmp.get(4));
                
                productItem.orderState=(String)tmp.get(8);
                productItem.productAttr=(String)tmp.get(5);
                productItem.title=productID2Title.get(productItem.productID);
                productItem.orderDate=date;
                
                
                /*
                //�Զ����Ž��д��� ��ԭΪ�ǿ�ѧ��������ʽ Ŀǰ���������������
                BigDecimal bd = new BigDecimal(productItem.orderID);  
                System.out.println(bd.toPlainString()+productItem.title);
                */
               // productItem.show();
                pID.add(productItem);
		}
		System.out.println("�洢�ɹ���");
	}
	
	//ͳ��ĳ��Ʒ ��Ӧ����������֮�� ��ĳ��
	public static int getTotalByPID(int productID,String date ) {
		List<ProductItem> PIList=pID.list(productID,date);//�����ض���Ʒ���ض�ʱ������ۼ�¼
		int sum=0;
		for(ProductItem tmp:PIList) {
			if(!tmp.orderState.equals("���׹ر�")) {
				sum+=tmp.number;
			}
		}
		return sum;
		
	}
	
	//���˲��� ��������Ʒ���г���ͳ�� ���洢��sumtable
	public static void makeSumTable(String date ) {
		
		if(productID2Title.isEmpty())makeHashMap();//��ʼ��HashMap
		
		for(int tmp:productID2Title.keySet()) {
           SumTableItem sTI=new SumTableItem();
           sTI.productID=tmp;
           sTI.cost=productID2Cost.get(tmp);
           sTI.number=getTotalByPID(tmp,date);
           sTI.numberPresent=0;//Ĭ������Ϊ0 ����Ҫ�ֶ��޸�
           sTI.numberSum=0;//������Ϊ0 ����Ҫ����
           sTI.sumCost=0;//�ܳɱ�Ĭ������Ϊ0 ��ʱ����Ҫ����
           sTI.title=productID2Title.get(tmp);
           sTI.orderDate=date;
           
           sTD.add(sTI);
        }
		System.out.println("makeSumTable�ɹ���");
	}
	
	//���˲��� ���Ͳ����Ѿ����� �˲������ڸ���sumtable�е�numberSum��sumCost���Լ���ʼ��anstable�ж�Ӧ����
	public static void updateSumtable(String date) {
		List<SumTableItem> list=sTD.list(date);
		float totalProductCost=0;
		for(SumTableItem tmp:list) {
			tmp.numberSum=tmp.number+tmp.numberPresent;
			tmp.sumCost=tmp.cost*tmp.numberSum;
			sTD.update(tmp);//����sumtable�ж�Ӧ����
			
			totalProductCost+=tmp.sumCost;
		}
		String sql="insert into anstable values(null,0,"+totalProductCost+",0,0,0,0,0,'"+date+"');";
		sTD.execute(sql);
	}
	public static void main(String[] args) throws Exception {
		//List <List> allList=CSVInput.readWithCsvListReader("D:\\my document\\����\\csv\\ExportOrderDetailList201908041104.csv");//allList�洢����csv�����Ľ����allList��ߵ�Ԫ�ػ���List  //CSVInput.show(allList);//������
		
		String date="2019-07-01";//ָ���洢�����ݿ������
		//save(allList,date);//��csv������洢�����ݿ���
		//makeSumTable(date);//����ָ������ �����ݿ��л�ȡ���� ͳ�ƺ�洢�����ݿ��sumTable��
		//updateSumtable(date);//������Ʒͳ�ƺ� �������ݿ��sumTable��
		sTD.updateProfit(date);//���˲��� ���������Ϣ���ʷѵȣ��Ѿ����� �˲������ڸ���anstable�ж�Ӧ���� �˲���ɺ��������
		
	}
}
