
package TestObjects;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

public class Post {

    @Expose
    private String body;
    @Expose
    private String title;
    @Expose
    private Long userId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
