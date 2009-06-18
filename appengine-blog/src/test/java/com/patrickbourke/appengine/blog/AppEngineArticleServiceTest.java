package com.patrickbourke.appengine.blog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.patrickbourke.appengine.StubEnvironment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"article-service-beans.xml"})
public class AppEngineArticleServiceTest {
    @Autowired
    private ArticleService blogService;
    
    @Before
    public void setUp() {
        StubEnvironment.setUpAppEngineTest();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testAddArticleNulLArg() {
        blogService.addArticle(null);
    }
}
