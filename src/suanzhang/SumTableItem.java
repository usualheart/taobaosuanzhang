package suanzhang;
//�����Ʒ��Ŀ����
public class SumTableItem {
	  int id;//���ݿ��е�id
	  int productID;//��Ʒ���
	  float cost;//��Ʒ�ɱ�
	  int  number;//��������
	  int  numberPresent;//��������
	  int  numberSum;//������
	  float sumCost;//��Ʒ�ܳɱ�
	  String title;//����״̬����Ʒ���ԡ���Ʒ����
	  String orderDate;//�������� ĳ��ĳ��
	  
	  public void show() {
		  System.out.println(id+"\t"+productID+"\t"+cost+"\t"+
	  number+"\t"+numberPresent+"\t"+numberSum+"\t"+title+"\t"+orderDate);
	  }
}
