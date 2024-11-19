package daos;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import dtos.CategoryDTO;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public List<CategoryDTO> getAllCategories() {
        String sql = "SELECT * FROM Categories";
        long categoryID;
        String categoryName,description;
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            ResultSet rs = preStm.executeQuery();
            List<CategoryDTO> categories = new ArrayList<>();
            while (rs.next()) {
                categoryID = rs.getLong(1);
                categoryName = rs.getString(2);
                description = rs.getString(3);
                CategoryDTO categoryDTO = new CategoryDTO(categoryID, categoryName, description);
                categories.add(categoryDTO);
            }
            return categories;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CategoryDTO getCategoryById(Long categoryId) {
        String sql = "SELECT * FROM Categories WHERE categoryId = ?";
        long categoryID;
        String categoryName,description;
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, categoryId);
            ResultSet rs = preStm.executeQuery();
            if (rs.next()) {
                categoryID = rs.getLong(1);
                categoryName = rs.getString(2);
                description = rs.getString(3);
                CategoryDTO categoryDTO = new CategoryDTO(categoryID, categoryName, description);
                return categoryDTO;
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addCategory(CategoryDTO category) {
        String sql = "INSERT INTO Categories (categoryName, description) VALUES (?, ?)";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, category.getCategoryName());
            preStm.setString(2, category.getDescription());
            int rowsInserted = preStm.executeUpdate();
            return rowsInserted > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCategory(CategoryDTO category) {
        String sql = "UPDATE Categories SET categoryName = ?, description = ? WHERE categoryId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setString(1, category.getCategoryName());
            preStm.setString(2, category.getDescription());
            preStm.setLong(3, category.getCategoryId());
            int rowsUpdated = preStm.executeUpdate();
            return rowsUpdated > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategory(Long categoryId) {
        String sql = "DELETE FROM Categories WHERE categoryId = ?";
        try {
            Connection cnn = DBUtils.getConnection();
            PreparedStatement preStm = cnn.prepareStatement(sql);
            preStm.setLong(1, categoryId);
            int rowsDeleted = preStm.executeUpdate();
            return rowsDeleted > 0;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryDTO> categories = categoryDAO.getAllCategories();
        for (CategoryDTO category : categories) {
            System.out.println(category);
        }
    }
}
