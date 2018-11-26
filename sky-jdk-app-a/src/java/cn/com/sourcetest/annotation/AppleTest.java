package cn.com.sourcetest.annotation;

/**
 * desc: cn.com.sourcetest.annotation
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/19
 * Time: 17:02
 */
public class AppleTest {


    @AnnotationTest("Apple")
    private String  appleName;

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }
}
