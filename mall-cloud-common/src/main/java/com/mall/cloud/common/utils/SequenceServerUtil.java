package com.mall.cloud.common.utils;

import com.mall.cloud.common.constant.Formats;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * <p>封装Qicloud项目SequenceServerUtil类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-29 01:32
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class SequenceServerUtil {
    /**
     * 第一种：Jeesite框架生成唯一ID算法实现
     */
    private static final SecureRandom random = new SecureRandom();
    /**
     * 开始时间截startTimeStamp,例如：2018-04-01
     */
    private static final long startTimeStamp = 1420041600000L;
    /**
     * 机器节点数ID长度workerIdBits：机器id所占的位数
     */
    private static final long workerIdBits = 5L;

    /** 第二种：Twitter的分布式自增ID算法Snowflake实现 */
    /**
     * Twitter_Snowflake算法:<br>
     * SnowFlake的结构如下(每部分用-分开):<br>
     * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
     * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
     * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
     * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由程序来指定的（如下下面程序IdWorker类的startTime属性）。 41位的时间截，可以使用69年，年T =
     * (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
     * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
     * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
     * 加起来刚好64位，为一个Long型。<br>
     * SnowFlake的优点: [1].整体上按照时间自增排序， [2].整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，
     * [3].效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
     */
    /**
     * *************************************************************************************************
     */
    /**
     * 数据中心ID长度datacenterIdBits：数据标识id所占的位数
     */
    private final long datacenterIdBits = 5L;
    /**
     * 最大支持机器节点数maxWorkerId:支持的最大机器id，0~31，一共32个，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * 最大支持数据中心节点数maxDatacenterId:支持的最大数据标识id，0~31，一共32个，结果是31
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /**
     * 序列节点ID长度sequenceBits：序列在id中占的位数，序列号12位
     */
    private final long sequenceBits = 12L;
    /**
     * 机器节点偏移长度workerIdShift：机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;
    /**
     * 数据中心节点偏移量datacenterIdShift：数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    /**
     * 时间截偏移量timestampLeftShift：时间截，时间毫秒数向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /**
     * 序列掩码长度sequenceMask:生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095),最大为4095
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 机器节点:workerId:工作机器ID(0~31)
     */
    private long workerId;
    /**
     * 数据中心节点datacenterId:数据中心ID(0~31)
     */
    private long datacenterId;
    /**
     * 时间戳序列sequence:时间戳毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    /**
     * 结束时间戳lastTimestamp:上次生成ID的时间截
     */
    private long lastTimestamp = -1L;
    /**
     * 第三种：按照IP地址+时间戳+5位随机数格式实现
     */
    private String clientIp = null;
    private SimpleDateFormat dateFormat = null;
    /**
     * *************************************************************************************************
     */
    /**
     * 生成有参构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public SequenceServerUtil(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format(
                            "This is the workerId can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format(
                            "This is the datacenterId can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 生成无参构造函数
     */
    public SequenceServerUtil() {
    }

    public SequenceServerUtil(String clientIp) {
        this.clientIp = clientIp;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int maxlength = 100;
        for (int i = 0; i < maxlength; i++) {
            long id = SequenceServerUtil.getInstance().produceId();
            System.out.println("id=" + id);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));

    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return resultSequence
     */
    public long produceId() {
        synchronized (this) {
            // 获取当前毫秒数
            long timestamp = generateCurrentTimeMillis();
            // 如果服务器时间有问题(时钟后退) 报错。
            if (timestamp < lastTimestamp) {
                throw new RuntimeException(
                        String.format(
                                "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                                lastTimestamp - timestamp));
            }
            // 如果上次生成时间和当前时间相同,在同一毫秒内
            if (lastTimestamp == timestamp) {
                // sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
                sequence = (sequence + 1) & sequenceMask;
                // 判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
                if (sequence == 0) {
                    // 自旋等待到下一毫秒
                    timestamp = blockNextMillis(lastTimestamp);
                }
            } else {
                // 如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
                sequence = 0L;
            }
            lastTimestamp = timestamp;
            // 最后按照规则拼出ID。
            // 000000000000000000000000000000000000000000  00000            00000       000000000000
            // time                                      datacenterId      workerId     sequence
            // return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
            //        | (workerId << workerIdShift) | sequence;
            return ((timestamp - startTimeStamp) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        }
    }

    public static SequenceServerUtil getInstance() {
        return SequenceServerHolder.sequenceInstance;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long generateCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long blockNextMillis(long lastTimestamp) {
        long timestamp = generateCurrentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = generateCurrentTimeMillis();
        }
        return timestamp;
    }

    /**
     * [2].封装使用SecureRandom随机生成Long.
     */
    public long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 生成IP地址+时间戳+3位随机数序列
     *
     * @return
     */
    public String generateIpAndTimestampId() {
        synchronized (this) {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.clientIp != null) {
                String str[] = this.clientIp.split(".");
                for (int i = 0; i < str.length; i++) {
                    stringBuffer.append(this.appendZero(str[i], 3));
                }
            }
      stringBuffer.append(getTimeStamp(Formats.DATE_FORMAT_TO_PM));
            for (int i = 0; i < 3; i++) {
                stringBuffer.append(random.nextInt(10));
            }
            return stringBuffer.toString();
        }
    }

    /**
     * 补0操作【如果不够指定位数，则在前面补0】
     *
     * @param parseStr
     * @param length
     * @return
     */
    private String appendZero(String parseStr, int length) {
        StringBuffer resultStr = new StringBuffer();
        resultStr.append(parseStr);
        while (resultStr.length() < length) {
            resultStr.insert(0, "0");
        }
        return resultStr.toString();
    }

    /**
     * 取得时间戳
     *
     * @return
     */
    private String getTimeStamp(String pattern) {
        this.dateFormat = new SimpleDateFormat(pattern);
        return this.dateFormat.format(generateCurrentTimeMillis());
    }

    public String getNextId() {
        return SequenceServerUtil.getInstance().uuid();
    }

    /**
     * [1].封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public  synchronized String uuid() {
        UUID uuid = UUID.randomUUID();
        String parseStr = uuid.toString();
        String resultUuid = parseStr.replaceAll("-", "");
        return resultUuid;
    }

    /**
     * 生成订单号 规则：前缀+yyyyMMddHHmmss+指定 长度随机数
     *
     * @param target    前缀
     * @param randomLen 随机数长度
     * @return
     */
    public  String generateCode(String target, int randomLen) {
        synchronized(this){
            StringBuffer stringBuffer = new StringBuffer(target);
            stringBuffer.append(getTimeStamp("yyyyMMddHHmmss"));
            for (int i = 0; i < randomLen; i++) {
                stringBuffer.append(random.nextInt(10));
            }
            return stringBuffer.toString();
        }
    }

    /**
     * 获得系统当前毫秒数
     *
     * @return timestamp
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    private static class SequenceServerHolder {
        private static final SequenceServerUtil sequenceInstance = new SequenceServerUtil();
    }
}
