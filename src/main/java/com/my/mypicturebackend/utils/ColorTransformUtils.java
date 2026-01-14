package com.my.mypicturebackend.utils;

public class ColorTransformUtils {

    private ColorTransformUtils() {
        // 工具类不需要实例化
    }

    /**
     * 将不完整的0x开头RGB十六进制色值补全为标准6位格式
     * 核心规则：高位0可省略，低位0不可省略，按RR/GG/BB三段补全
     * 示例：
     * 0xe0020 → 0xe00020
     * 0x0c00 → 0x00c000
     * 0xfff → 0x0f0f0f
     *
     * @param rawColor 以0x开头的十六进制颜色字符串
     * @return 标准6位的0x开头颜色字符串
     * @throws IllegalArgumentException 入参不合法时抛出
     */
    public static String getStandardColor(String rawColor) {
        // 1. 入参基础校验
        if (rawColor == null || rawColor.isEmpty()) {
            throw new IllegalArgumentException("颜色字符串不能为空");
        }
        if (!rawColor.startsWith("0x")) {
            throw new IllegalArgumentException("必须以0x开头的十六进制字符串");
        }
        // 提取0x后的十六进制部分并转小写
        String hex = rawColor.substring(2).toLowerCase();
        // 校验是否为合法十六进制字符
        if (!hex.matches("[0-9a-f]+")) {
            throw new IllegalArgumentException("包含非法十六进制字符，仅允许0-9、a-f");
        }
        int length = hex.length();

        // 2. 处理不同长度的场景
        String r = "00", g = "00", b = "00";
        // 标准6位，直接返回
        if (length == 6) {
            return "0x" + hex;
        }
        // 超过6位，直接抛异常（无意义的超长值）
        if (length > 6) {
            throw new IllegalArgumentException("颜色字符串长度超过6位，无法解析");
        }

        // 3. 处理1-5位的场景
        switch (length) {
            case 5: // 示例：e0020 → RR=e0, GG=00, BB=20
                r = hex.substring(0, 2); // 前两位为RR（高位无0省略，必占两位）
                String remain5 = hex.substring(2); // 剩余3位：020
                if (remain5.startsWith("0")) {
                    g = "00"; // GG高位0省略，实际为00
                    b = remain5.substring(1); // BB=20
                } else {
                    g = remain5.substring(0, 2); // GG占两位
                    b = "0" + remain5.charAt(2); // BB低位补0
                }
                break;
            case 4: // 示例：0c00 → RR=00, GG=c0, BB=00
                String first2 = hex.substring(0, 2);
                if (first2.startsWith("0")) {
                    r = "00"; // RR高位0省略，实际为00
                    String remain4 = hex.substring(1); // 剩余3位：c00
                    if (remain4.startsWith("0")) {
                        g = "00";
                        b = remain4.substring(1);
                    } else {
                        g = remain4.substring(0, 2);
                        b = "0" + remain4.charAt(2);
                    }
                } else {
                    r = first2; // RR占两位
                    String remain4 = hex.substring(2); // 剩余2位
                    g = "0" + remain4.charAt(0); // GG低位补0
                    b = "0" + remain4.charAt(1); // BB低位补0
                }
                break;
            case 3: // 示例：fff → 0f0f0f（COS规则，非CSS的ffffff）
                r = "0" + hex.charAt(0);
                g = "0" + hex.charAt(1);
                b = "0" + hex.charAt(2);
                break;
            case 2: // 示例：e0 → RR=e0, GG=00, BB=00
                r = hex;
                g = "00";
                b = "00";
                break;
            case 1: // 示例：f → RR=0f, GG=00, BB=00
                r = "0" + hex;
                g = "00";
                b = "00";
                break;
            default:
                throw new IllegalArgumentException("不支持的颜色字符串长度：" + length);
        }

        // 4. 最终拼接（确保每段都是2位）
        String standardHex = r + g + b;
        if (standardHex.length() != 6) {
            throw new RuntimeException("补全后颜色字符串长度异常：" + standardHex);
        }
        return "0x" + standardHex;
    }

    // 测试方法（验证核心场景）
    public static void main(String[] args) {
        // 测试用例1：核心场景 e0020 → e00020
        System.out.println(getStandardColor("0xe0020")); // 输出 0xe00020
        // 测试用例2：0c00 → 00c000
        System.out.println(getStandardColor("0x0c00")); // 输出 0x00c000
        // 测试用例3：fff → 0f0f0f
        System.out.println(getStandardColor("0xfff")); // 输出 0x0f0f0f
        // 测试用例4：e0 → e00000
        System.out.println(getStandardColor("0xe0")); // 输出 0xe00000
        // 测试用例5：f → 0f0000
        System.out.println(getStandardColor("0xf")); // 输出 0x0f0000
    }
}