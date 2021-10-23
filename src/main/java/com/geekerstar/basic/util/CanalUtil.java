package com.geekerstar.basic.util;


/**
 * @author geekerstar
 * @date 2021/8/19 20:35
 * @description canal同步mysql到redis
 */
public class CanalUtil {

//    public static void main(String[] args) {
//        // 创建链接
//        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
//                11111), "example", "", "");
//        int batchSize = 1000;
//        try (Jedis jedis = new Jedis("192.168.0.107", 16379)) {
//            connector.connect();
//            connector.subscribe(".*\\..*");
//            connector.rollback();
//            while (true) {
//                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
//                long batchId = message.getId();
//                try {
//                    int size = message.getEntries().size();
//                    if (batchId == -1 || size == 0) {
//                        Thread.sleep(1000);
//                    } else {
//                        processEntries(message.getEntries(), jedis);
//                    }
//
//                    connector.ack(batchId); // 提交确认
//                } catch (Throwable t) {
//                    connector.rollback(batchId); // 处理失败, 回滚数据
//                }
//            }
//
//        } finally {
//            connector.disconnect();
//
//        }
//    }
//
//    private static void processEntries(List<CanalEntry.Entry> entrys, Jedis jedis) {
//        for (CanalEntry.Entry entry : entrys) {
//            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
//                continue;
//            }
//
//            CanalEntry.RowChange rowChange;
//            try {
//                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
//            } catch (Exception e) {
//                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
//                        e);
//            }
//
//            CanalEntry.EventType eventType = rowChange.getEventType();
//            System.out.println(String.format("binlog[%s:%s] , name[%s,%s] , eventType : %s",
//                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
//                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
//                    eventType));
//
//            for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
//                if (eventType == CanalEntry.EventType.DELETE) { // 删除
//                    printColumn(rowData.getBeforeColumnsList());
//                    jedis.del(row2Key("user_id", rowData.getBeforeColumnsList()));
//                } else if (eventType == CanalEntry.EventType.INSERT) { // 插入
//                    printColumn(rowData.getAfterColumnsList());
//                    jedis.set(row2Key("user_id", rowData.getAfterColumnsList()), row2Value(rowData.getAfterColumnsList()));
//                } else { // 更新
//                    System.out.println("before");
//                    printColumn(rowData.getBeforeColumnsList());
//                    System.out.println("after");
//                    printColumn(rowData.getAfterColumnsList());
//                    jedis.set(row2Key("user_id", rowData.getAfterColumnsList()), row2Value(rowData.getAfterColumnsList()));
//
//                }
//            }
//        }
//    }
//
//    private static void printColumn(List<CanalEntry.Column> columns) {
//
//        for (CanalEntry.Column column : columns) {
//            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
//        }
//    }
//
//    private static byte[] row2Key(String keyColumn, List<CanalEntry.Column> columns) {
//        return columns.stream().filter(c -> keyColumn.equals(c.getName()))
//                .map(c -> c.getValue().getBytes(StandardCharsets.UTF_8)).findAny()
//                .orElseThrow(() -> new RuntimeException("Key column not found in the row!"));
//    }
//
//    private static byte[] row2Value(List<CanalEntry.Column> columns) {
//        return new JSONObject(
//                columns.stream().collect(Collectors.toMap(CanalEntry.Column::getName, CanalEntry.Column::getValue))
//        ).toString().getBytes(StandardCharsets.UTF_8);
//    }
}
