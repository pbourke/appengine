package com.patrickbourke.appengine.blog.ui;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.ModelAndViewAssert.assertAndReturnModelAttributeOfType;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

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
@ContextConfiguration(locations = {
        "/com/patrickbourke/appengine/blog/article-service-beans.xml",
        "/com/patrickbourke/appengine/blog/ui/controller-beans.xml" })
public class GetArticleControllerTest {
    @Autowired
    private GetArticleController getArticleController;

    @Autowired
    private ArticleService articleService;

    @Before
    public void setUp() {
        StubEnvironment.setUpAppEngineTest();
    }

    @Test
    @Transactional
    public void testRedirectToIndexPageWhenNotFound() throws Exception {
        String articleId = "/not/found/here";
        assertNull( articleService.findArticleById(articleId) );
        ModelAndView articleMav = getArticleController.handleRequest(new MockHttpServletRequest("GET", articleId), new MockHttpServletResponse());
        assertNotNull(articleMav);
        assertViewName(articleMav, getArticleController.getNotFoundViewName());
    }
    
    @Test
    @Transactional
    public void testArticleFound() throws Exception {
        String articleId = "/testArticle";
        {
            final Article a = new Article(articleId);
            a.setText(new Text("some text"));
            a.setTitle("Some Title");
            articleService.addArticle(a);
        }
        ModelAndView articleMav = getArticleController.handleRequest(new MockHttpServletRequest("GET", articleId), new MockHttpServletResponse());
        assertNotNull(articleMav);
        assertViewName(articleMav, getArticleController.getArticleViewName());
        Article matchedArticle = (Article) assertAndReturnModelAttributeOfType(articleMav, getArticleController.getModelName(), Article.class);
        assertEquals(articleId, matchedArticle.getId());
        assertEquals("some text", matchedArticle.getText().getValue());
        assertEquals("Some Title", matchedArticle.getTitle());
    }
}
