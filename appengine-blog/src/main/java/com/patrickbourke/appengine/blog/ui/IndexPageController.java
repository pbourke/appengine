package com.patrickbourke.appengine.blog.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.patrickbourke.appengine.blog.ArticleService;

/**
 * This Controller renders the index page. It displays the most
 * recent article.
 *
 * @author Patrick Bourke <pbourke@gmail.com>
 */
public class IndexPageController implements Controller {

    private String viewName;
    private String modelName;
    private ArticleService articleService;
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView(getViewName(), getArticleModelName(), articleService.findLatestArticle());
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getArticleModelName() {
        return modelName;
    }
    
    public void setArticleModelName(String name) {
        modelName = name;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
