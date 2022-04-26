
package com.htn.Test;

import com.htn.pojo.Account;
import com.htn.pojo.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.htn.services.CategoryServices;
import com.htn.utils.JdbcUtils;
import org.junit.jupiter.api.*;

import javax.tools.JavaCompiler;

import static org.junit.jupiter.api.Assertions.*;


public class CategoryServicesTest {
    CategoryServices service = new CategoryServices();

    @Test
    public void GetCategoryReturnOrNot() throws SQLException {
        assertNotEquals(0, GetAllCategory().size());
    }

    @Test
    public void GetCategoryDoesNotReturn() throws SQLException {
        List<Category> acc = service.getCategory("fbdewfwuyefuywbjhfbwuysfuwefjwefuyewgfuy");
        assertEquals(0, acc.size());
    }

    @Test
    public void GetCategorytReturnCorrectName() throws SQLException {
       Category acc = service.getCategory("Hanh dong").get(0);
       assertEquals("Hanh dong",acc.getName());
    }

    @Test
    public void FindDuplicateCategoryTest() throws SQLException {
        service.addCategory(new Category("Hanh dong"));
        assertEquals(1,service.getCategory("Hanh dong").size());
    }

    @Test
    public void AddCategoryTestNull()
    {
        boolean flag = false;
        try{
            Category temp = new Category();
            service.addCategory(temp);
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void AddCategoryTestNameBlank()
    {
        boolean flag = false;
        try{
            Category temp = new Category();
            temp.setName("                  ");
            service.addCategory(temp);
        }catch (Exception e)
        {
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    public void UpdateCategoryTest() throws SQLException {
        Category a = new Category();
        a.setName("Kinh di");
        service.addCategory(a);
        a.setId(4);
        a.setName("Hai huoc");
        assertTrue(service.updateCategory(a));
    }

    @Test
    public void DeleteCategoryTest() throws SQLException {
        assertTrue(service.deleteCategory(3));
    }

    public List<Category> GetAllCategory() throws SQLException {
        Connection conn = JdbcUtils.getConn();
        PreparedStatement stm = conn.prepareStatement("Select * from category");
        ResultSet acc = stm.executeQuery();
        List<Category> categories = new ArrayList<Category>();
        while (acc.next()) {
            Category a = new Category(acc);
            categories.add(a);
        }
        return categories;
    }

}
