package com.patrickbourke.appengine.blog.ui;

import static org.springframework.test.web.ModelAndViewAssert.*;
import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.Text;
import com.patrickbourke.appengine.StubEnvironment;
import com.patrickbourke.appengine.blog.Article;
import com.patrickbourke.appengine.blog.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/com/patrickbourke/appengine/blog/article-service-beans.xml", 
        "/com/patrickbourke/appengine/blog/ui/controller-beans.xml"})
public class IndexPageControllerTest {
    @Autowired
    private IndexPageController indexPageController;
    
    @Autowired
    private ArticleService articleService;
    
    @Before
    public void setUp() {
        StubEnvironment.setUpAppEngineTest();
    }
    
    @Test
    @Transactional
    public void testOneArticle() throws Exception {
        {
            Article frontPageItem = new Article("/testItem");
            frontPageItem.setTitle("Front Page News");
            frontPageItem.setText(new Text("Big News Today"));        
            articleService.addArticle(frontPageItem);
        }
        ModelAndView indexMav = indexPageController.handleRequest(new MockHttpServletRequest("GET", "/"), new MockHttpServletResponse());
        assertViewName(indexMav, indexPageController.getViewName());
        Article indexPageArticle = (Article) assertAndReturnModelAttributeOfType(indexMav, indexPageController.getArticleModelName(), Article.class);
        assertEquals("Front Page News", indexPageArticle.getTitle());
        assertEquals("Big News Today", indexPageArticle.getText().getValue());
        assertEquals("/testItem", indexPageArticle.getId());
    }
}
