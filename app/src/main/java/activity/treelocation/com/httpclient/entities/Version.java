package activity.treelocation.com.httpclient.entities;

/**
 * Created by admin on 2016/8/10.
 */
public class Version {

    /**
     * version_code : 1
     * version_name : 1.3.1
     * version_desc : 更新了上传的按钮，接口尚未开放
     * version_path : https://api.sinas3.com/v1/SAE_msgm/msgm/apk/1.3.1.apk
     */

    private String version_code;
    private String version_name;
    private String version_desc;
    private String version_path;

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getVersion_desc() {
        return version_desc;
    }

    public void setVersion_desc(String version_desc) {
        this.version_desc = version_desc;
    }

    public String getVersion_path() {
        return version_path;
    }

    public void setVersion_path(String version_path) {
        this.version_path = version_path;
    }

    @Override
    public String toString() {
        return "Version{" +
                "version_code='" + version_code + '\'' +
                ", version_name='" + version_name + '\'' +
                ", version_desc='" + version_desc + '\'' +
                ", version_path='" + version_path + '\'' +
                '}';
    }
}
