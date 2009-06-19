package com.patrickbourke.appengine.blog;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.patrickbourke.appengine.StubEnvironment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"article-service-beans.xml"})
public class AppEngineArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    
    @Before
    public void setUp() {
        StubEnvironment.setUpAppEngineTest();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testAddArticleNulLArg() {
        articleService.addArticle(null);
    }
    
    @Transactional
    @Test
    public void testAddArticleNewArticle() {
        Article aBefore = new Article("some_id");
        articleService.addArticle(aBefore);
        
        Article aAfter = articleService.findArticleById("some_id");
        assertNotNull(aAfter);
        assertEquals("some_id", aAfter.getId());
        assertEquals(aBefore, aAfter);
    }
    
    @Transactional
    @Test(expected=IllegalArgumentException.class)
    public void testAddArticleOldArticle() {
        Article a = new Article("some_id");
        articleService.addArticle(a);

        // call add a second time
        articleService.addArticle(a);
    }
    
    @Transactional
    @Test(expected=IllegalArgumentException.class)
    public void testFindArticleNullArgument() {
        articleService.findArticleById(null);
    }
}
