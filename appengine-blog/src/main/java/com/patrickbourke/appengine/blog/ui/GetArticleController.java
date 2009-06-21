package com.patrickbourke.appengine.blog.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.UrlPathHelper;

import com.patrickbourke.appengine.blog.Article;
import com.patrickbourke.appengine.blog.ArticleService;

/**
 * A Controller which serves up a Article identified by
 * its ID.
 * 
 * TODO: Combine with IndexPageController as MultiActionController
 *
 * @author Patrick Bourke <pbourke@gmail.com>
 */
public class GetArticleController extends ApplicationObjectSupport implements Controller {
    private String articleViewName;
    private String notFoundViewName;
    private String modelName;
    private ArticleService articleService;
    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        final String articleId = urlPathHelper.getLookupPathForRequest(request);
        logger.info("Article ID on path is '" + articleId + "'");
        if ( articleId == null ) {
            return new ModelAndView(getNotFoundViewName());
        }
        final Article article = articleService.findArticleById(articleId);
        if ( article == null ) {
            return new ModelAndView(getNotFoundViewName());
        }
        return new ModelAndView(getArticleViewName(), getModelName(), article);
    }

    public String getArticleViewName() {
        return articleViewName;
    }

    public void setArticleViewName(String viewName) {
        this.articleViewName = viewName;
    }

    public String getNotFoundViewName() {
        return notFoundViewName;
    }
    
    public void setNotFoundViewName(String name) {
        notFoundViewName = name;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

}
