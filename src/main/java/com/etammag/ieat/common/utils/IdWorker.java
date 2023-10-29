package com.etammag.ieat.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class IdWorker {

    private static final long BEGIN_TIMESTAMP =
            LocalDateTime.of(2023, 1, 1, 0, 0, 0).toEpochSecond(ZoneOffset.UTC);

    private static final Map<String, Long> map = new HashMap<>();

    public static long nextId(){
        LocalDateTime now = LocalDateTime.now();
        //1.生成时间戳
        long nowTimestamp = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowTimestamp - BEGIN_TIMESTAMP;
        timestamp <<= 32;
        //2.生成序列号
        String dateKey = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Long sequence = map.get(dateKey);
        long seq = sequence == null ? 0 : sequence;
        map.put(dateKey, ++seq);
        seq &= 4294967295L;
        //3.拼接并返回
        return timestamp | seq;
    }

}
