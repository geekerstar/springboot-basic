package com.geekerstar.basic.guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author geekerstar
 * @date 2022/5/15 18:51
 * https://mp.weixin.qq.com/s/3sTDX0ZIzi6ZCO0sPmbZFw
 */
public class GuavaTest {

    /**
     * Table：双键 Map
     */
    @Test
    public void tableTest() {
        Table<String, String, Integer> table = HashBasedTable.create();
        //存放元素
        table.put("Hydra", "Jan", 20);
        table.put("Hydra", "Feb", 28);

        table.put("Trunks", "Jan", 28);
        table.put("Trunks", "Feb", 16);

        //取出元素
        Integer dayCount = table.get("Hydra", "Feb");
        System.out.println("dayCount：" + dayCount);

        // 获得 key 或 value 的集合
        //rowKey或columnKey的集合
        Set<String> rowKeys = table.rowKeySet();
        Set<String> columnKeys = table.columnKeySet();
        System.out.println("rowKeys：" + rowKeys);
        System.out.println("columnKeys：" + columnKeys);

        //value集合
        Collection<Integer> values = table.values();
        System.out.println("values：" + values);

        // 计算 key 对应的所有 value 的和
        for (String key : table.rowKeySet()) {
            Set<Map.Entry<String, Integer>> rows = table.row(key).entrySet();
            int total = 0;
            for (Map.Entry<String, Integer> row : rows) {
                total += row.getValue();
            }
            System.out.println(key + ": " + total);
        }

        // 转换 rowKey 和 columnKey
        Table<String, String, Integer> table2 = Tables.transpose(table);
        Set<Table.Cell<String, String, Integer>> cells = table2.cellSet();
        cells.forEach(cell ->
                System.out.println(cell.getRowKey() + "," + cell.getColumnKey() + ":" + cell.getValue())
        );

        // 转为嵌套的 Map
        Map<String, Map<String, Integer>> rowMap = table.rowMap();
        Map<String, Map<String, Integer>> columnMap = table.columnMap();
        System.out.println("rowMap：" + rowMap);
        System.out.println("columnMap：" + columnMap);
    }

    /**
     * BiMap：双向 Map
     */
    @Test
    public void bitMapTest(){
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("Hydra","Programmer");
        biMap.put("Tony","IronMan");
        biMap.put("Thanos","Titan");
        //使用key获取value
        System.out.println(biMap.get("Tony"));

        BiMap<String, String> inverse = biMap.inverse();
        //使用value获取key
        System.out.println(inverse.get("Titan"));


        // 反转后操作的影响
        HashBiMap<String, String> biMap1 = HashBiMap.create();
        biMap1.put("Hydra","Programmer");
        biMap1.put("Tony","IronMan");
        biMap1.put("Thanos","Titan");
        BiMap<String, String> inverse1 = biMap1.inverse();

        inverse1.put("IronMan","Stark");
        System.out.println(biMap1);


        // value 不可重复
        HashBiMap<String, String> biMap2 = HashBiMap.create();
        biMap2.put("Tony","IronMan");
        biMap2.put("Stark","IronMan");

        HashBiMap<String, String> biMap3 = HashBiMap.create();
        biMap3.put("Tony","IronMan");
        biMap3.forcePut("Stark","IronMan");
    }

    /**
     * Multimap：多值 Map
     */
    @Test
    public void multiMapTest(){
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("day",1);
        multimap.put("day",2);
        multimap.put("day",8);
        multimap.put("month",3);

        // 获取值的集合
        Collection<Integer> day = multimap.get("day");

        ArrayListMultimap<String, Integer> multimap1 = ArrayListMultimap.create();
        List<Integer> day1 = multimap1.get("day");

        List<Integer> day2 = multimap1.get("day");
        List<Integer> year = multimap1.get("year");
        System.out.println(day2);
        System.out.println(year);

        // 操作 get 后的集合
        ArrayListMultimap<String, Integer> multimap2 = ArrayListMultimap.create();
        multimap2.put("day",1);
        multimap2.put("day",2);
        multimap2.put("day",8);
        multimap2.put("month",3);

        List<Integer> day3 = multimap2.get("day");
        List<Integer> month3 = multimap2.get("month");

        day3.remove(0);//这个0是下标
        month3.add(12);
        System.out.println(multimap2);

        // 转换为 Map
        Map<String, Collection<Integer>> map = multimap.asMap();
        for (String key : map.keySet()) {
            System.out.println(key+" : "+map.get(key));
        }
        map.get("day").add(20);
        System.out.println(multimap);
    }

    /**
     * RangeMap：范围 Map
     */
    @Test
    public void rangeMapTest(){
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closedOpen(0,60),"fail");
        rangeMap.put(Range.closed(60,90),"satisfactory");
        rangeMap.put(Range.openClosed(90,100),"excellent");

        System.out.println(rangeMap.get(59));
        System.out.println(rangeMap.get(60));
        System.out.println(rangeMap.get(90));
        System.out.println(rangeMap.get(91));

        rangeMap.remove(Range.closed(70,80));
        System.out.println(rangeMap.get(75));
    }

    /**
     * ClassToInstanceMap：实例 Map
     */
    @Test
    public void classToInstanceMapTest(){
        ClassToInstanceMap<Object> instanceMap = MutableClassToInstanceMap.create();
//        User user=new User("Hydra",18);
//        Dept dept=new Dept("develop",200);
//
//        instanceMap.putInstance(User.class,user);
//        instanceMap.putInstance(Dept.class,dept);
//
//        User user1 = instanceMap.getInstance(User.class);
//        System.out.println(user==user1);

    }
}
