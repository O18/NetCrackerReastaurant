package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 25.01.2017.
 */
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = -7334061331560529720L;

    private String name;

    public CategoryDTO(String name) {
        this.name = name;
    }

    public CategoryDTO() {
        name = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
