package suanzhang;
//�����Ʒ��Ŀ����
public class ProductItem {
	  int id;//���ݿ��е�id
	  String orderID;//�������
	  int productID;//��Ʒ���
	  float price;//��Ʒ�۸� �������ۼ۸�
	  int  number;//��������
	  String orderState,productAttr,title;//����״̬����Ʒ���ԡ���Ʒ����
	  String orderDate;//�������� ĳ��ĳ��
	  
	  public void show() {
		  System.out.println(id+"\t"+orderID+"\t"+productID+"\t"+price+"\t"+
	  number+"\t"+orderState+"\t"+productAttr+"\t"+title+"\t"+orderDate);
	  }
}
