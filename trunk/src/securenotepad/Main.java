/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package securenotepad;

import com.google.gdata.client.*;
import com.google.gdata.client.GoogleService.*;
import com.google.gdata.client.http.*;
import com.google.gdata.client.docs.*;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.docs.*;
import com.google.gdata.data.acl.*;
import com.google.gdata.data.extensions.LastModifiedBy;
import com.google.gdata.util.*;
import java.io.IOException;
import java.net.URL;
/**
 *
 * @author Kevin Hulin
 */
public class Main {
    private GoogleService client = new GoogleService("mail","UTD-secureNotepad-v1");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        new Main();
    }

    public Main(){
        //Agrument should be of form [company]-[appName]-[versionNum]
        //Used for logging purposes

        String nextUrl = "https://docs.google.com/feeds/documents/private/full"; //The website we want to have autorization on
        String scope = "https://docs.google.com/feeds/";
        boolean secure = false;
        boolean session = true;
        String authSubUrl = AuthSubUtil.getRequestUrl(nextUrl,scope,secure,session); //Returns an auth token to be appended to request for nextURL







    }

    public void showAllDocs() throws IOException, ServiceException {
      URL feedUri = new URL("https://docs.google.com/feeds/documents/private/full/");
      DocumentListFeed feed = client.getFeed(feedUri, DocumentListFeed.class);

      for (DocumentListEntry entry : feed.getEntries()) {
        printDocumentEntry(entry);
      }
    }
    public void printDocumentEntry(DocumentListEntry doc) {
      String resourceId = doc.getResourceId();
      String docType = resourceId.substring(0, resourceId.lastIndexOf(':'));

      System.out.println("'" + doc.getTitle().getPlainText() + "' (" + docType + ")");
      System.out.println("  link to Google Docs: " + doc.getHtmlLink().getHref());
      System.out.println("  resource id: " + resourceId);

      // print the parent folder the document is in
      if (!doc.getFolders().isEmpty()) {
        System.out.println("  in folder: " +  doc.getFolders());
      }

      // print the timestamp the document was last viewed
      DateTime lastViewed = doc.getLastViewed();
      if (lastViewed != null) {
        System.out.println("  last viewed: " + lastViewed.toString());
      }

      // print who made that modification
      LastModifiedBy lastModifiedBy = doc.getLastModifiedBy();
      if (lastModifiedBy != null) {
        System.out.println("  updated by: " +
            lastModifiedBy.getName() + " - " + lastModifiedBy.getEmail());
      }

      // print other useful metadata
      System.out.println("  last updated: " + doc.getUpdated().toString());
      System.out.println("  viewed by user? " + doc.isViewed());
      System.out.println("  writersCanInvite? " + doc.isWritersCanInvite().toString());
      System.out.println("  hidden? " + doc.isHidden());
      System.out.println("  starrred? " + doc.isStarred());
      System.out.println();
    }

}
