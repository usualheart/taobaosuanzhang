package suanzhang;
  
import java.util.List;

public interface DAO<T>{
    //增加
    public  void  add(T hero);
    //修改
    public void update(T hero);
    //删除
    public void delete(int id);
    //获取
    public T get(int id);
    //查询
    public List<T> list();
    //分页查询
    public List<T> list(int start, int count);
}