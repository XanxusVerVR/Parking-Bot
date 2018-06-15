
package javabean.sub;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserData {


    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }

}
