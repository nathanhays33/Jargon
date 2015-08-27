package redquadron.com.jargon;

import android.app.Application;
import android.os.Bundle;

import com.parse.Parse;

/**
 * Created by nathan on 4/2/2015.
 */
public class App  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
          Parse.enableLocalDatastore(this);

        Parse.initialize(this, "XYmKKuTwYTesb5tt8BWF8TlmJGF77QOSXItok3Dn", "YW0M9TxdZTqnUnOmf7g6fFbPxFRsWtPYa8VUKzmH");
        }
        }
