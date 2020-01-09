package com.blog.anal.service.impl;

import com.blog.anal.domain.User;
import com.blog.anal.service.BlogService;
import com.blog.anal.utils.MapUtils;
import java.io.BufferedReader;
import java.util.Map;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @author Ranger
 * @create 2020-01-08 15:52
 */
@Service
public class BlogServiceImpl implements BlogService {

    private static Jedis jedis = new Jedis("39.107.249.220", 6379);

    @Override
    public User anal(BufferedReader reader,BufferedReader reader2) {
        Map<String, Integer> sexMap = MapUtils.sexMap;
        Map<String, Integer> ageMap = MapUtils.ageMap;
        String[] sexWeight = jedis.get("sex_weight_1").split("\\,");
        String[] ageWeight1 = jedis.get("age_weight_1").split("\\,");
        String[] ageWeight2 = jedis.get("age_weight_2").split("\\,");
        int sex = analSex(reader, sexMap, sexWeight);
        int age = analAge(reader2, ageMap, ageWeight1, ageWeight2);
        return new User(sex, age);
    }

    /**
     * 分析性别
     *
     * @param reader
     * @param sexMap
     * @param sexWeight
     * @return
     */
    private int analSex(BufferedReader reader, Map<String, Integer> sexMap, String[] sexWeight) {

        String line;
        try {
            while (null != (line = reader.readLine())) {
                String[] s1 = line.split(" ");
                for (String s2 : s1) {
                    for (String word : sexMap.keySet()) {
                        if (s2.contains(word))
                            sexMap.put(word, sexMap.get(word) + 1);
                    }
                }
            }
            int i = 0;
            double c = 0.0;
            for (String word : sexMap.keySet()) {
                c += sexMap.get(word) * Double.parseDouble(sexWeight[i++]);
            }
            c = 1 / (1 + Math.pow(Math.E, -c));
            //记录清零
            for (String word : sexMap.keySet()) {
                sexMap.put(word, 0);
            }
            return c >= 0.5 ? 1 : 0;
        } catch (Exception e) {
            throw new RuntimeException("性别分析异常！");
        }
    }

    /**
     * 分析年龄
     *
     * @param reader
     * @param ageMap
     * @return
     */
    private int analAge(BufferedReader reader, Map<String, Integer> ageMap, String[] ageWeight1, String[] ageWeight2) {

        String line;
        try {
            while (null != (line = reader.readLine())) {
                String[] s1 = line.split(" ");
                for (String s2 : s1) {
                    for (String word : ageMap.keySet()) {
                        if (s2.contains(word))
                            ageMap.put(word, ageMap.get(word) + 1);
                    }
                }
            }
            int i = 0;
            double c = 0.0;
            for (String word : ageMap.keySet()) {
                c += ageMap.get(word) * Double.parseDouble(ageWeight1[i++]);
                System.out.println(word+"=="+ageMap.get(word));
            }
            c = 1 / (1 + Math.pow(Math.E, -c));
            System.out.println("c = " + c);
            if (c > 0.5) {
                //再次分类
                i = 0;
                c = 0.0;
                for (String word : ageMap.keySet()) {
                    c += ageMap.get(word) * Double.parseDouble(ageWeight2[i++]);
                }
                c = 1 / (1 + Math.pow(Math.E, -c));
                System.out.println("c2 = " + c);
                //记录清零
                for (String word : ageMap.keySet()) {
                    ageMap.put(word, 0);
                }
                return c > 0.5 ? 2 : 1;
            } else{
                //记录清零
                for (String word : ageMap.keySet()) {
                    ageMap.put(word, 0);
                }
                return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException("性别分析异常！");
        }
    }
}
