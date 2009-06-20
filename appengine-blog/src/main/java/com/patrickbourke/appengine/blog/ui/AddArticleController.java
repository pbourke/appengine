package com.patrickbourke.appengine.blog.ui;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.google.appengine.api.datastore.Text;
import com.patrickbourke.appengine.blog.Article;
import com.patrickbourke.appengine.blog.ArticleService;
import com.patrickbourke.appengine.blog.ui.support.GoogleTextEditor;

/**
 * Allows Article instances to be added to the ArticleService.
 *
 * @author Patrick Bourke <pbourke@gmail.com>
 */
public class AddArticleController extends SimpleFormController {
    private ArticleService articleService;
    
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(Text.class, new GoogleTextEditor());
    }

    public void setArticleService(final ArticleService as) {
        articleService = as;
    }

    @Override
    protected void doSubmitAction(Object command) throws Exception {
        articleService.addArticle((Article)command);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception {
        if ( !isFormSubmission(request) || ServletRequestUtils.getStringParameter(request, "id") == null ) {
            return super.formBackingObject(request);
        }
        return new Article( ServletRequestUtils.getStringParameter(request, "id") );
    }
}
