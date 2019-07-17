package filter;

import javafx.scene.shape.Path;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;

/**
 * @create: 2019-07-04 14:52
 * @author: Aner
 * @description:
 **/
public class MyPathMatchingFilter extends PathMatchingFilter {
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        System.out.println("url matches config is"+ Arrays.toString((String[])mappedValue));
        return true;
    }
}
