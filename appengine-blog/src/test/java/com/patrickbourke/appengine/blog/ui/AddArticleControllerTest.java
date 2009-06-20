package com.patrickbourke.appengine.blog.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

import javax.jdo.annotations.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.Text;
import com.patrickbourke.appengine.StubEnvironment;
import com.patrickbourke.appengine.blog.Article;
import com.patrickbourke.appengine.blog.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/com/patrickbourke/appengine/blog/article-service-beans.xml", 
        "/com/patrickbourke/appengine/blog/ui/controller-beans.xml"})
public class AddArticleControllerTest {
    @Autowired
    private AddArticleController addArticleController;
    
    @Autowired
    private ArticleService articleService;
    
    @Before
    public void setUp() {
        StubEnvironment.setUpAppEngineTest();
    }
    
    @Test
    public void testGetForm() throws Exception {
        ModelAndView form = addArticleController.handleRequest(new MockHttpServletRequest("GET", "/test"), new MockHttpServletResponse());
        assertViewName(form, addArticleController.getFormView());
        assertModelAttributeAvailable(form, addArticleController.getCommandName());
    }
    
    @Test 
    @Transactional
    public void testAddArticleSuccess() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("POST");
        req.addParameter("id", "/testId");
        req.addParameter("title", "Test Title");
        req.addParameter("text", "test text");
        
        ModelAndView success = addArticleController.handleRequest(req, new MockHttpServletResponse());
        assertViewName(success, addArticleController.getSuccessView());
    
        Article a = articleService.findArticleById("/testId");
        assertNotNull(a);
        assertEquals("Test Title", a.getTitle());
        assertEquals(new Text("test text"), a.getText());
    }
}
