package id.kido1611.simplenote;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import id.kido1611.simplenote.realm.RealmDB;
import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by ahmad on 21/08/2016.
 */
public class MainApplication extends Application {

    RealmDB realmDB;

    @Override
    public void onCreate() {
        super.onCreate();
        realmDB = new RealmDB(this);

        /**
         * Stetho used for view Realm Database on Chrome based browser
         */
        Stetho.initialize(Stetho.newInitializerBuilder(this).enableDumpapp(Stetho.defaultDumperPluginsProvider(this)).enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build()).build());

    }

    public RealmDB getRealmDB(){
        return realmDB;
    }

}
