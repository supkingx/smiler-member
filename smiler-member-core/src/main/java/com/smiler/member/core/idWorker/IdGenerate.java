package com.smiler.member.core.idWorker;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/6/27
 */
public class IdGenerate {

    public static BigInteger getId() {
        SnowFlake snowFlake = new SnowFlake(1, 1);
        return BigInteger.valueOf(snowFlake.nextId());
    }

    public static List<BigInteger> getIdList(int size) {
        List<BigInteger> idList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            idList.add(getId());
        }
        return idList;
    }
}
