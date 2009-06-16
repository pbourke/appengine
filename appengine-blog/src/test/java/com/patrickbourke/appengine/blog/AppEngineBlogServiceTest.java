package com.patrickbourke.appengine.blog;

import org.junit.Before;
import org.junit.Test;

public class AppEngineBlogServiceTest {
    private BlogService blogService;
    
    @Before
    public void setUp() {
        blogService = new AppEngineBlogService();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testAddArticleNulLArg() {
        blogService.addArticle(null);
    }
}
