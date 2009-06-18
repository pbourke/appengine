package com.patrickbourke.appengine.blog;

import org.junit.Before;
import org.junit.Test;

public class AppEngineArticleServiceTest {
    private ArticleService blogService;
    
    @Before
    public void setUp() {
        blogService = new AppEngineArticleService();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testAddArticleNulLArg() {
        blogService.addArticle(null);
    }
}
