package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 25.01.2017.
 */
public class MenuDTO implements Serializable{

    private static final long serialVersionUID = 2529360957561018848L;
    private Set<CategoryDTO> categories;

    public MenuDTO() {
        categories = new HashSet<>();
    }

    public MenuDTO(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "categories=" + categories +
                '}';
    }
}
