package com.blog.anal.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
/**
 * @author Ranger
 * @create 2020-01-08 11:46
 */
public class ImportDataToRedis {
    private static Jedis jedis = new Jedis("39.107.249.220", 6379);

    /**
     * 将逻辑回归训练的性别权重导入redis
     */
    @Test
    public void testImportLogicSexWeight() {
        jedis.set("sex_weight_1", "0.06055311386677266,0.035291368909435454,0.1527549945549942,0.3061290548148473,0.2576155470008734,0.08939618605609786,0.19404133393968945,0.12028689004807011,0.17982109757141368,0.147156505694113,0.009594231317965624,0.3042021567251011,0.1248776772238743,0.17171745524597928,0.1880866313776783,-0.00779966317433352,0.030812601295116565,-0.07332575683057006,-0.09633516842538818,-0.20195406984305964,-0.013605967327517988,-0.08537465223632075,-0.04252742156543406,-0.1885298212619807,-0.13695439702583792,-0.13779328560154974,-0.13444845341342998,-0.09007348901969443,-0.24091856974814752,-0.13630484332266238");
        String v = jedis.get("sex_weight_1");
        System.out.println("v = " + v);
        jedis.close();
    }

    /**
     * 将逻辑回归训练的年龄权重导入redis
     */
    @Test
    public void testImportLogicAgeWeight_1() {
        jedis.set("age_weight_1", "-0.7142133006783443,-1.507148254289986,-1.5457662719064869,0.3633740393088579,-1.510252801131388,-1.4361673359817322,-0.5672866253164884,-0.16705797113970078,-0.051741359200311254,-0.536266393012884,0.36621298562039295,1.3150582490230531,0.29051260214345376,0.8117637626232944,0.3451501632376612,0.46572944291348073,-0.07016239641110866,0.020399290197533282,0.17942115624536553,1.2073719810080277,0.4013430977287455,0.3666987744025365,0.014730408698879965,0.3517202560641209,1.0899240349803558,0.035064646238483335,0.1549765785152175,0.2290871191702863,0.6917704495730365,0.5517032215594908");
        String v = jedis.get("age_weight_1");
        System.out.println("v = " + v);
        jedis.close();
    }

    /**
     * 将逻辑回归训练的年龄权重导入redis
     */
    @Test
    public void testImportLogicAgeWeight_2() {
        jedis.set("age_weight_2", "-0.029140510816090287,-0.24079632222287448,-0.7342060882853774,0.1600138308522649,-0.7258876503848792,-1.2494669873218767,-0.4111339613271682,-0.2584033527130205,-0.21929208096260433,-0.5815384621199369,-0.773995936990687,-0.8770194673024374,-0.9597944992664489,-0.49598676511415474,-0.07049980905468903,0.006867716827096214,-0.42655525926569715,-0.03693389494833135,-0.3761156160585593,0.05341897109727667,0.5026680306374683,0.19368319364860032,0.9216987179066018,0.42084519699134415,1.1495623596516933,0.04839474400712172,-0.45119824552521937,0.13038374876154374,0.5096327019449823,0.14789960863230736");
        String v = jedis.get("age_weight_2");
        System.out.println("v = " + v);
        jedis.close();
    }
}
