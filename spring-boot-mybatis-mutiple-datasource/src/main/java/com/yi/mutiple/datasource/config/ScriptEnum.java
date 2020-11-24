package com.yi.mutiple.datasource.config;

/**
 * 命令指令
 * @author YI
 * @date 2018-12-6 09:52:22
 */
public enum ScriptEnum {
    RED("linux命令", Byte.valueOf("0")), GREEN("linux shll脚本", Byte.valueOf("1")), BLANK("windows命令", Byte.valueOf("2")), YELLO("windows bat脚本", Byte.valueOf("3"));

    /**
     * 成员变量
     */
    private String name;
    private Byte value;

    /**
     * 构造方法
     * @param name  名称
     * @param value 值
     */
    ScriptEnum(String name, Byte value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    /**
     * 覆盖方法
     * @return
     */
    @Override
    public String toString() {
        return this.value+"_"+this.name;
    }
}
