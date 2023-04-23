package org.ankol.server.tools;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.ankol.server.dao.entity.RaidSeasonEntity;

public class ClashUtil {
    /**
     * 转换{@link RaidSeasonEntity}中的时间戳.
     * <p>格式为：20230414T070000.000Z</p>
     *
     * @param timestamp 时间戳，需要符合上述格式
     * @return {@link DateTime}
     */
    public static DateTime formatterRaidSeasonTime(String timestamp) {
        return DateUtil.parse(timestamp, DatePattern.createFormatter("yyyyMMdd'T'HHmmss.SSS'Z'"));
    }

    /**
     * 根据{@link RaidSeasonEntity}中的起始时间戳生成对应的数据库ID
     * <p>时间戳格式为：20230414T070000.000Z</p>
     *
     * @param timestamp 时间戳，需要符合上述格式
     * @return ID 示例: 20220412142200
     */
    public static String getRaidSeasonId(String timestamp) {
        return formatterRaidSeasonTime(timestamp)
                .toString(DatePattern.PURE_DATETIME_PATTERN);

    }

    public static void main(String[] args) {
        String id = getRaidSeasonId("20230414T070000.000Z");
        System.out.println("id = " + id);
    }
}
