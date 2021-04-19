package cn.edu.scau.blog.beans;

import lombok.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/15
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultJson {
    private Integer code;
    private String msg;
    private Map<String, Object> infoMap = new HashMap<>();

    public ResultJson(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultJson success() {
        return new ResultJson(200,"成功");
    }

    public static ResultJson fail() {
        return new ResultJson(400,"失败");
    }

    public ResultJson addInfo(String key, Object value) {
        this.infoMap.put(key,value);
        return this;
    }

    public ResultJson removeInfo(String key) {
        this.infoMap.remove(key);
        return this;
    }
}
