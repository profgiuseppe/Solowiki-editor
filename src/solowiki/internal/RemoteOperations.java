/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solowiki.internal;

import info.bliki.api.Page;
import info.bliki.api.User;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Giuseppe Profiti
 */
public class RemoteOperations {

    String username;
    String pass;
    String actionLink;
    User user;

    public RemoteOperations(String api) {
        this("","",api);
    }

    public RemoteOperations(String user, String password, String api) {
        username = user;
        pass = password;
        actionLink = api;
        this.user = new User(username, pass, actionLink);
    }

    public Vector<String> getPageId(Vector<String> names) {
        user.login();
        List<Page> listOfPages = user.queryInfo(names);
        Vector<String> v = new Vector(names.size());

        //Workaround to fix the wrong result order
        //TODO must be removed after a solotion is found
        v.addAll(names);

        for (Page page : listOfPages) {
            //Workaround to fix the wrong result order
            //TODO must be removed after a solotion is found
            int pos = names.indexOf(page.getTitle());
            //Page titles begin with uppercase letter, link names may start in lowercase
            if (pos==-1) {
                String title = String.valueOf(page.getTitle().charAt(0)).toLowerCase()+page.getTitle().substring(1);
                pos = names.indexOf(title);
            }

            v.setElementAt(page.getPageid(), pos);
            //Simple way, must be reactivated after removing the workaround
            //    v.add(page.getPageid());
        }
        return v;
    }

    public String getPageContent(String pagename) {
        user.login();
        Vector<String> v=new Vector(1);
        v.add(pagename);
        List<Page> results = user.queryContent(v);
        Page page = results.get(0);
        String res=null;
        //TODO may check for a negative pageID
        //see http://www.mediawiki.org/wiki/API:Query#Missing_and_invalid_titles
        if ((page.getPageid()!=null) && (Integer.parseInt(page.getPageid())>0))
            res = page.getCurrentContent();
        return res;
    }
}
