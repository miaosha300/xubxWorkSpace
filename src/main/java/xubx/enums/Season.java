package xubx.enums;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.enums
 * 类名称:     Season
 * 类描述:     类功能描述
 * 创建人:     xubx
 * 创建时间:   2020/7/22 14:11
 */
public enum Season {
    SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);

    private int code;
    private Season(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
